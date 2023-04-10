package com.Test.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 演示小球通过键盘控制上下左右的移动
 */
public class BallMoveByKey extends JFrame { //窗口
    MyPanel mp;
    public static void main(String[] args) {
        new BallMoveByKey();
    }

    //构造器
    public BallMoveByKey() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400, 300);
        //窗口JFrame 对象可以监听键盘事件, 即可以监听到面板发生的键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

//面板, 可以画出小球
//KeyListener 是监听器, 可以监听键盘事件
class MyPanel extends JPanel implements KeyListener {

    //为了让小球可以移动, 把他的左上角的坐标(x,y)设置变量
    int x = 10;
    int y = 10;
    int speed = 5;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20); //默认黑色
    }

    //有字符输出时，该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {
    }
    //当某个键按下，该方法会触发
    @Override
//    public void keyPressed(KeyEvent e) {
//
//        //System.out.println((char)e.getKeyCode() + "被按下..");
//
//        //根据用户按下的不同键，来处理小球的移动 (上下左右的键)
//        //在java中，会给每一个键，分配一个值(int)
//        if(e.getKeyCode() == KeyEvent.VK_DOWN) {//KeyEvent.VK_DOWN就是向下的箭头对应的code
//            y+=4;//向下
//        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
//            y-=4;//向上
//        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
//            x-=4;//向左
//        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            x+=4;//向右
//        }
//        //让面板重绘
//        this.repaint();
//    }
    //方法改进，使小球能同时在水平和竖直方向移动
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            y -= speed;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            y += speed;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            x -= speed;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            x += speed;
        }
        repaint();
    }


    //当某个键释放(松开)，该方法会触发
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
