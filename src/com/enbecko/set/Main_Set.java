package com.enbecko.set;

import com.enbecko.set.analyzer.ModSetFinder;
import com.enbecko.set.analyzer.TestCardProvider;

public class Main_Set {
    public static void main(String[] args) {
        TestCardProvider cardProvider = new TestCardProvider();
        cardProvider.fetchCards();
        long startTime = System.nanoTime();
        int[][] sets_mod = ModSetFinder._findAllSets(cardProvider, false);
        long d1 = System.nanoTime() - startTime;
        int[][] sets_compare = ModSetFinder._findAllSets(cardProvider, false);
        long d2 = System.nanoTime() - d1 - startTime;

        System.out.println("Mod Method: " + d1 + " ns, Compare Method: " + d2 + " ns.");
    }
}
