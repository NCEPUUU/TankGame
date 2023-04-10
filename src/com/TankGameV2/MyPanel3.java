package com.TankGameV2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * 坦克大战的绘图区域，包含坦克身体和可旋转的炮筒
 */
public class MyPanel3 extends JPanel implements MouseMotionListener {
    Hero hero;
    private int turretAngle = 0;//炮筒旋转角度
    public MyPanel3(Hero hero) {
        this.hero = hero;
        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        //画出坦克
        drawTank(hero.getX(), hero.getY(), g, 1, 0);
        //画出炮筒
        drawBarrel(hero.getX(), hero.getY(), g);
    }

    /**
     * 绘制炮筒
     */
    private void drawBarrel(int x, int y, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(x+30, y+30); //平移坐标系
        g2d.rotate(Math.toRadians(turretAngle));//旋转坐标系
        g2d.fillRect(0, -3, 30, 6);//绘制炮筒

        //旋转后，还原坐标系
        g2d.rotate(-Math.toRadians(turretAngle));
        g2d.translate(-x, -y);

    }

    /**
     * 坦克绘制
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
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
            case 0, 2 -> { //向上、向下
                g.fill3DRect(x, y, 15, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 45, y, 15, 60, false);//画出坦克右边轮子
            }
            case 1, 3 -> { //向右、向左
                g.fill3DRect(x, y, 60, 15, false);//画出坦克左边轮子
                g.fill3DRect(x, y + 45, 60, 15, false);//画出坦克右边轮子
            }
            //向上 g.fill3DRect(x + 27, y, 6, 30, false);//画出炮筒
            //向右 g.fill3DRect(x + 30, y + 27, 30, 6, false);//画出炮筒
            //向下 g.fill3DRect(x + 27, y + 30, 6, 30, false);//画出炮筒
            //向左 g.fill3DRect(x, y + 27, 30, 6, false);//画出炮筒
            default -> System.out.println("暂时没有处理");
        }
        g.setColor(Color.RED);
        g.fillOval(x + 20, y + 20, 20, 20);//画出圆形盖子

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //指针坐标与旋转中心的横纵坐标差值，用于计算旋转角度
        double dx = e.getX() - hero.getX() - 30;
        double dy = e.getY() - hero.getY() - 30;
        //计算炮筒应该旋转的角度
        turretAngle = (int) Math.toDegrees(Math.atan2(dy, dx));
        repaint(hero.getX(),hero.getY(),60,60);//重绘坦克所在区域
        System.out.println(turretAngle);
    }
}


