package models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Element implements Comparable{
    private int value;
    private Rectangle rect;

    public Element(int value, int width) {
        this.value = value;
        rect = new Rectangle();
        rect.setWidth(width);
        rect.setHeight(value);
        rect.setFill(Color.GREEN);
        rect.setStroke(Color.BLACK);
    }

    public Rectangle getRect() {return rect;}
    public int getValue() {return value;}

    public void positionRect(int x, int y) {
        rect.setX(x);
        rect.setY(y);
    }

    private static Element generateElement(int n){
        return new Element((int)(Math.random()*530), 950/n);
    }

    public static Element[] generateNElements(int n) {
        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = generateElement(n);
        }
        return elements;
    }

    @Override
    public int compareTo(Object o) {
        return value - ((Element)o).getValue();
    }
}
