package no.liv1.cardgames.blackjack;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class GameOfBlackJack {
    public static int NUMBER_OF_PLAYERS = 2;
    private static int POINT_OF_STOP = 17;
    public String WINNER;
    private boolean waitingForAWinner = true;
    private String[] output = new String[NUMBER_OF_PLAYERS];
    private DealerStack dealerStack = new DealerStack(new StackOfCardsConverter().makeDealerStack());
    private ArrayList<Card> dealerCardsOnTable = new ArrayList<>();
    private Player[] players = new Player[NUMBER_OF_PLAYERS];

    public String showDealerCards(){
        return dealerStack.toString();
    }

    public String dealersHand(){
        Card card = dealerStack.pollCard();
        dealerCardsOnTable.add(card);
        return String.format("Dealer | %s", dealerCardsOnTable);
    }

    public String[] dealCardsToPlayer() {
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++ ) {
                players[i].addCard(dealerStack.pollCard());
                output[i] += String.format("%s | %s | %s\n", players[i].name, players[i].getSumOfCards(), players[i].showCards());
               // System.out.println(output[i]);
               evaluateWinner(i);
            }
        return output;
    }

    public void playRound(){
        dealInitial();
        ArrayList<Player> playerlist = new ArrayList<>(Arrays.asList(this.players));
        Iterator<Player> i = playerlist.iterator();
        while ( waitingForAWinner ) {
            dealCardsToPlayer();
           // System.out.println(dealersHand());
        }
    }

    public String dealInitial(){
            for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                players[i] = new Player(i, dealerStack.pollCard());
                players[i].addCard(dealerStack.pollCard());
                if(players[i].getSumOfCards() >= POINT_OF_STOP){
                    break;
                }
                output[i] = String.format("%s | %s | %s\n", players[i].name, players[i].getSumOfCards(), players[i].showCards());
                System.out.println(output[i]);
                evaluateWinner(i);
                if(players[i].getSumOfCards() >= POINT_OF_STOP){
                    break;
                }
            }
        return output.toString();
    }

    private void evaluateWinner(int index){
        if(BlackJack.isBusted(players[index].getSumOfCards())){
            final String looser = players[index].name;
            WINNER = Arrays.stream(players).filter(name -> !name.equals(looser)).findFirst().get().name;
            System.out.println("WINNER : " + WINNER);
            waitingForAWinner = false;
        }
    }
}
