package lainex.directed_acyclic_graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Coord2DTest {

    @Test
    void equalsShouldReturnTrueOnComparingEqualObjects() {
        Coord2D coord2D = new Coord2D(0, 0);
        assertEquals(coord2D, coord2D);
    }

    @Test
    void equalsShouldReturnFalseOnComparingObjectsOfDifferentTypes() {
        Coord2D coord2D = new Coord2D(0, 0);
        String str = "str";
        assertNotEquals(coord2D, str);
    }
}
