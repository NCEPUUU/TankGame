package com.TankGameV3;

import java.awt.*;
import java.util.Vector;

@SuppressWarnings("all")
//敌方坦克
public class Enemy extends Tank implements Runnable {
    public static final double PI = 3.1415926;

    //在敌人坦克类，使用Vector 保存多个bullet
    Vector<Bullet> bullets = new Vector<>();
    boolean isLive = true;

    public Enemy(int x, int y) {
        super(x, y);
    }

    //将方向direct转换为角度angle
    public static double getAngleByDirect(int direct){
        return PI/2*(direct - 1);
    }

    /**
     * 绘制敌人坦克炮筒
     */
    public void drawEnemyBarrel(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        switch (getDirect()) {
            case 0 -> g2d.fillRect(getX() + 27, getY(), 6, 30);
            case 1 -> g2d.fillRect(getX() + 30, getY() + 27, 30, 6);
            case 2 -> g2d.fillRect(getX() + 27, getY() + 30, 6, 30);
            case 3 -> g2d.fillRect(getX(), getY() + 27, 30, 6);
        }
    }
    @Override
    public void run() {
        while (true) {
//            //判断如果bullets size() = 0, 创建一颗子弹，放入到bullets集合，并启动
//            if (isLive && bullets.size() < 1) {
//                Bullet s = new Bullet(getX() + 30, getY() + 30, getAngleByDirect(getDirect()));
//                bullets.add(s);
//                //启动
//                new Thread(s).start();
//            }
            try {
                int random = (int) (10 * (Math.random() * 4));
                // 1/4的概率让坦克不移动
                if (random < 10) {
                    setMoving(false);
                    Thread.sleep(800);
                    continue;
                }
                setMoving(true);
                //根据坦克的方向来继续激动，让坦克保持一个方向，走10~40步
                switch (getDirect()) {
                    case 0:  //向上
                        for (int i = 0; i < random; i++) {
                            if (getY() > 0) {
                                moveUp();
                            } else {
                                break;
                            }
                            Thread.sleep(50);
                        }
                        break;
                    case 1:  //向右
                        for (int i = 0; i < random; i++) {
                            if (getX() + 60 < Game03.WIDTH) {
                                moveRight();
                            } else {
                                break;
                            }
                            Thread.sleep(50);
                        }
                        break;
                    case 2:  //向下
                        for (int i = 0; i < random; i++) {
                            if (getY() + 60 < Game03.HEIGHT) {
                                moveDown();
                            } else {
                                break;
                            }
                            Thread.sleep(50);
                        }
                        break;
                    case 3:  //向左
                        for (int i = 0; i < random; i++) {
                            if (getX() > 0) {
                                moveLeft();
                            } else {
                                break;
                            }
                            Thread.sleep(50);
                        }
                        break;
                }
                //然后随机的改变坦克方向 0-3
                setDirect((int) (Math.random() * 4));
                if (!isLive) {
                    break; //退出线程.
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
