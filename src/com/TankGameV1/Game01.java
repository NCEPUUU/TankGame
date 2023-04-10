package com.TankGameV1;

import javax.swing.*;

public class Game01 extends JFrame {

    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        Game01 game01 = new Game01();
    }

    public Game01() {
        mp = new MyPanel();
        this.add(mp);//把面板(就是游戏的绘图区域)
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
