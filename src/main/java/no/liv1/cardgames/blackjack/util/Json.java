package no.liv1.cardgames.blackjack.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.liv1.cardgames.blackjack.game.Card;
import no.liv1.cardgames.blackjack.game.Player;

import java.util.ArrayList;

public class Json {

    public static String Serialize(Player p) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{ \"error\": \"Failed to serialize object\"}";
        }
    }

    public static String Serialize(ArrayList<Card> cl) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(cl);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{ \"error\": \"Failed to serialize object\"}";
        }
    }
}
