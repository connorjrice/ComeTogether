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
public class GameGUI {
    
    private Game game;
    private UserState userState;
    private ObstacleState obstacleState;
    private AngelCodeFont arialFont;
    private CollisionState collisionState;
    
    public GameGUI(Game g) {
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
    public void drawWinLoss(Graphics g) {
        drawString(20, 20, "Win: " + game.getWin()); 
        drawString(20, 50, "Lose: " + game.getLose());
    }
    
    private void initFont() {
        try {
            this.arialFont = new AngelCodeFont("arial.fnt", "arial_0.tga");
        } catch (SlickException ex) {
            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
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
