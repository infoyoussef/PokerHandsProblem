import mapping.HandPockerMapping;
import model.Card;
import model.HandPocker;
import model.Rank;

import java.util.*;

public class PockerHandsSolution {




        //A poker deck contains 52 cards - each card has a suit which is one of clubs, diamonds, hearts, or spades (denoted C, D, H, and S in the input data).

         //For scoring purposes, the suits are unordered while the values are ordered as given above, with 2 being the lowest and ace the highest value.

        //A poker hand consists of 5 cards dealt from the deck. Poker hands are ranked by the following partial order from lowest to highest.

    //Your job is to compare several pairs of poker hands and to indicate which, if either, has a higher rank.
    public static String compareHandPocker(String black, String white){

        HandPocker blackHand = HandPockerMapping.stringToModel(black);
        HandPocker whiteHand = HandPockerMapping.stringToModel(white);

        String result = compareHandPocker(blackHand, whiteHand);

        return result;
    }
    public  String compareHandPocker(HandPocker black, HandPocker white){


        Rank blackRank = getRankForPlayer(black);
        Rank whiteRank = getRankForPlayer(white);

        return "";
    }

    private static Rank getRankForPlayer(HandPocker player) {
        //Straight flush: 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.
        if (isStraightFlush(player.getCards()).isExistence() ) {
            return isStraightFlush(player.getCards());
        }


        //Four of a kind: 4 cards with the same value.
        // Ranked by the value of the 4 cards.
        else if (isFourOfAKind(player.getCards()).isExistence() ) {
            return isFourOfAKind(player.getCards());
        }

        //Full House: 3 cards of the same value, with the remaining 2 cards forming a pair.
        // Ranked by the value of the 3 cards
        else if (isFullHouse(player.getCards()).isExistence() ) {
            return isFullHouse(player.getCards());
        }



        //Flush: Hand contains 5 cards of the same suit.
        // Hands which are both flushes are ranked using the rules for High model.Card.
        else if (isFlush(player.getCards()).isExistence()) {
            return isFlush(player.getCards());



            //Straight: Hand contains 5 cards with consecutive values.
            // Hands which both contain a straight are ranked by their highest card.
        } else if (isStraight(player.getCards()).isExistence() ) {
            return isStraight(player.getCards());



            //Three of a Kind: Three of the cards in the hand have the same value.
            // Hands which both contain three of a kind are ranked by the value of the 3 cards.
        } else if (isThreeOfAKind(player.getCards()).isExistence() ) {
            return isThreeOfAKind(player.getCards());

            //Two Pairs: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair.
            // Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
        } else if (isTwoPair(player.getCards()).isExistence() ) {
            return isTwoPair(player.getCards());



            //Pair: 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the cards forming the pair.
            // If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order.
        } else if (isOnePair(player.getCards()) ) {
            return "One Pair";

            //High model.Card: Hands which do not fit any higher category are ranked by the value of their highest card. If the highest cards have the same value,
            // the hands are ranked by the next highest, and so on.
        } else {
            return "High model.Card";
        }
    }

    private static Rank isStraightFlush(List<Card> cards) {
        char suit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != suit) {
                return new Rank(false);
            }
        }
        return new Rank(9, true,"Straight Flush", List.of(getHighCardResultsForValues(cards)));
    }


    private static Rank isFourOfAKind(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);
        if (rankCounts.containsValue(4)) {
            Character value = getKey(rankCounts, 4 );
            return new Rank(8, true, "Four of a Kind", List.of(value));
        } else {
            return new Rank(false);
        }


    }


    private static Rank isFullHouse(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);

        if (rankCounts.containsValue(3) && rankCounts.containsValue(2)){
            Character value = getKey(rankCounts, 3);
            return new Rank(7, true, "Full House", List.of(value));
        } else {
            return new Rank(false);
        }


    }

    private static Rank isFlush(List<Card> cards) {
        char suit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != suit) {
                return new Rank(false);
            }
        }
        return new Rank(6, true, "Flush", List.of(getHighCardResultsForValues(cards)));
    }

    private static Rank isStraight(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() + 1 != cards.get(i + 1).getValue()) {
                return new Rank(false);
            }
        }
        return new Rank(5, true, "Straight", List.of(getHighCardResultsForValues(cards)));
    }


    private static Rank isTwoPair(List<Card> cards) {

        Map<Character, Integer> rankCounts = getRankCounts(cards);
        int pairCount = 0;

        List<Character> listOrdered = new ArrayList<>();
        Character impairs = 0;


        for (int count : rankCounts.values()) {
            if (count == 2) {
                pairCount++;
                listOrdered.add(getKey(rankCounts , count));
            } else {
                impairs = getKey(rankCounts , count);
            }
        }
        if (pairCount == 2) {
            listOrdered.add(impairs);
            return new Rank(4, true, "Two Pair", listOrdered);
        } else {
            return new Rank(false);
        }

    }

    private static boolean isOnePair(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.containsValue(2);
    }



    private static Rank isThreeOfAKind(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);

        if (rankCounts.containsValue(3)) {
            Character value = getKey(rankCounts, 3);
            return new Rank(4, true, "Three of a Kind", List.of(value));
        } else {
            return new Rank(false);
        }
    }

    private static Map<Character, Integer> getRankCounts(List<Card> cards) {
        Map<Character, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            char value = card.getValue();
            rankCounts.put(value, rankCounts.getOrDefault(value, 0) + 1);
        }
        return rankCounts;
    }

    static char getHighCardResultsForValues(List<Card> cards) {
        int index = cards.size() - 1;
        return cards.get(index).getValue();
    }

    private static Character getKey(Map<Character, Integer> rankCounts , int searchValue) {
        Character foundKey = null;

        for (Map.Entry<Character, Integer> entry : rankCounts.entrySet()) {
            if (entry.getValue().equals(searchValue)) {
                foundKey = entry.getKey();
                break;
            }
        }

        return foundKey;
    }
}