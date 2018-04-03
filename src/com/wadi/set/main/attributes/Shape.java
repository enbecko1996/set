package com.wadi.set.main.attributes;

import java.util.Random;

public enum Shape implements IAttributeEnum{
    DIAMOND (0),
    OVAL    (1),
    SQUIGGLE(2);

    private int index;

    Shape(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Shape getRandom() {
        return Shape.values()[new Random().nextInt(Shape.values().length)];
    }
}
