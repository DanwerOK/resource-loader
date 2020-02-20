package com.ponodan.hashcode.model;

import java.util.Arrays;

public class InputDTO {
    public final int limit;
    public final int[] elements;

    public InputDTO(int limit, int[] elements) {
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
