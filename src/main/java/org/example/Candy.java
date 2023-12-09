package org.example;

import org.example.exception.SweetIllegalArgumentException;

/**
 * The Candy class represents a type of sweet with additional information about its chocolate content.
 * It extends the Sweet class and inherits its properties such as name and weight.
 */
public class Candy extends Sweet {

    /**
     * The chocolate content of the candy, measured in some unit.
     */
    private final double chocolateContent;

    /**
     * Constructs a Candy object with the specified name, weight, and chocolate content.
     *
     * @param name             The name of the candy.
     * @param weight           The weight of the candy, measured in some unit.
     * @param chocolateContent The chocolate content of the candy, measured in some unit.
     */
    public Candy(String name, double weight, double chocolateContent) throws SweetIllegalArgumentException {
        super(name, weight);
        if (chocolateContent < 0 || chocolateContent > 1) {
            throw new IllegalArgumentException("The chocolate content must be between 0 and 1.");
        }
        this.chocolateContent = chocolateContent;
    }

    /**
     * Gets the chocolate content of the candy.
     *
     * @return The chocolate content of the candy, measured in some unit.
     */
    public double getChocolateContent() {
        return chocolateContent;
    }
}
