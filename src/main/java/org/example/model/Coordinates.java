package org.example.model;

import org.example.interfaces.Validatable;

public class Coordinates implements Validatable {
    private int x;
    private float y; //Поле не может быть null

    public Coordinates(){};

    public Coordinates(int x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean validate() {
        return true;
    }
}
