package model;

import java.util.List;

public class HandPocker {

    List<Card> cards;
    String namePlayer;

    public HandPocker(List<Card> cards, String namePlayer) {
        this.namePlayer = namePlayer;
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }


    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

}
