package no.liv1.cardgames.blackjack.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Card {

    private String cardType;
    private String value;

    public Card(@JsonProperty("suit")String cardType, @JsonProperty("value")String value){
        this.cardType = cardType;
        this.value = value;
    }

    @Override
    public String toString() {
        return cardType + " " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Card)) {
            return false;
        }

        Card c = (Card) obj;
        return c.getCardType().equals(this.cardType) && c.getValue().equals(this.getValue());
    }

}
