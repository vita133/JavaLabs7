package org.example;

import org.example.exception.SweetIllegalArgumentException;

/**
 * The Sweet class represents a generic type of sweet with basic information such as name and weight.
 */
public class Sweet {

    /**
     * The name of the sweet.
     */
    String name;

    /**
     * The weight of the sweet, measured in some unit.
     */
    double weight;

    /**
     * Constructs a Sweet object with the specified name and weight.
     *
     * @param name   The name of the sweet.
     * @param weight The weight of the sweet, measured in some unit.
     */
    public Sweet(String name, double weight) throws SweetIllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new SweetIllegalArgumentException();
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("The weight of the sweet must be positive.");
        }

        this.name = name;
        this.weight = weight;
    }

    /**
     * Gets the weight of the sweet.
     *
     * @return The weight of the sweet, measured in some unit.
     */
    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }
}
