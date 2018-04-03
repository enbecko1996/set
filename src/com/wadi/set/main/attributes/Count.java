package com.wadi.set.main.attributes;

import java.util.Random;

public enum Count implements IAttributeEnum{
    ONE     (0),
    TWO     (1),
    THREE   (2);

    private int index;

    Count(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Count getRandom() {
        return Count.values()[new Random().nextInt(Count.values().length)];
    }
}
