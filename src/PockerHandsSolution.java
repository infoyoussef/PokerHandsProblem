import mapping.HandPockerMapping;
import model.Card;
import model.HandPocker;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static String compareHandPocker(HandPocker black, HandPocker white){


        String blackRank = getRankForPlayer(black);
        String whiteRank = getRankForPlayer(white);

        return "";
    }

    private static String getRankForPlayer(HandPocker player) {
        //Straight flush: 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.
        if (isStraightFlush(player.getCards()) ) {
            return "Straight Flush";
        }


        //Four of a kind: 4 cards with the same value.
        // Ranked by the value of the 4 cards.
        else if (isFourOfAKind(player.getCards()) ) {
            return "Four of a Kind";



            //Full House: 3 cards of the same value, with the remaining 2 cards forming a pair.
            // Ranked by the value of the 3 cards
        } else if (isFullHouse(player.getCards()) ) {
            return "Full House";


            //Flush: Hand contains 5 cards of the same suit.
            // Hands which are both flushes are ranked using the rules for High model.Card.
        } else if (isFlush(player.getCards()) ) {
            return "Flush";



            //Straight: Hand contains 5 cards with consecutive values.
            // Hands which both contain a straight are ranked by their highest card.
        } else if (isStraight(player.getCards()) ) {
            return "Straight";



            //Three of a Kind: Three of the cards in the hand have the same value.
            // Hands which both contain three of a kind are ranked by the value of the 3 cards.
        } else if (isThreeOfAKind(player.getCards()) ) {
            return "Three of a Kind";

            //Two Pairs: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair.
            // Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
        } else if (isTwoPair(player.getCards()) ) {
            return "Two Pair";



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

    private static boolean isStraightFlush(List<Card> cards) {
        char suit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }


    private static boolean isFourOfAKind(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.containsValue(4);
    }


    private static boolean isFullHouse(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.containsValue(3) && rankCounts.containsValue(2);
    }

    private static boolean isFlush(List<Card> cards) {
        char suit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }


    private static boolean isTwoPair(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);
        int pairCount = 0;
        for (int count : rankCounts.values()) {
            if (count == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

    private static boolean isOnePair(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.containsValue(2);
    }

    private static boolean isStraight(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() + 1 != cards.get(i + 1).getValue()) {
                return false;
            }
        }
        return true;
    }



    private static boolean isThreeOfAKind(List<Card> cards) {
        Map<Character, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.containsValue(3);
    }

    private static Map<Character, Integer> getRankCounts(List<Card> hand) {
        Map<Character, Integer> rankCounts = new HashMap<>();
        for (Card card : hand) {
            char value = card.getValue();
            rankCounts.put(value, rankCounts.getOrDefault(value, 0) + 1);
        }
        return rankCounts;
    }

    static int getHighCardResultsForValues(HandPocker black, HandPocker white) {
        int index = black.getCards().size() - 1;
        while (index >= 0) {
            if (black.getCards().get(index).getValue() > white.getCards().get(index).getValue()) {
                black.getCards().get(index).getValue();
            } else if (black.getCards().get(index).getValue() < white.getCards().get(index).getValue()) {
                return white.getCards().get(index).getValue();
            }
            index--;
        }
        return 0;
    }
}