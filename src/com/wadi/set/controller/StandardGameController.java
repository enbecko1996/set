package com.wadi.set.controller;

import com.wadi.set.gui.StandardGamePanel;
import com.wadi.set.logic.StandardGameModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StandardGameController implements ActionListener, Controller {

    private StandardGamePanel view;
    private StandardGameModel model;

    public StandardGameController(StandardGameModel model, StandardGamePanel view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         Object src = e.getSource();
         if (src instanceof StandardGamePanel.CardDisplay.CardButton) {
             StandardGamePanel.CardDisplay.CardButton btn = (StandardGamePanel.CardDisplay.CardButton) src;
             System.out.println(btn.getCardStateIndex());
         }
    }
}
