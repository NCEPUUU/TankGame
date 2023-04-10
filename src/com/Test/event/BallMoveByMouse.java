package com.Test.event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 演示小球通过鼠标控制上下左右的移动
 */
public class BallMoveByMouse extends JFrame implements MouseListener {
    private int ballX = 50;
    private int ballY = 50;
    private int ballSize = 20;
    private int speed = 20;

    public BallMoveByMouse() {
        this.setSize(500, 500);
        this.setTitle("Ball Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(Color.BLUE);
        g.fillOval(ballX, ballY, ballSize, ballSize);
    }

    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (mouseX > ballX) {
            ballX += speed;
        } else if (mouseX < ballX) {
            ballX -= speed;
        }

        if (mouseY > ballY) {
            ballY += speed;
        } else if (mouseY < ballY) {
            ballY -= speed;
        }

        repaint();
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public static void main(String[] args) {
        new BallMoveByMouse();
    }
}

