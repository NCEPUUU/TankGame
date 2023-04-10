package com.TankGameV2;

import javax.swing.*;

public class Game02 extends JFrame {

    public static void main(String[] args) {
        Game02 game02 = new Game02();
    }

    public Game02() {
        this.add(new MyPanel3(new Hero(200, 100)));//初始化自己坦克

        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
