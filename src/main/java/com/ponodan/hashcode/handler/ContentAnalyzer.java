package com.ponodan.hashcode.handler;


import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.OutputDTO;

import java.util.List;

public class ContentAnalyzer {

    FullSearchAlgorithm fullSearch = new FullSearchAlgorithm();
    OptimalSearchAlgorithm optimalSearch = new OptimalSearchAlgorithm();

    public OutputDTO handle(InputDTO input) {
        /*
        if (input.elements.length > 10_000) {
            return new OutputDTO(optimalSearch.calculate(input));
        } else{
            return new OutputDTO(fullSearch.calculate(input));
        }
        */
        return new OutputDTO();
    }
}
