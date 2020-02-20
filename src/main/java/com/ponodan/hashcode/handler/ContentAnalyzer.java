package com.ponodan.hashcode.handler;


import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.OutputDTO;

public class ContentAnalyzer {

    SearchAlgorithm fullSearch = new FullSearchAlgorithm();
    SearchAlgorithm optimalSearch = new OptimalSearchAlgorithm();

    public OutputDTO handle(InputDTO input) {
        return fullSearch.calculate(input);
    }
}
