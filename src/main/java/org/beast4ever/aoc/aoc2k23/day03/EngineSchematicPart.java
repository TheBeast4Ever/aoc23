package org.beast4ever.aoc.aoc2k23.day03;

import java.util.Arrays;

public class EngineSchematicPart {
    public Character getContent() {
        return content;
    }

    private Character content;

    public Integer getFullPartRepresentation() {
        return fullPartRepresentation;
    }

    public void setFullPartRepresentation(Integer fullPartRepresentation) {
        this.fullPartRepresentation = fullPartRepresentation;
    }

    private Integer fullPartRepresentation;

    public EngineSchematicPart(Character content) {
        this.content = content;
    }



    public boolean isUnassigned() {
        return content == '.' ;
    }

    public boolean isSymbol() {
        return !isUnassigned() && !Character.isDigit(content) ;
    }

    public boolean isDigit() {
        return Character.isDigit(content);
    }

}

