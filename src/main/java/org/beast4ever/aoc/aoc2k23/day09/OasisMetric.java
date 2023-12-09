package org.beast4ever.aoc.aoc2k23.day09;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.LongFunction;

@Slf4j
public class OasisMetric {
    private List<Long> measures;

    public OasisMetric(List<Long> measures) {
        this.measures = measures;
    }

    public OasisMetric(String rawText) {
        this.measures = new ArrayList<Long>();
        StringTokenizer tokenizedMeasures = new StringTokenizer(rawText);
        while (tokenizedMeasures.hasMoreTokens()) {
            measures.add(Long.parseLong(tokenizedMeasures.nextToken()));
        }
        this.measures = measures;
    }

    public void addValue(Long valToAdd) {
        this.measures.add(valToAdd);
    }

    public void addFirstValue(Long valToAdd) {
        this.measures.add(0, valToAdd);
    }

    public Long getLastMetric() {
        return this.measures.get(measures.size()-1);
    }

    public Long getFirstMetric() {
        return this.measures.get(0);
    }

    public Long getNextValue() {
        List<List<Long>> listOfDiffList = buildDifferentialLists();

        Long lastUpperValue = 0l;
        for(int i=listOfDiffList.size()-1; i>0; i--) {
            List<Long> currDiffList = listOfDiffList.get(i);
            Long lastCurrentMetric = currDiffList.get(currDiffList.size()-1);
            List<Long> upperDiffList = listOfDiffList.get(i-1);
            lastUpperValue = upperDiffList.get(upperDiffList.size()-1)+lastCurrentMetric;
            upperDiffList.add(lastUpperValue);
            listOfDiffList.set(i-1, upperDiffList);
        }

        return lastUpperValue + measures.get(measures.size()-1);
    }

    public Long getPreviousValue() {
        List<List<Long>> listOfDiffList = buildDifferentialLists();

        Long firstUpperValue = 0l;
        for(int i=listOfDiffList.size()-1; i>0; i--) {
            List<Long> currDiffList = listOfDiffList.get(i);
            Long firstCurrentMetric = currDiffList.get(0);
            List<Long> upperDiffList = listOfDiffList.get(i-1);
            firstUpperValue = upperDiffList.get(0) - firstCurrentMetric;
            upperDiffList.add(0, firstUpperValue);
            listOfDiffList.set(i-1, upperDiffList);
        }

        return measures.get(0) - firstUpperValue;
    }

    private List<List<Long>> buildDifferentialLists() {
        List<List<Long>> listOfDiffList = new ArrayList<>();
        List<Long> diffList = getDifferentialList(measures);
        listOfDiffList.add(diffList);
        while (!OasisMetric.containsOnlyZero(diffList)) {
            diffList = getDifferentialList(diffList);
            listOfDiffList.add(diffList);
        }
        return listOfDiffList;
    }

    public static List<Long> getDifferentialList(List<Long> measures) {
        List<Long> diffList = new ArrayList<>();
        if (measures != null) {
            Long previousVal = measures.get(0);
            Long diff;
            for (int i = 1; i < measures.size(); i++) {
                diff = measures.get(i) - previousVal;
                previousVal = measures.get(i);
                diffList.add(diff);
            }
        }
        return diffList;
    }

    public static Boolean containsOnlyZero(List<Long> measures) {
        return measures.stream().filter(nb -> nb==0).count() == measures.size();
    }
}
