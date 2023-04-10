package com.TankGameV4;

import java.awt.*;

@SuppressWarnings("all")
//敌方坦克
public class Enemy extends Tank implements Runnable {
    public static final double PI = 3.1415926;

    public static double fireLag = 0.1;//敌方坦克每次移动的开火概率

    public static int moveSpeed = 20;//移动速度

    public Enemy(int x, int y,int blood) {
        super(x, y, blood);
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

    //让敌方坦克随机移动和开火
    @Override
    public void run() {
        while (true) {
            try {
                int random = (int) (10 * (Math.random() * 4));
                // 1/4的概率让坦克不移动
                if (random < 10) {
                    setMoving(false);
                    Thread.sleep(1000/moveSpeed);
                    continue;
                }

                //根据坦克的方向来继续激动，让坦克保持一个方向，走10~40步，并且进行边界检测
                setMoving(true);
                switch (getDirect()) {
                    case 0:  //向上
                        for (int i = 0; i < random; i++) {
                            if (getY() > 0) {
                                randomFire();
                                moveUp();
                            } else {
                                break;
                            }
                            Thread.sleep(1000/moveSpeed);
                        }
                        break;
                    case 1:  //向右
                        for (int i = 0; i < random; i++) {
                            if (getX() + 60 < Game04.WIDTH) {
                                randomFire();
                                moveRight();
                            } else {
                                break;
                            }
                            Thread.sleep(1000/moveSpeed);
                        }
                        break;
                    case 2:  //向下
                        for (int i = 0; i < random; i++) {
                            if (getY() + 60 < Game04.HEIGHT) {
                                randomFire();
                                moveDown();
                            } else {
                                break;
                            }
                            Thread.sleep(1000/moveSpeed);
                        }
                        break;
                    case 3:  //向左
                        for (int i = 0; i < random; i++) {
                            if (getX() > 0) {
                                randomFire();
                                moveLeft();
                            } else {
                                break;
                            }
                            Thread.sleep(1000/moveSpeed);
                        }
                        break;
                }
                randomFire();
                Thread.sleep(100);
                //然后随机的改变坦克方向 0-3
                setDirect((int) (Math.random() * 4));
                if (!isTankLive()) {
                    break; //退出线程
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void randomFire() throws InterruptedException {
        //随机开火
        double randomFire = Math.random();
        if(randomFire<=fireLag)
            fire(getAngleByDirect(getDirect()));
    }

}
