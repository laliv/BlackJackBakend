package no.liv1.cardgames.blackjack.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class BlackJack {

    public static final int BLACK_JACK = 21;
    public static final int LOW_ACE_VALUE = 1;
    public static int dealersCount = 0;
    public static int playersCount = 0;


    public String blackJackResult;


    public static boolean isBusted(int count){
        return count > BLACK_JACK;
    }

    public boolean isBlackJack(){
        return playersCount == BLACK_JACK;
    }

    private String jsonSerialize(Player p) {
        try {
            ObjectMapper om = new ObjectMapper();
            String o = om.writeValueAsString(p);
            return o;
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        return "Unable to convert to json";
    }

    public String getBlackJackResult() {
        return blackJackResult;
    }

    public void updateBlackJackResult(Player p, ArrayList<Card> dealersHand) {

        this.blackJackResult += jsonSerialize(p);
    }

    private static int generateAceValue(int countType){
        if(countType + BigCard.A.getNumber() > BLACK_JACK){
            countType += LOW_ACE_VALUE;
        } else {
            countType += BigCard.A.getNumber();
        }
        return countType;
    }
}
