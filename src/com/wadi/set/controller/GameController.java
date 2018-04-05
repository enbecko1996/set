package com.wadi.set.controller;

import com.wadi.set.gui.GamePanel;
import com.wadi.set.gui.JCardButton;
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
         if (src instanceof JCardButton) {
             JCardButton btn = (JCardButton) src;
             this.model.cardSelected(btn.getDisplayCard());
         } else if (src == this.view.cAutoDraw) {
             this.model.toggleAutoDraw();
         } else if (src == this.view.bFindSets) {
             this.model.findSets();
         }
    }
}
