package com.TankGameV4;

import java.awt.*;

import static com.TankGameV4.Game04.bulletSpeed;
import static com.TankGameV4.Game04.fps;

//坦克发射的炮弹
public class Bullet{
    //子弹对象的属性
    double x; //子弹x坐标 (刷新率高，每一帧更新的变化很小，用double)
    double y; //子弹y坐标
    double angle; //子弹方向
    boolean isLive = true; //子弹是否还存活

    static int size = 7; //子弹的大小

    static Image ammo = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/ammo.png"));


    public Bullet(int x, int y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    //判断子弹是否出界
    public boolean isOutBound(){
        return !(x >= 0 && x <= Game04.WIDTH && y >= 0 && y <= Game04.HEIGHT);
    }

    //在timer中更新子弹坐标
    public void updateBullet(){
        //根据方向来改变x,y坐标
        x += bulletSpeed * Math.cos(angle) / fps; //计算前，fps自动转换为double
        y += bulletSpeed * Math.sin(angle) / fps;
        if (isOutBound()) {
            System.out.println("子弹线程退出");
            isLive = false;
        }
    }
    /**
     * 绘制阵亡坦克剩余的子弹
     */
    public static void drawPublicBullet(Graphics g) {
        for (Bullet publicBullet : MyPanel4.publicBullets) {
            int x = (int) publicBullet.x;
            int y = (int) publicBullet.y;
            int size;
            size = Bullet.size;
            g.fillOval(x - size / 2, y - size / 2, size, size);
        }
    }
}

