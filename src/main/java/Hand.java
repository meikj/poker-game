import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * This class represents a hand in poker with functionality for hand scoring.
 */
public class Hand {

    private List<Card> cards;
    private String handDescription;
    private Map<Suit, Integer> suitCount;
    private Map<Rank, Integer> rankCount;
    private int score;

    public Hand() {
        cards = new ArrayList<>(5);
        suitCount = new HashMap<>();
        rankCount = new HashMap<>();
        score = 0;
    }

    public void addCard(Card card) {
        if (cards.contains(card))
            throw new IllegalArgumentException("Can't add same card to hand: " + card);
        int i = 0;
        for (; i < cards.size(); i++) {
            if (cards.get(i).getRank().compareTo(card.getRank()) > 0)
                break;
        }
        cards.add(i, card);
        incrementSuitRankCount(card.getSuit(), card.getRank());
        score += card.getRank().ordinal();
    }

    /**
     * Increment the suit and rank counts.
     * @param suit the suit.
     * @param rank the rank.
     */
    private void incrementSuitRankCount(Suit suit, Rank rank) {
        suitCount.put(suit, suitCount.containsKey(suit) ? suitCount.get(suit) + 1 : 1);
        rankCount.put(rank, rankCount.containsKey(rank) ? rankCount.get(rank) + 1 : 1);
    }

    public String getHandDescription() {
        return handDescription;
    }

    /**
     * Clear the hand.
     */
    public void clear() {
        cards.clear();
        handDescription = "";
        suitCount.clear();
        rankCount.clear();
        score = 0;
    }

    /**
     * Check if the hand fulfills the condition of a flush.
     * @return the flush suit or null if no flush.
     */
    private Suit hasFlush() {
        for (Suit suit : suitCount.keySet()) {
            if (suitCount.get(suit) >= 5)
                return suit;
        }
        return null;
    }

    /**
     * Check if the hand fulfills the condition of a straight.
     * @return the high card of the straight or null if no straight.
     */
    private Rank hasStraight() {
        Rank prevRank = null;
        int straightCount = 1;
        for (Card card : cards) {
            Rank rank = card.getRank();
            if (prevRank == null) {
                prevRank = rank;
                continue;
            }
            if (prevRank.ordinal() == rank.ordinal() - 1 || prevRank == Rank.Ace && rank == Rank.Five) {
                straightCount++;
                if (straightCount == 5) {
                    if (rank == Rank.Two)
                        return Rank.Five;
                    return rank;
                }
            } else
                return null;
            prevRank = rank;
        }
        return null;
    }

    /**
     * Evaluate the Texas Hold 'em hand returning a score that can be compared. Hands are scored as follows:
     *
     * 13 is multiplied by:
     * 1. Royal Flush
     * 2. Straight Flush
     * 3. Four of a Kind
     * 4. Full House
     * 5. Flush
     * 6. Straight
     * 7. Three of a Kind
     * 8. Two Pairs
     * 9. Pair
     * 10. High Card
     *
     * On top of the above calculated score, the rank of each card is added.
     *
     * @return the hand score from lowest = best to highest = worst.
     */
    public int evaluate() {
        if (cards.isEmpty())
            throw new IllegalStateException("Can't evaluate an empty hand.");
        Rank highCard = cards.get(0).getRank();
        Suit flushSuit = hasFlush();
        Rank straightRank = hasStraight();
        boolean royalFlush = flushSuit != null && highCard == Rank.Ace && rankCount.keySet().contains(Rank.Ten);

        // Extract first and second pair ranks and counts
        Rank firstPair = null;
        int firstPairCount = 0;
        Rank secondPair = null;
        int secondPairCount = 0;
        for (Rank rank : rankCount.keySet()) {
            int count = rankCount.get(rank);
            if (count >= 2) {
                if (firstPair == null) {
                    firstPair = rank;
                    firstPairCount = count;
                } else if (secondPair == null) {
                    secondPair = rank;
                    secondPairCount = count;
                }
            }
        }

        // Form hand description and score
        StringBuilder sb = new StringBuilder();
        if (royalFlush) {
            sb.append("Royal Flush");
            score += 13;
        } else if (straightRank != null && flushSuit != null) {
            sb.append("Straight Flush ");
            sb.append(straightRank);
            sb.append("-high");
            score += 13 * 2;
        } else if (firstPairCount == 4) {
            sb.append("Four of a Kind ");
            sb.append(firstPair);
            sb.append("'s");
            score += 13 * 3;
        } else if (secondPairCount == 4) {
            sb.append("Four of a Kind ");
            sb.append(secondPair);
            sb.append("'s");
            score += 13 * 3;
        } else if (firstPairCount == 3 && secondPairCount == 2) {
            sb.append("Full House ");
            String letter = firstPair.getLetter();
            sb.append(letter).append(letter).append(letter);
            letter = secondPair.getLetter();
            sb.append(letter).append(letter);
            score += 13 * 4;
        } else if (secondPairCount == 3 && firstPairCount == 2) {
            sb.append("Full House ");
            String letter = secondPair.getLetter();
            sb.append(letter).append(letter).append(letter);
            letter = firstPair.getLetter();
            sb.append(letter).append(letter);
            score += 13 * 4;
        } else if (flushSuit != null) {
            sb.append(flushSuit);
            sb.append(" Flush ");
            sb.append(highCard);
            sb.append("-high");
            score += 13 * 5;
        } else if (straightRank != null) {
            sb.append("Straight ");
            sb.append(straightRank);
            sb.append("-high");
            score += 13 * 6;
        } else if (firstPairCount == 3) {
            sb.append("Three of a Kind ");
            sb.append(firstPair);
            sb.append("'s");
            score += 13 * 7;
        } else if (secondPairCount == 3) {
            sb.append("Three of a Kind ");
            sb.append(secondPair);
            sb.append("'s");
            score += 13 * 7;
        } else if (firstPairCount == 2 && secondPairCount == 2) {
            sb.append("Two Pair ");
            sb.append(firstPair);
            sb.append("-");
            sb.append(secondPair);
            score += 13 * 8;
        } else if (firstPairCount == 2) {
            sb.append(firstPair);
            sb.append(" Pair");
            score += 13 * 9;
        } else if (secondPairCount == 2) {
            sb.append(secondPair);
            sb.append(" Pair");
            score += 13 * 9;
        } else {
            sb.append(highCard);
            sb.append(" High Card");
            score += 13 * 10;
        }

        this.handDescription = sb.toString();
        return score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card c : cards) {
            sb.append(c.getRank().getLetter());
            sb.append("-");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
