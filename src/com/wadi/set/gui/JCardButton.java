package com.wadi.set.gui;

import com.wadi.set.main.Card;
import com.wadi.set.main.attributes.Count;
import com.wadi.set.main.attributes.IAttributeEnum;
import com.wadi.set.main.attributes.Shading;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class JCardButton extends JButton{

    private Card displayCard;
    private final int cardStateIndex;
    private Dimension cardArc;
    private Color partOfSetColor = null;

    public JCardButton(Card card, int cardStateIndex) {
        this.displayCard = card;
        this.cardStateIndex = cardStateIndex;
    }

    public int getCardStateIndex() {
        return this.cardStateIndex;
    }

    public Card getDisplayCard() {
        return displayCard;
    }

    public void setDisplayCard(Card displayCard) {
        this.displayCard = displayCard;
    }

    public void setPartOfSetColor(Color partOfSetColor) {
        this.partOfSetColor = partOfSetColor;
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return super.getMaximumSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return super.getMinimumSize();
    }

    protected void paintBorder(Graphics g) {

    }

    @Override
    public void paintComponent(Graphics g) {
        // super.paintComponent(g);

        this.cardArc = new Dimension((int)Math.sqrt(this.getSize().width), (int)Math.sqrt(this.getSize().height));

        // turn on anti-alias mode
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cardArc.width, cardArc.height);

        Color borderColor = null;
        Color fillColor = null;
        int count = 0;
        AbstractPaintMethod shape = null;
        for (IAttributeEnum attribute : this.displayCard.getAttributes()) {
            if (attribute instanceof com.wadi.set.main.attributes.Color) {
                com.wadi.set.main.attributes.Color c = (com.wadi.set.main.attributes.Color)attribute;
                switch (c) {
                    case RED:
                        borderColor = Color.RED;
                        break;
                    case GREEN:
                        borderColor = Color.GREEN;
                        break;
                    case PURPLE:
                        borderColor = new Color(100, 0, 255);
                }
            } else if (attribute instanceof Shading && borderColor != null) {
                Shading s = (Shading)attribute;
                switch (s) {
                    case FILLED:
                        fillColor = new Color(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), 255);
                        break;
                    case SHADED:
                        fillColor = new Color(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), 70);
                        break;
                    case HOLLOW:
                        fillColor = new Color(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), 0);
                }
            } else if (attribute instanceof Count) {
                Count c = (Count)attribute;
                switch (c) {
                    case ONE:
                        count = 1;
                        break;
                    case TWO:
                        count = 2;
                        break;
                    case THREE:
                        count = 3;
                }
            } else if (attribute instanceof com.wadi.set.main.attributes.Shape) {
                com.wadi.set.main.attributes.Shape s = (com.wadi.set.main.attributes.Shape)attribute;
                final int stroke = 1;
                switch (s) {
                    case OVAL:
                        shape = new AbstractPaintMethod() {
                            int arcWid = 40;
                            int arcHei = 40;

                            @Override
                            public void paintBorder(Graphics g, int x, int y, int maxWidth, int maxHeight) {
                                Graphics2D g2 = (Graphics2D)g;
                                Stroke oldStroke = g2.getStroke();
                                g2.setStroke(new BasicStroke(stroke));
                                g.drawRoundRect(x, y, maxWidth, maxHeight, arcWid, arcHei);
                                g2.setStroke(oldStroke);
                            }

                            @Override
                            public void paintFill(Graphics g, int x, int y, int maxWidth, int maxHeight) {
                                g.fillRoundRect(x, y, maxWidth, maxHeight, arcWid, arcHei);
                            }
                        };
                        break;
                    case DIAMOND:
                        shape = new AbstractPaintMethod() {

                            @Override
                            public void paintBorder(Graphics g, int x, int y, int maxWidth, int maxHeight) {
                                int[] xPoints = {x, x + maxWidth / 2, x + maxWidth, x + maxWidth / 2, x};
                                int[] yPoints = {y + maxHeight / 2, y, y + maxHeight / 2, y + maxHeight, y + maxHeight / 2};

                                int nPoints = xPoints.length;
                                Graphics2D g2 = (Graphics2D)g;
                                Stroke oldStroke = g2.getStroke();
                                g2.setStroke(new BasicStroke(stroke));
                                g.drawPolygon(xPoints, yPoints, nPoints);
                                g2.setStroke(oldStroke);
                            }

                            @Override
                            public void paintFill(Graphics g, int x, int y, int maxWidth, int maxHeight) {
                                int[] xPoints = {x, x + maxWidth / 2, x + maxWidth, x + maxWidth / 2, x};
                                int[] yPoints = {y + maxHeight / 2, y, y + maxHeight / 2, y + maxHeight, y + maxHeight / 2};

                                int nPoints = xPoints.length;

                                g.fillPolygon(xPoints, yPoints, nPoints);
                            }
                        };
                        break;
                    case SQUIGGLE:
                        shape = new AbstractPaintMethod() {

                            GeneralPath getPath(int x, int y, int maxWidth, int maxHeight) {
                                GeneralPath path = new GeneralPath();
                                path.moveTo(x, y + maxHeight);
                                path.curveTo(x, y - maxHeight, x + maxWidth - 20, y + 30, x + maxWidth, y);
                                path.curveTo(x + maxWidth, y + 2 * maxHeight, x + 20, y + maxHeight - 30, x, y + maxHeight);
                                return path;
                            }

                            @Override
                            public void paintBorder(Graphics g, int x, int y, int maxWidth, int maxHeight) {
                                GeneralPath path = getPath(x, y, maxWidth, maxHeight);
                                Graphics2D g2 = (Graphics2D)g;
                                g2.draw(path);
                            }

                            @Override
                            public void paintFill(Graphics g, int x, int y, int maxWidth, int maxHeight) {
                                GeneralPath path = getPath(x, y, maxWidth, maxHeight);
                                Graphics2D g2 = (Graphics2D)g;
                                g2.fill(path);
                            }
                        };
                }
            }
        }

        if (shape != null) {
            Dimension dim = this.getSize();
            Dimension center = new Dimension(dim.width / 2, dim.height / 2);
            Dimension objSize = new Dimension(dim.width - 30, dim.height / 3 - 20);
            int yPad = 10;
            for (int c = 0; c < count; c ++) {
                int yPos = center.height - (objSize.height + c * yPad) / 2 - (int)((count - 1) / 2f * (objSize.height + yPad / 2f)) + c * (objSize.height + yPad);
                g.setColor(borderColor);
                shape.paintBorder(g, (dim.width - objSize.width) / 2, yPos, objSize.width, objSize.height);
                g.setColor(fillColor);
                shape.paintFill(g, (dim.width - objSize.width) / 2, yPos, objSize.width, objSize.height);
            }
        }

        if (this.displayCard.isSelected()) {
            Color y = Color.YELLOW;
            Color highlight = new Color(y.getRed(), y.getGreen(), y.getBlue(), 70);
            g.setColor(highlight);
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cardArc.width, cardArc.height);
        }

        if (this.partOfSetColor != null) {
            Color c = this.partOfSetColor;
            Color highlight = new Color(c.getRed(), c.getGreen(), c.getBlue(), 70);
            g.setColor(highlight);
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cardArc.width, cardArc.height);
        }
    }

    interface AbstractPaintMethod {

        void paintBorder(Graphics g, int x, int y, int maxWidth, int maxHeight);

        void paintFill(Graphics g, int x, int y, int maxWidth, int maxHeight);
    }
}
