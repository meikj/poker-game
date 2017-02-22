/**
 * This class represents a card with a suit and rank.
 *
 * @author John Meikle
 */
public class Card {

    /**
     * Card suit.
     */
    public enum Suit { HEART, DIAMOND, CLUB, SPADE }

    /**
     * Card rank.
     */
    public enum Rank { ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, ONE }

    private Suit suit;
    private Rank rank;

    /**
     * Construct a new card.
     * @param suit the suit.
     * @param rank the rank.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Get the card suit.
     * @return the suit.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Get the card rank.
     * @return the rank.
     */
    public Rank getRank() {
        return rank;
    }

}
