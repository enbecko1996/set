package com.enbecko.set.analyzer;

import com.enbecko.set.ICardProvider;

public class ModSetFinder extends SetFinder {

    public ModSetFinder(ICardProvider cardProvider) {
        super(cardProvider);
    }

    public static int[] _findFirstSet(ICardProvider cardProvider, boolean fetchNew) {
        int[][] cards = cardProvider.getCards(fetchNew);
        // TODO
        return null;
    }

    @Override
    public int[] findFirstSet(ICardProvider cardProvider, boolean fetchNew) {
        return ModSetFinder._findFirstSet(cardProvider, fetchNew);
    }

    public static int[][] _findAllSets(ICardProvider cardProvider, boolean fetchNew) {
        int[][] cards = cardProvider.getCards(fetchNew);
        // TODO
        return null;
    }

    @Override
    public int[][] findAllSets(ICardProvider cardProvider, boolean fetchNew) {
        return ModSetFinder._findAllSets(cardProvider, fetchNew);
    }
}
