package no.liv1.cardgames.blackjack.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.liv1.cardgames.blackjack.game.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class ThirdPartyStackOfCards implements ValidateJsonContent {

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyStackOfCards.class);
    private String file;
    private String url;

    @Autowired
    public ThirdPartyStackOfCards(String file, String url){
        this.file = file;
        this.url = url;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public ArrayList<Card> getJsonFromUrl() {
        var restTemplate = new RestTemplate(new RestTemplateBuilder().buildRequestFactory());
        ArrayList<Card> cards = new ArrayList<>();
        try {
            Card[] cardsArray = restTemplate.getForObject(
                    url, Card[].class);
            if(!hasFifitwoCards(cardsArray)){
                log.error("INVALID NUMBER OF CARDS!!");
            }
            if(!hasDistinctCards(cardsArray)) {
                log.error("NOT DISTINCT CARDS!!");
            }
            return (ArrayList<Card>) Arrays.asList(cardsArray);
        } catch(Exception e) {
            e.getMessage();
        } finally {
            return cards;
        }
    }

    public ArrayList<Card> getJsonFromFile() {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Card[] cardsArray = mapper.readValue(new File(file), Card[] .class);
            if(!hasFifitwoCards(cardsArray)){
                log.error("INVALID NUMBER OF CARDS!!");
            }
            if(!hasDistinctCards(cardsArray)) {
                log.error("NOT DISTINCT CARDS!!");
            }
            cards = new ArrayList<>(Arrays.asList(cardsArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards;
    }

    @Override
    public boolean hasFifitwoCards(Card[] cards) {
        return cards.length == ValidateJsonContent.EXPECTED_NUMBER_OF_CARDS;
    }

    @Override
    public boolean hasDistinctCards(Card[] cards) {
        HashMap<String,ArrayList<String>> cardNumbers = Utils.getVerifierStack();
        ArrayList<Card> cardsList = new ArrayList<Card>(Arrays.asList(cards));
        for (Card c : cardsList) {
            if (cardNumbers.containsKey(c.getCardType())) {
                var list = cardNumbers.get(c.getCardType());
                if (list.contains(c.getValue())) {
                    list.remove(c.getValue());
                } else {
                 log.error(String.format("NOT DISTINCT NUMBER OF CARDS. THE SOURCE STACK CONTAINS MORE THAN ONE %s !!", c.getCardType() + " " +c.getValue()));
                }
            }
        }
        return true;
    }
}