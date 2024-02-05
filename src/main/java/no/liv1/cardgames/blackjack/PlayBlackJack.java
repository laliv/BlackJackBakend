package no.liv1.cardgames.blackjack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class PlayBlackJack {



    private GameOfBlackJack game = new GameOfBlackJack();

    @RequestMapping(value = "/showCards", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/showCards")
    public String showCards() {
        return game.showDealerCards();//String.format("%s", dealerStack);
    }

    @RequestMapping(value = "/dealCards", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/dealCards")
    public String[] dealCards() {
        return game.dealCardsToPlayer();
        //return String.format("%s", new StackOfCardsConverter().makeDealerStack());
    }

    @RequestMapping(value = "/dealInitialCards", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/dealInitialCards")
    public String dealInitialCards() {
        return game.dealInitial();
        //return String.format("%s", new StackOfCardsConverter().makeDealerStack());
    }

    @RequestMapping(value = "/hit", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/hit")
    public String hit() {
        return game.dealInitial();
        //return String.format("%s", new StackOfCardsConverter().makeDealerStack());
    }

    @RequestMapping(value = "/handOfTheDealer", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/handOfTheDealer")
    public String handOfTheDealer() {
        return game.dealersHand();
        //return String.format("%s", new StackOfCardsConverter().makeDealerStack());
    }

    @RequestMapping(value = "/playRound", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/playRound")
    public String playRound() {
        game.playRound();
        return "";
        //return String.format("%s", new StackOfCardsConverter().makeDealerStack());
    }

}
