package no.liv1.cardgames.blackjack;

public interface ValidateJsonContent {

    public static final int EXPECTED_NUMBER_OF_CARDS = 52;

    boolean hasFifitwoCards(Quote[] q);
}
