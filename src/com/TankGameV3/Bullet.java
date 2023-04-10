package com.TankGameV3;

import static com.TankGameV3.Game03.bulletSpeed;
import static com.TankGameV3.Game03.fps;

//坦克发射的炮弹
public class Bullet implements Runnable {
    //子弹对象的属性
    double x; //子弹x坐标 (刷新率高，每一帧更新的变化很小，用double)
    double y; //子弹y坐标
    double angle; //子弹方向
    boolean isLive = true; //子弹是否还存活

    static int size = 7; //子弹的大小

    public Bullet(int x, int y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    //判断子弹是否出界
    public boolean isOutBound(){
        return !(x >= 0 && x <= Game03.WIDTH && y >= 0 && y <= Game03.HEIGHT);
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

//    /**
//     * 绘制子弹
//     */
//    public void drawBullet(Tank tank, Graphics g) {
//        if(tank.bullet != null && tank.bullet.isLive) {
//            int x = (int) tank.bullet.x;
//            int y = (int) tank.bullet.y;
//            int size = Bullet.size;
//            g.fillOval(x-size/2, y-size/2, size, size);
////            setRepaintArea(x-size/2, y-size/2, size, size);
//        }
//    }
    @Override
    public void run() { //射击
        while (true) {
            try {
                Thread.sleep(1000/ fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向来改变x,y坐标
            x += bulletSpeed * Math.cos(angle) / fps; //计算前，fps自动转换为double
            y += bulletSpeed * Math.sin(angle) / fps;
//            System.out.println("子弹 x=" + (int)x + " y=" + (int)y);

            if (isOutBound()) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }
        }
    }
}

