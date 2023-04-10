package com.TankGameV3;

import java.awt.*;
import java.util.Vector;

public class Tank {
    private int x;//坦克的横坐标
    private int y;//坦克的纵坐标
    private int speed = 10;//坦克移动速度
    private int direct = 0;//坦克方向 0 上1 右 2下 3左
    private double angle = 0;//炮筒方向，弧度制
    private boolean isMoving;//判断坦克是否在移动
    private Vector<Bullet> bullets;//存放坦克的子弹

    Image img1,img2,img3,img4;//坦克轮子的图片

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        isMoving = true;//初始化为移动状态，以便在初次调用paint方法时画出坦克
        //坦克轮子的图片
        img1 = Toolkit.getDefaultToolkit().getImage(MyPanel3.class.getResource("images/heroUp.png"));
        img2 = Toolkit.getDefaultToolkit().getImage(MyPanel3.class.getResource("images/heroRight.png"));
        img3 = Toolkit.getDefaultToolkit().getImage(MyPanel3.class.getResource("images/heroDown.png"));
        img4 = Toolkit.getDefaultToolkit().getImage(MyPanel3.class.getResource("images/heroLeft.png"));
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void moveUp(){
        y -= speed;
    }
    public void moveRight(){
        x += speed;
    }
    public void moveDown(){
        y += speed;
    }
    public void moveLeft(){
        x -= speed;
    }

    /**
     * 绘制坦克
     * @param type   坦克类型
     */
    public void drawTank(int type, Graphics g) {

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
                g.drawImage(img1,x,y,null);
                g.drawImage(img1,x+45,y,null);
            }
            case 1 -> { //向右
                g.drawImage(img2,x,y,null);
                g.drawImage(img2,x,y+45,null);
            }
            case 2 -> { //向下
                g.drawImage(img3,x,y,null);
                g.drawImage(img3,x+45,y,null);
            }
            case 3 -> { //向左
                g.drawImage(img4,x,y,null);
                g.drawImage(img4,x,y+45,null);
            }
            default -> System.out.println("暂时没有处理");
        }
        g.setColor(Color.RED);
        g.fillOval(x + 20, y + 20, 20, 20);//画出圆形盖子
//        setRepaintArea(x,y,60,60);
    }

}
