package entity.cards;

public class CardBack implements Card {
    @Override
    public String getValue() { return "0"; }

    @Override
    public String getImg() {
        return "https://www.deckofcardsapi.com/static/img/back.png";
    }
}
