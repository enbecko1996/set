package com.wadi.set.main.attributes;

import java.util.Random;

public enum Color implements IAttributeEnum{

    RED     (0),
    GREEN   (1),
    PURPLE  (2);

    private int index;

    Color(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Color getRandom() {
        return Color.values()[new Random().nextInt(Color.values().length)];
    }
}
