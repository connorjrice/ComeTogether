package cometogether.Gameplay;

import cometogether.Gameplay.Objects.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor
 */
public class UserState {
    
    private Game g;
    private int width, height;
    private Shape[] userRects;
    private Entity[] entities;

    
    public UserState(Game g) {
        this.g = g;
        this.width = g.getWidth();
        this.height = g.getHeight();

    }
    
    /**
     * Creates 2 rectangles, calls function to set starting position.
     */
    public void createUserRect() {
        userRects = new Shape[] {new Rectangle(50, 50, 50, 50),
            new Rectangle(50, 50, 50, 50)};
        setInitialUserRectPosition();
        entities = new Entity[] { new Entity(userRects[0], Color.red, true),
            new Entity(userRects[1], Color.green, true)};
    }
    
    public Entity createEntity(Shape s, Color c) {
        return new Entity(s, c, true);
    }
    
    private void setInitialUserRectPosition() {
        userRects[0].setLocation(10, height/2);
        userRects[1].setLocation(width-60, height/2);
    }
    
    public Entity[] getEntities() {
        return entities;
    }
    
    public int getUserRectRadius() {
        return 100;
    }
}
