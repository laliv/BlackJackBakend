package no.liv1.cardgames.blackjack;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ThirdPartyStackOfCardsTest {

    private final int EXPECTED_NUMBER_OF_CARDS = 52;

    @Autowired
    private ThirdPartyStackOfCards stackOfCards;

    @Test
    public void getCardsFromRemote() {
        Quote[] cards = stackOfCards.getJson(stackOfCards.restTemplate(new RestTemplateBuilder()));
        assertTrue(cards.length == EXPECTED_NUMBER_OF_CARDS);
    }
}
