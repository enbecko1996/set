package com.wadi.set.gui;

import java.awt.*;

public class CardGridLayout extends GridLayout {

    public CardGridLayout(int rows, int cols, int hgap, int vgap) {
        super(rows, cols, hgap, vgap);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            int nrows = this.getRows();
            int ncols = this.getColumns();
            boolean ltr = parent.getComponentOrientation().isLeftToRight();

            if (ncomponents == 0) {
                return;
            }
            if (nrows > 0) {
                ncols = (ncomponents + nrows - 1) / nrows;
            } else {
                nrows = (ncomponents + ncols - 1) / ncols;
            }
            int w = parent.getWidth() - (insets.left + insets.right);
            int h = parent.getHeight() - (insets.top + insets.bottom);
            w = (w - (ncols - 1) * this.getHgap()) / ncols;
            h = (h - (nrows - 1) * this.getVgap()) / nrows;

            if (ltr) {
                for (int c = 0, x = insets.left ; c < ncols ; c++, x += w + this.getHgap()) {
                    for (int r = 0, y = insets.top ; r < nrows ; r++, y += h + this.getVgap()) {
                        int i = c * nrows + r;
                        if (i < ncomponents) {
                            parent.getComponent(i).setBounds(x, y, w, h);
                        }
                    }
                }
            } else {
                for (int c = 0, x = parent.getWidth() - insets.right - w; c < ncols ; c++, x -= w + this.getHgap()) {
                    for (int r = 0, y = insets.top ; r < nrows ; r++, y += h + this.getVgap()) {
                        int i = c * nrows + r;
                        if (i < ncomponents) {
                            parent.getComponent(i).setBounds(x, y, w, h);
                        }
                    }
                }
            }
        }
    }
}
