package cometogether.Gameplay;

import cometogether.Game;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
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
    
     public void checkCollisions() {
        if (checkBarrierCollision(g.getUserRects())) {
            g.incLose();
        }
        if (g.getUserRects()[0].intersects(g.getUserRects()[1])) {
            g.incWin();
        }
    }

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
    
    private boolean checkIntersection(Shape[] s) {
        return s[0].intersects(s[1]);
    }
    
}
