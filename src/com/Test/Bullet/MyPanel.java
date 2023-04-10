package com.Test.Bullet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MyPanel extends JPanel {
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public MyPanel() {
        setPreferredSize(new Dimension(400, 400));

        // 启动定时器，每隔10毫秒更新一次画面
        Timer timer = new Timer(10, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    private void update() {
        // 遍历所有的子弹，更新位置
        for (Bullet bullet : bullets) {
            bullet.move();
        }
        // 移除已经越界的子弹
        bullets.removeIf(Bullet::isOutOfBound);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 遍历所有的子弹，绘制在画面上
        for (Bullet bullet : bullets) {
            // 只绘制子弹所在的矩形区域，避免重复绘制
            g.clipRect(bullet.x, bullet.y, 5, 10);
            bullet.draw(g);
            // 恢复绘制区域
            g.setClip(null);
        }
    }

    public void fire(int x, int y, int speed) {
        bullets.add(new Bullet(x, y, speed));
    }
}

