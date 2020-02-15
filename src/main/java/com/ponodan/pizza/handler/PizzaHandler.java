package com.ponodan.pizza.handler;


import com.ponodan.pizza.model.InputPizzasDTO;
import com.ponodan.pizza.model.OutputPizzaDTO;

public class PizzaHandler {

    FullSearchAlgorithm fullSearch = new FullSearchAlgorithm();
    OptimalSearchAlgorithm optimalSearch = new OptimalSearchAlgorithm();

    public OutputPizzaDTO handle(InputPizzasDTO input) {
        if (input.elements.length < 10_000) {
            return new OutputPizzaDTO(optimalSearch.calculate(input));
        } else{
            return new OutputPizzaDTO(fullSearch.calculate(input));
        }
    }
}
