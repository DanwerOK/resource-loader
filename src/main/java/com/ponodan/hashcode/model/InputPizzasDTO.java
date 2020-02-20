package com.ponodan.hashcode.model;

import java.util.Arrays;

public class InputPizzasDTO {
    public final int limit;
    public final int[] elements;

    public InputPizzasDTO(int limit, int[] elements) {
        this.limit = limit;
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
