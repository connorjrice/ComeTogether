package cometogether;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor Rice
 */

public class Game extends BasicGame {
    
    private GameContainer gameContainer;
    private ObstacleState obstacleState;
    private InputState inputState;
    private GraphicsState graphicsState;
    private GUIState guiState;
    private AngelCodeFont arialFont;
    private boolean keys[];

    private int win, lose;
    private Shape[] userRects;
    private Shape[] barriers;

    public Game(String title) {
        super(title);
        this.win = 0;
        this.lose = 0;
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        this.gameContainer = gc;
        this.obstacleState = new ObstacleState(this);
        this.inputState = new InputState(this);
        this.guiState = new GUIState(this);
        this.graphicsState = new GraphicsState(this);
        this.arialFont = new AngelCodeFont("arial.fnt", "arial_0.tga");
        this.keys = new boolean[256];

        createUserRect();
        setInitialUserRectPosition();
        this.barriers = obstacleState.getBarriers();
    }
    


    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        inputState.inputHandle();
        checkCollisions(gc);
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
        g.draw(userRects[0]);
        g.setColor(Color.green);
        g.draw(userRects[1]);
        g.setColor(Color.blue);
        for (Shape s : barriers) {
            g.draw(s);
        }
    }
    
    private void createUserRect() {
        this.userRects = new Shape[] {new Rectangle(50, 50, 50, 50),
            new Rectangle(50, 50, 50, 50)};
    }

    
    private void setInitialUserRectPosition() {
        userRects[0].setLocation(10, gameContainer.getHeight()/2);
        userRects[1].setLocation(gameContainer.getWidth()-60, gameContainer.getHeight()/2);
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
     
    private void checkCollisions(GameContainer gc) {
        if (checkCollision(userRects[0]) | checkCollision(userRects[1])) {
            try {
                lose++;
                gc.reinit();
            } catch (SlickException ex) {
                Logger.getLogger(Game.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        if (userRects[0].intersects(userRects[1])) {
            try {
                win++;
                gc.reinit();
            } catch (SlickException ex) {
                Logger.getLogger(Game.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }
    
    private boolean checkCollision(Shape s) {
        for (Shape barrier : barriers) {
            if (s.intersects(barrier)) {
                return true;
            }
        }
        return false;
    }
    

    
    public boolean[] getKeys() {
        return keys;
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
        return userRects;
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
        keys[key] = true;
    }
    
    @Override
    public void keyReleased(int key, char c) {
        keys[key] = false;
    }    
    

}
