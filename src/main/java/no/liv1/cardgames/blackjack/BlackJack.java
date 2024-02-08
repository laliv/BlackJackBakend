package no.liv1.cardgames.blackjack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

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

    public void updateBlackJackResult(Player p) {
        this.blackJackResult += jsonSerialize(p);
    }

    public static Card addPlayersCount(Card card) {
        if(card.getValue().equals(BigCard.J.toString())){
            playersCount += BigCard.J.getNumber();
        } else if(card.getValue().equals(BigCard.Q.toString())){
            playersCount += BigCard.Q.getNumber();
        } else if(card.getValue().equals(BigCard.K.toString())){
            playersCount += BigCard.K.getNumber();
        } else if(card.getValue().equals(BigCard.A.toString())){
            playersCount = generateAceValue(playersCount);
        } else {
            playersCount += Integer.parseInt(card.getValue());
        }
        return card;
    }

    public static int getPlayersCount(Card card) {
        if(card.getValue().equals(BigCard.J.toString())){
            playersCount += BigCard.J.getNumber();
        } else if(card.getValue().equals(BigCard.Q.toString())){
            playersCount += BigCard.Q.getNumber();
        } else if(card.getValue().equals(BigCard.K.toString())){
            playersCount += BigCard.K.getNumber();
        } else if(card.getValue().equals(BigCard.A.toString())){
            playersCount = generateAceValue(playersCount);
        } else {
            playersCount += Integer.parseInt(card.getValue());
        }
        return playersCount;
    }

    public static Card addDealersCount(Card card) {
        if(card.getValue().equals(BigCard.J.toString())){
            dealersCount += BigCard.J.getNumber();
        } else if(card.getValue().equals(BigCard.Q.toString())){
            dealersCount += BigCard.Q.getNumber();
        } else if(card.getValue().equals(BigCard.K.toString())){
            dealersCount += BigCard.K.getNumber();
        } else if(card.getValue().equals(BigCard.A.toString())){
            dealersCount = generateAceValue(dealersCount);
        } else {
            dealersCount += Integer.parseInt(card.getValue());
        }
        return card;
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
