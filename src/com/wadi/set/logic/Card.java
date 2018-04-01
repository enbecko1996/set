package com.wadi.set.logic;

public class Card {

    private final int[] data;

    public Card(int col, int shade, int count, int shape) {
        this.data = new int[]{col, shade, count, shape};
    }

    public int[] getData() {
        return this.data;
    }

    public int getColor() {
        return 0;
    }

    public int getShading() {
        return 0;
    }

    public int getCount() {
        return 0;
    }

    public int getShape() {
        return 0;
    }

}
