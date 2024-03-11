import java.util.*;

public class PokerHand {
    public enum HandRank {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH
    }

    private List<Card> cards;

    public PokerHand(String[] cardStrings) {
        cards = new ArrayList<>();
        for (String cardString : cardStrings) {
            cards.add(new Card(cardString));
        }
        Collections.sort(cards);
    }

    public HandRank evaluateHand() {
        if (isStraightFlush()) return HandRank.STRAIGHT_FLUSH;
        if (isFourOfAKind()) return HandRank.FOUR_OF_A_KIND;
        if (isFullHouse()) return HandRank.FULL_HOUSE;
        if (isFlush()) return HandRank.FLUSH;
        if (isStraight()) return HandRank.STRAIGHT;
        if (isThreeOfAKind()) return HandRank.THREE_OF_A_KIND;
        if (isTwoPair()) return HandRank.TWO_PAIR;
        if (isPair()) return HandRank.ONE_PAIR;
        return HandRank.HIGH_CARD;
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isFourOfAKind() {
        for (int i = 0; i <= cards.size() - 4; i++) {
            if (cards.get(i).getValue() == cards.get(i + 1).getValue() &&
                    cards.get(i + 1).getValue() == cards.get(i + 2).getValue() &&
                    cards.get(i + 2).getValue() == cards.get(i + 3).getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse() {
        return (cards.get(0).getValue() == cards.get(1).getValue() && cards.get(1).getValue() == cards.get(2).getValue() &&
                cards.get(3).getValue() == cards.get(4).getValue()) ||
                (cards.get(0).getValue() == cards.get(1).getValue() &&
                        cards.get(2).getValue() == cards.get(3).getValue() && cards.get(3).getValue() == cards.get(4).getValue());
    }

    private boolean isFlush() {
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() != cards.get(0).getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() != cards.get(i + 1).getValue() - 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind() {
        for (int i = 0; i <= cards.size() - 3; i++) {
            if (cards.get(i).getValue() == cards.get(i + 1).getValue() &&
                    cards.get(i + 1).getValue() == cards.get(i + 2).getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair() {
        int pairsCount = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() == cards.get(i + 1).getValue()) {
                pairsCount++;
                i++;
            }
        }
        return pairsCount == 2;
    }

    private boolean isPair() {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() == cards.get(i + 1).getValue()) {
                return true;
            }
        }
        return false;
    }

    public List<Card> getCards() {
        return cards;
    }
}