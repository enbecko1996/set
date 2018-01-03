package com.enbecko.set;

public interface ICardProvider {
    int[][] getCards(boolean fetch);

    ICardProvider fetchCards();
}
