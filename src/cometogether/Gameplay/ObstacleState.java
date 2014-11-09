package cometogether.Gameplay;

import cometogether.Gameplay.Objects.Entity;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor
 */
public class ObstacleState {
    
    private Game g;
    private Shape[] barriers;
    private Entity[] entities;
    private Random r;
    
    public ObstacleState(Game g) {
        this.r = new Random();
        this.g = g;
    }
    
    /**
     * Creates barriers based on g.getWin()
     */
    public void createBarriers() {
        barriers = new Shape[g.getWin()+1];
        entities = new Entity[g.getWin()+1];
        for (int i = 0; i < g.getWin()+1; i++) {
            createNewBarrier(i);
        }
        for (int i = 0; i < g.getWin()+1; i++) {
            entities[i] = new Entity(barriers[i], Color.blue, true);
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
        for (Entity e : g.getUserRects()) {
            if (barriers[i].intersects(e.getShape())) {
                createNewBarrier(i);
            }
        }
        r.setSeed(System.nanoTime());
    }
    
    public Entity[] getEntities() {
        return entities;
    }
}
