package com.enbecko.set.visual;

import com.enbecko.set.ICardProvider;

public class VisualCardProvider implements ICardProvider {

    public VisualCardProvider() {

    }

    /**
     *
     * @return two-dimensional tensor, with dimensions [12][4] for the standard game.
     * First dimension is the card index i, with i = 3 * x + y for the standard game.
     * Second dimension is the attribute vector, with
     *
     *      [i][0] = color, red = 0, green = 1, violet = 2,
     *      [i][1] = count, 1 = 0, 2 = 1, 3 = 2,
     *      [i][2] = shape, diamond = 0, stripe = 1, wave = 2,
     *      [i][3] = filling, empty = 0, shaded = 1, filled = 2.
     *
     */

    public int[][] getCards() {
        return this.getCards(false);
    }

    @Override
    public int[][] getCards(boolean fetch) {
        // TODO
        return null;
    }

    @Override
    public VisualCardProvider fetchCards() {
        // TODO
        return this;
    }
}
