package no.liv1.cardgames.blackjack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String suit, String value) {

    public String toString(){
        return suit + " = " + value;
    }
}