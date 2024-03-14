package no.liv1.cardgames.blackjack.rest;

import no.liv1.cardgames.blackjack.game.GameOfBlackJack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PlayBlackJack {

    @Autowired
    private GameOfBlackJack game;

    @RequestMapping(value = "/showcards",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String showCards() {
        return game.showDealerCards();//String.format("%s", dealerStack);
    }

    @RequestMapping(value = "/dealcards",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String dealCards() {
        return game.dealCardsToPlayers();
    }

    @RequestMapping(value = "/dealinitial",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String dealInitialCards() {
        return game.initialRound();
    }

    @RequestMapping(value = "/hit",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String hit(@RequestParam("playerName")String playerName) {
        return game.hit(playerName);
    }

    @RequestMapping(value = "/handofthedealer",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String handOfTheDealer() {
        return game.dealersHand();
    }

}
