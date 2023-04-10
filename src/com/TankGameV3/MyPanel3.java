package com.TankGameV3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static com.TankGameV3.Game03.fps;

/**
 * 坦克大战的绘图区域，包含坦克身体和可旋转的炮筒，能够发射炮弹
 */
public class MyPanel3 extends JPanel implements ActionListener {
    Hero hero;
    //定义敌人坦克，放入到Vector
    Vector<Enemy> enemies = new Vector<>();
    //定义一个Vector ,用于存放炸弹
    //当子弹击中坦克时，加入一个Bomb对象到bombs
//    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;

    Timer timer;
    Image img1,img2,img3,img4;
    Image bomb_1,bomb_2,bomb_3;
    public MyPanel3(Hero hero) {
        this.hero = hero;
        setBackground(Color.darkGray);

        //初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
            //创建一个敌人的坦克
            Enemy enemy = new Enemy((100 * (i + 1)), 0);
            //设置方向
            enemy.setDirect(2);
            //启动敌人坦克线程，让他动起来
            new Thread(enemy).start();
            //给该enemyTank 加入一颗子弹
            Bullet bullet = new Bullet(enemy.getX() + 30, enemy.getY() + 30, Enemy.getAngleByDirect(enemy.getDirect()));
            //加入enemyTank的Vector 成员
            enemy.bullets.add(bullet);
            //启动 shot 对象
            new Thread(bullet).start();
            //加入
            enemies.add(enemy);
        }
        //初始化图片对象
        bomb_1 = Toolkit.getDefaultToolkit().getImage(MyPanel3.class.getResource("images/bomb_3.gif"));
        bomb_2 = Toolkit.getDefaultToolkit().getImage(MyPanel3.class.getResource("images/bomb_2.gif"));
        bomb_3 = Toolkit.getDefaultToolkit().getImage(MyPanel3.class.getResource("images/bomb_1.gif"));


        timer = new Timer(1000 / fps, this);
        timer.start();
        System.out.println("计时器启动！");
    }
    private Image offScreenImage;  // 双缓冲画布
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        //画出己方坦克
//        hero.drawTank(0, g);
//        hero.drawHeroBarrel(g);
//        //画出敌方坦克
//        for (Enemy enemy : enemies) {
//            enemy.drawTank(1, g);
//            enemy.drawEnemyBarrel(g);
//        }
//        drawBullet(g);

        //利用双缓冲技术，可以解决画面的卡顿问题
        g.drawImage(offScreenImage, 0, 0, null);

//        Toolkit.getDefaultToolkit().sync();  // 同步画面，防止闪烁

