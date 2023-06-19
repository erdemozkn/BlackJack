
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        boolean gameover = false;
        ArrayList<Card> player = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        Random rnd = new Random();
        Deck deck = new Deck();
        JFrame f = new JFrame("BlackJack Game");
        JButton dealstay = new JButton("Deal");
        JButton hit = new JButton("Hit");
        JButton PlayAgain = new JButton("Play Again");
        dealstay.setBounds(40, 390, 100, 50);

        ImageIcon bck = new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\background.jpg");
        JLabel backl = new JLabel("", bck, JLabel.CENTER);
        backl.setBounds(0, 0, 800, 550);
        //Player
        JLabel playerscore = new JLabel("Player = 0");
        playerscore.setForeground(Color.red);
        playerscore.setBounds(120, 220, 70, 12);
        JLabel playerimg1 = new JLabel();
        JLabel playerimg2 = new JLabel();
        JLabel playerimg3 = new JLabel();
        JLabel playerimg4 = new JLabel();
        JLabel playerimg5 = new JLabel();

        //Computer
        JLabel dealerscore = new JLabel("Dealer = 0");
        dealerscore.setBounds(120, 100, 70, 12);
        JLabel dealerimg1 = new JLabel();
        JLabel dealerimg2 = new JLabel();
        JLabel dealerimg3 = new JLabel();
        dealerscore.setForeground(Color.red);

        dealstay.setActionCommand("Deal");
        hit.setBounds(170, 390, 100, 50);
        hit.setActionCommand("Stay");
        PlayAgain.setBounds(650, 390, 100, 50);
        hit.setEnabled(false);
        PlayAgain.setEnabled(false);
        dealstay.addActionListener(new ActionListener() {
            int cardused = 0;
            boolean gameover = false;
            int playerstay = 0;
            int dealerstay = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameover) {
                    if (e.getActionCommand().equals("Deal")) {
                        hit.setEnabled(true);
                        dealstay.setText("Stay");
                        dealstay.setActionCommand("Stay");

                        //Player Cards------------------
                        int a1 = rnd.nextInt(52 - deck.getcardUsed());
                        player.add(deck.getCard(a1));
                        deck.removeCard(a1);
                        deck.setcardUsed(deck.getcardUsed() + 1);
                        int a2 = rnd.nextInt(52 - deck.getcardUsed());
                        player.add(deck.getCard(a2));
                        deck.removeCard(a2);
                        deck.setcardUsed(deck.getcardUsed() + 1);

                        //first card
                        playerimg1.setIcon(new ImageIcon(player.get(0).getImagename()));
                        Dimension size1 = playerimg1.getPreferredSize();
                        playerimg1.setBounds(280, 230, size1.width, size1.height);

                        //second card
                        playerimg2.setIcon(new ImageIcon(player.get(1).getImagename()));
                        Dimension size2 = playerimg2.getPreferredSize();
                        playerimg2.setBounds(330, 230, size2.width, size2.height);
                        deck.setPlayervalue(player.get(0).getValue() + player.get(1).getValue());

                        //Dealer Cards-----------------
                        int a3 = rnd.nextInt(52 - deck.getcardUsed());
                        dealer.add(deck.getCard(a3));
                        deck.removeCard(a3);
                        deck.setcardUsed(deck.getcardUsed() + 1);
                        int a4 = rnd.nextInt(52 - deck.getcardUsed());
                        dealer.add(deck.getCard(a4));
                        deck.removeCard(a4);
                        deck.setcardUsed(deck.getcardUsed() + 1);

                        //first card
                        dealerimg1.setIcon(new ImageIcon(dealer.get(0).getImagename()));
                        Dimension size3 = dealerimg1.getPreferredSize();
                        dealerimg1.setBounds(280, 100, size3.width, size3.height);

                        //second card
                        dealerimg2.setIcon(new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\back.jpg"));
                        Dimension size4 = dealerimg2.getPreferredSize();
                        dealerimg2.setBounds(330, 100, size4.width, size4.height);
                        deck.setDealervalue(dealer.get(0).getValue());

                    } 
                    else if (e.getActionCommand().equals("Stay")) {
                        Hand hand = new Hand(player, dealer);
                        hand.handValue(dealer, player);
                        dealerstay = hand.getSumdealer();
                        playerstay = hand.getSumplayer();
                        //one more card
                        if (dealerstay < 17) {
                            int a5 = rnd.nextInt(52 - deck.getcardUsed());
                            dealer.add(deck.getCard(a5));
                            deck.removeCard(a5);
                            deck.setcardUsed(cardused++);
                        }
                        //check after dealer's card
                        hand = new Hand(player, dealer);
                        hand.handValue(dealer, player);
                        dealerstay = hand.getSumdealer();
                        deck.setDealervalue(dealerstay);

                        //dealer show cards after second card
                        for (int i = 1; i < dealer.size(); i++) {
                            if (i == 1) {
                                dealerimg2.setIcon(new ImageIcon(dealer.get(i).getImagename()));
                            } else if (i == 2) {
                                dealerimg3.setIcon(new ImageIcon(dealer.get(2).getImagename()));
                                Dimension size5 = dealerimg3.getPreferredSize();
                                dealerimg3.setBounds(380, 100, size5.width, size5.height);

                            }
                        }
                        //winner
                        if (playerstay == dealerstay) {
                            //draw
                            JOptionPane.showMessageDialog(null, "<html><h1>DRAW!!!!</h1></html>", "DRAW", JOptionPane.PLAIN_MESSAGE,new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\draw.png"));
                        } else if (playerstay == 21) {
                            //playerwinner
                            JOptionPane.showMessageDialog(null, "<html><h1>You Win!!!!</h1></html>", "DRAW", JOptionPane.PLAIN_MESSAGE,new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\win.png"));
                        } else if (dealerstay == 21) {
                            //dealerwinner
                            JOptionPane.showMessageDialog(null, "<html><h1>You Lost!!!!</h1></html>", "DRAW", JOptionPane.PLAIN_MESSAGE,new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\lose.jpg"));
                        } else if (playerstay > 21) {
                            //dealerwinner
                            JOptionPane.showMessageDialog(null, "<html><h1>You Lost!!!!</h1></html>", "DRAW", JOptionPane.PLAIN_MESSAGE,new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\lose.jpg"));
                        } else if (dealerstay > 21) {
                            //playerwinner
                            JOptionPane.showMessageDialog(null, "<html><h1>You Win!!!!</h1></html>", "DRAW", JOptionPane.PLAIN_MESSAGE,new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\win.png"));
                        } else if (dealerstay > playerstay) {
                            //dealerwinner
                            JOptionPane.showMessageDialog(null, "<html><h1>You Lost!!!!</h1></html>", "DRAW", JOptionPane.PLAIN_MESSAGE,new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\lose.jpg"));
                        } else if (dealerstay < playerstay) {
                            //playerwinner
                            JOptionPane.showMessageDialog(null, "<html><h1>You Win!!!!</h1></html>", "DRAW", JOptionPane.PLAIN_MESSAGE,new ImageIcon("C:\\Users\\erdem\\OneDrive\\Masaüstü\\cs201jpg\\win.png"));
                        }
                        playerscore.setText("Player = " + deck.getPlayervalue());
                        dealerscore.setText("Dealer = " + deck.getDealervalue());
                        dealstay.setEnabled(false);
                        hit.setEnabled(false);
                        PlayAgain.setEnabled(true);
                        gameover = true;
                    }
                    playerscore.setText("Player = " + deck.getPlayervalue());
                    dealerscore.setText("Dealer = " + deck.getDealervalue());
                }
                f.repaint();
            }
        });
        hit.addActionListener(new ActionListener() {
            int index = 2;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (deck.getPlayervalue() < 21) {
                    int a = rnd.nextInt(52 - deck.getcardUsed());
                    player.add(deck.getCard(a));
                    deck.removeCard(a);
                    deck.setcardUsed(deck.getcardUsed() + 1);
                    Hand hand = new Hand(player, dealer);
                    hand.handValue(dealer, player);
                    //i have already 2 card
                    if (index == 2) {
                        playerimg3.setIcon(new ImageIcon(player.get(index).getImagename()));
                        Dimension size3 = playerimg3.getPreferredSize();
                        playerimg3.setBounds(380, 230, size3.width, size3.height);
                        deck.setPlayervalue(deck.getPlayervalue() + player.get(index).getValue());
                    } else if (index == 3) {
                        playerimg4.setIcon(new ImageIcon(player.get(index).getImagename()));
                        Dimension size4 = playerimg4.getPreferredSize();
                        playerimg4.setBounds(430, 230, size4.width, size4.height);
                        deck.setPlayervalue(deck.getPlayervalue() + player.get(index).getValue());
                    } else if (index == 4) {
                        playerimg5.setIcon(new ImageIcon(player.get(index).getImagename()));
                        Dimension size5 = playerimg5.getPreferredSize();
                        playerimg5.setBounds(480, 230, size5.width, size5.height);
                        deck.setPlayervalue(deck.getPlayervalue() + player.get(index).getValue());
                    }
                    index++;
                    playerscore.setText("Player = " + deck.getPlayervalue());
                }
            }
        });
        PlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayAgain.setEnabled(false);
                playerscore.setText("Player = 0");
                dealerscore.setText("Dealer = 0");
                playerimg1.setIcon(null);
                playerimg2.setIcon(null);
                playerimg3.setIcon(null);
                playerimg4.setIcon(null);
                dealerimg1.setIcon(null);
                dealerimg2.setIcon(null);
                dealerimg3.setIcon(null);
                dealstay.setEnabled(true);
                dealstay.setActionCommand("Deal");
                f.repaint();
            }
        });
        f.add(playerscore);
        f.add(playerimg4);
        f.add(playerimg3);
        f.add(playerimg2);
        f.add(playerimg1);
        f.add(dealerscore);
        f.add(dealerimg3);
        f.add(dealerimg2);
        f.add(dealerimg1);
        f.add(dealstay);
        f.add(hit);
        f.add(PlayAgain);
        f.add(backl);
        f.setSize(800, 550);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setResizable(false);
        f.setVisible(true);
    }
}
