package org.beast4ever.aoc.aoc2k23.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EngineSchematicPartTest {
    @Test
    public void whenInlineData_thenIsSymbolOK() {
        String data = "$*%!/-=)\\#\"&az";

        List<EngineSchematicPart> engineSchematicParts = new ArrayList<>();

        for (int i=0; i<data.length(); i++) {
            engineSchematicParts.add(new EngineSchematicPart(data.charAt(i)));
        }

        engineSchematicParts.stream().forEach(currPart -> Assertions.assertEquals(true, currPart.isSymbol()));
    }

    @Test
    public void whenInlineData_thenIsDigitOK() {
        String data = "123456789";

        List<EngineSchematicPart> engineSchematicParts = new ArrayList<>();

        for (int i=0; i<data.length(); i++) {
            engineSchematicParts.add(new EngineSchematicPart(data.charAt(i)));
        }

        engineSchematicParts.stream().forEach(currPart -> Assertions.assertEquals(true, currPart.isDigit()));
    }

    @Test
    public void whenInlineData_thenIsUnassignedOK() {
        String data = ".";

        List<EngineSchematicPart> engineSchematicParts = new ArrayList<>();

        for (int i=0; i<data.length(); i++) {
            engineSchematicParts.add(new EngineSchematicPart(data.charAt(i)));
        }

        engineSchematicParts.stream().forEach(currPart -> Assertions.assertEquals(true, currPart.isUnassigned()));
    }

}
