import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This class represents a table with a deck of cards and a collection of players with functionality for dealing. The
 * table is observable so that players can be notified of additions to the community cards for hand forming purposes.
 *
 * @author John Meikle
 */
public class Table extends Observable {

    private Deck deck;
    private List<Player> players;
    private List<Card> communityCards;

    /**
     * Construct a new empty table with a fresh shuffled deck.
     */
    public Table() {
        deck = new Deck();
        players = new ArrayList<>();
        communityCards = new ArrayList<>();
    }

    /**
     * Add a player to the table, adding them as an observer.
     * @param player the player.
     */
    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            addObserver(player);
            players.add(player);
        }
    }

    /**
     * Remove a player from the table, removing them as an observer.
     * @param player the player.
     */
    public void removePlayer(Player player) {
        if (players.contains(player)) {
            deleteObserver(player);
            players.remove(player);
        }
    }

    /**
     * Deal a number of cards to each and every player.
     * @param n the number of cards each and every player should be dealt.
     */
    public void dealToAllPlayers(int n) {
        if (n > deck.getDeck().size())
            throw new IllegalArgumentException("Can't deal more cards that are in deck to players.");
        for (int i = 0; i < n; i++) {
            for (Player p : players) {
                Card c = deck.pop();
            }
        }
    }

    public void dealToPlayer(int n, Player p) {
        if (n > deck.getDeck().size())
            throw new IllegalArgumentException("Can't deal more cards that are in deck to player.");
        for (int i = 0; i < n; i++) {
            Card c = deck.pop();
            // Add card to player hand
        }
    }

    /**
     * Deal a number of cards to the table (i.e. community cards) and notify the observers.
     * @param n the number of cards the table should be dealt.
     */
    public void dealToTable(int n) {
        if (n > deck.getDeck().size())
            throw new IllegalArgumentException("Can't deal more cards that are in deck to table.");
        for (int i = 0; i < n; i++)
            communityCards.add(deck.pop());
        notifyObservers();
    }

    /**
     * Get the list of community cards.
     * @return the list of community cards.
     */
    public List<Card> getCommunityCards() {
        return communityCards;
    }

    /**
     * Final showdown of player hands.
     * @param n the number of cards that specify a hand (e.g. 5 in Texas Hold 'Em).
     * @return the Player that has the best hand.
     */
    public Player showdown(int n) {
        // See who has the best hand of n cards
        // ...
        communityCards.clear();
        deck = new Deck();
        return null;
    }

}
