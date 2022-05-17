import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        System.out.println("\n Welcome to Blackjack!");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        double initialAmt = 500;
        try (Scanner in = new Scanner(System.in)) {

            while (initialAmt > 0) {
                System.out.println("\n Your balance is $" + initialAmt + 
                "\n How much would you like to Bet? (You can only bet an increment of 5)");
                double playerBet = in.nextDouble();
                if (playerBet % 5 != 0) {
                    System.out.println("Please only enter an increment of 5 and up to your balance");
                    continue;
                } else if (playerBet > initialAmt) {
                    System.out.println("Sorry, you dont have that much amount.");
                    break;
                }

                boolean endRound = false;
                playerHand.draw(playingDeck);
                playerHand.draw(playingDeck);

                dealerHand.draw(playingDeck);
                dealerHand.draw(playingDeck);

                while (true) {
                    System.out.println("Your hand: ");
                    System.out.println(playerHand.toString());
                    System.out.println("Your hand total is: " + playerHand.cardsValue());
                    System.out.println("The dealer's hand is: " + dealerHand.getCard(0).toString() + " and a hidden card");                    
                    System.out.println("What do you like to do? 1) Hit 2) Stay");
                    int reply = in.nextInt();

                    if (reply == 1) {
                        playerHand.draw(playingDeck);
                        System.out.println("You draw: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                        if (playerHand.cardsValue() > 21) {
                            System.out.println("You busted!!! Card value: " + playerHand.cardsValue());
                            initialAmt -= playerBet;
                            endRound = true;
                            break;
                        }
                    }
                    if (reply == 2) {
                        break;
                    }
                }
                System.out.println("Dealer's hand: " + dealerHand.toString());
                if ((dealerHand.cardsValue() > playerHand.cardsValue()) && !endRound) {
                    System.out.println("House wins!");
                    initialAmt -= playerBet;
                    endRound = true;
                }
                while ((dealerHand.cardsValue()) < 17 && !endRound) {
                    dealerHand.draw(playingDeck);
                    System.out.println("Dealer draws: " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());
                }
                System.out.println("Dealers hand total: " + dealerHand.cardsValue());
                if ((dealerHand.cardsValue() > 21) && !endRound) {
                    System.out.println("you won!");
                    initialAmt += playerBet;
                    endRound = true;
                }
                if ((playerHand.cardsValue() == dealerHand.cardsValue()) && !endRound) {
                    System.out.println("You are tied");
                    endRound = true;
                }

                if ((playerHand.cardsValue() > dealerHand.cardsValue()) && !endRound) {
                    System.out.println("You won!");
                    initialAmt += playerBet;
                    endRound = true;
                } else if (!endRound) {
                    System.out.println("You lost");
                    initialAmt -= playerBet;
                    endRound = true;
                }

                playerHand.moveToDeck(playingDeck);
                dealerHand.moveToDeck(playingDeck);
                System.out.println("End of round!");
            }
        }

        if (initialAmt == 0) {
            System.out.println("You balance is zero, Better luck next time:(");
        }
    }
}