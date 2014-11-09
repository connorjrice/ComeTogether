package cometogether.Graphics;

import cometogether.Gameplay.Game;
import cometogether.Gameplay.CollisionState;
import cometogether.Gameplay.ObstacleState;
import cometogether.Gameplay.UserState;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor
 */
public class GraphicsState {
    
    private Game game;
    private UserState userState;
    private ObstacleState obstacleState;
    private AngelCodeFont arialFont;
    private CollisionState collisionState;
    
    public GraphicsState(Game g) {
        this.game = g;
        this.userState = g.getUserState();
        this.obstacleState = g.getObstacleState();
        this.collisionState = g.getCollisionState();
        initFont();
    }
    
    /**
     * Draws the user rectangles and barriers.
     * @param g 
     */
    public void initialRender(Graphics g) {
        /*g.setColor(Color.red);
        g.draw(userState.getUserRects()[0]);
        g.setColor(Color.green);
        g.draw(userState.getUserRects()[1]);
        g.setColor(Color.blue);
        for (Shape s : obstacleState.getBarriers()) {
            g.draw(s);
        }
        */
        drawString(20, 20, "Win: " + game.getWin()); 
        drawString(20, 50, "Lose: " + game.getLose());
    }
    
    private void initFont() {
        try {
            this.arialFont = new AngelCodeFont("arial.fnt", "arial_0.tga");
        } catch (SlickException ex) {
            Logger.getLogger(GraphicsState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Uses the AngelCodeFont to draw a string at the specified position.
     * @param x
     * @param y
     * @param text 
     */
    private void drawString(int x, int y, String text) {
        arialFont.drawString(x, y, text);
    }
    
    /**
     * @return AngelCodeFont (currently Arial)
     */
    public AngelCodeFont getFont() {
        return arialFont;
    }
    
}
