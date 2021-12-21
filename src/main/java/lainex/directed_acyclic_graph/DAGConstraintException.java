package lainex.directed_acyclic_graph;

/**
 * This class represents an exception that is thrown on cycling graphs.
 */
public class DAGConstraintException extends Exception {
    public DAGConstraintException(String message) {
        super(message);
    }
}
