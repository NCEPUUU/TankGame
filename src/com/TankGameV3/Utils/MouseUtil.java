package com.TankGameV3.Utils;

import com.TankGameV3.Game03;
import com.TankGameV3.Hero;
import com.TankGameV3.MyPanel3;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//鼠标的移动监听事件和点击监听事件
public class MouseUtil implements MouseListener, MouseMotionListener {
    static Hero hero;
    static MyPanel3 mp;
    static double turretAngle;

    //鼠标指针的坐标，用于坦克移动而指针不动时计算炮筒旋转角度
    static int mouseX;
    static int mouseY;

    private MouseUtil(MyPanel3 m, Hero h) {
        hero = h;
        mp = m;
    }
    public static void addMouseListener(Game03 game, MyPanel3 m, Hero h) {
        MouseUtil mouseUtil = new MouseUtil(m,h);
        game.addMouseListener(mouseUtil);
        game.addMouseMotionListener(mouseUtil);
    }

    //鼠标单击，发射炮弹
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("用户点击了鼠标左键, 开始射击.");
        hero.shotEnemyTank();
//        mp.repaint(hero.getX(),hero.getY(),60,60);
    }
    //鼠标移动，旋转炮筒
    @Override
    public void mouseMoved(MouseEvent e) {
        //校正后的指针坐标
        mouseX = e.getX() - 7;
        mouseY = e.getY() - 30;
        //指针坐标与旋转中心的横纵坐标差值，用于计算旋转角度
        double dx = mouseX - hero.getX() - 30;
        double dy = mouseY - hero.getY() - 30;
        //计算炮筒应该旋转的角度
        turretAngle = Math.toDegrees(Math.atan2(dy, dx));
        //更新炮筒旋转角度
        hero.setAngle(Math.toRadians(turretAngle));//将角度转换为弧度
        // 如果鼠标处于点击状态，则同时触发鼠标点击事件
//        if (SwingUtilities.isLeftMouseButton(e)) {
//            mouseClicked(e);
//        }
        //重绘坦克所在区域
//        mp.repaint(hero.getX(),hero.getY(),60,60);
    }

    public static void setTurretAngle(Hero hero){
        double dx = mouseX - hero.getX() - 30;
        double dy = mouseY - hero.getY() - 30;
        turretAngle = Math.toDegrees(Math.atan2(dy, dx));
    }
    //绘制炮筒时，获取角度
    public static double getTurretAngle() {
        return turretAngle;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
}

