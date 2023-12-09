package org.example;

import org.example.exception.SweetIllegalArgumentException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Gift implementation.
 */
public class GiftTest {

    private Gift<Sweet> gift;
    private static Candy candy;
    private static Cookie cookie;
    private static Candy candy2;

    @BeforeAll
    public static void setUpBeforeAll() {
        try{
             candy = new Candy("Chocolate", 10, 0.5);
             candy2 = new Candy("Chocolate", 10, 0.8);
             cookie = new Cookie("Cookie", 15, true);
        } catch (SweetIllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() {
        gift = new Gift<>();
    }

    @Test
    public void testAddSweet() {
        gift.addSweet(candy);
        assertTrue(gift.sweetsList.contains(candy));
    }

    @Test
    public void testCalculateWeight() {
        gift.addSweet(candy);
        gift.addSweet(cookie);
        assertEquals(25, gift.calculateWeight(), 0.001);
    }

    @Test
    public void testSortSweets() {
        gift.addSweet(cookie);
        gift.addSweet(candy);
        gift.sortSweets(Comparator.comparingDouble(Sweet::getWeight));
        assertEquals(candy, gift.sweetsList.get(0));
        assertEquals(cookie, gift.sweetsList.get(1));
    }

    @Test
    public void testFindCandyByChocolateContent() {
        gift.addSweet(candy);
        gift.addSweet(candy2);
        List<Candy> result = gift.findCandyByChocolateContent(0.7, 0.8);
        assertEquals(1, result.size());
        assertEquals(candy2, result.get(0));
    }
}
