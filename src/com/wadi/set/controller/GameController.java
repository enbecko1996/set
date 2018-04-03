package com.wadi.set.controller;

import com.wadi.set.gui.GamePanel;
import com.wadi.set.logic.GameModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener, IController {

    private GamePanel view;
    private GameModel model;

    public GameController(GameModel model, GamePanel view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         Object src = e.getSource();
         if (src instanceof GamePanel.CardDisplay.CardButton) {
             GamePanel.CardDisplay.CardButton btn = (GamePanel.CardDisplay.CardButton) src;
             this.model.cardsSelected(btn.getCardStateIndex());
         }
    }
}
