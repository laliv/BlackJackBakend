package no.liv1.cardgames.blackjack.external;

import no.liv1.cardgames.blackjack.game.Card;

import java.util.ArrayList;

public interface JsonToStackOfCards {
    ArrayList<Card> makeDealerStack();
}
