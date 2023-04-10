package com.TankGameV4;

import java.awt.*;
import java.util.Vector;

public abstract class Tank {
    private int x;//坦克的横坐标
    private int y;//坦克的纵坐标
    private int moveLength = 10;//坦克移动步幅
    private int direct = 0;//坦克方向 0 上1 右 2下 3左
    private double angle = 0;//炮筒方向，弧度制
    private boolean isMoving;//判断坦克是否在移动
    private boolean isTankLive;//坦克是否存活
    private int maxBullets = 6;//发射的最大子弹数
    private Vector<Bullet> bullets;//存放坦克的子弹
    private int blood;//坦克血量

    Image imgHero_1, imgHero_2, imgHero_3, imgHero_4,ammo;//我方坦克轮子的图片
    Image imgEnemy_1, imgEnemy_2, imgEnemy_3, imgEnemy_4;//我方坦克轮子的图片

    public Tank(int x, int y, int blood) {
        this.x = x;
        this.y = y;
        this.blood = blood;
        isMoving = true;//初始化为移动状态，以便在初次调用paint方法时画出坦克
        isTankLive = true;
        bullets = new Vector<>();
        //坦克轮子的图片
        imgHero_1 = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/heroUp.png"));
        imgHero_2 = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/heroRight.png"));
        imgHero_3 = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/heroDown.png"));
        imgHero_4 = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/heroLeft.png"));
        ammo = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/ammo.png"));
        imgEnemy_1 = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/enemyUP.png"));
        imgEnemy_2 = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/enemyRight.png"));
        imgEnemy_3 = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/enemyDown.png"));
        imgEnemy_4 = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/enemyLeft.png"));

    }

    public double getAngle() {
        return angle;
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getMoveLength() {
        return moveLength;
    }
    public void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
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

    public boolean isTankLive() {
        return isTankLive;
    }
    public void setTankLive(boolean tankLive) {
        isTankLive = tankLive;
    }

    public int getMaxBullets() {
        return maxBullets;
    }
    public void setMaxBullets(int maxBullets) {
        this.maxBullets = maxBullets;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }
    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public void moveUp(){
        y -= moveLength;
    }
    public void moveRight(){
        x += moveLength;
    }
    public void moveDown(){
        y += moveLength;
    }
    public void moveLeft(){
        x -= moveLength;
    }

    /**
     * 绘制坦克
     * @param type   坦克类型
     */
    public void drawTank(int type, Graphics g) {
        //根据坦克类型，设置不同颜色
        switch (type) {
            case 0 -> //我方坦克
                    g.setColor(Color.cyan);
            case 1 -> //敌人的坦克
                    g.setColor(Color.yellow);
        }
        g.fill3DRect(x + 15, y + 15, 30, 30, false);//画出坦克方形盖子
        //根据坦克类型绘制不同的轮子
        if(type == 0) {
            switch (direct) {
                case 0 -> { //向上
                    g.drawImage(imgHero_1, x, y, null);
                    g.drawImage(imgHero_1, x + 45, y, null);
                }
                case 1 -> { //向右
                    g.drawImage(imgHero_2, x, y, null);
                    g.drawImage(imgHero_2, x, y + 45, null);
                }
                case 2 -> { //向下
                    g.drawImage(imgHero_3, x, y, null);
                    g.drawImage(imgHero_3, x + 45, y, null);
                }
                case 3 -> { //向左
                    g.drawImage(imgHero_4, x, y, null);
                    g.drawImage(imgHero_4, x, y + 45, null);
                }
                default -> System.out.println("暂时没有处理");
            }
        }
        else if(type == 1) {
            switch (direct) {
                case 0 -> { //向上
                    g.drawImage(imgEnemy_1, x, y, null);
                    g.drawImage(imgEnemy_1, x + 45, y, null);
                }
                case 1 -> { //向右
                    g.drawImage(imgEnemy_2, x, y, null);
                    g.drawImage(imgEnemy_2, x, y + 45, null);
                }
                case 2 -> { //向下
                    g.drawImage(imgEnemy_3, x, y, null);
                    g.drawImage(imgEnemy_3, x + 45, y, null);
                }
                case 3 -> { //向左
                    g.drawImage(imgEnemy_4, x, y, null);
                    g.drawImage(imgEnemy_4, x, y + 45, null);
                }
                default -> System.out.println("暂时没有处理");
            }
        }
        g.setColor(Color.RED);
        g.fillOval(x + 20, y + 20, 20, 20);//画出圆形盖子
    }

    /**
     * 绘制子弹
     */
    public void drawBullet(int type, Graphics g) {
        //删除已经失效的子弹
        getBullets().removeIf(bullet -> !bullet.isLive);
        for (Bullet bullet : getBullets()) {
            //更新子弹坐标
            bullet.updateBullet();
            int x = (int) bullet.x;
            int y = (int) bullet.y;
            int size;
            if(type == 0){
                size = 12;
                g.drawImage(ammo,x - size / 2, y - size / 2,null);
            }
            if(type == 1) {
                size = Bullet.size;
                g.fillOval(x - size / 2, y - size / 2, size, size);
            }
        }
    }

    /**
     * 坦克发射炮弹
     * @param angle 发射角度
     */
    public void fire(double angle){
        //创建子弹
        if (isTankLive && bullets.size() < maxBullets) {
            Bullet s = new Bullet(x + 30, y + 30, angle);
            bullets.add(s);
        }
        System.out.println((x+30)+" "+(y+30)+" "+direct);
    }

}
