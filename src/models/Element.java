package models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Element implements Comparable<Element>{
    private int value;
    private final Rectangle rect;

    public Element(int value, int width) {
        this.value = value;
        rect = new Rectangle();
        rect.setWidth(width);
        rect.setHeight(value);
        rect.setFill(Color.rgb(2,170,2));
        rect.setStroke(Color.BLACK);
    }

    public Rectangle getRect() {return rect;}

    public int getValue() {return value;}

    /**
     * Moves rectangle to a new position.
     * @param x new horizontal coordinate of the rectangle.
     * @param y new vertical coordinate of the rectangle.
     */
    public void positionRect(int x, int y) {
        rect.setX(x);
        rect.setY(y);
    }

    /**
     * Compares two elements.
     * Returns 1 if calling element's value is greater than e's value
     * Returns 0 if calling element's value equals e's value
     * Returns -1 if calling element's value is smaller than e's value.
     * @param e the element object to be compared.
     * @return one of the integer value's listed above based on the result of the comparison.
     */
    public int compareTo(Element e) {
        return value - e.getValue();
    }
}
