package no.liv1.cardgames.blackjack.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
public enum CardType {
    HEARTS("HEARTS"),
    SPADES("SPADES"),
    DIAMONDS("DIAMONDS"),
    CLUBS("CLUBS");

    public final String cardType;

    CardType(String cardType) {
        this.cardType = cardType;
    }
}
