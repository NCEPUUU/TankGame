package com.TankGameV4.Utils;

import com.TankGameV4.Game04;
import com.TankGameV4.Hero;
import com.TankGameV4.MyPanel4;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//鼠标的移动监听事件和点击监听事件
public class MouseUtil implements MouseListener, MouseMotionListener {
    static Hero hero;
    static MyPanel4 mp;

    //鼠标指针的坐标，用于坦克移动而指针不动时计算炮筒旋转角度
    static int mouseX;
    static int mouseY;
    static double turretAngle;

    public MouseUtil(MyPanel4 m, Hero h) {
        hero = h;
        mp = m;
    }

    /**
     * 给游戏窗口添加鼠标监听
     */
    public void addMouseListener(Game04 game) {
        game.addMouseListener(this);
        game.addMouseMotionListener(this);
    }

    /**
     * 我方坦克阵亡，移除鼠标监听
     */
    public static void removeMouseListener(Game04 game) {
        game.removeMouseListener(game.getMouseListener());
        game.removeMouseMotionListener(game.getMouseListener());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * 鼠标移动，旋转炮筒
     */
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
        //重绘坦克所在区域
        mp.repaint(hero.getX(),hero.getY(),60,60);
    }

    public static void setTurretAngle(Hero hero){
        double dx = mouseX - hero.getX() - 30;
        double dy = mouseY - hero.getY() - 30;
        turretAngle = Math.toDegrees(Math.atan2(dy, dx));
    }

    /**
     * 绘制炮筒时，获取角度
     */
    public static double getTurretAngle() {
        return turretAngle;
    }

    /**
     * 鼠标单击，发射炮弹
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("用户点击了鼠标左键, 开始射击.");
        hero.fire(hero.getAngle());
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

