import java.util.Observable;
import java.util.Observer;

/**
 * Created by John on 22/02/2017.
 */
public class Player implements Observer {

    private Hand hand;

    public Player() {
        hand = new Hand();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
