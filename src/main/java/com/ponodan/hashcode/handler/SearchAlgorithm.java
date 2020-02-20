package com.ponodan.hashcode.handler;

import java.util.List;

import com.ponodan.hashcode.model.InputPizzasDTO;

public interface SearchAlgorithm {
    List<Integer> calculate(InputPizzasDTO pizzas);
}
