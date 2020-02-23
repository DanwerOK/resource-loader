package com.ponodan.hashcode.handler;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ponodan.hashcode.model.Book;
import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.Library;
import com.ponodan.hashcode.model.LibraryScore;
import com.ponodan.hashcode.model.OutputDTO;
import com.ponodan.hashcode.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimalSearchAlgorithm implements SearchAlgorithm {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptimalSearchAlgorithm.class);

    @Override
    public OutputDTO calculate(InputDTO task) {
        int delay = task.getScanDaysAmount();

        List<Library> libraries = new ArrayList<>(task.getLibraries());
        List<Pair<Library, List<Book>>> result = process(delay, libraries);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Total score: %s", task.getBooks()
                    .values()
                    .stream()
                    .filter(Book::isScanned)
                    .mapToInt(Book::getScore)
                    .sum()));
        }
        OutputDTO outputDTO = new OutputDTO();
        outputDTO.libraryScores = result.stream()
                .filter(el -> !el.getRight().isEmpty())
                .map(el -> {
                    LibraryScore libraryScore = new LibraryScore();
                    libraryScore.processedBooks = el.getRight();
                    libraryScore.library = el.getLeft();
                    libraryScore.booksProcessedAmount = el.getRight().size();
                    return libraryScore;
                }).collect(Collectors.toList());
        return outputDTO;
    }

    private List<Pair<Library, List<Book>>> process(int delay,
                                                    List<Library> libraries) {
        List<Pair<Library, List<Book>>> result = new ArrayList<>();
        int processedBookSize = 0;
        int nextDelay = delay;
        do {
            Pair<Library, List<Book>> pair = findBest(libraries, nextDelay);
            if (pair.getLeft() == null) {
                break;
            }
            result.add(pair);
            List<Book> processedByBestLibrary = pair.getRight();
            processedByBestLibrary.forEach(el -> el.setScanned(true));
            processedBookSize += processedByBestLibrary.size();
            libraries.remove(pair.getLeft());
            nextDelay = nextDelay - pair.getLeft().getSignupDelay();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                        String.format("Days left: %s, Libraries left: %s, Processed books: %s.", nextDelay, libraries.size(), processedBookSize));
            }
        } while (nextDelay > 0 && !libraries.isEmpty());
        return result;
    }

    private Pair<Library, List<Book>> findBest(List<Library> libraries, int delay) {
        int bestPotentialScore = 0;
        Library bestLibrary = null;
        int processedBookAmount = 0;
        for (Library library : libraries) {
            Pair<Integer, Integer> potentialScore = library.potentialScore(delay);
            Integer potentialScoreLeft = potentialScore.getLeft();
            if (potentialScoreLeft > bestPotentialScore) {
                bestPotentialScore = potentialScoreLeft;
                bestLibrary = library;
                processedBookAmount = potentialScore.getRight();
            }
        }
        int finalProcessedBookAmount = processedBookAmount;
        List<Book> processedBooks = Optional.ofNullable(bestLibrary)
                .map(library -> library.getSortedBooksByScore().parallelStream()
                        .filter(el -> !el.isScanned())
                        .collect(Collectors.toList()))
                .map(books -> books.subList(0, Math.min(finalProcessedBookAmount, books.size())))
                .orElse(Collections.emptyList());
        return new Pair<>(bestLibrary, processedBooks);
    }

}
