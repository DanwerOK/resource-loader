package com.ponodan.hashcode.handler;

import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.OutputDTO;

public interface SearchAlgorithm {
    OutputDTO calculate(InputDTO pizzas);
}
