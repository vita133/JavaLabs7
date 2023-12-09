package org.example;

import org.example.exception.SweetIllegalArgumentException;

/**
 * The Cookie class represents a type of sweet with additional information about its gluten-free status.
 * It extends the Sweet class and inherits its properties such as name and weight.
 */
public class Cookie extends Sweet {

    /**
     * A flag indicating whether the cookie is gluten-free or not.
     */
    private final boolean glutenFree;

    /**
     * Constructs a Cookie object with the specified name, weight, and gluten-free status.
     *
     * @param name       The name of the cookie.
     * @param weight     The weight of the cookie, measured in some unit.
     * @param glutenFree A boolean flag indicating whether the cookie is gluten-free or not.
     */
    public Cookie(String name, double weight, boolean glutenFree) throws SweetIllegalArgumentException {
        super(name, weight);
        this.glutenFree = glutenFree;
    }

    /**
     * Checks if the cookie is gluten-free.
     *
     * @return {@code true} if the cookie is gluten-free, {@code false} otherwise.
     */
    public boolean isGlutenFree() {
        return glutenFree;
    }
}
