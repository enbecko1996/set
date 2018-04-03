package com.wadi.set.main;

import com.wadi.set.gui.GamePanel;
import com.wadi.set.logic.GameModel;

import javax.swing.*;
import java.awt.*;

public class Game implements Runnable {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Game());
    }

    @Override
    public void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameModel model = new GameModel();
        f.setContentPane(new GamePanel(model));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
