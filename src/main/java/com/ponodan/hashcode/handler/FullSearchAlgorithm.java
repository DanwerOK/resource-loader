package com.ponodan.hashcode.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ponodan.hashcode.model.InputPizzasDTO;

public class FullSearchAlgorithm implements SearchAlgorithm {
    public List<Integer> calculate(InputPizzasDTO pizzas) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> resultSets = new HashMap<>();
            List<Integer> resSet = new ArrayList<>();
            for (int i = 0; i < pizzas.elements.length; i++) {
                if (resSet.stream().reduce(0, Integer::sum) + pizzas.elements[i] < pizzas.limit) {
                    resSet.add(pizzas.elements[i]);
                }
            resultSets.put(0, resSet);
        }
        return result;
    }
}
