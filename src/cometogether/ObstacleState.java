package cometogether;

import java.util.Random;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor
 */
public class ObstacleState {
    
    private Game g;
    private Shape[] barriers;
    private Random r;
    
    public ObstacleState(Game g) {
        this.r = new Random();
        this.g = g;
    }
    
    private void createBarriers() {
        barriers = new Shape[g.getWin()+1];
        for (int i = 0; i < g.getWin()+1; i++) {
            createNewBarrier(i);
        }
    }
    
        /**
     * Creates a new barrier shape that doesn't intersect any other barrier.
     * @param i 
     */
    private void createNewBarrier(int i) {
        barriers[i] = new Rectangle(25,25,25,25);
        barriers[i].setLocation(r.nextInt(g.getWidth()-50)
                , r.nextInt(g.getHeight()-50));
        for (int j = 0; j < i; j++) {
            if (barriers[i].intersects(barriers[j])) {
                createNewBarrier(i);
            }
        }
        for (Shape s : g.getUserRects()) {
            if (barriers[i].intersects(s)) {
                createNewBarrier(i);
            }
        }
        r.setSeed(System.nanoTime());
    }
    
    
    public Shape[] getBarriers() {
        createBarriers();
        return barriers;
    }
}
