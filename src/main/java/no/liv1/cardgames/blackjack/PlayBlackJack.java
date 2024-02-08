package no.liv1.cardgames.blackjack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


@RestController
public class PlayBlackJack {

    @Autowired
    private GameOfBlackJack game;

    @RequestMapping(value = "/showCards", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String showCards() {
        return game.showDealerCards();//String.format("%s", dealerStack);
    }

    @RequestMapping(value = "/dealCards", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String dealCards() {
        return game.dealCardsToPlayer();
    }

    @RequestMapping(value = "/dealInitialCards", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String dealInitialCards() {
        return game.dealInitial();
    }

    @RequestMapping(value = "/hit", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String hit() {
        return game.dealCardsToPlayer();
    }

    @RequestMapping(value = "/handOfTheDealer", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String handOfTheDealer() {
        return game.dealersHand();
    }

    @RequestMapping(value = "/playRound", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String playRound() {
        return game.playRound();
    }

}
