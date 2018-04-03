package com.wadi.set.main;

import com.wadi.set.main.attributes.IAttributeEnum;

import java.util.Arrays;

public class Card {

    final IAttributeEnum[] attributes;
    private final int[] attributeInt;

    public Card(IAttributeEnum ... attributes) {
        this.attributes = attributes;
        this.attributeInt = new int[this.attributes.length];
        for (int i = 0; i < this.attributes.length; i++) {
            this.attributeInt[i] = this.attributes[i].getIndex();
        }
    }

    public int[] getAttributeInt() {
        return this.attributeInt;
    }

    public IAttributeEnum[] getAttributes() {
        return this.attributes;
    }

    public IAttributeEnum getAttributeAt(int index) {
        return this.attributes[index];
    }

    public String toString() {
        return Arrays.toString(this.attributes);
    }
}
