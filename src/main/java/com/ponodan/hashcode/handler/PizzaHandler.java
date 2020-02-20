package com.ponodan.hashcode.handler;


import com.ponodan.hashcode.model.InputPizzasDTO;
import com.ponodan.hashcode.model.OutputPizzaDTO;

public class PizzaHandler {

    FullSearchAlgorithm fullSearch = new FullSearchAlgorithm();
    OptimalSearchAlgorithm optimalSearch = new OptimalSearchAlgorithm();

    public OutputPizzaDTO handle(InputPizzasDTO input) {
        if (input.elements.length > 10_000) {
            return new OutputPizzaDTO(optimalSearch.calculate(input));
        } else{
            return new OutputPizzaDTO(fullSearch.calculate(input));
        }
    }
}
