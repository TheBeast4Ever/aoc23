package org.beast4ever.aoc.aoc2k23.day07;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CamelCardHand implements Comparable<CamelCardHand> {
    public static enum TYPES {
        FIVE_OF_KINDS("5oK"),
        FOUR_OF_KINDS("4oK"),
        FULL_HOUSE("FH"),
        THREE_OF_KINDS("3oK"),
        TWO_PAIRS("2P"),
        ONE_PAIR("1P"),
        HIGH_CARD("HC");

        String code() {
            return code;
        }

        private final String code;

        TYPES(String code) {
            this.code = code;
        }
    }

    private static final Map<String, Character> encodingTable;
    static {
        encodingTable = new HashMap<>();
        encodingTable.put(TYPES.FIVE_OF_KINDS.code(), 'g');
        encodingTable.put(TYPES.FOUR_OF_KINDS.code(), 'f');
        encodingTable.put(TYPES.FULL_HOUSE.code(), 'e');
        encodingTable.put(TYPES.THREE_OF_KINDS.code(), 'd');
        encodingTable.put(TYPES.TWO_PAIRS.code(), 'c');
        encodingTable.put(TYPES.ONE_PAIR.code(), 'b');
        encodingTable.put(TYPES.HIGH_CARD.code(), 'a');
        encodingTable.put("A", 'E');
        encodingTable.put("K", 'D');
        encodingTable.put("Q", 'C');
        encodingTable.put("J", 'B');
        encodingTable.put("T", 'A');
        encodingTable.put("9", '9');
        encodingTable.put("8", '8');
        encodingTable.put("7", '7');
        encodingTable.put("6", '6');
        encodingTable.put("5", '5');
        encodingTable.put("4", '4');
        encodingTable.put("3", '3');
        encodingTable.put("2", '2');
    }

    public String getHand() {
        return hand;
    }

    private String hand;

    public Integer getBid() {
        return bid;
    }

    private Integer bid;

    private String type;

    public String getEncodedHand() {
        return encodedHand;
    }

    private String encodedHand;

    public CamelCardHand(String hand) {
        this.hand = hand;
        this.type = encodeType(false);
        this.encodedHand = encode(false);
    }

    public CamelCardHand(String hand, Boolean withJokers) {
        this.hand = hand;
        this.type = encodeType(withJokers);
        this.encodedHand = encode(withJokers);
    }

    public CamelCardHand(String hand, Integer bid) {
        this.hand = hand;
        this.type = encodeType(false);
        this.encodedHand = encode(false);
        this.bid = bid;
    }

    public CamelCardHand(String hand, Integer bid, Boolean withJokers) {
        this.hand = hand;
        this.type = encodeType(withJokers);
        this.encodedHand = encode(withJokers);
        this.bid = bid;
    }

    public String encode(Boolean withJokers) {
        StringBuilder sb = new StringBuilder();
        sb.append(encodingTable.get(type));
        for(int i=0; i<hand.length(); i++) {
            String key = "" + hand.charAt(i);
            if (key.equals("J") && withJokers) {
                sb.append('1');
            } else {
                sb.append(encodingTable.get(key));
            }

        }
        return sb.toString();
    }

    private String encodeType(Boolean withJokers) {
        String localType;
        if (withJokers) {
            localType = getBestType();
        } else {
            if (evaluateFiveOfKind()) {
                localType = TYPES.FIVE_OF_KINDS.code();
            } else if (evaluateFourOfKind()) {
                localType = TYPES.FOUR_OF_KINDS.code();
            } else if (evaluateFullHouse()) {
                localType = TYPES.FULL_HOUSE.code();
            } else if (evaluateThreeOfKind()) {
                localType = TYPES.THREE_OF_KINDS.code();
            } else if (evaluateTwoPair()) {
                localType = TYPES.TWO_PAIRS.code();
            } else if (evaluateOnePair()) {
                localType = TYPES.ONE_PAIR.code();
            } else if (evaluateHighCard()) {
                localType = TYPES.HIGH_CARD.code();
            } else {
                log.error("Something went wrong 1");
                localType = "unknown";
            }
        }

        return localType;
    }

    private String getBestType() {
        String localType = "";
        long jokersOccurs = hand.chars().filter(currentChar -> currentChar == 'J').count();
        if (jokersOccurs>0) {
            List<Character> distinctCardsWithoutJokers = getDistinctCards(true);
            switch((int) jokersOccurs) {
                case 1:
                    if (distinctCardsWithoutJokers.size()==1) {
                        localType = TYPES.FIVE_OF_KINDS.code();
                    } else if (distinctCardsWithoutJokers.size()==2) {
                        long card1Occurs = hand.chars().filter(currentChar -> currentChar == distinctCardsWithoutJokers.get(0)).count();
                        long card2Occurs = hand.chars().filter(currentChar -> currentChar == distinctCardsWithoutJokers.get(1)).count();
                        if (card1Occurs == card2Occurs) {
                            localType = TYPES.FULL_HOUSE.code();
                        } else {
                            localType = TYPES.FOUR_OF_KINDS.code();
                        }

                    } else if (distinctCardsWithoutJokers.size()==3) {
                        localType = TYPES.THREE_OF_KINDS.code();
                    } else {
                        localType = TYPES.ONE_PAIR.code();
                    }
                    break;
                case 2:
                    if (distinctCardsWithoutJokers.size()==1) {
                        localType = TYPES.FIVE_OF_KINDS.code();
                    } else if (distinctCardsWithoutJokers.size()==2) {
                        localType = TYPES.FOUR_OF_KINDS.code();
                    } else {
                        localType = TYPES.THREE_OF_KINDS.code();
                    }
                    break;
                case 3:
                    if (distinctCardsWithoutJokers.size()==1) {
                        localType = TYPES.FIVE_OF_KINDS.code();
                    } else {
                        localType = TYPES.FOUR_OF_KINDS.code();
                    }
                    break;
                case 4, 5:
                    localType = TYPES.FIVE_OF_KINDS.code();
                    break;
                default :
                    log.error("something went wrong 2");
            }
        } else {
            localType = encodeType(false);
        }
        return localType;
    }

    public Boolean isFiveOfKind() {
        return type.equals(TYPES.FIVE_OF_KINDS.code());
    }

    private Boolean evaluateFiveOfKind() {
        return getDistinctCards().size() == 1;
    }

    public Boolean isFourOfKind() {
        return type.equals(TYPES.FOUR_OF_KINDS.code());
    }

    private Boolean evaluateFourOfKind() {
        List<Character> distinctCards = getDistinctCards();
        boolean is4oK = false;
        if (distinctCards.size() == 2) {
            long card1Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(0)).count();
            long card2Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(1)).count();
            if (card1Occurs==4 || card2Occurs==4) {
                is4oK=true;
            }
        }
        return is4oK;
    }

    public Boolean isFullHouse() {
        return type.equals(TYPES.FULL_HOUSE.code());
    }

    private Boolean evaluateFullHouse() {
        List<Character> distinctCards = getDistinctCards();
        boolean isFH = false;
        if (distinctCards.size() == 2) {
            long card1Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(0)).count();
            long card2Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(1)).count();
            if (card1Occurs==3 || card2Occurs==3) {
                isFH=true;
            }
        }
        return isFH;
    }

    public Boolean isThreeOfKind() {
        return type.equals(TYPES.THREE_OF_KINDS.code());
    }

    private Boolean evaluateThreeOfKind() {
        List<Character> distinctCards = getDistinctCards();
        boolean is3oK = false;
        if (distinctCards.size() == 3) {
            long card1Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(0)).count();
            long card2Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(1)).count();
            long card3Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(2)).count();
            if (card1Occurs==3 || card2Occurs==3 || card3Occurs==3) {
                is3oK=true;
            }
        }
        return is3oK;
    }

    public Boolean isTwoPairs() {
        return type.equals(TYPES.TWO_PAIRS.code());
    }

    private Boolean evaluateTwoPair() {
        List<Character> distinctCards = getDistinctCards();
        boolean is2P = false;
        if (distinctCards.size() == 3) {
            long card1Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(0)).count();
            long card2Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(1)).count();
            long card3Occurs = hand.chars().filter(currentChar -> currentChar == distinctCards.get(2)).count();
            if ((card1Occurs==2 && card2Occurs==2) || (card1Occurs==2 && card3Occurs==2) || (card2Occurs==2 && card3Occurs==2)) {
                is2P=true;
            }
        }
        return is2P;
    }

    public Boolean isOnePair() {
        return type.equals(TYPES.ONE_PAIR.code());
    }

    private Boolean evaluateOnePair() {
        return getDistinctCards().size() == 4;
    }

    public Boolean isHighCard() {
        return type.equals(TYPES.HIGH_CARD.code());
    }

    private Boolean evaluateHighCard() {
        return getDistinctCards().size() == 5;
    }

    private List<Character> getDistinctCards() {
        return getDistinctCards(false);
    }

    private List<Character> getDistinctCards(Boolean withoutJokers) {
        Set<Character> origSet = new LinkedHashSet<Character>();
        List<Character> distinctCards = new ArrayList<>();
        for (int i = 0; i < hand.length(); i++) {
            if (origSet.add(hand.charAt(i)) && (!withoutJokers || hand.charAt(i) != 'J')) {
                distinctCards.add(hand.charAt(i));
            }
        }

        return distinctCards;
    }

    @Override
    public int compareTo(CamelCardHand otherCard) {
        return this.getEncodedHand().compareTo(otherCard.getEncodedHand());
    }

}
