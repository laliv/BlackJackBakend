package no.liv1.cardgames.blackjack.external;

import no.liv1.cardgames.blackjack.game.Card;

import java.util.*;

public class StackOfCardsConverter implements JsonToStackOfCards {

    private String file;
    private String url;

    public StackOfCardsConverter(String file, String url){
        this.file = file;
        this.url = url;
    }

    private ArrayList<Card> loadJsonFromResource(){
        var remoteCards = new ThirdPartyStackOfCards(file,url);
        var fromUrl = remoteCards.getJsonFromUrl();
        if(!fromUrl.isEmpty()){
            return fromUrl;
        } else {
            return remoteCards.getJsonFromFile();
        }
    }

    @Override
    public ArrayList<Card> makeDealerStack() {
        return loadJsonFromResource();
    }
}
