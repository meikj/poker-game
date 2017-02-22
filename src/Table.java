import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by John on 22/02/2017.
 */
public class Table extends Observable {

    private Deck deck;
    private List<Player> players;

    public Table() {
        deck = new Deck();
        players = new ArrayList<>();
    }

}
