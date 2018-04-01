package com.wadi.set;

import com.wadi.set.gui.StandardGamePanel;
import com.wadi.set.logic.StandardGameModel;

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
        StandardGameModel model = new StandardGameModel();
        f.setContentPane(new StandardGamePanel(model));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
