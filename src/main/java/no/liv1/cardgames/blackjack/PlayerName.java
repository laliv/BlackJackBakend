package no.liv1.cardgames.blackjack;

public enum PlayerName {

    Magnus(0),
    Lars(1);

    private int number;

    PlayerName(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
