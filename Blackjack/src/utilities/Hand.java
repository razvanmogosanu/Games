package utilities;

import cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand extends Deck {
    List<Card> hand = new ArrayList<>(2);
    private int cardPosition = 0;

    private int sum = 0;

    public Hand() {
        shuffle();
        addCard();
        addCard();
    }

    public void printCards(){
        for(int i = 0; i < hand.size(); i++)
            System.out.print(hand.get(i).getCardType() + " ");
    }

    public void addCard() {
        hand.add(deck.get(cardPosition));
        if(cardPosition + 1 > 52){
            shuffle();
            cardPosition = 0;
        }
        else
            cardPosition++;
    }

    private int doSum(){
        int nrOfAces = 0;
        sum = 0;
        for (int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getCardType().toString().equals("ACE")){
                sum += 10;
                nrOfAces++;
            }
            sum += hand.get(i).getCardType().getValue();
            if(sum > 21 && nrOfAces > 0){
                nrOfAces--;
                sum -= 10;
            }
        }
        return sum;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getSum() {
        return doSum();
    }
}
