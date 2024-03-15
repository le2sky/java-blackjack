package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import blackjack.domain.common.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Player("atom", new Money(1)))
                .doesNotThrowAnyException();
    }

    @DisplayName("사용자의 초기 돈은 최소 1 이상이어야 한다.")
    @Test
    void validatePlayerMoney() {
        Money invalidPlayerMoney = new Money(0.9);

        assertThatThrownBy(() -> new Player("atom", invalidPlayerMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자는 카드를 한 장 받을 수 있다.")
    @Test
    void hitOneCard() {
        Player player = new Player("atom", new Money(1000));
        Card givenCard = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        player.hit(givenCard);

        assertThat(player.getCards()).containsExactly(givenCard);
    }

    @DisplayName("사용자가 버스트 상태면 더 이상 카드를 받을 수 없다")
    @Test
    void hitWhenBust() {
        Player player = new Player("atom", new Money(1000));
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.QUEEN, CardShape.DIAMOND);
        Card card3 = new Card(CardRank.JACK, CardShape.DIAMOND);
        Card card4 = new Card(CardRank.ACE, CardShape.DIAMOND);

        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThatThrownBy(() -> player.hit(card4))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("사용자가 21점에 도달하면 더 이상 카드를 받을 수 없다")
    @Test
    void hitWhenBlackJackScore() {
        Player player = new Player("atom", new Money(1000));
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.QUEEN, CardShape.DIAMOND);
        Card card3 = new Card(CardRank.ACE, CardShape.DIAMOND);
        Card card4 = new Card(CardRank.ACE, CardShape.HEART);

        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThatThrownBy(() -> player.hit(card4))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("사용자는 카드를 더 받을 수 있는지 확인할 수 있다.")
    @Test
    void isPlayable() {
        Player player = new Player("atom", new Money(1000));
        player.hit(new Card(CardRank.JACK, CardShape.DIAMOND));
        player.hit(new Card(CardRank.ACE, CardShape.CLOVER));

        boolean result = player.isPlayable();

        assertThat(result).isFalse();
    }
}
