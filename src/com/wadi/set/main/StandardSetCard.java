package com.wadi.set.main;

import com.wadi.set.main.attributes.Color;
import com.wadi.set.main.attributes.Count;
import com.wadi.set.main.attributes.Shading;
import com.wadi.set.main.attributes.Shape;

public class StandardSetCard extends Card {

    /**
     * All parameters are in the range [0, 2]. (m = 2).
     * In the attributes array
     *      attributes[0]: Count,
     *      attributes[1]: Color,
     *      attributes[2]: Shading,
     *      attributes[3]: Shape,
     * @param color color identifier (0: red, 1: green, 2: purple).
     * @param shading shading identifier (0: filled, 1: shaded, 2: hollow).
     * @param count count identifier (0: one, 1: two, 2: three).
     * @param shape shape identifier (0: diamond, 1: oval, 2: squiggle).
     */
    public StandardSetCard(Count count, Color color, Shading shading, Shape shape) {
        super(count, color, shading, shape);
    }

    public Color getColor() {
        return (Color) this.attributes[0];
    }

    public Shading getShading() {
        return (Shading) this.attributes[1];
    }

    public Count getCount() {
        return (Count) this.attributes[2];
    }

    public Shape getShape() {
        return (Shape) this.attributes[3];
    }
}
