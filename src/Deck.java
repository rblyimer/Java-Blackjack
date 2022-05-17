import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> Deck;
    public Deck(){
        this.Deck = new ArrayList<Card>();
    }

    public void createFullDeck() {

        for(Suits cardSuit: Suits.values()) {
            for(Values cardValue : Values.values()) {
                this.Deck.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(Deck, new Random());
    }

    public String toString(){
        String cardListOutput = "";
        for( Card aCard : this.Deck){
            cardListOutput += "\n " + aCard.toString();
        }
        return cardListOutput;
    }

    public void removeCard(int i) {
        this.Deck.remove(i);
    }

    public Card getCard(int i) {
        return this.Deck.get(i);
    }

    public void addCard(Card addCard){
        this.Deck.add(addCard);
    }

    public void draw(Deck comingFrom){
        this.Deck.add(comingFrom.getCard(0));
        comingFrom.Deck.remove(0);
    }

    public int deckSize(){
        return this.Deck.size();
    }
    public void moveToDeck(Deck moveTo){
        int thisDeckSize = this.Deck.size();
        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));
        }
        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);
        }
    }
    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;

        for(Card aCard : this.Deck) {
            switch(aCard.getValue()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break; 
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }
        for(int i = 0; i < aces; i++){
            if(totalValue > 10){
                totalValue += 1;
            } else { totalValue += 11; }
        }
        return totalValue;
    }
}