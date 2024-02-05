package no.liv1.cardgames.blackjack;

import java.util.ArrayList;

public class Player {

    private static String[] playerName = {"Lars", "Magnus"};
    protected ArrayList<Card> cards;
    protected String name;
    protected int sumOfCards = 0;
    public Player(int name, Card card) {
        this.name = playerName[name];
        this.cards = new ArrayList();
        this.cards.add(card);
    }

    public void addCard(Card c) {
        this.cards.add(c);
        sumOfCards += addPlayersCount(c);
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

    public int addPlayersCount(Card card) {
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
        return sumOfCards;
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

    public int getNumberOfCards() {
        return cards.size();
    }

    @Override
    public String toString() {
        return cards.toString();
    }

}

