package no.liv1.cardgames.blackjack.external;

import no.liv1.cardgames.blackjack.game.CardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Utils {

    public static HashMap<String,ArrayList<String>> getVerifierStack(){
        final ArrayList<String> tempList = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","J","Q","K","A"));
        HashMap<String,ArrayList<String>> cardNumbers = new HashMap<String,ArrayList<String>>();
        cardNumbers.put(CardType.CLUBS.getCardType(), (ArrayList<String>)tempList.clone());
        cardNumbers.put(CardType.SPADES.getCardType(), (ArrayList<String>)tempList.clone());
        cardNumbers.put(CardType.HEARTS.getCardType(), (ArrayList<String>)tempList.clone());
        cardNumbers.put(CardType.DIAMONDS.getCardType(), (ArrayList<String>)tempList.clone());
        return cardNumbers;
    }
}
