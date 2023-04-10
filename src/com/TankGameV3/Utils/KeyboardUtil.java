package com.TankGameV3.Utils;

import com.TankGameV3.Game03;
import com.TankGameV3.MyPanel3;
import com.TankGameV3.Hero;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

//为坦克添加键盘监听事件
public class KeyboardUtil implements KeyListener {

    static Hero hero;
    static MyPanel3 mp;
    private KeyboardUtil(MyPanel3 m, Hero h) {
        hero = h;
        mp = m;
    }

    public static void addKeyListener(Game03 game, MyPanel3 m, Hero h) {
        KeyboardUtil keyboardUtil = new KeyboardUtil(m,h);
        game.addKeyListener(keyboardUtil);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //用集合来判断已经按下的键
    private Set<Integer> pressedKeys = new HashSet<>();
    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if(k == KeyEvent.VK_W || k == KeyEvent.VK_D ||k == KeyEvent.VK_S ||k == KeyEvent.VK_A) {
            System.out.println("按下了方向键，坦克发生移动:"+k);
            hero.setMoving(true);
            pressedKeys.add(k);
            if (k == KeyEvent.VK_W) {//W，向上
                hero.setDirect(0);
                hero.moveUp();
            } else if (k == KeyEvent.VK_D) {//D, 向右
                hero.setDirect(1);
                hero.moveRight();
            } else if (k == KeyEvent.VK_S) {//S，向下
                hero.setDirect(2);
                hero.moveDown();
            } else {//A，向左
                hero.setDirect(3);
                hero.moveLeft();
            }
        } else {
            System.out.println("无效输入！");
        }
        //坦克移动后，重新计算炮筒旋转角度
        MouseUtil.setTurretAngle(hero);
        hero.setAngle(Math.toRadians(MouseUtil.getTurretAngle()));//将角度转换为弧度
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("松开了按键:"+keyCode);
        System.out.println(pressedKeys.size());
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S
                || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D) {
            pressedKeys.remove(keyCode);
        }
        if(pressedKeys.isEmpty()){
            hero.setMoving(false);
            System.out.println("松开了所有按键，坦克停止移动！");
        }
    }
}

