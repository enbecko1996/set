package com.wadi.set.gui;

import com.wadi.set.View;
import com.wadi.set.controller.Controller;
import com.wadi.set.controller.StandardGameController;
import com.wadi.set.logic.CardState;
import com.wadi.set.logic.StandardGameModel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class StandardGamePanel extends JPanel implements View {

    private StandardGameModel model;
    private StandardGameController controller;

    public StandardGamePanel(StandardGameModel model) {
        super(new BorderLayout());
        this.model = model;
        this.controller = new StandardGameController(this.model, this);
        this.model.addView(this);

        JButton cardButton = new JButton();
        cardButton.setPreferredSize(new Dimension(100, 500));
        cardButton.addActionListener(this.controller);
        this.add(new JLabel("HI"), BorderLayout.EAST);
        this.add(new CardDisplay(this.model.getCardState(), this.controller), BorderLayout.WEST);
    }

    @Override
    public void refresh() {

    }

    public class CardDisplay extends JPanel {

        private CardState cardState;

        CardDisplay(CardState cardState, StandardGameController controller) {

            this.cardState = cardState;
            this.setLayout(new GridLayout(4, 4));

            for (int i = 0; i < this.cardState.size(); i++) {
                CardButton btn = new CardButton(Arrays.toString(this.cardState.get(i).getData()), i);
                btn.setPreferredSize(new Dimension(100, 150));
                btn.addActionListener(controller);
                this.add(btn);
            }
        }

        public class CardButton extends JButton {

            private int cardStateIndex;

            CardButton(String text, int cardStateIndex) {
                super(text);
                this.cardStateIndex = cardStateIndex;
            }

            public int getCardStateIndex() {
                return this.cardStateIndex;
            }
        }
    }
}
