package cometogether.Gameplay;

import cometogether.Game;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor
 */
public class UserState {
    
    private Game g;
    private int width, height;
    
    public UserState(Game g) {
        this.g = g;
        this.width = g.getWidth();
        this.height = g.getHeight();
    }
    
    private Shape[] userRects;
    
    public void createUserRect() {
        this.userRects = new Shape[] {new Rectangle(50, 50, 50, 50),
            new Rectangle(50, 50, 50, 50)};
        setInitialUserRectPosition();
    }

    
    private void setInitialUserRectPosition() {
        userRects[0].setLocation(10, height/2);
        userRects[1].setLocation(width-60, height/2);
    }
    
    public Shape[] getUserRects() {
        return userRects;
    }
    
    
}
