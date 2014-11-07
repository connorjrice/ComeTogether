package cometogether.Gameplay;

import cometogether.Game;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor
 */
public class CollisionState {
    
    private Game g;
    
    public CollisionState(Game g) {
        this.g = g;
    }
    
    /**
     * Checks to see if the user has collided with the other rectangle, or with
     * a barrier.
     * 
     * This is where the end of the game is initiated.
     */
    public void checkCollisions() {
        if (checkBarrierCollision(g.getUserRects())) {
            g.incLose();
            g.restart();
        }
        if (checkIntersection(g.getUserRects())) {
            g.incWin();
            g.restart();
        }
    }

    /**
     * Checks to see whether or not the userRects have collided with a barrier.
     * @param s
     * @return 
     */
    private boolean checkBarrierCollision(Shape[] s) {
        for (Shape barrier : g.getBarriers()) {
            for (Shape userRect : s) {
                if (barrier.intersects(userRect)) {
                        return true;
                }
            }

        }
        return false;
    } 
    
    /**
     * Checks to see if the user rectangles intersect each other.
     * @param s
     * @return 
     */
    private boolean checkIntersection(Shape[] s) {
        return s[0].intersects(s[1]);
    }
    
}
