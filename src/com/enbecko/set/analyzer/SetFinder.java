package com.enbecko.set.analyzer;

import com.enbecko.set.ICardProvider;

public abstract class SetFinder {
    private final ICardProvider cardProvider;

    public SetFinder(ICardProvider cardProvider) {
        this.cardProvider = cardProvider;
    }

    /**
     *
     * Das hier ist auch eine strange Eigenschaft von Java.
     * Abstrakte statische Methoden sind nicht möglich, obwohl das in vielen Situationen sinnvoll wäre.
     * So eine Limitation gibt es in Python nicht.
     *
     *
     public abstract static int[] findFirstSet(ICardProvider cardProvider) {

     }
     */

    public abstract int[] findFirstSet(ICardProvider cardProvider, boolean fetchNew);

    public int[] findFirstSet(boolean fetchNew) {
        return this.findFirstSet(this.cardProvider, fetchNew);
    }

    public abstract int[][] findAllSets(ICardProvider cardProvider, boolean fetchNew);

    public int[][] findAllSets(boolean fetchNew) {
        return this.findAllSets(this.cardProvider, fetchNew);
    }
}
