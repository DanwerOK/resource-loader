package com.ponodan.hashcode.handler;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.ponodan.hashcode.model.Book;
import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.OutputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContentAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentAnalyzer.class);

    private static int totalScore = 0;

    SearchAlgorithm fullSearch = new FullSearchAlgorithm();
    SearchAlgorithm optimalSearch = new OptimalSearchAlgorithm();
    SearchAlgorithm walkThroughBooks = new WalkThroughBooks();
    SearchAlgorithm mostProductiveLibrary = new MostProductiveLibrary();

    public OutputDTO handle(InputDTO input) {
        OutputDTO outputDTO = mostProductiveLibrary.calculate(input);
        printScore(input, outputDTO);
        return outputDTO;
    }

    private void printScore(InputDTO input, OutputDTO outputDTO) {
        Set<Book> processedBooks = outputDTO.libraryScores.stream()
                .map(el -> el.processedBooks)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        int fileScore = processedBooks.stream()
                .mapToInt(Book::getScore)
                .sum();
        totalScore += fileScore;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("File score: %s, Processed %s books of %s", fileScore, processedBooks.size(), input.booksAmount));
            LOGGER.info(String.format("Total score: %s", totalScore));
        }
    }
}
