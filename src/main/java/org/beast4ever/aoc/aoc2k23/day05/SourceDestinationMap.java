package org.beast4ever.aoc.aoc2k23.day05;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.ranges.Range;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Slf4j
public class SourceDestinationMap {
    private Map<Integer, RangeDefinition> rangeDefinitions = new HashMap<>();

    public String getName() {
        return name;
    }

    private String name;

    public SourceDestinationMap(String name, List<String> rangeDefLines) {
        this.name = name;
        if (rangeDefLines.size()>0) {
            for (int i=1; i<=rangeDefLines.size(); i++) {
                StringTokenizer rangeDefTokenized = new StringTokenizer(rangeDefLines.get(i-1).trim(), " ");
                rangeDefinitions.put(i, new RangeDefinition(i, Long.parseLong(rangeDefTokenized.nextToken()),
                        Long.parseLong(rangeDefTokenized.nextToken()), Long.parseLong(rangeDefTokenized.nextToken())));
            }
        }
    }

    public RangeDefinition getRangeDefForSource(Long sourceNumber) {
        List<RangeDefinition> foundRanges = rangeDefinitions.values().stream().filter(rangeDef -> rangeDef.containsSource(sourceNumber)).toList();
        if (foundRanges.size()==1) {
            return foundRanges.get(0);
        } else {
            return null;
        }
    }


    public Long getDestinationForSource(Long sourceNumber) {
       RangeDefinition rangeDef = getRangeDefForSource(sourceNumber);
       if (rangeDef != null) {
           return rangeDef.getDestination(sourceNumber);
       } else {
           return sourceNumber;
       }
    }

}
