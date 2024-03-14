package no.liv1.cardgames.blackjack.game;

import no.liv1.cardgames.blackjack.external.StackOfCardsConverter;

import java.util.*;

public class DealerStack {

    public ArrayList<Card> cards;

    public DealerStack(String file, String url){
        this.cards = new StackOfCardsConverter(file,url).makeDealerStack();
    }

    public Card pollCard() {
       Card card = cards.remove(0);
       cards.trimToSize();
       return card;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}

