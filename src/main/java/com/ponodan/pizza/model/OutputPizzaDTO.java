package com.ponodan.pizza.model;

import java.util.List;

public class OutputPizzaDTO {
    public OutputPizzaDTO(List<Integer> elementNumbers) {
        this.elementNumbers = elementNumbers;
    }

    public final List<Integer> elementNumbers;
}
