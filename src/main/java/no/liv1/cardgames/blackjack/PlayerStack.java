package no.liv1.cardgames.blackjack;

import org.springframework.stereotype.Component;

import java.util.ArrayList;


public class PlayerStack {

    public ArrayList<Card> cards = new ArrayList<>();

   // public PlayerStack(){}
    public PlayerStack(Card card){
        cards.add(card);
     //   this.cards.add(card);
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
 /*   public int getSumOfCards(){
        return sumOfCards;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
*/

    public void add(Card card) {
        this.cards.add(card);
    }
}

