package com.Test.Bullet;

import java.awt.*;

public class Bullet {
    int x; // 子弹的横坐标
    int y; // 子弹的纵坐标
    int speed; // 子弹的速度

    public Bullet(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 5, 10);
    }

    public boolean isOutOfBound() {
        return y < 0;
    }
}
