package no.liv1.cardgames.blackjack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@EnableConfigurationProperties(BlackJackProperties.class)
public class GameOfBlackJack {
    public static int NUMBER_OF_PLAYERS = 2;
    private static int POINT_OF_STOP = 17;
    public String WINNER;
    private boolean waitingForAWinner = true;
    private HashMap<String, String> outputMap = new HashMap();
    private DealerStack dealerStack;
    private ArrayList<Card> dealerCardsOnTable = new ArrayList<>();
    private ArrayList<Player> players;
    private BlackJackProperties properties;

    @Autowired
    public GameOfBlackJack(BlackJackProperties properties) {
        this.properties = properties;
        dealerStack = new DealerStack(properties.getShuffleUrl());
        players = (ArrayList) Arrays.asList(properties.getPlayers()).stream().map(name -> new Player(name)).collect(Collectors.toList());
    }

    public String showDealerCards() {
        return dealerStack.toString();
    }

    public String dealersHand() {
        Card card = dealerStack.pollCard();
        dealerCardsOnTable.add(card);
        return String.format("Dealer | %s", dealerCardsOnTable);
    }

    public String dealCardsToPlayer() {
        for (Player player : players) {
            player.addCard(dealerStack.pollCard());
            outputMap.put(player.name, String.format("%s | %s | %s\n", player.name, player.getSumOfCards(), player.showCards()));
            System.out.println(outputMap.get(player.name));
            if (player.getSumOfCards() >= POINT_OF_STOP && player.getSumOfCards() <= BlackJack.BLACK_JACK) {
                player.setExhausted();
            }
            evaluateWinner(player);
            if (!waitingForAWinner) {
                break;
            }
        }
        return outputMap.values().stream().toString();
    }

    public String playRound() {
        dealInitial();
        while (waitingForAWinner) {
            dealCardsToPlayer();
        }
        System.out.println("Round finished");
        resetGame();
        return Arrays.toString(outputMap.values().toArray());
    }

    private void resetGame(){
        dealerStack = new DealerStack(properties.getShuffleUrl());
        players.forEach(player -> player.resetPlayer());
    }

    public String dealInitial() {
        for (Player player : players) {
            player.addCard(dealerStack.pollCard());
            player.addCard(dealerStack.pollCard());
            outputMap.put(player.name, String.format("%s | %s | %s\n", player.name, player.getSumOfCards(), player.showCards()));
            System.out.println(outputMap.get(player.name));
            if (player.getSumOfCards() >= POINT_OF_STOP && player.getSumOfCards() <= BlackJack.BLACK_JACK) {
                continue;
            }
            evaluateWinner(player);
            if (!waitingForAWinner) {
                break;
            }
        }
        return outputMap.toString();
    }

    private void evaluateWinner(Player aboutToloos) {
        if (BlackJack.isBusted(aboutToloos.getSumOfCards())) {
            final String looser = aboutToloos.name;
            // WORKS ONLY WITH TWO PLAYERS
            WINNER = players.stream().filter(player -> !player.name.equals(looser)).findFirst().get().name;
            System.out.println("WINNER : " + WINNER);
            waitingForAWinner = false;
        }
    }
}
