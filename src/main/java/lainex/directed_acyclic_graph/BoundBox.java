package lainex.directed_acyclic_graph;

final class BoundBox {
    private final Coord2D minCoord;
    private final Coord2D maxCoord;
    private final BoundBoxType boundBoxType;

    public BoundBox(Coord2D minCoord, Coord2D maxCoord, BoundBoxType type) {
        boundBoxType = type;
        this.minCoord = minCoord;
        this.maxCoord = maxCoord;
    }

    public Coord2D getMinCoord() {
        return minCoord;
    }

    public Coord2D getMaxCoord() {
        return maxCoord;
    }

    /**
     * Method merges two boundBoxes.
     * @param other second BoundBox to be merged.
     * @return union of two BoundBoxes.
     */
    public BoundBox merge(BoundBox other) {
        if (other.boundBoxType == BoundBoxType.DEFAULT) {
            return this;
        }
        if (this.boundBoxType == BoundBoxType.DEFAULT) {
            return other;
        }
        double maxX = Math.max(maxCoord.getX(), other.getMaxCoord().getX());
        double maxY = Math.max(maxCoord.getY(), other.getMaxCoord().getY());
        double minX = Math.min(minCoord.getX(), other.getMinCoord().getX());
        double minY = Math.min(minCoord.getY(), other.getMinCoord().getY());

        return new BoundBox(new Coord2D(minX, minY), new Coord2D(maxX, maxY), BoundBoxType.NORMAL);
    }
}

/**
 * Enum represents types of BoundBoxes.
 */
enum BoundBoxType {
    DEFAULT,
    NORMAL
}

