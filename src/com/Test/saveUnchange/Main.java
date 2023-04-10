package com.Test.saveUnchange;

import javax.swing.*;

public class Main extends JFrame {

    public Main(){
        this.setSize(200,200);
        this.add(new MyPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setFocusable(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
