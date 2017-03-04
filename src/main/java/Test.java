/**
 * Created by John on 22/02/2017.
 */
public class Test {

    public static void main(String[] args) {
        Card c1 = new Card(Suit.Club, Rank.Ace);
        Card c2 = new Card(Suit.Diamond, Rank.Ten);
        Card c3 = new Card(Suit.Club, Rank.Ten);
        Card c4 = new Card(Suit.Heart, Rank.Ten);
        Card c5 = new Card(Suit.Spade, Rank.Ten);
        Hand hand = new Hand();
        hand.addCard(c1);
        hand.addCard(c2);
        hand.addCard(c3);
        hand.addCard(c4);
        hand.addCard(c5);
        System.out.println("hand =        " + hand);
        System.out.println("evaluation =  " + hand.evaluate());
        System.out.println("description = " + hand.getHandDescription());
    }

}
