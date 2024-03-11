import java.util.List;

public class PokerHandComparator {

    public static int compareHands(PokerHand hand1, PokerHand hand2) {
        PokerHand.HandRank rank1 = hand1.evaluateHand();
        PokerHand.HandRank rank2 = hand2.evaluateHand();

        if (rank1.ordinal() < rank2.ordinal()) {
            return 1;
        } else if (rank1.ordinal() > rank2.ordinal()) {
            return -1;
        } else {
            List<Card> cards1 = hand1.getCards();
            List<Card> cards2 = hand2.getCards();

            for (int i = cards1.size() - 1; i >= 0; i--) {
                if (cards1.get(i).getValue() > cards2.get(i).getValue()) {
                    return 1;
                } else if (cards1.get(i).getValue() < cards2.get(i).getValue()) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public static String compareHandsAndGetMessage(PokerHand hand1, PokerHand hand2) {
        int result = compareHands(hand1, hand2);
        if (result > 0)
            return "Black wins. - with " + hand1.evaluateHand();
        else if (result < 0)
            return "White wins. - with " + hand2.evaluateHand();
        else
            return "Tie.";
    }
}