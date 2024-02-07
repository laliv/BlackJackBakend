package no.liv1.cardgames.blackjack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class ThirdPartyStackOfCards implements ValidateJsonContent {

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyStackOfCards.class);
    private String shuffleUrl;

    @Autowired
    public ThirdPartyStackOfCards(String shuffleUrl){
        this.shuffleUrl = shuffleUrl;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public Quote[] getJson(RestTemplate restTemplate) {
        try {
            Quote[] quote = restTemplate.getForObject(
                    shuffleUrl, Quote[].class);
            log.info(Arrays.toString(quote));
            if(!hasFifitwoCards(quote)){
                log.error("INVALID NUMBER OF CARDS!!");
            }
            return quote;
        } catch(Exception e) {
            e.getMessage();
        }
        return new Quote[0];
    }

    @Override
    public boolean hasFifitwoCards(Quote[] q) {
        return q.length == ValidateJsonContent.EXPECTED_NUMBER_OF_CARDS;
    }

}
