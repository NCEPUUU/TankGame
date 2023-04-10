package com.TankGameV4.Dialog;

import com.TankGameV4.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyDialogTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dialog Test");
        frame.setSize(400, 400);
//        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        JButton button = new JButton("Show Dialog");
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
                MyDialog dialog = new MyDialog(frame, "Dialog Title", true);
                dialog.setVisible(true);
//            }
//        });

        JPanel panel = new JPanel();
//        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }

    static class MyDialog extends JDialog {
        public MyDialog(Frame owner, String title, boolean modal) {
            super(owner, title, modal);
            setSize(400, 200);
            setLocationRelativeTo(owner);
            setResizable(false);

            JPanel contentPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    Image imageIcon = Toolkit.getDefaultToolkit().getImage(Tank.class.getResource("images/gameOver.png"));
                    g.drawImage(imageIcon, 0, 0, getWidth(), getHeight() / 2, null);
                }
            };

            contentPanel.setLayout(null);

//            JButton retryButton = new JButton(new ImageIcon("images/heroUp.png"));
//            retryButton.setBounds(20, 220, 100, 50);
//            contentPanel.add(retryButton);
//
//            JButton changeButton = new JButton(new ImageIcon("images/heroUp.png"));
//            changeButton.setBounds(150, 220, 100, 50);
//            contentPanel.add(changeButton);
//
//            JButton exitButton = new JButton(new ImageIcon("images/heroUp.png"));
//            exitButton.setBounds(280, 220, 100, 50);
//            contentPanel.add(exitButton);

            add(contentPanel);

            pack();
        }
    }
}

