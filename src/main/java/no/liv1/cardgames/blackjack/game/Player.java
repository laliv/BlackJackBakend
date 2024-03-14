package no.liv1.cardgames.blackjack.game;

import lombok.Getter;
import no.liv1.cardgames.blackjack.util.Json;
import java.util.ArrayList;

@Getter
public class Player {
    private ArrayList<Card> cards = new ArrayList();

    private String name;
    private int sumOfCards = 0;

    private boolean isExhausted = false;
    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card c) {
        this.cards.add(c);
        addPlayersCount(c);
    }

    public void addPlayersCount(Card card) {
        if(card.getValue().equals(BigCard.J.toString())){
            sumOfCards += BigCard.J.getNumber();
        } else if(card.getValue().equals((BigCard.Q.toString()))){
            sumOfCards += BigCard.Q.getNumber();
        } else if(card.getValue().equals((BigCard.K.toString()))){
            sumOfCards += BigCard.K.getNumber();
        } else if(card.getValue().equals((BigCard.A.toString()))){
            sumOfCards += BigCard.A.getNumber();
        } else {
            sumOfCards += Integer.parseInt(card.getValue());
        }
    }

    public String showCards() {
        return cards.toString();
    }

    public String toJson() {
        return Json.Serialize(this);
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

