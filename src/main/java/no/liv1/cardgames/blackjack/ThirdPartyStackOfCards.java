package no.liv1.cardgames.blackjack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import java.sql.Array;
import java.util.Arrays;

@Component
public class ThirdPartyStackOfCards implements ValidateJsonContent {

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyStackOfCards.class);

    @Autowired
    BlackJackProperties env;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public Quote[] getJson(RestTemplate restTemplate) {
        //log.info(env.getShuffleUrl());
        try {
            Quote[] quote = restTemplate.getForObject(
                    "https://blackjack.ekstern.dev.nav.no/shuffle", Quote[].class);
            log.info(Arrays.toString(quote));
            return quote;
        } catch(Exception e) {
            e.getMessage();
        }
        return new Quote[0];
    }

    @Override
    public boolean hasFifitwoCards() {
        return false;
    }

    @Override
    public boolean hasDistinctCards() {
        return false;
    }
}
