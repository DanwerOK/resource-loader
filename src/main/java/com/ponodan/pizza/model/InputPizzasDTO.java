package com.ponodan.pizza.model;

public class InputPizzasDTO {
    public final int limit;
    public final int[] elements;

    public InputPizzasDTO(int limit, int[] elements) {
        this.limit = limit;
        this.elements = elements;
    }
}
