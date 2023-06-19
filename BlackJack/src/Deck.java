
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;
    private int cardUsed;
    private final int width = 100;
    private int playervalue;
    private int dealervalue;
    public Deck() {
        deck = new ArrayList<Card>();

        //52 cards
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                if (j == 1) { //if j is 1 which means ace value is 11 in the beginning
                    Card card = new Card(i, j, 11);
                    deck.add(card);
                } 
                else if (j >= 10) { //Jack, Queen, or King.
                    Card card = new Card(i, j, 10);
                    deck.add(card);
                } 
                else {
                    Card card = new Card(i, j, j);
                    deck.add(card);
                }
            }
        }

    }
    public void setcardUsed(int i){
        this.cardUsed = i;
    }
    public int getcardUsed(){
        return cardUsed;
    }
    public Card getCard(int i) { //This method returns the ith (index) card of the deck.
        return deck.get(i);
    }

    public Card removeCard(int i) { //This method removes the ith (index) card of the deck.
        return deck.remove(i);
    }

    /**
     * @return the playervalue
     */
    public int getPlayervalue() {
        return playervalue;
    }

    /**
     * @param playervalue the playervalue to set
     */
    public void setPlayervalue(int playervalue) {
        this.playervalue = playervalue;
    }

    /**
     * @return the dealervalue
     */
    public int getDealervalue() {
        return dealervalue;
    }

    /**
     * @param dealervalue the dealervalue to set
     */
    public void setDealervalue(int dealervalue) {
        this.dealervalue = dealervalue;
    }
}
