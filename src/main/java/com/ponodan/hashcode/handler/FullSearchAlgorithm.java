package com.ponodan.hashcode.handler;

import com.ponodan.hashcode.model.InputDTO;
import com.ponodan.hashcode.model.Library;
import com.ponodan.hashcode.model.OutputDTO;

import java.util.ArrayList;
import java.util.List;

public class FullSearchAlgorithm implements SearchAlgorithm {
    @Override
    public OutputDTO calculate(InputDTO input) {
        List<Integer> processedLibraries = getProcessedLibraries(input.scanDaysAmount, input.libraries);

        for (int i = 0; i < input.scanDaysAmount; i++) {
            
        }
        
        
        return new OutputDTO();
    }

    private List<Integer> getProcessedLibraries(int scanDaysAmount, List<Library> libraries) {
        List<Integer> processedLibraries = new ArrayList<>();
        int overallSignupDelay = 0;
        for (Library library: libraries) {
            if (overallSignupDelay < scanDaysAmount + overallSignupDelay) {
                overallSignupDelay = overallSignupDelay + library.signupDelay;
                processedLibraries.add(library.id);
            }
        }
        return processedLibraries;
    }
}
