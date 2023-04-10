package com.TankGameV4.Dialog;
import com.TankGameV4.Game04;
import com.TankGameV4.Tank;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageIconDemo extends JFrame {
    Game04 game;
    public ImageIconDemo(Game04 game) {
        this.game = game;
        //获取图片地址
        //图片要放在相同的包里
        URL url = Tank.class.getResource("images/gameOver3.png");
        //新建要放图标的标签
        JLabel jLabel = new JLabel();
        //以图片新建图标
        ImageIcon imageIcon = new ImageIcon(url);
        //把图片放进标签中
        jLabel.setIcon(imageIcon);
        //标签位置居中
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //获得容器
        Container container = getContentPane();
        container.add(jLabel);

        // 下半部分添加按钮
        JPanel buttonPanel = new JPanel();
        JButton retryButton = new JButton("重试");
        retryButton.addActionListener(e -> {
            // 点击重试按钮的操作
            game.restart();
        });
        JButton changeDifficultyButton = new JButton("更改难度");
        changeDifficultyButton.addActionListener(e -> {
            // 点击更改难度按钮的操作
            game.setDifficulty(1);
        });
        JButton quitButton = new JButton("退出游戏");
        quitButton.addActionListener(e -> {
            // 点击退出游戏按钮的操作
            System.exit(0);
        });
        buttonPanel.add(retryButton);
        buttonPanel.add(changeDifficultyButton);
        buttonPanel.add(quitButton);
        container.add(buttonPanel, BorderLayout.SOUTH);


        //设置关闭事件监听
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 290);
        setLocationRelativeTo(null);
        setResizable(false);
    }
//    public static void main(String[] args) {
//        new ImageIconDemo();
//    }
}