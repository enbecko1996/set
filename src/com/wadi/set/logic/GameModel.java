package com.wadi.set.logic;

import com.wadi.set.main.attributes.*;
import com.wadi.set.main.Card;
import com.wadi.set.main.CardState;
import com.wadi.set.IView;
import com.wadi.set.main.StandardSetCard;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {

    private final CardState cardState;
    private ArrayList<IView> views;

    public GameModel() {
        this.views = new ArrayList<>();
        this.cardState = new CardState();
        Random rand = new Random();
        for (int i = 0; i < 16; i++) {
            this.cardState.add(new StandardSetCard(Count.getRandom(), Color.getRandom(), Shading.getRandom(), Shape.getRandom()));
        }
    }

    /**
     *
     * @return amount of cards per set (number of species).
     */
    public int getM() {
        return 0;
    }

    /**
     *
     * @return amount of attributes.
     */
    public int getN() {
        return 0;
    }

    /**
     *
     * @return cards in a set.
     */
    public int getCardsPerSet() {
        return 0;
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
     * @return ArrayList of sets in the current cardState.
     */
    public ArrayList<Card[]> getSets() {
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

    }

    public void newStandardGame(int mode) {
        this.newGame(new IAttributeEnum[][]{Count.values(), Color.values(), Shading.values(), Shape.values()},
                3, mode);
    }

    /**
     * Called when user selects m cards with indices in CardState.
     * @param indices see above.
     */
    public void cardsSelected(int ... indices) {
        Card c = cardState.get(indices[0]);
        System.out.println(c);
    }

    /**
     * New card from deck to cardState.
     */
    public void drawCard() {

    }

    /**
     * Calculate sets in given cardState and put into ArrayList<Card[]>.
     */
    public void showSets() {

    }

    /**
     * End game.
     */
    public void endGame() {

    }

    public void addView(IView view) {
        this.views.add(view);
        view.refresh();
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
