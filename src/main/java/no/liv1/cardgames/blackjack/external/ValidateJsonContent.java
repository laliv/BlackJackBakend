package no.liv1.cardgames.blackjack.external;

import no.liv1.cardgames.blackjack.game.Card;

public interface ValidateJsonContent {

    public static final int EXPECTED_NUMBER_OF_CARDS = 52;

    boolean hasFifitwoCards(Card[] cards);

    boolean hasDistinctCards(Card[] cards);
}
