package org.beast4ever.aoc.aoc2k23.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CamelCardHandTest {
    @Test
    public void whenMultipleHands_thenEachHandEncodedOK() {
        List<String> expectedResults = Arrays.asList("b32A3D", "dA55B5", "cDD677", "cDABBA", "dCCCBE", "");
        List<String> encodedResults = new ArrayList<>();
        List<String> multipleHands = Arrays.asList(
                "32T3K",
                "T55J5",
                "KK677",
                "KTJJT",
                "QQQJA",
                "J3737",
                "J4244"
        );


        multipleHands.stream().forEach(hand -> {
           encodedResults.add((new CamelCardHand(hand)).encode(false));
        });

        Assertions.assertEquals(expectedResults, encodedResults);
    }

    @Test
    public void whenMultipleHands_thenRanksOK() {
        List<String> expectedResults = Arrays.asList("32T3K", "KTJJT", "KK677", "T55J5", "QQQJA");
        List<String> results = new ArrayList<>();
        List<CamelCardHand> multipleHands = Arrays.asList(
                new CamelCardHand("32T3K", 765),
                new CamelCardHand("T55J5", 684),
                new CamelCardHand("KK677", 28),
                new CamelCardHand("KTJJT", 220),
                new CamelCardHand("QQQJA", 483)
        );

        Collections.sort(multipleHands);

        multipleHands.stream().forEach(h -> results.add(h.getHand()));

        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenMultipleHands_thenScoresOK() {
        List<Integer> expectedResults = Arrays.asList(765, 440, 84, 2736, 2415);
        List<Integer> results = new ArrayList<>();
        List<CamelCardHand> multipleHands = Arrays.asList(
                new CamelCardHand("32T3K", 765),
                new CamelCardHand("T55J5", 684),
                new CamelCardHand("KK677", 28),
                new CamelCardHand("KTJJT", 220),
                new CamelCardHand("QQQJA", 483)
        );

        Collections.sort(multipleHands);

        for (int rank=1; rank<=multipleHands.size(); rank++) {
            results.add(rank*(multipleHands.get(rank-1).getBid()));
        }

        Assertions.assertEquals(expectedResults, results);
    }


    @Test
    public void whenFiveOfKind_thenTypeOK() {
        String hand = "AAAAA";
        CamelCardHand cardHand = new CamelCardHand(hand);

        Assertions.assertTrue(cardHand.isFiveOfKind());
        Assertions.assertFalse(cardHand.isFourOfKind());
        Assertions.assertFalse(cardHand.isFullHouse());
        Assertions.assertFalse(cardHand.isThreeOfKind());
        Assertions.assertFalse(cardHand.isTwoPairs());
        Assertions.assertFalse(cardHand.isOnePair());

    }

    @Test
    public void whenFourOfKind_thenTypeOK() {
        String hand = "AA8AA";
        CamelCardHand cardHand = new CamelCardHand(hand);

        Assertions.assertFalse(cardHand.isFiveOfKind());
        Assertions.assertTrue(cardHand.isFourOfKind());
        Assertions.assertFalse(cardHand.isFullHouse());
        Assertions.assertFalse(cardHand.isThreeOfKind());
        Assertions.assertFalse(cardHand.isTwoPairs());
        Assertions.assertFalse(cardHand.isOnePair());

    }

    @Test
    public void whenFullHouse_thenTypeOK() {
        String hand = "23332";
        CamelCardHand cardHand = new CamelCardHand(hand);

        Assertions.assertFalse(cardHand.isFiveOfKind());
        Assertions.assertFalse(cardHand.isFourOfKind());
        Assertions.assertTrue(cardHand.isFullHouse());
        Assertions.assertFalse(cardHand.isThreeOfKind());
        Assertions.assertFalse(cardHand.isTwoPairs());
        Assertions.assertFalse(cardHand.isOnePair());

    }

    @Test
    public void whenThreeOfKind_thenTypeOK() {
        String hand = "TTT98";
        CamelCardHand cardHand = new CamelCardHand(hand);

        Assertions.assertFalse(cardHand.isFiveOfKind());
        Assertions.assertFalse(cardHand.isFourOfKind());
        Assertions.assertFalse(cardHand.isFullHouse());
        Assertions.assertTrue(cardHand.isThreeOfKind());
        Assertions.assertFalse(cardHand.isTwoPairs());
        Assertions.assertFalse(cardHand.isOnePair());

    }

    @Test
    public void whenTwoPairs_thenTypeOK() {
        String hand = "23432";
        CamelCardHand cardHand = new CamelCardHand(hand);

        Assertions.assertFalse(cardHand.isFiveOfKind());
        Assertions.assertFalse(cardHand.isFourOfKind());
        Assertions.assertFalse(cardHand.isFullHouse());
        Assertions.assertFalse(cardHand.isThreeOfKind());
        Assertions.assertTrue(cardHand.isTwoPairs());
        Assertions.assertFalse(cardHand.isOnePair());

    }

    @Test
    public void whenOnePair_thenTypeOK() {
        String hand = "A23A4";
        CamelCardHand cardHand = new CamelCardHand(hand);

        Assertions.assertFalse(cardHand.isFiveOfKind());
        Assertions.assertFalse(cardHand.isFourOfKind());
        Assertions.assertFalse(cardHand.isFullHouse());
        Assertions.assertFalse(cardHand.isThreeOfKind());
        Assertions.assertFalse(cardHand.isTwoPairs());
        Assertions.assertTrue(cardHand.isOnePair());

    }

    @Test
    public void whenHighCard_thenTypeOK() {
        String hand = "23456";
        CamelCardHand cardHand = new CamelCardHand(hand);

        Assertions.assertFalse(cardHand.isFiveOfKind());
        Assertions.assertFalse(cardHand.isFourOfKind());
        Assertions.assertFalse(cardHand.isFullHouse());
        Assertions.assertFalse(cardHand.isThreeOfKind());
        Assertions.assertFalse(cardHand.isTwoPairs());
        Assertions.assertFalse(cardHand.isOnePair());
        Assertions.assertTrue(cardHand.isHighCard());

    }

    @Test
    public void whenFourOfKindWithJokers_thenTypeOK() {
        List<CamelCardHand> hands = Arrays.asList(new CamelCardHand("T55J5", true),
                new CamelCardHand("KTJJT", true),
                new CamelCardHand("QQQJA", true));

        hands.forEach(cardHand -> {
            Assertions.assertFalse(cardHand.isFiveOfKind());
            Assertions.assertTrue(cardHand.isFourOfKind());
            Assertions.assertFalse(cardHand.isFullHouse());
            Assertions.assertFalse(cardHand.isThreeOfKind());
            Assertions.assertFalse(cardHand.isTwoPairs());
            Assertions.assertFalse(cardHand.isOnePair());
        });
    }

    @Test
    public void whenMultipleHandsWithJokers_thenScoresOK() {
        List<Integer> expectedResults = Arrays.asList(765, 56, 2052, 1932, 1100);
        List<Integer> results = new ArrayList<>();
        List<CamelCardHand> multipleHands = Arrays.asList(
                new CamelCardHand("32T3K", 765, true),
                new CamelCardHand("T55J5", 684, true),
                new CamelCardHand("KK677", 28, true),
                new CamelCardHand("KTJJT", 220, true),
                new CamelCardHand("QQQJA", 483, true)
        );

        Collections.sort(multipleHands);

        for (int rank=1; rank<=multipleHands.size(); rank++) {
            results.add(rank*(multipleHands.get(rank-1).getBid()));
        }

        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenTwoHandsWithJokers_thenComparisonOK() {
        CamelCardHand card1 = new CamelCardHand("JKKK2", 765, true);
        CamelCardHand card2 = new CamelCardHand("QQQQ2", 684, true);

        Assertions.assertEquals(true, card1.compareTo(card2) < 0);
    }

    @Test
    public void whenMultipleHandsWithJokers_thenRanksOK() {
        List<String> expectedResults = Arrays.asList("K36QA", "K543T", "AT68Q", "J9427", "65K2J",
                "KK274", "TQ5TT", "TQQJ2", "JKTTJ", "55Q55");
        List<String> results = new ArrayList<>();
        List<CamelCardHand> multipleHands = Arrays.asList(
                new CamelCardHand("TQ5TT", 421, true),
                new CamelCardHand("65K2J", 973, true),
                new CamelCardHand("K543T", 50, true),
                new CamelCardHand("AT68Q", 952, true),
                new CamelCardHand("JKTTJ", 766, true),
                new CamelCardHand("55Q55", 860, true),
                new CamelCardHand("J9427", 977, true),
                new CamelCardHand("K36QA", 569, true),
                new CamelCardHand("TQQJ2", 710, true),
                new CamelCardHand("KK274", 900, true)
        );

        Collections.sort(multipleHands);

        multipleHands.stream().forEach(h -> results.add(h.getHand()));

        Assertions.assertEquals(expectedResults, results);
    }

}
