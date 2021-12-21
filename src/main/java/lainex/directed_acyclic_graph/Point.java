package lainex.directed_acyclic_graph;

import java.util.Objects;

/**
 * Class represents a node in general and could be a leaf node of a graph.
 */
public class Point {
    private Coord2D position;

    public Point(Coord2D position) {
        this.position = position;
    }

    public Coord2D getPosition() {
        return position;
    }

    public void setPosition(Coord2D newValue) {
        this.position = newValue;
    }

    public BoundBox getBounds() {
        return new BoundBox(position, position, BoundBoxType.NORMAL);
    }
}
