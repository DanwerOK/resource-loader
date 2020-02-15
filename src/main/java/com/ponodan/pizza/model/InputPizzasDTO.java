package com.ponodan.pizza.model;

import java.util.Arrays;

public class InputPizzasDTO {
    int limit;
    
    int[] elements;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int[] getElements() {
        return elements;
    }

    public void setElements(int[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "InputPizzasDTO{" +
                "limit=" + limit +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
}
