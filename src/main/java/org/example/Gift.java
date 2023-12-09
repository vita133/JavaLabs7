package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The Gift class represents a collection of sweets.
 */
class Gift<T extends Sweet> {
    /**
     * The list containing the sweets in the gift.
     */
    List<T> sweetsList = new ArrayList<>();

    /**
     * Adds a sweet to the gift.
     *
     * @param sweet The sweet to be added to the gift.
     */
    public void addSweet(T sweet) {
        sweetsList.add(sweet);
    }

    /**
     * Calculates the total weight of all sweets in the gift.
     *
     * @return The total weight of the sweets in the gift, measured in some unit.
     */
    public double calculateWeight() {
        double totalWeight = 0;
        for (Sweet sweet : sweetsList) {
            totalWeight += sweet.getWeight();
        }
        return totalWeight;
    }

    /**
     * Sorts the sweets in the gift using the provided comparator.
     *
     * @param comparator The comparator to be used for sorting the sweets.
     */
    public void sortSweets(Comparator<T> comparator) {
        sweetsList.sort(comparator);
    }

    /**
     * Finds candies in the gift with chocolate content within the specified range.
     *
     * @param min The minimum chocolate content for the candies.
     * @param max The maximum chocolate content for the candies.
     * @return A list of candies with chocolate content within the specified range.
     */
    public List<Candy> findCandyByChocolateContent(double min, double max) {
        if (min < 0 || max > 1) {
            throw new IllegalArgumentException("The chocolate content must be between 0 and 1.");
        }
        if (min > max) {
            throw new IllegalArgumentException("The minimum chocolate content cannot be greater than the maximum.");
        }
        List<Candy> result = new ArrayList<>();
        for (Sweet sweet : sweetsList) {
            if (sweet instanceof Candy) {
                Candy candy = (Candy) sweet;
                if (candy.getChocolateContent() >= min && candy.getChocolateContent() <= max) {
                    result.add(candy);
                }
            }
        }
        return result;
    }

    public void printGift(){
        for (Sweet sweet : sweetsList) {
            System.out.println(sweet.name + " - " + sweet.getWeight() + " kg");
        }
    }
}
