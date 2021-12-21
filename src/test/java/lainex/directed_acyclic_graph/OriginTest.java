package lainex.directed_acyclic_graph;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OriginTest {

    @Test
    void getBoundsShouldReturnValidBoundBoxOnOriginWithTwoPointsInChildren() {
        Origin origin = new Origin(new Coord2D(1, 1));
        Point p1 = new Point(new Coord2D(2, 1));
        Point p2 = new Point(new Coord2D(4, 2));
        Set<Point> set = new HashSet<>();
        set.add(p1);
        set.add(p2);

        assertAll(() -> {
            origin.setChildren(set);
            BoundBox boundBox = origin.getBounds();
            assertEquals(boundBox.getMaxCoord(), p2.getPosition());
            assertEquals(boundBox.getMinCoord(), p1.getPosition());
        });
    }

    @Test
    void getBoundsShouldReturnValidBoundBoxOnOriginWithOriginInChildren() {
        Origin origin = new Origin(new Coord2D(0, 0));
        Origin origin1 = new Origin(new Coord2D(2, 5));
        Point point = new Point(new Coord2D(1, -1));

        assertAll(() -> {
            Set<Point> set1 = new HashSet<>();
            set1.add(point);
            origin1.setChildren(set1);

            Set<Point> set = new HashSet<>();
            set.add(origin1);
            origin.setChildren(set);

            BoundBox boundBox = origin.getBounds();
            assertEquals(boundBox.getMaxCoord(), new Coord2D(3, 4));
            assertEquals(boundBox.getMinCoord(), new Coord2D(3, 4));
        });
    }

    @Test
    void getBoundsShouldThrowDAGConstraintExceptionOnCyclicGraph() {
        Origin origin = new Origin(new Coord2D(0, 0));
        Origin origin1 = new Origin(new Coord2D(2, 2));

        Throwable exception = assertThrows(DAGConstraintException.class, () -> {
            Set<Point> set = new HashSet<>();
            set.add(origin1);
            origin.setChildren(set);

            Set<Point> set1 = new HashSet<>();
            set1.add(origin);
            origin1.setChildren(set1);
        });
    }
}
