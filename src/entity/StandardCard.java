package entity;

import entity.Card;

/**
 * Represents a standard playing card with a specific value and image.
 */
public class StandardCard implements Card {
    private final String value;
    private final String img;

    /**
     * Constructs a new instance of StandardCard with the specified value and image.
     *
     * @param value The value of the card.
     * @param img   The image representation of the card.
     */
    public StandardCard(String value, String img) {
        this.value = value;
        this.img = img;
    }

    /**
     * Gets the value of the card.
     *
     * @return The value of the card.
     */
    @Override
    public String getValue() {
        return this.value;
    }


    /**
     * Gets the image representation of the card.
     *
     * @return The image representation of the card.
     */
    @Override
    public String getImg() {
        return this.img;
    }
}
