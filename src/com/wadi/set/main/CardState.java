package com.wadi.set.main;

import com.wadi.set.exceptions.WrongCardsAddedException;

import java.util.ArrayList;
import java.util.Collections;

public class CardState extends ArrayList<Card> {

    public  CardState() {

    }

    public CardState(CardState other) {
        super(other);
    }

    public void addCards(Card ... cards) throws WrongCardsAddedException {
        if (cards.length == 3) {
            Collections.addAll(this, cards);
        } else {
            throw new WrongCardsAddedException();
        }
    }
}
