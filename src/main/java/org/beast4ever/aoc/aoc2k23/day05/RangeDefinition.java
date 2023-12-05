package org.beast4ever.aoc.aoc2k23.day05;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeDefinition {
    public Integer getId() {
        return id;
    }

    private Integer id;
    private Long startSource;
    private Long startDestination;
    private Long length;

    public RangeDefinition(Integer id, Long startDestination, Long startSource, Long length) {
        this.id = id;
        this.startDestination = startDestination;
        this.startSource = startSource;
        this.length = length;
    }

    public Boolean containsSource(Long sourceNumber) {
        return (sourceNumber >= startSource && sourceNumber <= getMaxSourceNumber());
    }

    public Long getDestination(Long sourceNumber) {
        if (containsSource(sourceNumber)) {
            Long diff = sourceNumber-startSource;
            return startDestination+diff;
        } else {
            log.error("Source {} not contained in this {}", sourceNumber, this);
            return -1l;
        }
    }

    private Long getMaxSourceNumber() {
        return startSource+length-1;
    }

    @Override
    public String toString() {
        return "RangeDefinition{" +
                "id=" + id +
                ", startDestination=" + startDestination +
                ", startSource=" + startSource +
                ", length=" + length +
                '}';
    }
}
