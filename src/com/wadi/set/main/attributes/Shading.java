package com.wadi.set.main.attributes;

import java.util.Random;

public enum Shading implements IAttributeEnum{
    FILLED  (0),
    SHADED  (1),
    HOLLOW  (2);

    private int index;

    Shading(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Shading getRandom() {
        return Shading.values()[new Random().nextInt(Shading.values().length)];
    }
}
