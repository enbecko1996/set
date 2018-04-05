package com.wadi.set.gui;

import com.wadi.set.controller.GameController;
import com.wadi.set.main.Card;
import com.wadi.set.main.CardState;
import com.wadi.set.logic.GameModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements IView, ActionListener {

    private GameModel model;
    private GameController controller;
    private int currentSetDisplay = 0;
    private final ArrayList<int[]> displayableSets = new ArrayList<>();
    private CardDisplay cardDisplay;

    public JButton bFindSets, bPrevSet, bNextSet, bZeroSet, bEndGame, bDrawCard;
    public JLabel lCardsRemain, lSetCounter;
    public JPanel pNextPrevSet;
    public JCheckBox cAutoDraw;

    public GamePanel(GameModel model) {
        super(new BorderLayout(3, 5));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.model = model;
        this.controller = new GameController(this.model, this);
        this.model.addView(this);

        JButton cardButton = new JButton();
        cardButton.setPreferredSize(new Dimension(100, 500));
        cardButton.addActionListener(this.controller);
        this.cardDisplay = new CardDisplay(this.model.getCardState(), this.controller);
        this.add(cardDisplay, BorderLayout.WEST);

        JPanel navigation = new JPanel();
        navigation.setLayout(new BoxLayout(navigation, BoxLayout.Y_AXIS));
        this.bFindSets = new JButton("Find Sets");
        this.bFindSets.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.bFindSets.addActionListener(this.controller);

        this.pNextPrevSet = new JPanel();
        this.pNextPrevSet.setLayout(new FlowLayout());
        this.pNextPrevSet.setMaximumSize(new Dimension(500, 35));
        this.pNextPrevSet.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.bZeroSet = new JButton("<<");
        this.bZeroSet.addActionListener(this);
        this.bZeroSet.setEnabled(false);
        this.pNextPrevSet.add(this.bZeroSet);

        this.bPrevSet = new JButton("<");
        this.bPrevSet.addActionListener(this);
        this.bPrevSet.setEnabled(false);
        this.pNextPrevSet.add(this.bPrevSet);

        this.bNextSet = new JButton(">");
        this.bNextSet.addActionListener(this);
        this.bNextSet.setEnabled(false);
        this.pNextPrevSet.add(this.bNextSet);

        this.lSetCounter = new JLabel(this.currentSetDisplay + "/" + this.displayableSets.size());
        this.pNextPrevSet.add(this.lSetCounter);

        this.lCardsRemain = new JLabel("Deck: " + this.model.getDeckSize() + " Cards");
        this.lCardsRemain.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.cAutoDraw = new JCheckBox("Auto Draw");
        this.cAutoDraw.addActionListener(this.controller);
        this.cAutoDraw.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.bDrawCard = new JButton("draw");
        this.bDrawCard.addActionListener(this.controller);
        this.bDrawCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.bEndGame = new JButton("End");
        this.bEndGame.addActionListener(this.controller);
        this.bEndGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        navigation.add(this.bFindSets);
        navigation.add(Box.createRigidArea(new Dimension(0,2)));
        navigation.add(this.pNextPrevSet);
        navigation.add(Box.createRigidArea(new Dimension(0,15)));
        navigation.add(this.lCardsRemain);
        navigation.add(Box.createVerticalGlue());
        navigation.add(this.cAutoDraw);
        navigation.add(Box.createRigidArea(new Dimension(0,5)));
        navigation.add(this.bDrawCard);
        navigation.add(Box.createRigidArea(new Dimension(0,80)));
        navigation.add(this.bEndGame);
        this.add(navigation, BorderLayout.EAST);
    }

    @Override
    public void refresh() {
        boolean changedState = this.cardDisplay.newCardState(this.model.getCardState());
        this.updateAutoDraw(this.model.getAutoDraw());
        ArrayList<int[]> foundSets = this.model.getSetsInt();
        if (foundSets != null) {
            if (this.displayableSets.size() != foundSets.size() || changedState) {
                this.displayableSets.clear();
                this.displayableSets.addAll(foundSets);
            }
        } else {
            this.displayableSets.clear();
            this.setSetButtonsEnabled(false);
            this.currentSetDisplay = 0;
        }
        if (changedState)
            this.currentSetDisplay = 0;
        if (this.displayableSets.size() > 0) {
            this.setSetButtonsEnabled(true);
        }
        this.updateSetCounter();
        this.repaint();
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.pack();
    }

    private void setSetButtonsEnabled(boolean enabled) {
        this.bNextSet.setEnabled(enabled);
        this.bZeroSet.setEnabled(enabled);
        this.bPrevSet.setEnabled(enabled);
    }

    private void updateSetCounter() {
        this.lSetCounter.setText(this.currentSetDisplay + "/" + this.displayableSets.size());
    }

    private void updateAutoDraw(boolean autoDraw) {
        this.cAutoDraw.setSelected(autoDraw);
        this.bDrawCard.setEnabled(!autoDraw);
    }

    private void displayOneSet() {
        if (this.currentSetDisplay > 0) {
            int[] curSet = this.displayableSets.get(this.currentSetDisplay - 1);
            this.cardDisplay.displayOneSet(curSet);
        } else {
            this.cardDisplay.displayNoSet();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == this.bNextSet) {
            if (this.currentSetDisplay < this.displayableSets.size()) {
                this.currentSetDisplay++;
                this.updateSetCounter();
                this.displayOneSet();
            }
        } else if (src == this.bPrevSet) {
            if (this.currentSetDisplay > 0) {
                this.currentSetDisplay--;
                this.updateSetCounter();
                this.displayOneSet();
            }
        } else if (src == this.bZeroSet) {
            this.currentSetDisplay = 0;
            this.updateSetCounter();
            this.cardDisplay.displayNoSet();
        }
    }

    public class CardDisplay extends JPanel {

        private CardState cardState;
        ArrayList<JCardButton> cardButtons = new ArrayList<>();

        CardDisplay(CardState cardState, GameController controller) {
            this.cardState = new CardState(cardState);
            this.setLayout(new CardGridLayout(3, (int) Math.ceil(this.cardState.size() / 3d), 10, 10));

            for (int i = 0; i < this.cardState.size(); i++) {
                JCardButton btn = new JCardButton(this.cardState.get(i), i);
                this.cardButtons.add(btn);
                btn.setPreferredSize(new Dimension(100, 160));
                btn.addActionListener(controller);
                this.add(btn);
            }
        }

        boolean newCardState(CardState newCardState) {
            boolean reallyChanged = false;
            if (newCardState != null) {
                if (newCardState.size() != this.cardState.size()) {
                    this.removeAll();
                    this.cardButtons.clear();
                    this.setLayout(new CardGridLayout(3, (int) Math.ceil(newCardState.size() / 3d), 10, 10));
                    for (int i = 0; i < newCardState.size(); i++) {
                        JCardButton btn = new JCardButton(newCardState.get(i), i);
                        this.cardButtons.add(btn);
                        btn.setPreferredSize(new Dimension(100, 160));
                        btn.addActionListener(controller);
                        this.add(btn);
                    }
                    return true;
                } else {
                    for (int i = 0; i < newCardState.size(); i++) {
                        if (newCardState.get(i) != this.cardState.get(i))
                            reallyChanged = true;
                            this.cardButtons.get(i).setDisplayCard(newCardState.get(i));
                    }
                }
                this.cardState = new CardState(newCardState);
                return reallyChanged;
            }
            return false;
        }

        void displayOneSet(int[] cardIndices) {
            for (JCardButton btn : this.cardButtons) {
                for (int i : cardIndices) {
                    if (btn.getCardStateIndex() == i) {
                        btn.setPartOfSetColor(Color.CYAN);
                        btn.repaint();
                        break;
                    } else {
                        btn.setPartOfSetColor(null);
                        btn.repaint();
                    }
                }
            }
        }

        void displayNoSet() {
            for (JCardButton btn : this.cardButtons) {
                btn.setPartOfSetColor(null);
                btn.repaint();
            }
        }
    }
}
