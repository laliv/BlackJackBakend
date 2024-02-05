package no.liv1.cardgames.blackjack;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public final class Card {

    private Enum cardType;
    private String value;

    public Card(Enum cardType, String value){
        this.cardType = cardType;
        this.value = value;
    }

    public Enum getCardType() {
        return cardType;
    }

    public String getValue() {
        return value;
    }


    public String toString() {
        return cardType + " " + value;
    }
}
