package lainex.directed_acyclic_graph;

/**
 * Class represents a global coordinate system and the root node of graph.
 */
public class Space {
    private Coord2D globalCoordinateSystem;
    private final Point root;

    public Space(Coord2D coord2D, Point point) {
        globalCoordinateSystem = coord2D;
        root = point;
    }

    public Coord2D getGlobalCoordinateSystem() {
        return globalCoordinateSystem;
    }

    public void setGlobalCoordinateSystem(Coord2D newSystem) {
        globalCoordinateSystem = newSystem;
        root.setPosition(new Coord2D(root.getPosition().getX() - newSystem.getX(),
                        root.getPosition().getY() - newSystem.getY()));
    }

    public Point getRoot() {
        return root;
    }

    public BoundBox getBounds() {
        return new BoundBox(
                new Coord2D(root.getBounds().getMinCoord().getX() + root.getPosition().getX(),
                        root.getBounds().getMinCoord().getY() + root.getPosition().getY()),
                new Coord2D(root.getBounds().getMaxCoord().getX() + root.getPosition().getX(),
                        root.getBounds().getMaxCoord().getY() + root.getPosition().getY()), BoundBoxType.NORMAL);
    }
}
