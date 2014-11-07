package cometogether.Gameplay;

import cometogether.Game;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor
 */
public class CollisionState {
    
    private Game g;
    private Shape windowBoundary;
    
    public CollisionState(Game g) {
        this.g = g;
        int userRectRadius = g.getUserState().getUserRectRadius();
        this.windowBoundary = new Rectangle(0,0,590,430);
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
    
    public Shape getWindowBoundary() {
        return windowBoundary;
    }
    
}
