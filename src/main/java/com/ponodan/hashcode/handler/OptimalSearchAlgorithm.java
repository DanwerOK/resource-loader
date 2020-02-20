package com.ponodan.hashcode.handler;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.ponodan.hashcode.model.Book;
import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.Library;
import com.ponodan.hashcode.model.OutputDTO;
import com.ponodan.hashcode.util.Pair;

public class OptimalSearchAlgorithm implements SearchAlgorithm {

    @Override
    public OutputDTO calculate(InputDTO task) {
        int delay = task.getScanDaysAmount();

        List<Library> libraries = task.getLibraries();
        List<Pair<Library, List<Book>>> result = new ArrayList<>();
        process(result, delay, libraries);

        return new OutputDTO();
    }

    private List<Pair<Library, List<Book>>> process(List<Pair<Library, List<Book>>> result,
                                                    int delay,
                                                    List<Library> list) {
        ArrayList<Library> libraries = new ArrayList<>(list);
        List<Book> processed = result.stream().map(Pair::getRight).flatMap(Collection::stream).collect(Collectors.toList());
        Pair<Library, List<Book>> pair = findBest(libraries, delay, processed);
        if (pair == null) {
            return result;
        }
        result.add(pair);
        libraries.remove(pair.getLeft());
        int nextDelay = delay - pair.getLeft().getShipPerDay();
        return process(result, nextDelay, libraries);
    }

    private Pair<Library, List<Book>> findBest(List<Library> libraries, int delay, List<Book> processed) {
        int bestPotentialScore = 0;
        Pair<Library, List<Book>> bestLibrary = null;
        for (Library library : libraries) {
            if (bestLibrary == null) {
                bestLibrary = new Pair<>(library, new ArrayList<>());
            }
            Pair<Integer, List<Book>> potentialScore = library.potentialScore(delay, processed);
            Integer potentialScoreLeft = potentialScore.getLeft();
            if (potentialScoreLeft > bestPotentialScore) {
                bestPotentialScore = potentialScoreLeft;
                bestLibrary = new Pair<>(library, potentialScore.getRight());
            }
        }
        return bestLibrary;
    }

}
