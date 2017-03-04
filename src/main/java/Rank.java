/**
 * Card rank.
 */
public enum Rank {

    Ace, King, Queen, Jack, Ten, Nine, Eight, Seven, Six, Five, Four, Three, Two;

    /**
     * Get a letter representation of the rank.
     * @return the letter.
     */
    public String getLetter() {
        if (this == Ace) { return "A"; }
        if (this == King) { return "K"; }
        if (this == Queen) { return "Q"; }
        if (this == Jack) { return "J"; }
        if (this == Ten) { return "10"; }
        if (this == Nine) { return "9"; }
        if (this == Eight) { return "8"; }
        if (this == Seven) { return "7"; }
        if (this == Six) { return "6"; }
        if (this == Five) { return "5"; }
        if (this == Four) { return "4"; }
        if (this == Three) { return "3"; }
        if (this == Two) { return "2"; }
        return "";
    }

}
