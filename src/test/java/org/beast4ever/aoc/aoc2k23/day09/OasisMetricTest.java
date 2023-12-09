package org.beast4ever.aoc.aoc2k23.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class OasisMetricTest {
    @Test
    public void whenInlineTextMetrics_thenMetricsParsedOK() {
        String inlineData = """
                0 3 6 9 12 15
                1 3 6 10 15 21
                10 13 16 21 30 45
                """;

        List<OasisMetric> metrics = new ArrayList<>();

        inlineData.lines().forEach(line -> metrics.add(new OasisMetric(line)));

        Assertions.assertEquals(3, metrics.size());
    }

    @Test
    public void whenInlineMetrics_thenNextValueOK() {
        OasisMetric metric1 = new OasisMetric(Arrays.asList(0l, 3l, 6l, 9l, 12l, 15l));
        OasisMetric metric2 = new OasisMetric(Arrays.asList(1l, 3l, 6l, 10l, 15l, 21l));
        OasisMetric metric3 = new OasisMetric(Arrays.asList(10l, 13l, 16l, 21l, 30l, 45l));


        Assertions.assertEquals(18, metric1.getNextValue());
        Assertions.assertEquals(28, metric2.getNextValue());
        Assertions.assertEquals(68, metric3.getNextValue());
    }

    @Test
    public void whenInlineMetrics_thenPreviousValueOK() {
        OasisMetric metric2 = new OasisMetric(Arrays.asList(1l, 3l, 6l, 10l, 15l, 21l));
        OasisMetric metric3 = new OasisMetric(Arrays.asList(10l, 13l, 16l, 21l, 30l, 45l));

        Assertions.assertEquals(0, metric2.getPreviousValue());
        Assertions.assertEquals(5, metric3.getPreviousValue());
    }


}
