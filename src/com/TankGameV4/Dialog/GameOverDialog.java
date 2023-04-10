package com.TankGameV4.Dialog;

import com.TankGameV4.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverDialog extends JDialog {
    private int choice;  // 用于记录用户的选择，0表示重试，1表示下一关，2表示退出游戏，3表示更改难度

    public GameOverDialog(JFrame parent, String title) {
        super(parent, title, true);
        this.setSize(300,200);
        this.setResizable(false);
        //图片
        JPanel imagePane = new JPanel(){
            Image gameOver = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/gameOver.png"));
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(gameOver,300,200,null);
            }
        };
        getContentPane().add(imagePane);

        //按钮面板
        JPanel buttonPane = new JPanel();
        //1.
        JButton retryButton = new JButton("重试");
        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = 0;
                setVisible(false);
            }
        });
        buttonPane.add(retryButton);
        //2.
        JButton nextLevelButton = new JButton("下一关");
        nextLevelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = 1;
                setVisible(false);
            }
        });
        buttonPane.add(nextLevelButton);
        //3.
        JButton exitButton = new JButton("退出游戏");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = 2;
                setVisible(false);
            }
        });
        buttonPane.add(exitButton);
        //4.
        JButton changeDifficultyButton = new JButton("更改难度");
        changeDifficultyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choice = 3;
                setVisible(false);
            }
        });
        buttonPane.add(changeDifficultyButton);


        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public int getChoice() {
        return choice;
    }
}




