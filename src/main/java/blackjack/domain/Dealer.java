package blackjack.domain;

import java.util.List;

public class Dealer extends Participant {

    private static final int STAND_BOUND = 17;
    private static final int HIDDEN_CARD_SIZE = 1;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public GameResult judge(Player player) {
        int playerScore = player.calculateScore();
        int dealerScore = calculateScore();

        if (player.isBust()) {
            return GameResult.LOSE;
        }

        if (isBust() || playerScore > dealerScore) {
            return GameResult.WIN;
        }

        if (player.isBlackJack() && isBlackJack()) {
            return GameResult.TIE;
        }

        if (player.isBlackJack()) {
            return GameResult.WIN;
        }

        if (isBlackJack() || playerScore < dealerScore) {
            return GameResult.LOSE;
        }

        return GameResult.TIE;
    }

    public List<Card> getOpenCards() {
        List<Card> allCards = getCards();

        return allCards.subList(HIDDEN_CARD_SIZE, allCards.size());
    }

    @Override
    public boolean isPlayable() {
        int score = calculateScore();

        return score < STAND_BOUND;
    }
}
