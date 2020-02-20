package com.ponodan.hashcode.handler;

import com.ponodan.hashcode.model.Book;
import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.Library;
import com.ponodan.hashcode.model.LibraryScore;
import com.ponodan.hashcode.model.OutputDTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FullSearchAlgorithm implements SearchAlgorithm {
    @Override
    public OutputDTO calculate(InputDTO input) {
        List<Library> processedLibraries = getProcessedLibraries(input.scanDaysAmount, input.libraries);
        LinkedHashMap<Library, List<Book>> scannedBooks = shipBooksForScan(processedLibraries, input.scanDaysAmount);
        return getOutputDTO(scannedBooks);
    }

    private OutputDTO getOutputDTO(LinkedHashMap<Library, List<Book>> scannedBooks) {
        OutputDTO outputDTO = new OutputDTO();
        List<LibraryScore> libraryScores = new ArrayList<>();
        for (Map.Entry<Library, List<Book>> entry : scannedBooks.entrySet()) {
            LibraryScore libraryScore = new LibraryScore();
            libraryScore.booksProcessedAmount = entry.getValue().size();
            libraryScore.library = entry.getKey();
            libraryScore.processedBooks = entry.getValue();
            libraryScores.add(libraryScore);
        }
        outputDTO.libraryScores = libraryScores;
        return outputDTO;
    }

    private List<Library> getProcessedLibraries(int scanDaysAmount, List<Library> libraries) {
        List<Library> processedLibraries = new ArrayList<>();
        int overallSignupDelay = 0;
        for (Library library: libraries) {
            if (overallSignupDelay < scanDaysAmount + overallSignupDelay) {
                overallSignupDelay = overallSignupDelay + library.signupDelay;
                processedLibraries.add(library);
            }
        }
        return processedLibraries;
    }

    private LinkedHashMap<Library, List<Book>> shipBooksForScan(List<Library> libraries, int deadline) {
        List<Integer> signupDays = new ArrayList<>();
        int signupDelay = 0;
        for (Library library: libraries) {
            signupDays.add(signupDelay += library.signupDelay);
        }

        LinkedHashMap<Library, List<Book>> scannedBooks = new LinkedHashMap<>();
        for (int i = 0; i < deadline; i++) {
            if (signupDays.contains(i-1)) {
                int libraryIndex = signupDays.indexOf(i-1);
                Library library = libraries.get(libraryIndex);

                scanBooks(deadline, scannedBooks, i, library);
            }
        }
        return scannedBooks;
    }

    private void scanBooks(int deadline, LinkedHashMap<Library, List<Book>> scannedBooks, int currentDate, Library library) {
        int canBeScannedTillDeadline = library.shipPerDay * (deadline - currentDate);
        int booksToScan = library.books.size() <= canBeScannedTillDeadline ? library.books.size() : canBeScannedTillDeadline;
        List<Book> sortedBooks = library.getSortedBooksByScore();
        List<Book> processedBooks = new ArrayList<>();
        for (int j = 0; j < booksToScan; j++) {
            processedBooks.add(sortedBooks.get(j));
        }
        scannedBooks.put(library, processedBooks);
    }
}
