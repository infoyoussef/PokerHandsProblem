public class Card implements Comparable<Card> {
    private int value;
    private char suit;

    public Card(String cardString) {
        value = parseValue(cardString.charAt(0));
        suit = cardString.charAt(1);
    }

    private int parseValue(char valueChar) {
        if (Character.isDigit(valueChar)) {
            return Character.getNumericValue(valueChar);
        } else {
            switch (valueChar) {
                case 'T':
                    return 10;
                case 'J':
                    return 11;
                case 'Q':
                    return 12;
                case 'K':
                    return 13;
                case 'A':
                    return 14;
                default:
                    throw new IllegalArgumentException("Invalid card value");
            }
        }
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.value, other.value);
    }
}