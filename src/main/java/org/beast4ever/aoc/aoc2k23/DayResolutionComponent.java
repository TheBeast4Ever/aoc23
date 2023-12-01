package org.beast4ever.aoc.aoc2k23;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public abstract class DayResolutionComponent {

    public abstract String resolveFirstStar(String inputFilePath) throws IOException;
    public abstract String resolveSecondStar(String inputFilePath) throws IOException;

    private static final String UNIMPLEMENTED_OUTPUT_RESULT = "-999999";

    protected String unimplementedResolution(String inputFilePath) {
        log.warn("unimplemented resolution for this entry {}", inputFilePath);
        return UNIMPLEMENTED_OUTPUT_RESULT;
    }
}
