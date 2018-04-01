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

    public void addNewCards() {

    }

    public void cardsSelected(int ... indices) {

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
