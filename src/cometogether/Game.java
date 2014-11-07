package cometogether;

import cometogether.Gameplay.CollisionState;
import cometogether.Graphics.GUIState;
import cometogether.Graphics.GraphicsState;
import cometogether.Gameplay.ObstacleState;
import cometogether.Gameplay.UserState;
import cometogether.Input.InputState;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor Rice
 */

public class Game extends BasicGame {
    
    private GameContainer gameContainer;
    private ObstacleState obstacleState;
    private UserState userState;
    private CollisionState collisionState;
    private InputState inputState;
    private GraphicsState graphicsState;
    private GUIState guiState;
    private AngelCodeFont arialFont;
    private int win, lose;


    public Game(String title) {
        super(title);
        this.win = 0;
        this.lose = 0;
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        this.gameContainer = gc;
        this.obstacleState = new ObstacleState(this);
        this.userState = new UserState(this);
        this.collisionState = new CollisionState(this);
        this.inputState = new InputState(this);
        this.guiState = new GUIState(this);
        this.graphicsState = new GraphicsState(this);
        this.arialFont = new AngelCodeFont("arial.fnt", "arial_0.tga");
        userState.createUserRect();
        obstacleState.createBarriers();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        inputState.inputHandle();
        collisionState.checkCollisions();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
//        guiState.createGUI(gc, g);
        initialRender(g);
        drawString(20, 20, "Win: " + win); 
        drawString(20, 50, "Lose: " + lose);
    }
    
    /**
     * Draws the user rectangles and barriers.
     * @param g 
     */
    private void initialRender(Graphics g) {
        g.setColor(Color.red);
        g.draw(userState.getUserRects()[0]);
        g.setColor(Color.green);
        g.draw(userState.getUserRects()[1]);
        g.setColor(Color.blue);
        for (Shape s : obstacleState.getBarriers()) {
            g.draw(s);
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
   
    public void incWin() {
        try {
            win++;
            gameContainer.reinit();
        } catch (SlickException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void incLose() {
        try {
            lose++;
            gameContainer.reinit();
        } catch (SlickException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getWin() {
        return win;
    }
    
    public int getHeight() {
        return gameContainer.getHeight();
    }
    
    public int getWidth() {
        return gameContainer.getWidth();
    }
    
    public Shape[] getUserRects() {
        return userState.getUserRects();
    }
    
    public Shape[] getBarriers() {
        return obstacleState.getBarriers();
    }
    
    public GameContainer getGameContainer() {
        return gameContainer;
    }
    
    public Graphics getGraphics() {
        return gameContainer.getGraphics();
    }
    
    public AngelCodeFont getFont() {
        return arialFont;
    }
    
    public void exitGame() {
        System.exit(0);
    }
            
    @Override
    public void keyPressed(int key, char c) {
        inputState.setKey(key, true);
    }
    
    @Override
    public void keyReleased(int key, char c) {
        inputState.setKey(key, false);
    }    
    

}