//        if(hero.isMoving()) {
//            //设置绘图区域
//            g.clipRect(hero.getX(),hero.getY(),60,60);
//            //画出我方坦克
//            hero.drawTank(0, g);
//            //画出炮筒
//            hero.drawHeroBarrel(g);
//            //恢复绘制区域
//            g.setClip(null);
//        }
//        //画出敌方坦克
//        for (Enemy enemy : enemies) {
//            if (enemy.isMoving()) {
//                //设置绘图区域
//                g.clipRect(enemy.getX(),enemy.getY(),60,60);
//                enemy.drawTank(1, g);
//                enemy.drawEnemyBarrel(g);
//                //恢复绘制区域
//                g.setClip(null);
//            }
//        }
//
//        //设置绘图区域
//        g.clipRect(hero.getX(),hero.getY(),60,60);
//        //画出hero射击的子弹
//        drawBullet(g);
//        //恢复绘制区域
//        g.setClip(null);
    }


    /**
     * 绘制坦克
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, int direct, int type, Graphics g) {

        //根据不同类型坦克，设置不同颜色
        switch (type) {
            case 0 -> //我们的坦克
                    g.setColor(Color.cyan);
            case 1 -> //敌人的坦克
                    g.setColor(Color.yellow);
        }
        g.fill3DRect(x + 15, y + 15, 30, 30, false);//画出坦克方形盖子
        //direct 表示方向(0: 向上 1 向右 2 向下 3 向左 )
        switch (direct) {
            case 0 -> { //向上
//                g.fill3DRect(x, y, 15, 60, false);//画出坦克左边轮子
//                g.fill3DRect(x + 45, y, 15, 60, false);//画出坦克右边轮子
                g.drawImage(img1,x,y,null);
                g.drawImage(img1,x+45,y,null);
            }
            case 1 -> { //向右
//                g.fill3DRect(x, y, 60, 15, false);//画出坦克左边轮子
//                g.fill3DRect(x, y + 45, 60, 15, false);//画出坦克右边轮子
                g.drawImage(img2,x,y,null);
                g.drawImage(img2,x,y+45,null);
            }
            case 2 -> { //向下
                g.drawImage(img3,x,y,null);
                g.drawImage(img3,x+45,y,null);
            }
            case 3 -> { //向左
                g.drawImage(img4,x,y,null);
                g.drawImage(img4,x,y+45,null);
            }
            default -> System.out.println("暂时没有处理");
        }
        g.setColor(Color.RED);
        g.fillOval(x + 20, y + 20, 20, 20);//画出圆形盖子
//        setRepaintArea(x,y,60,60);
    }

    /**
     * 绘制子弹
     */
    public void drawBullet(Graphics g) {
        if(hero.bullet != null && hero.bullet.isLive) {
            int x = (int) hero.bullet.x;
            int y = (int) hero.bullet.y;
            int size = Bullet.size;
            g.fillOval(x-size/2, y-size/2, size, size);
//            setRepaintArea(x-size/2, y-size/2, size, size);
        }
    }

    /**
     * 设置绘图区域，并重绘
     */
    public void setRepaintArea(int x, int y, int width, int height, Graphics g){
        //设置绘图区域
        g.clipRect(x,y,width,height);
        //只重绘坦克所在区域
        repaint();
        //恢复绘制区域
        g.setClip(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
////        // 定时重绘坦克和子弹
//        if(hero.isMoving()){
////                    //画出我方坦克
////                    drawTank(hero.getX(), hero.getY(), hero.getDirect(), 0, g);
////                    //画出炮筒
////                    drawBarrel(hero.getX(), hero.getY(), g);
//            repaint(hero.getX()-20,hero.getY()-20,100,100);
//        }
//        if(hero.bullet != null && hero.bullet.isLive) {
//            //定时更新子弹坐标
//            hero.bullet.updateBullet();
////                    //画出hero射击的子弹
////                    drawBullet(g);
//            repaint((int) hero.bullet.x-20, (int) hero.bullet.x-20, Bullet.size+40, Bullet.size+40);
//        }
//        for (Enemy enemy : enemies) {
////                    //画出敌方坦克
////                    drawTank(enemy.getX(),enemy.getY(),enemy.getDirect(),1, g);
////                    drawEnemyBarrel(enemy.getX(),enemy.getY(),enemy.getDirect(), g);
//            if (enemy.isMoving()) {
//                repaint(enemy.getX()-20, enemy.getY()-20, 100, 100);
//            }
//        }


        if (offScreenImage == null) {  // 第一次使用时创建双缓冲画布
            offScreenImage = createImage(getWidth(), getHeight());
        }
        Graphics gOff = offScreenImage.getGraphics();
        gOff.setColor(Color.darkGray);
        gOff.fillRect(0,0,Game03.WIDTH,Game03.HEIGHT);
        gOff.clearRect(0,0,Game03.WIDTH,Game03.HEIGHT);
        //画出己方坦克
        hero.drawTank(0, gOff);
        hero.drawHeroBarrel(gOff);
        //画出敌方坦克
        for (Enemy enemy : enemies) {
            enemy.drawTank(1, gOff);
            enemy.drawEnemyBarrel(gOff);
        }
        drawBullet(gOff);
        repaint();

//        Graphics g = this.getGraphics();
//        Image offScreenImage = createImage(this.getWidth(), this.getHeight());
//        Graphics offScreenGraphics = offScreenImage.getGraphics();
//
//        // 清空之前的绘制
//        offScreenGraphics.setColor(getBackground());
//        offScreenGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
//
//        // 绘制坦克
//        if (hero.isMoving()) {
//            hero.drawTank(1,offScreenGraphics);
//        }
////        if (hero.bullet != null && hero.bullet.isLive) {
////            hero.bullet.d(offScreenGraphics);
////        }
//        for (Enemy enemy : enemies) {
//            if (enemy.isMoving()) {
//                enemy.drawTank(0,offScreenGraphics);
//            }
//        }
//
//        // 将绘制后的画面显示在屏幕上
//        g.drawImage(offScreenImage, 0, 0, null);
    }
}



