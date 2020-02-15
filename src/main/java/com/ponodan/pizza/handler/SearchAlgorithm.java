package com.ponodan.pizza.handler;

import java.util.List;

import com.ponodan.pizza.model.InputPizzasDTO;

public interface SearchAlgorithm {
    List<Integer> calculate(InputPizzasDTO pizzas);
}
