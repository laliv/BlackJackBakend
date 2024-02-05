package no.liv1.cardgames.blackjack;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;


public class DealerStack {

    public ArrayList<Card> cards;


    public DealerStack(ArrayList<Card> card){
        this.cards = card;
/*        addCardsByType(CardType.HEARTS);
        addCardsByType(CardType.DIAMONDS);
        addCardsByType(CardType.SPADES);
        addCardsByType(CardType.CLUBS);*/
        //       cards = rotate();
        //      cards = rotate();
        //     cards = rotate();
    }

    /*
    private void addCardsByType(CardType type){
        for(int i = 1; i <= maxCardTypes; i++) {
            cards.add(new Card(type, i));
        }
    }*/

  /*  private LinkedList<Card> rotate(){
        for(int i=0; i < cards.size()/2 ;i+=2) {
            Card card = cards.pollLast();
            cards.add(i, card);
        }
        return cards;
    }

*/

    public Card pollCard() {
       Card card = cards.remove(0);
       cards.trimToSize();
       return card;
    }


}

