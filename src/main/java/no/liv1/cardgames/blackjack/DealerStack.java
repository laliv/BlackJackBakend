package no.liv1.cardgames.blackjack;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class DealerStack {

    public ArrayList<Card> cards;

    public DealerStack(String shuffleUrl){
        this.cards = new StackOfCardsConverter(shuffleUrl).makeDealerStack();
    }

    public Card pollCard() {
       Card card = cards.remove(0);
       cards.trimToSize();
       return card;
    }


}

