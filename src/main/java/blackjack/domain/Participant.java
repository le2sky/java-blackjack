package blackjack.domain;

import java.util.List;

public abstract class Participant {

    protected final Hand hand;
    private final Name name;

    protected Participant(String name) {
        this.hand = new Hand();
        this.name = new Name(name);
    }

    public void hit(Card card) {
        validateIsPlayable();

        hand.add(card);
    }

    private void validateIsPlayable() {
        if (!isPlayable()) {
            throw new IllegalStateException("카드를 더이상 받을 수 없습니다.");
        }
    }

    protected abstract boolean isPlayable();

    public int calculateScore() {
        return hand.calculateScore();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return name.getValue();
    }
}