/**
 * This class represents a card with a suit and rank.
 *
 * @author John Meikle
 */
public class Card {

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rank).append(" of ").append(suit).append("'s");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + rank.hashCode();
        hash = 31 * hash + suit.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Card))
            return false;
        if (this == obj)
            return true;
        Card c = (Card) obj;
        return suit.equals(c.suit) && rank.equals(c.rank);
    }

}
