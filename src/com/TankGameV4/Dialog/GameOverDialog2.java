package com.TankGameV4.Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class GameOverDialog2 extends JDialog {

    private static final long serialVersionUID = 1L;
    private Image image;

    public GameOverDialog2() {
        // 设置为模态对话框
        setModal(true);
        setTitle("游戏结束");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 上半部分绘制图片
        image = new ImageIcon("gameOver.png").getImage();
        JPanel imagePanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };
        imagePanel.setSize(400, 250);
        add(imagePanel, BorderLayout.NORTH);

        // 下半部分添加按钮
        JPanel buttonPanel = new JPanel();
        JButton retryButton = new JButton("重试");
        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击重试按钮的操作
            }
        });
        JButton changeDifficultyButton = new JButton("更改难度");
        changeDifficultyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击更改难度按钮的操作
            }
        });
        JButton quitButton = new JButton("退出游戏");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击退出游戏按钮的操作
            }
        });
        buttonPanel.add(retryButton);
        buttonPanel.add(changeDifficultyButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 设置对话框背景色为透明
//        setBackground(new Color(0, 0, 0, 0));
    }

    public static void main(String[] args) {
        new GameOverDialog2();
    }

}

