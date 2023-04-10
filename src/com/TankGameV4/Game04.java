package com.TankGameV4;

import com.TankGameV4.Utils.KeyboardUtil;
import com.TankGameV4.Utils.MouseUtil;

import javax.swing.*;
import java.util.Arrays;

public class Game04 extends JFrame {

    static final int WIDTH = 1300;
    static final int HEIGHT = 790;
    static final int fps = 120;//刷新率
    int[] params;

    static double bulletSpeed = 1300; //子弹的速度， 像素/秒
    static String title;//窗口标题

    int iniHeroBlood;
    int enemyTankNum;
    int iniEnemyBlood;

    MouseUtil mouseListener;
    KeyboardUtil keyListener;

    public Game04(int[] p) {
        this.params = p;
        iniHeroBlood = params[0];
        enemyTankNum = params[1];
        iniEnemyBlood = params[2];

        Hero hero = new Hero(WIDTH-60, HEIGHT-60, iniHeroBlood);
        MyPanel4 mp = new MyPanel4(this,hero,enemyTankNum,iniEnemyBlood);

        //为panel添加监听事件
        mouseListener = new MouseUtil(mp,hero);
        keyListener = new KeyboardUtil(mp,hero);
        mouseListener.addMouseListener(this);
        keyListener.addKeyListener(this);

        this.setTitleByBlood(iniHeroBlood);

        this.setResizable(false);//窗口大小不可变
        this.setSize(WIDTH+14, HEIGHT+35);//设置窗口大小，并校正边界
        this.setTitle(title);
        this.add(mp);//初始化自己坦克

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * 游戏界面标题，显示游戏难度和坦克当前血量
     */
    public void setTitleByBlood(int heroBlood) {
        String difficulty;
        if(iniHeroBlood == 6)
            difficulty = "简单";
        else if(iniHeroBlood == 3)
            difficulty = "一般";
        else
            difficulty = "困难";
        title = "游戏难度: " + difficulty + "   坦克血量: ";
        for (int i = 0; i < heroBlood; i++) {
            title += "#";
        }
        setTitle(title);
    }

    public void setParams() {
        this.params = new int[]{iniHeroBlood,enemyTankNum,iniEnemyBlood};
    }

    public MouseUtil getMouseListener() {
        return mouseListener;
    }

    public KeyboardUtil getKeyListener() {
        return keyListener;
    }

    /**
     * 重新开始游戏
     */
    public void restart(){
        // 从窗口中删除当前Game实例对象
        this.setVisible(false);
        int[] params = this.params;
        this.dispose();
        // 创建一个新的Game实例对象来重新开始游戏
        System.out.println(Arrays.toString(params));
        Game04 newGame = new Game04(params);
    }

    public static void main(String[] args) {
        int[] params = new int[]{1,4,6};
        Game04 game04 = new Game04(params);
    }

    /**
     * 更改游戏难度并重新开始游戏
     */
    public void setDifficulty(int type) {
        switch (type) {
            case 0 -> {//简单
                iniHeroBlood = 6;
                enemyTankNum = 3;
                iniEnemyBlood = 4;
                setParams();
            }
            case 1 -> {//一般
                iniHeroBlood = 3;
                enemyTankNum = 4;
                iniEnemyBlood = 8;
                setParams();
            }
            case 2 -> {//困难
                iniHeroBlood = 1;
                enemyTankNum = 6;
                iniEnemyBlood = 15;
                setParams();
            }
        }
        System.out.println(Arrays.toString(params));
        restart();
    }
}
