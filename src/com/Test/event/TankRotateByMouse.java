package com.Test.event;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//让坦克炮筒实时指向鼠标
public class TankRotateByMouse extends JPanel implements MouseMotionListener {

    private int tankX = 100;
    private int tankY = 200;
    private int turretAngle = 0;

    public TankRotateByMouse() {
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // 绘制炮筒
        g2d.translate(tankX, tankY);
        g2d.rotate(Math.toRadians(turretAngle));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, -5, 35, 10);
        g2d.rotate(-Math.toRadians(turretAngle));
        g2d.translate(-tankX, -tankY);
    }

    public void mouseMoved(MouseEvent e) {
        // 计算炮筒应该旋转的角度
        double dx = e.getX() - tankX;
        double dy = e.getY() - tankY;
        turretAngle = (int) Math.toDegrees(Math.atan2(dy, dx));
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        TankRotateByMouse game = new TankRotateByMouse();
        frame.add(game);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

