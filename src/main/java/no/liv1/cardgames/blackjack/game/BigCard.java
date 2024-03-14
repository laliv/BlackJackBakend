package no.liv1.cardgames.blackjack.game;

public enum BigCard {

    J(10),
    Q(10),
    K(10),
    A(11);

    private int number;

    BigCard(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
