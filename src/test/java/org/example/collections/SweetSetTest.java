package org.example.collections;

import org.example.Candy;
import org.example.Cookie;
import org.example.Sweet;
import org.example.exception.SweetIllegalArgumentException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the SweetSet implementation.
 */
public class SweetSetTest {

    private static Candy candy;
    private static Cookie cookie;

    @BeforeAll
    public static void setUp() {
        try{
             candy = new Candy("Chocolate", 10, 0.7);
             cookie = new Cookie("Cookie", 15, true);
        } catch (SweetIllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testConstructors() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        assertTrue(sweetSet.isEmpty());

        SweetSet<Sweet> sweetSet2 = new SweetSet<>(List.of(candy, cookie));
        assertTrue(sweetSet2.contains(candy));

        SweetSet<Sweet> sweetSet3 = new SweetSet<>(candy);
        assertTrue(sweetSet3.contains(candy));
    }


    @Test
    public void testAdd() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        assertTrue(sweetSet.add(candy));
        assertTrue(sweetSet.contains(candy));
        assertFalse(sweetSet.add(candy));
    }

    @Test
    public void testAddAll() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        assertTrue(sweetSet.addAll(List.of(candy, cookie)));
        assertTrue(sweetSet.contains(candy));
        assertTrue(sweetSet.contains(cookie));
    }

    @Test
    public void testRemove() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.add(candy);
        assertTrue(sweetSet.contains(candy));
        assertTrue(sweetSet.remove(candy));
        assertFalse(sweetSet.contains(candy));
    }

    @Test
    public void testRemoveAll() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.addAll(List.of(candy, cookie));
        assertTrue(sweetSet.contains(candy));
        assertTrue(sweetSet.contains(cookie));
        assertTrue(sweetSet.removeAll(List.of(candy, cookie)));
        assertFalse(sweetSet.contains(candy));
        assertFalse(sweetSet.contains(cookie));
    }

    @Test
    public void testContainsAll() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.addAll(List.of(candy, cookie));
        assertTrue(sweetSet.containsAll(List.of(candy, cookie)));
    }

    @Test
    public void testClear() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.addAll(List.of(candy, cookie));
        assertTrue(sweetSet.contains(candy));
        assertTrue(sweetSet.contains(cookie));
        sweetSet.clear();
        assertFalse(sweetSet.contains(candy));
        assertFalse(sweetSet.contains(cookie));
    }

    @Test
    public void testRetainAll() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.addAll(List.of(candy, cookie));
        assertTrue(sweetSet.contains(candy));
        assertTrue(sweetSet.contains(cookie));
        assertTrue(sweetSet.retainAll(List.of(candy)));
        assertTrue(sweetSet.contains(candy));
        assertFalse(sweetSet.contains(cookie));
    }

    @Test
    public void testSize() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.addAll(List.of(candy, cookie));
        assertEquals(2, sweetSet.size());
    }

    @Test
    public void testIsEmpty() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        assertTrue(sweetSet.isEmpty());
        sweetSet.add(candy);
        assertFalse(sweetSet.isEmpty());
    }

    @Test
    public void testContains()  {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.add(candy);
        assertTrue(sweetSet.contains(candy));

    }

    @Test
    public void testToArray() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.add(candy);
        Object[] sweets = sweetSet.toArray();
        assertEquals(1, sweets.length);
        assertTrue(sweets[0] instanceof Candy);
    }

    @Test
    public void testToArrayWithParameter() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.add(candy);
        Sweet[] sweets = sweetSet.toArray(new Sweet[0]);
        assertEquals(1, sweets.length);
        assertTrue(sweets[0] instanceof Candy);
    }

    @Test
    public void testIterator() {
        SweetSet<Sweet> sweetSet = new SweetSet<>();
        sweetSet.add(candy);

        Iterator<Sweet> iterator = sweetSet.iterator();
        assertTrue(iterator.hasNext());

        Sweet retrievedSweet = iterator.next();
        assertTrue(retrievedSweet instanceof Candy);

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testEquals() {
        SweetSet<Sweet> sweetSet1 = new SweetSet<>();
        SweetSet<Sweet> sweetSet2 = new SweetSet<>();
        sweetSet1.add(candy);
        sweetSet2.add(candy);
        assertTrue(sweetSet1.equals(sweetSet2));
    }

    @Test
    public void testHashCode() {
        SweetSet<Sweet> sweetSet1 = new SweetSet<>();
        SweetSet<Sweet> sweetSet2 = new SweetSet<>();
        sweetSet1.add(candy);
        sweetSet2.add(candy);
        assertEquals(sweetSet1.hashCode(), sweetSet2.hashCode());
    }

}
