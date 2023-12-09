package org.example;

import org.example.exception.SweetIllegalArgumentException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Sweet implementation.
 */
public class SweetTest {

    @Test
    public void testConstructorValidInput() {
        try {
            Sweet sweet = new Sweet("TestSweet", 50);
            assertNotNull(sweet);
            assertEquals("TestSweet", sweet.getName());
            assertEquals(50, sweet.getWeight(), 0.01);
        } catch (SweetIllegalArgumentException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testConstructorNullName() {
        assertThrows(SweetIllegalArgumentException.class, () -> new Sweet(null, 50));
    }

    @Test
    public void testConstructorEmptyName() {
        assertThrows(SweetIllegalArgumentException.class, () -> new Sweet("", 50));
    }

    @Test
    public void testConstructorNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Sweet("TestSweet", -50));
    }

    @Test
    public void testConstructorZeroWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Sweet("TestSweet", 0));
    }

    @Test
    public void testGetWeight() {
        try {
            Sweet sweet = new Sweet("TestSweet", 50);
            assertEquals(50, sweet.getWeight(), 0.01);
        } catch (SweetIllegalArgumentException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
