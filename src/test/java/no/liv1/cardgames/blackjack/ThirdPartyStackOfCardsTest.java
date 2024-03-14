package no.liv1.cardgames.blackjack;

import no.liv1.cardgames.blackjack.external.ThirdPartyStackOfCards;
import no.liv1.cardgames.blackjack.game.Card;
import no.liv1.cardgames.blackjack.game.CardType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ThirdPartyStackOfCardsTest {

    private final int EXPECTED_NUMBER_OF_CARDS = 52;
    private final int EXPECTED_NUMBER_OF_EACH_CARD_TYPE = 13;

    List<String> helperCardNumbers = Arrays.asList("1","2","3","4","5","6","7","8","9","10","J","Q","K","A");

    @Autowired
    private ThirdPartyStackOfCards stackOfCards;

    @Disabled
    public void getCardsFromRemote() {
        var json = stackOfCards.getJsonFromUrl();
        assertTrue(json.size() == EXPECTED_NUMBER_OF_CARDS);
    }

    @Test
    public void hasFiftyTwoCards() {
        var cards = stackOfCards.getJsonFromFile();
        assertTrue(cards.size() == EXPECTED_NUMBER_OF_CARDS);
    }

    @Disabled
    public void hasThirteenOfEachCardType() {
        var json = stackOfCards.getJsonFromFile();
        assertTrue(json.stream().filter(card -> card.getCardType().equals(CardType.CLUBS.name())).count() == EXPECTED_NUMBER_OF_EACH_CARD_TYPE);
        assertTrue(json.stream().filter(card -> card.getCardType().equals(CardType.DIAMONDS.name())).count() == EXPECTED_NUMBER_OF_EACH_CARD_TYPE);
        assertTrue(json.stream().filter(card -> card.getCardType().equals(CardType.SPADES.name())).count() == EXPECTED_NUMBER_OF_EACH_CARD_TYPE);
        assertTrue(json.stream().filter(card -> card.getCardType().equals(CardType.HEARTS.name())).count() == EXPECTED_NUMBER_OF_EACH_CARD_TYPE);
    }

    @Test
    public void hasDistinctCards() {
        var cards = stackOfCards.getJsonFromFile();

        var clubs = cloneCards();
        assertTrue(cards.stream().
                filter(card -> card.getCardType().equals(CardType.CLUBS.name())).
                filter(card -> cardTypeHasOneToAce(card,clubs)).count() == EXPECTED_NUMBER_OF_EACH_CARD_TYPE);

        var diamonds = cloneCards();
        assertTrue(cards.stream()
                .filter(card -> card.getCardType().equals(CardType.DIAMONDS.name()))
                .filter(card -> cardTypeHasOneToAce(card,diamonds)).count() == EXPECTED_NUMBER_OF_EACH_CARD_TYPE);

        var spades = cloneCards();
        assertTrue(cards.stream()
                .filter(card -> card.getCardType().equals(CardType.SPADES.name()))
                .filter(card -> cardTypeHasOneToAce(card,spades)).count() == EXPECTED_NUMBER_OF_EACH_CARD_TYPE);

        var hearts = cloneCards();
        assertTrue(cards.stream()
                .filter(card -> card.getCardType().equals(CardType.HEARTS.name()))
                .filter(card -> cardTypeHasOneToAce(card,hearts)).count() == EXPECTED_NUMBER_OF_EACH_CARD_TYPE);
    }

    private ArrayList cloneCards() {
        ArrayList<String> cards = new ArrayList<>();
        for(String number : helperCardNumbers ){
            cards.add(number);
        }
        return cards;
    }

    private boolean cardTypeHasOneToAce(Card c, ArrayList<String> cards) {
            if( cards.contains(c.getValue()) ){
                cards.remove(c.getValue());
            } else {
                return false;
            }
            return true;
    }

}
