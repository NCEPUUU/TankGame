package com.Test.saveUnchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener {

    private int x = 0; // 变化的坐标

    private Timer timer;

    public MyPanel() {
        timer = new Timer(50, this); // 创建定时器，每50毫秒触发一次actionPerformed方法
        timer.start(); // 启动定时器
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK); // 绘制不变化的组件
        g.fillRect(0, 0, 200, 200);

        g.setColor(Color.RED); // 绘制变化的组件
        g.fillRect(x, 50, 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x++; // 更新坐标
        repaint(x - 1, 50, 50, 50); // 只重绘变化的部分
    }
}

