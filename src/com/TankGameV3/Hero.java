package com.TankGameV3;

import com.TankGameV3.Utils.MouseUtil;

import java.awt.*;

public class Hero extends Tank {
    //定义一个Shot对象, 表示一个射击(线程)
    Bullet bullet = null;
    public Hero(int x, int y) {
        super(x, y);
    }

    //坦克发射炮弹
    public void shotEnemyTank() {
        bullet = new Bullet(getX()+30,getY()+30, getAngle());

        System.out.println((getX()+30)+" "+(getY()+30)+" "+getAngle());
        //启动我们的Shot线程
        new Thread(bullet).start();
    }
    /**
     * 绘制炮筒
     */
    public void drawHeroBarrel(Graphics g) {
        //炮筒旋转角度
        double turretAngle = MouseUtil.getTurretAngle();
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getX()+30, getY()+30); //平移坐标系
        g2d.rotate(Math.toRadians(turretAngle));//旋转坐标系
        g2d.fillRect(0, -3, 30, 6);//绘制炮筒

        //旋转后，还原坐标系
        g2d.rotate(-Math.toRadians(turretAngle));
//        setRepaintArea(x,y,60,60);
        g2d.translate(-getX()-30, -getY()-30);
    }
}
