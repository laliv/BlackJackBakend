package no.liv1.cardgames.blackjack;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import java.util.*;


public class StackOfCardsConverter implements JsonToStackOfCards {

    private String shuffleUrl;

    public StackOfCardsConverter(String shuffleUrl){
        this.shuffleUrl = shuffleUrl;
    }

    private Quote[] loadCardsFromRemoteUrl(){
        var remoteCards = new ThirdPartyStackOfCards(shuffleUrl);
        return remoteCards.getJson(remoteCards.restTemplate(new RestTemplateBuilder()));
    }

    @Override
    public ArrayList<Card> makeDealerStack() {
        List<Card> listOfCards = new ArrayList(List.of(loadCardsFromRemoteUrl())).stream()
                .map(quote -> makeCardFromString(quote.toString()))
                 .toList();

        return new ArrayList(listOfCards);
    }

    private Card makeCardFromString(String quote) {
        return new Card(CardType.valueOf(
                quote.substring(0,quote.indexOf("=")-1))
                ,quote.substring(quote.indexOf("=")+2));
    }
}
