package com.wadi.set.logic;

import com.wadi.set.View;

import java.util.ArrayList;
import java.util.Random;

public class StandardGameModel {

    private final CardState cardState;
    private ArrayList<View> views;

    public StandardGameModel() {
        this.views = new ArrayList<>();
        this.cardState = new CardState();
        Random rand = new Random();
        for (int i = 0; i < 16; i++) {
            this.cardState.add(new Card(rand.nextInt(4), rand.nextInt(4), rand.nextInt(4), rand.nextInt(4)));
        }
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
     * Initialize new Game State (Deck, Empty State).
     * @param n Amount of attributes.
     * @param m Number of cards in one Set. (Number of different species).
     * @param mode 1: Set, 2: SuperSet
     */
    public void newGame(int n, int m, int mode) {

    }

    /**
     * Called when user selects three cards with indices in CardState.
     * @param indices see above.
     */
    public void cardsSelected(int ... indices) {
        Card c = cardState.get(indices[0]);
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

    public CardState getCardState() {
        return this.cardState;
    }

    public void addView(View view) {
        this.views.add(view);
        view.refresh();
    }

    public void removeView(View view) {
        this.views.remove(view);
    }

    public void notifyViews() {
        for (View v : views) {
            v.refresh();
        }
    }
}
