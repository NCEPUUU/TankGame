package com.TankGameV4;

import java.awt.*;

/**
 * @author 韩顺平
 * @version 1.0
 *
 * 炸弹
 */
public class Bomb {
    int x, y; //炸弹的坐标
    int type;//爆炸类型，由轻到重：1:打到坦克轮子   2:达到坦克中心    3:坦克被击毁
    int life;//炸弹的爆炸生命周期

    Image bomb_1, bomb_2, bomb_3;

    public Bomb(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        //根据不同爆炸类型设置不同的爆炸周期
        if(type==1 || type == 2)
            life = 6;
        else
            life = 12;
        bomb_1 = Toolkit.getDefaultToolkit().getImage(Bomb.class.getResource("images/bomb_1.gif"));
        bomb_2 = Toolkit.getDefaultToolkit().getImage(Bomb.class.getResource("images/bomb_2.gif"));
        bomb_3 = Toolkit.getDefaultToolkit().getImage(Bomb.class.getResource("images/bomb_3.gif"));
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    /**
     * 爆炸生命周期减少
     */
    public void lifeDown(){
        if(life > 0) {
            life--;
        }
    }
    //爆炸效果绘制
    public void draw(Graphics g) {

        switch (type){
            case 1:{
                g.drawImage(bomb_1, x, y, 60, 60, null);
            }
            case 2:{
                g.drawImage(bomb_2, x, y, 60, 60, null);
            }
            case 3:{
                if(life >= 8)
                    g.drawImage(bomb_3, x, y, 60, 60, null);
                else if(life >=4)
                    g.drawImage(bomb_2, x, y, 60, 60, null);
                else
                    g.drawImage(bomb_1, x, y, 60, 60, null);
            }
        }
    }
}
