package utilities;

import cards.Card;
import cards.CardSuit;
import cards.CardType;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> deck = new ArrayList<>(52);

    Deck() {
        for (CardType cardType : CardType.values()) {
            for (CardSuit cardSuit : CardSuit.values()) {
                deck.add(new Card(cardSuit, cardType));
            }
        }
    }

    protected void shuffle(){
        Random random = new Random();
        for(int i = 0; i < 52; i++){
            int randomPosition = random.nextInt(52);
            interchange(i,randomPosition);
        }
    }

    private void interchange(int i, int j){
        Card temp = deck.get(i);
        deck.set(i, deck.get(j));
        deck.set(j, temp);
    }

}
