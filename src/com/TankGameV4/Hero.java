package com.TankGameV4;

import com.TankGameV4.Utils.MouseUtil;

import java.awt.*;

public class Hero extends Tank {
    public Hero(int x, int y, int blood) {
        super(x, y, blood);
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
        g2d.translate(-getX()-30, -getY()-30);
    }
}
