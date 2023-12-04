package entity;

public class StandardCard implements Card {
    private final String value;
    private final String img;

    public StandardCard(String value, String img) {
        this.value = value;
        this.img = img;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getImg() {
        return this.img;
    }
}
