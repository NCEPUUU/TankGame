//package com.TankGame3.Utils;
//
//import com.TankGame3.Game03;
//import com.TankGame3.Hero;
//import com.TankGame3.MyPanel3;
//
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
////鼠标的移动监听事件和点击监听事件
//public class MouseUtil2 implements MouseListener {
//    static Hero hero;
//    static MyPanel3 mp;
//    static double turretAngle;
//
//    //鼠标指针的坐标，用于坦克移动而指针不动时计算炮筒旋转角度
//    static int mouseX;
//    static int mouseY;
//
//
//    private MouseUtil2(MyPanel3 m, Hero h) {
//        hero = h;
//        mp = m;
//    }
//    public static void addMouseListener(Game03 game, MyPanel3 m, Hero h) {
//        MouseUtil2 mouseUtil2 = new MouseUtil2(m,h);
//        game.addMouseListener(mouseUtil2);
////        game.addMouseMotionListener(mouseUtil);
//    }
//
//    //鼠标单击，发射炮弹
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        System.out.println("用户点击了鼠标左键, 开始射击.");
//        hero.shotEnemyTank();
////        mp.repaint();
//        mp.repaint(hero.getX(),hero.getY(),60,60);
//    }
//    //鼠标移动，旋转炮筒
////    @Override
////    public void mouseMoved(MouseEvent e) {
////        //校正后的指针坐标
////        mouseX = e.getX() - 7;
////        mouseY = e.getY() - 30;
////        //指针坐标与旋转中心的横纵坐标差值，用于计算旋转角度
////        double dx = mouseX - hero.getX() - 30;
////        double dy = mouseY - hero.getY() - 30;
////        //计算炮筒应该旋转的角度
////        turretAngle = Math.toDegrees(Math.atan2(dy, dx));
////
//////        System.out.println("=========="+(e.getX()-7)+" "+(e.getY()-30));
//////        System.out.println("=======+++"+hero.getX()+" "+hero.getY());
//////        System.out.println("================="+turretAngle+"===="+Math.toRadians(getTurretAngle()));
////        //更新炮筒旋转角度
////        hero.setAngle(Math.toRadians(turretAngle));//将角度转换为弧度
////        //重绘坦克所在区域
////        mp.repaint(hero.getX(),hero.getY(),60,60);
////    }
//
////    public static void setTurretAngle(Hero hero){
////        double dx = mouseX - hero.getX();
////        double dy = mouseY - hero.getY();
////        turretAngle = Math.toDegrees(Math.atan2(dy, dx));
////    }
////    //绘制炮筒时，获取角度
////    public static double getTurretAngle() {
////        return turretAngle;
////    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//    }
//    @Override
//    public void mouseReleased(MouseEvent e) {
//    }
//    @Override
//    public void mouseEntered(MouseEvent e) {
//    }
//    @Override
//    public void mouseExited(MouseEvent e) {
//    }
////    @Override
////    public void mouseDragged(MouseEvent e) {
////    }
//}
