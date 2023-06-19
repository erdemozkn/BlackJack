import java.util.ArrayList;

public class Hand {
    ArrayList<Card> dealerHand;
    ArrayList<Card> playerHand;
    boolean acecontaindealer = false;
    boolean acecontainplayer = false;
    private int sumdealer = 0;
    private int sumplayer = 0;
    
    public Hand(ArrayList<Card> playerhand, ArrayList<Card> dealerhand){
        this.dealerHand = dealerhand;
        this.playerHand = playerhand;
    }

    /**
     * @return the sumdealer
     */
    public int getSumdealer() {
        return sumdealer;
    }

    /**
     * @return the sumplayer
     */
    public int getSumplayer() {
        return sumplayer;
    }
    public void handValue(ArrayList<Card> dealer, ArrayList<Card> player){
        for(int i = 0;i<dealer.size();i++){
            if(dealer.get(i).getNum()==1){
                acecontaindealer = true;
            }
            sumdealer += dealer.get(i).getValue();
        }
        if(getSumdealer()>21 && acecontaindealer==true){
            sumdealer-=10;
        }
        for(int i = 0;i<player.size();i++){
            if(player.get(i).getNum()==1){
                acecontainplayer = true;
            }
            sumplayer += player.get(i).getValue();
        }
        if(getSumplayer()>21 && acecontainplayer == true){
            sumplayer-=10;
        }
    }
}
