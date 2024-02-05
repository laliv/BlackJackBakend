package no.liv1.cardgames.blackjack;

import java.util.*;

public class DealerStack {

    public ArrayList<Card> cards;

    public DealerStack(ArrayList<Card> card){
        this.cards = card;
    }

    public Card pollCard() {
       Card card = cards.remove(0);
       cards.trimToSize();
       return card;
    }


}

