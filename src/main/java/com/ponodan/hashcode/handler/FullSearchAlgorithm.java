package com.ponodan.hashcode.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ponodan.hashcode.model.InputDTO;

public class FullSearchAlgorithm implements SearchAlgorithm {
    @Override
    public List<Integer> calculate(InputDTO pizzas) {
        return null;
    }
/*
    public List<Integer> calculate(InputDTO pizzas) {
        int limit = pizzas.limit;
        int typesAmount = pizzas.elements.length;
        int[] typesArray = pizzas.elements;
        Map<Integer, List<Integer>> sumCombinationMap = new TreeMap<>();
        for (int i = 1; i < Math.pow(2, typesAmount); i++) {
            char[] binaryNumber = Integer.toBinaryString(i).toCharArray();
            int currentBinaryPos = binaryNumber.length - 1;
            List<Integer> result = new ArrayList<>();
            for (int j = typesAmount - 1; j >= 0 && currentBinaryPos >= 0; j--, currentBinaryPos--) {
                if (binaryNumber[currentBinaryPos] == 49) {
                    if (sum(result) + typesArray[j] == limit){
                        result.add(typesArray[j]);
                        return result;
                    } else if (sum(result) + typesArray[j] < limit){
                        result.add(typesArray[j]);
                    }
                }
            }
            sumCombinationMap.put(sum(result), result);
        }
       return sumCombinationMap.get(sumCombinationMap.keySet().toArray()[sumCombinationMap.size()-1]);
    }

    private int sum(List<Integer> list) {
       return list.stream().reduce(0, Integer::sum);
    }
*/
}
