package no.liv1.cardgames.blackjack;

import java.util.ArrayList;

public class Player {
    protected ArrayList<Card> cards = new ArrayList();

    protected String name;
    protected int sumOfCards = 0;

    private boolean isExhausted = false;
    public Player(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card c) {
        this.cards.add(c);
        addPlayersCount(c);
    }

    public Card pollCard() {
        Card card = cards.get(0);
        cards.remove(0);
        cards.trimToSize();
        return card;
    }

    public int getSumOfCards(){
        return sumOfCards;
    }

    public void addPlayersCount(Card card) {
        if(card.getValue().equals(BigCard.J.toString())){
            sumOfCards += BigCard.J.getNumber();
        } else if(card.getValue().equals(BigCard.Q.toString())){
            sumOfCards += BigCard.Q.getNumber();
        } else if(card.getValue().equals(BigCard.K.toString())){
            sumOfCards += BigCard.K.getNumber();
        } else if(card.getValue().equals(BigCard.A.toString())){
            sumOfCards += BigCard.A.getNumber(); //generateAceValue(sumOfCards);
        } else {
            sumOfCards += Integer.parseInt(card.getValue());
        }
    }

    private static int generateAceValue(int countType){
        if(countType + BigCard.A.getNumber() > BlackJack.BLACK_JACK){
            countType += BlackJack.LOW_ACE_VALUE;
        } else {
            countType += BigCard.A.getNumber();
        }
        return countType;
    }


    public String showCards() {
        return cards.toString();
    }

    public void resetPlayer() {
        cards = new ArrayList();
        isExhausted = false;
        sumOfCards = 0;
    }

    public void setExhausted() {
        this.isExhausted = true;
    }

    @Override
    public String toString() {
        return this.name + " " + this.sumOfCards + " " + cards.toString();
    }

}

