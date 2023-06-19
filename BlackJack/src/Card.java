
public class Card {

    private int value;
    private int suit;
    private int num;

    public Card(int suit, int num, int value) {
        this.suit = suit;
        this.num = num;
        this.value = value;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the suit
     */
    public int getSuit() {
        return suit;
    }

    public int getNum() {
        return num;
    }

    public String convertToSpecial() {
        String s1 = "";
        String s2 = "";
        if (num == 11) {
            s1 = "Jack";
        } else if (num == 12) {
            s1 = "queen";
        } else if (num == 13) {
            s1 = "king";
        } 
        else if(num==1){
            s1 = "ace";
        }else {
            s1 = Integer.toString(num);
        }

        if (suit == 0) {//Club
            s2 = "c";
        } else if (suit == 1) {
            s2 = "s";
        } else if (suit == 2) {
            s2 = "d";
        } else if (suit == 3) {
            s2 = "h";

        }
        return s1 + s2;
    }

    public String getImagename() {
        String b = convertToSpecial();
        return "C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\" + b + ".jpg";
    }

}
