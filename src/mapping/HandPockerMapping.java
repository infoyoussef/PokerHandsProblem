package mapping;

import model.Card;
import model.HandPocker;

import java.util.ArrayList;
import java.util.List;

public class HandPockerMapping {

    public static HandPocker stringToModel(String input) {

        String[] splitedString = input.split(": ");

        String name = splitedString[0];
        List<Card> cards = getListCards(splitedString[0]);

        return new HandPocker( cards, name);
    }
    private static List<Card> getListCards(String cardsString) {
        String[] splitedString = cardsString.split(" ");
        List<Card> cards = new ArrayList<>();
        for (String card : splitedString) {
            cards.add(new Card(card.charAt(0), card.charAt(1)));
        }
        return cards;
    }




}
