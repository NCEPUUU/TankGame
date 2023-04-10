package com.tankgame4;

/**
 * @author 韩顺平
 * @version 1.0
 * 射击子弹
 */
public class Bullet implements Runnable {
    int x; //子弹x坐标
    int y; //子弹y坐标
    int direct = 0; //子弹方向
    int speed = 20; //子弹的速度
    boolean isLive = true; //子弹是否还存活

    //构造器
    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击
        while (true) {

            //休眠 50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向来改变x,y坐标
            switch (direct) {
                case 0 ->//上
                        y -= speed;
                case 1 ->//右
                        x += speed;
                case 2 ->//下
                        y += speed;
                case 3 ->//左
                        x -= speed;
            }
            //老师测试，这里我们输出x,y的坐标
            System.out.println("子弹 x=" + x + " y=" + y);
            //当子弹移动到面板的边界时，就应该销毁（把启动的子弹的线程销毁)
            //当子弹碰到敌人坦克时，也应该结束线程
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }

        }
    }
}
