package com.wadi.set.logic;

import com.wadi.set.gui.IView;
import com.wadi.set.main.attributes.*;
import com.wadi.set.main.Card;
import com.wadi.set.main.CardState;
import com.wadi.set.main.StandardSetCard;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {

    private final CardState cardState;
    private boolean autoDraw = false;
    private ArrayList<IView> views;
    private ArrayList<Card> selectedCards = new ArrayList<>();

    public GameModel() {
        this.views = new ArrayList<>();
        this.cardState = new CardState();
        for (int i = 0; i < 12; i++) {
            this.cardState.add(new StandardSetCard(Count.getRandom(), Color.getRandom(), Shading.getRandom(), Shape.getRandom()));
        }
    }

    /**
     *
     * @return whether auto draw is turned on or off.
     */
    public boolean getAutoDraw() {
        return this.autoDraw;
    }

    /**
     *
     * @return amount of attributes.
     */
    public int getNumberOfAttributes() {
        return 0;
    }

    /**
     *
     * @return cards in a set.
     */
    public int getCardsPerSet() {
        return 3;
    }

    /**
     *
     * @return self explanatory.
     */
    public int getDeckSize() {
        return -1;
    }

    /**
     *
     * @return MUST return null if cardState has been changed (e.g. by drawn card or set completion) and findSets()
     *         hasn't been called afterwards.
     *         Otherwise returns ArrayList of sets as Card[] in the current cardState (Card objects are members of cardState).
     */
    public ArrayList<Card[]> getSets() {
        return null;
    }

    /**
     *
     * @return MUST return null if cardState has been changed (e.g. by drawn card or set completion) and findSets()
     *         hasn't been called afterwards.
     *         Otherwise returns ArrayList of sets as int[] in the current cardState (ints are indices of the card in the cardState).
     */
    public ArrayList<int[]> getSetsInt() {
        return null;
    }

    /**
     *
     * @return current game state as CardState.
     */
    public CardState getCardState() {
        return this.cardState;
    }

    /**
     * Initialize new Game.
     * @param attributes attributes.length = m (Number of attributes in the game).
     *                   attributes[i].length = n (Number of species per attribute).
     * @param cardsPerSet cards to complete a set (= l).
     * @param mode 1: set, 2: Superset
     */
    public void newGame(IAttributeEnum[][] attributes, int cardsPerSet, int mode) {
        this.notifyViews();
    }

    public void newStandardGame(int mode) {
        this.newGame(new IAttributeEnum[][]{Count.values(), Color.values(), Shading.values(), Shape.values()},
                3, mode);
    }

    /**
     * Called when user selects m cards with indices in CardState.
     *
     * Use cardSelected instead.
     *
     * @param indices see above.
     */
    @Deprecated
    public void cardsSelected(int ... indices) {
        Card c = cardState.get(indices[0]);
        this.notifyViews();
    }

    /**
     * Called when user selects or deselects ONE card.
     * @param card selected/deselected card. The Card object is a member of the cardState.
     */
    public void cardSelected(Card card) {
        assert this.cardState.contains(card);
        if (card.toggleSelected()) {
            if (this.selectedCards.size() < this.getCardsPerSet())
                this.selectedCards.add(card);
            else
                card.toggleSelected();
        } else
            this.selectedCards.remove(card);
        if (this.selectedCards.size() == this.getCardsPerSet()) {
            for (Card c : this.selectedCards)
                System.out.println(c);
            //
            // TODO
            //
        }
        this.notifyViews();
    }

    /**
     * Toggles between auto draw on and off.
     */
    public void toggleAutoDraw() {
        this.autoDraw = !autoDraw;
        this.notifyViews();
    }

    /**
     * New card from deck to cardState.
     */
    public void drawCard() {
        this.notifyViews();
    }

    /**
     * Calculate sets in given cardState and put into ArrayList<Card[]>.
     *
     * Renamed to findSets().
     */
    @Deprecated
    public void showSets() {
        this.notifyViews();
    }

    /**
     * Calculate sets in given cardState and put into ArrayList<Card[]>.
     */
    public void findSets() {
        this.notifyViews();
    }

    /**
     * End game.
     */
    public void endGame() {
        this.notifyViews();
    }

    public void addView(IView view) {
        this.views.add(view);
    }

    public void removeView(IView view) {
        this.views.remove(view);
    }

    public void notifyViews() {
        for (IView v : views) {
            v.refresh();
        }
    }
}
