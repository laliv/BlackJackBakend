package no.liv1.cardgames.blackjack.game;

import no.liv1.cardgames.blackjack.BlackJackProperties;
import no.liv1.cardgames.blackjack.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import java.util.*;

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

    private BlackJack bj = new BlackJack();

    @Autowired
    public GameOfBlackJack(BlackJackProperties properties) {
        this.properties = properties;
        dealerStack = new DealerStack(properties.getFile(), properties.getUrl());
        players = new ArrayList<>(Arrays.asList(new Player("Name1"), new Player("Name2")));
    }

    public String showDealerCards() {
        return Json.Serialize(dealerStack.getCards());
    }

    public String dealersHand() {
        Card card = dealerStack.pollCard();
        dealerCardsOnTable.add(card);
        return Json.Serialize(dealerCardsOnTable);
    }

    public String dealCardsToPlayers() {
        for (Player player : players) {
            player.addCard(dealerStack.pollCard());
            bj.updateBlackJackResult(player,dealerCardsOnTable);
            outputMap.put(player.getName(), outputMap.get(player.getName()) + "\n" +String.format("%s | %s | %s\n", player.getName(), player.getSumOfCards(), player.showCards()));
            System.out.println(outputMap.get(player.getName()));
            if (player.getSumOfCards() >= POINT_OF_STOP && player.getSumOfCards() <= BlackJack.BLACK_JACK) {
                player.setExhausted();
            }
            evaluateWinner(player);
        }
        return players.stream().map(player -> player.toJson()).toList().toString();
    }

    public String hit(String playerName) {
            dealersHand();
            Player player = players.stream().filter(p -> p.getName().equals(playerName)).findFirst().get();
            bj.updateBlackJackResult(player,dealerCardsOnTable);
            outputMap.put(player.getName(), outputMap.get(player.getName()) + "\n" +String.format("%s | %s | %s\n", player.getName(), player.getSumOfCards(), player.showCards()));
            System.out.println(outputMap.get(player.getName()));
            if (player.getSumOfCards() >= POINT_OF_STOP && player.getSumOfCards() <= BlackJack.BLACK_JACK) {
                player.setExhausted();
            }
            evaluateWinner(player);

        return players.stream().map(p -> p.toJson()).toList().toString();
    }

    public String initialRound() {
        dealInitial();
        dealCardsToPlayers();
        return players.stream().map(player -> player.toJson()).toList().toString();
    }

    private void resetGame(){
        dealerStack = new DealerStack(properties.getFile(),properties.getUrl());
        players.forEach(player -> player.resetPlayer());
    }

    public String dealInitial() {
        for (Player player : players) {
            player.addCard(dealerStack.pollCard());
            player.addCard(dealerStack.pollCard());
            bj.updateBlackJackResult(player,dealerCardsOnTable);
            outputMap.put(player.getName(), player.toJson());
            System.out.println(outputMap.get(player.getName()));
            if (player.getSumOfCards() >= POINT_OF_STOP && player.getSumOfCards() <= BlackJack.BLACK_JACK) {
                continue;
            }
            evaluateWinner(player);
            if (!waitingForAWinner) {
                break;
            }
        }
        return players.stream().map(player -> player.toJson()).toList().toString();
    }

    private void evaluateWinner(Player aboutToloose) {
        if (BlackJack.isBusted(aboutToloose.getSumOfCards())) {
            final String looser = aboutToloose.getName();
            // WORKS ONLY WITH TWO PLAYERS
            WINNER = players.stream().filter(player -> !player.getName().equals(looser)).findFirst().get().getName();
            System.out.println("WINNER : " + WINNER);
            waitingForAWinner = false;
        }
    }
}
