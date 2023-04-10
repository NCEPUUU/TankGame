package com.TankGameV2;


import javax.swing.*;
import java.awt.*;

/**
 * 坦克大战的绘图区域
 */
public class MyPanel2 extends JPanel {
    //定义我的坦克
    Hero hero;

    public MyPanel2() {
        hero = new Hero(100, 100);//初始化自己坦克
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色

        //画出坦克-封装方法
        drawTank(hero.getX(), hero.getY(), g, 1, 0);

    }

    public void drawTank(int x, int y, Graphics g, int direct, int type) {

        //根据不同类型坦克，设置不同颜色
        switch (type) {
            case 0 -> //我们的坦克
                    g.setColor(Color.cyan);
            case 1 -> //敌人的坦克
                    g.setColor(Color.yellow);
        }
        g.fill3DRect(x + 15, y + 15, 30, 30, false);//画出坦克方形盖子

        //direct 表示方向(0: 向上 1 向右 2 向下 3 向左 )
        switch (direct) {
            case 0 -> { //向上
                g.fill3DRect(x, y, 15, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 45, y, 15, 60, false);//画出坦克右边轮子
                g.setColor(Color.RED);
                g.fill3DRect(x + 27, y, 6, 30, false);//画出炮筒

            }
            case 1 -> { //向右
                g.fill3DRect(x, y, 60, 15, false);//画出坦克左边轮子
                g.fill3DRect(x, y + 45, 60, 15, false);//画出坦克右边轮子
                g.setColor(Color.RED);
                g.fill3DRect(x + 30, y + 27, 30, 6, false);//画出炮筒
            }
            case 2 -> { //向下
                g.fill3DRect(x, y, 15, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 45, y, 15, 60, false);//画出坦克右边轮子
                g.setColor(Color.RED);
                g.fill3DRect(x + 27, y + 30, 6, 30, false);//画出炮筒
            }
            case 3 -> { //向左
                g.fill3DRect(x, y, 60, 15, false);//画出坦克左边轮子
                g.fill3DRect(x, y + 45, 60, 15, false);//画出坦克右边轮子
                g.setColor(Color.RED);
                g.fill3DRect(x, y + 27, 30, 6, false);//画出炮筒
            }
            default -> System.out.println("暂时没有处理");
        }
        g.fillOval(x + 20, y + 20, 20, 20);//画出圆形盖子
    }
}

