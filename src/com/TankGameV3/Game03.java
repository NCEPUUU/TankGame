package com.TankGameV3;

import com.TankGameV3.Utils.KeyboardUtil;
import com.TankGameV3.Utils.MouseUtil;

import javax.swing.*;

public class Game03 extends JFrame {

    static final int WIDTH = 1100;
    static final int HEIGHT = 800;
    static int fps = 100;//刷新率
    static double bulletSpeed = 500; //子弹的速度， 像素/秒

    public Game03() {
        this.setSize(WIDTH, HEIGHT);
        setResizable(false);
        Hero hero = new Hero(0, 0);
        System.out.println(WIDTH+" "+HEIGHT);
        System.out.println(hero.getX()+"  "+hero.getY());
        MyPanel3 mp = new MyPanel3(hero);
        this.add(mp);//初始化自己坦克

        //为panel添加监听事件
        KeyboardUtil.addKeyListener(this,mp,hero);
        MouseUtil.addMouseListener(this,mp,hero);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        Game03 game03 = new Game03();
    }

}
