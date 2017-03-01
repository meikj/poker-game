import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a deck of cards with functionality for shuffling.
 *
 * @author John Meikle
 */
public class Deck {

    // Fresh ordered unshuffled deck for new deck copying purposes
    private static List<Card> freshDeck;

    private List<Card> deck;
    private boolean shuffled;

    /**
     * Construct a new shuffled deck.
     */
    public Deck() {
        this(true);
    }

    /**
     * Construct a new deck.
     * @param shuffle whether or not the deck should be initially shuffled.
     */
    public Deck(boolean shuffle) {
        if (freshDeck == null)
            initFreshDeck();
        deck = new ArrayList<>(freshDeck);
        if (shuffle)
            shuffle();
    }

    /**
     * Check if the deck is empty.
     * @return whether or not the deck is empty.
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    /**
     * Check if the deck is shuffled.
     * @return whether or not the deck is shuffled.
     */
    public boolean isShuffled() {
        return shuffled;
    }

    /**
     * Pop a card from the deck.
     * @return the card.
     */
    public Card pop() {
        return !isEmpty() ? deck.remove(deck.size() - 1) : null;
    }

    /**
     * Get the ordered list of cards in the deck.
     * @return the deck.
     */
    public List<Card> getDeck() {
        return deck;
    }

    /**
     * Shuffle the deck of cards using the Fisher-Yates shuffle, also known as the Knuth shuffle.
     */
    public void shuffle() {
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < deck.size() - 1; i++) {
            int j = rand.nextInt(i + 1); // 0 <= j <= i, therefore i must be inclusive
            Card card = deck.get(i);
            // exchange deck[i] and deck[j]
            deck.set(i, deck.get(j));
            deck.set(j, card);
        }
        shuffled = true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Card card : deck)
            str.append(String.format("%s %s\n", card.getRank(), card.getSuit()));
        return str.toString();
    }

    /**
     * Initialise the fresh deck in order of rank and suit.
     */
    private static void initFreshDeck() {
        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();
        freshDeck = new ArrayList<>(suits.length * ranks.length);
        for (int i = 0; i < ranks.length; i++)
            for (int j = 0; j < suits.length; j++)
                freshDeck.add(new Card(suits[j], ranks[i]));
    }

}
