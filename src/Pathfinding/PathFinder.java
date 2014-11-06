package Pathfinding;

/**
 *
 * @author Development
 */
public interface PathFinder {

    public Path pathFind(Path curPath);
    
    public Path getPath(Comparable startNode, Comparable endNode);
    
}
