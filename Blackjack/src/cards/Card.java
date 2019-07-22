package cards;


public class Card {
    private CardType cardType;
    private CardSuit cardSuit;

    public Card(CardSuit cardSuit, CardType cardType){
        this.cardType = cardType;
        this.cardSuit = cardSuit;
    }

    public CardType getCardType() {
        return cardType;
    }
}
