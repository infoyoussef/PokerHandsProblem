import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandComparatorParameterizedTest {

    @ParameterizedTest
    @CsvSource(value = {
            "2H, 3D, 5S, 9C, KD; 2C, 3H, 4S, 8C, AH; White; White wins. - with high card: Ace",
            "2H, 4S, 4C, 2D, 4H; 2S, 8S, AS, QS, 3S; Black; Black wins. - with full house: 4 over 2",
            "2H, 3D, 5S, 9C, KD; 2C, 3H, 4S, 8C, KH, Black; Black wins. - with high card: 9",
            "2H, 3D, 5S, 9C, KD; 2D, 3H, 5C, 9S, KH; Tie; Tie."
    }, delimiter = ';')
    public void testCompareHands(String blackCards, String whiteCards, String expectedWinner, String expectedMessage) {
        PokerHand blackHand = new PokerHand(blackCards.split(", "));
        PokerHand whiteHand = new PokerHand(whiteCards.split(", "));

        String actualMessage = PokerHandComparator.compareHandsAndGetMessage(blackHand, whiteHand);

        assertEquals(expectedMessage, actualMessage);
    }
}
