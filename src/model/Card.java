package model;

public class Card {

    char suit;
    char value;

    public Card(char suit, char value) {
        this.suit = suit;
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    public char getValue() {
        return value;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public void setValue(char value) {
        this.value = value;
    }



}
