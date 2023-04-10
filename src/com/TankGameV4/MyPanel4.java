package com.TankGameV4;

import com.TankGameV4.Dialog.ImageIconDemo;
import com.TankGameV4.Utils.KeyboardUtil;
import com.TankGameV4.Utils.MouseUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.TankGameV4.Game04.*;

/**
 * 坦克大战的绘图区域，包含坦克身体和可旋转的炮筒，能够发射炮弹
 */
public class MyPanel4 extends JPanel implements ActionListener {
    Game04 game04;
    Hero hero;
    //定义敌人坦克，放入到Vector
    Vector<Enemy> enemies = new Vector<>();
    //当子弹击中坦克时，加入一个Bomb对象到bombs数组中
    Vector<Bomb> bombs = new Vector<>();
    //若坦克阵亡，则将其已发射的子弹加入到公共子弹集中，防止坦克阵亡其子弹也消失
    static CopyOnWriteArrayList<Bullet> publicBullets = new CopyOnWriteArrayList<>();
    Timer timer;
    Image bomb_1,bomb_2,bomb_3;
    private Image offScreenImage;  // 双缓冲画布

    public MyPanel4(Game04 game04, Hero hero, int enemyTankNum, int enemyBlood) {
        this.game04 = game04;
        this.hero = hero;
        //初始化敌人坦克
        for (int i = 0; i < enemyTankNum; i++) {
            //创建一个敌人的坦克
            Enemy enemy = new Enemy((150 * (i + 1)), 80,enemyBlood);
            //设置方向
            enemy.setDirect(2);
            //启动敌人坦克线程
            new Thread(enemy).start();
            //加入
            enemies.add(enemy);
        }
        //初始化爆炸的图片对象
        bomb_1 = Toolkit.getDefaultToolkit().getImage(MyPanel4.class.getResource("images/bomb_3.gif"));
        bomb_2 = Toolkit.getDefaultToolkit().getImage(MyPanel4.class.getResource("images/bomb_2.gif"));
        bomb_3 = Toolkit.getDefaultToolkit().getImage(MyPanel4.class.getResource("images/bomb_1.gif"));

        timer = new Timer(1000 / fps, this);
        timer.start();
        System.out.println("计时器启动！");
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //利用双缓冲技术，可以解决画面的卡顿问题
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //每隔一定时间执行该方法
    @Override
    public void actionPerformed(ActionEvent e) {
        //遍历阵亡的坦克
        for (Enemy enemy : enemies) {
            if (!enemy.isTankLive()){
                //将阵亡坦克的子弹添加到公共子弹集
                publicBullets.addAll(enemy.getBullets());
            }
        }
        //更新公共子弹集的子弹
        for (Bullet publicBullet : publicBullets) {
            publicBullet.updateBullet();
        }
        //遍历我方坦克的子弹集
        for (Bullet bullet : hero.getBullets()) {
            //普通for循环在遍历过程中不能修改集合
            //若坦克被击中且被摧毁，则删除
            enemies.removeIf(enemy -> hitTank(bullet,enemy) && !enemy.isTankLive());
        }
//        //遍历地方坦克的子弹集
//        for (Enemy enemy : enemies) {
//            for (Bullet bullet : enemy.getBullets()) {
//                if(hitTank(bullet,hero)){
//                    if(!hero.isTankLive()) {
//                        gameOver();
//                    }
//                    //根据坦克血量设置标题
//                    game04.setTitleByBlood(hero.getBlood());
//                }
//            }
//        }
        //遍历地方坦克的子弹集
        for (Enemy enemy : enemies) {
            Iterator<Bullet> bulletIterator = enemy.getBullets().iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                if(hitTank(bullet,hero)){
                    if(!hero.isTankLive()) {
                        gameOver();
                    }
                    //根据坦克血量设置标题
                    game04.setTitleByBlood(hero.getBlood());
                    bulletIterator.remove(); //使用迭代器的remove方法删除元素
                }
            }
        }

        draw();
    }

    public void draw(){
        if (offScreenImage == null) {  // 第一次使用时创建双缓冲画布
            offScreenImage = createImage(getWidth(), getHeight());
        }
        Graphics gOff = offScreenImage.getGraphics();
        //将画布恢复为空白状态，确保之前绘制的内容被清除，避免残留的图像对下一次绘制造成干扰
        gOff.clearRect(0,0,Game04.WIDTH,Game04.HEIGHT);
        if(hero.isTankLive()) {
            //画出己方坦克
            hero.drawTank(0, gOff);
            hero.drawHeroBarrel(gOff);
            hero.drawBullet(0, gOff);
        }
        //画出敌方坦克
        for (Enemy enemy : enemies) {
            enemy.drawTank(1, gOff);
            enemy.drawEnemyBarrel(gOff);
            enemy.drawBullet(1,gOff);
        }
        //画出爆炸效果
        for (Bomb bomb : bombs) {
            if(bomb.life>0) {
                bomb.draw(gOff);
                bomb.lifeDown();
            }
        }
        //画出阵亡坦克剩余的子弹
        Bullet.drawPublicBullet(gOff);
        repaint();
    }

    /**
     * 若子弹命中坦克，则扣除相应血量，并创建相应的Boom类
     */
    public Boolean hitTank(Bullet b, Tank tank){
        if(b.x>tank.getX() && b.x<tank.getX()+60 && b.y>tank.getY() && b.y<tank.getY()+60){
            b.isLive = false;
            System.out.println("hit!");
            //打到坦克中心，扣2滴血
            if(b.x>tank.getX()+15 && b.x<tank.getX()+45 && b.y>tank.getY()+15 && b.y<tank.getY()+45){
                tank.setBlood(tank.getBlood()-2);
                System.out.println("剩余血量："+tank.getBlood());
                if(tank.getBlood()<=0) {
                    //坦克摧毁
                    tank.setTankLive(false);
                    bombs.add(new Bomb(tank.getX(), tank.getY(), 3));
                } else {
                    bombs.add(new Bomb(tank.getX(), tank.getY(), 2));
                }
            }
            //打到坦克轮子，扣1滴血
            else{
                tank.setBlood(tank.getBlood()-1);
                if(tank.getBlood()<=0) {
                    //坦克摧毁
                    tank.setTankLive(false);
                    bombs.add(new Bomb(tank.getX(), tank.getY(), 3));
                } else {
                    bombs.add(new Bomb(tank.getX(), tank.getY(), 1));
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 游戏结束
     */
    public void gameOver(){
        timer.stop();
        //我方坦克阵亡后，取消监听事件
        MouseUtil.removeMouseListener(game04);
        KeyboardUtil.removeKeyListener(game04);
        System.out.println("游戏结束============================");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //暂停所有敌方坦克线程
        for (Enemy enemy : enemies) {
            enemy.setTankLive(false);
        }
        new ImageIconDemo(game04);

    }

}



