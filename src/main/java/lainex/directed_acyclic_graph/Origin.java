package lainex.directed_acyclic_graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Class represents parent node and a coordinate system with shift.
 */
public class Origin extends Point{
    private Coord2D position;
    private Set<Point> children;

    public Origin(Coord2D position) {
        super(position);
        children = new HashSet<Point>();
    }

    public Set<Point> getChildren() {
        return new HashSet<Point>(children);
    }

    public void setChildren(Set<Point> newChildren) throws DAGConstraintException {
        try {
            Set<Point> used = new HashSet<Point>();
            used.add(this);
            traverse(newChildren, used);
        } catch(DAGConstraintException ex) {
            throw new DAGConstraintException(ex.getMessage());
        }
        children = newChildren;
    }

    @Override
    public BoundBox getBounds() {
        BoundBox boundBox = new BoundBox(new Coord2D(0, 0), new Coord2D(0, 0), BoundBoxType.DEFAULT);
        if (!children.isEmpty()) {
            for (Point child : children) {
                if (child.getClass() == Origin.class) {
                    boundBox = boundBox.merge(moveBounds(child.getBounds(), child.getPosition()));
                } else {
                    boundBox = boundBox.merge(child.getBounds());
                }
            }
        }

        return boundBox;
    }

    /**
     * Method shifts the coordinates when moved to another parent coordinate system.
     * @param boundBox BoundBox that should be moved.
     * @param parentPosition position of parent node.
     * @return
     */
    private BoundBox moveBounds(BoundBox boundBox, Coord2D parentPosition) {

        return new BoundBox(new Coord2D(boundBox.getMinCoord().getX() + parentPosition.getX(),
                boundBox.getMinCoord().getY() + parentPosition.getY()),
                new Coord2D(boundBox.getMaxCoord().getX() + parentPosition.getX(),
                        boundBox.getMaxCoord().getY() + parentPosition.getY()), BoundBoxType.NORMAL);
    }

    /**
     * Method that checks DAG for cycles.
     * @param newChildren set of new children nodes.
     * @param used set of nodes that were passed already.
     * @throws DAGConstraintException thrown when a cycle is found.
     */
    private void traverse(Set<Point> newChildren, Set<Point> used) throws DAGConstraintException {
        for (Point child : newChildren) {
            if (!used.contains(child) && child.getClass() == Origin.class) {
                traverse(((Origin) child).getChildren(), used);
            } else if (used.contains(child)) {
                throw new DAGConstraintException("Cycle was found.");
            }
            used.add(child);
        }
    }
}
