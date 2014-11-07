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
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

/**
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
    private int win, lose;


    public Game(String title) {
        super(title);
        this.win = 0;
        this.lose = 0;
    }
    
    /**
     * Init states: obstacleState, userState, collisionState, inputState, 
     *              graphicsState, guiState
     * 
     * Init resorc: gameContainer
     * 
     * Calls: userState.createUserRect(), obsctacleState.createBarriers().
     * 
     * @param gc GameContainer
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        this.gameContainer = gc;
        this.obstacleState = new ObstacleState(this);
        this.userState = new UserState(this);
        this.collisionState = new CollisionState(this);
        this.inputState = new InputState(this);
        this.guiState = new GUIState(this);
        this.graphicsState = new GraphicsState(this);
        userState.createUserRect();
        obstacleState.createBarriers();
    }

    /**
     * Calls for input and collision handling.
     * @param gc
     * @param i
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        inputState.inputHandle();
        collisionState.checkCollisions();
    }

    /**
     * Calls initialRender in graphicsState
     * @param gc
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        //guiState.createGUI(gc, g);
        graphicsState.initialRender(g);

    }
    
   /**
    * Increments win counter.
    */
    public void incWin() {
        win++;
    }
    
    /**
     * Increments loss counter.
     */
    public void incLose() {
        lose++;
    }
    
    /**
     * Reinit game.
     */
    public void restart() {
        try {
            gameContainer.reinit();
        } catch (SlickException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return number of user wins
     */
    public int getWin() {
        return win;
    }
    
    /**
     * @return int number of user losses
     */
    public int getLose() {
        return lose;
    }
    
    /**
     * @return int height of window
     */
    public int getHeight() {
        return gameContainer.getHeight();
    }
    
    /**
     * @return int width of window
     */
    public int getWidth() {
        return gameContainer.getWidth();
    }
    
    /**
     * @return Shape[] userRectangles, controlled by used
     */
    public Shape[] getUserRects() {
        return userState.getUserRects();
    }
    
    /**
     * @return Shape[] barriers that userRect collide with
     */
    public Shape[] getBarriers() {
        return obstacleState.getBarriers();
    }
    
    /**
     * @return UserState userState
     */
    public UserState getUserState() {
        return userState;
    }
    
    /**
     * @return ObstacleState obstacleState
     */
    public ObstacleState getObstacleState() {
        return obstacleState;
    }
    
    /**
     * @return GameContainer gameContainer
     */
    public GameContainer getGameContainer() {
        return gameContainer;
    }
    
    /**
     * @return Graphics from gameContainer
     */
    public Graphics getGraphics() {
        return gameContainer.getGraphics();
    }
    
    /**
     * @return AngelCodeFont from GraphicsState
     */
    public AngelCodeFont getFont() {
        return graphicsState.getFont();
    }
    
    /**
     * Exits game with system code 0
     */
    public void exitGame() {
        System.exit(0);
    }
            
    /**
     * Updates keys[key] in InputState to true
     * @param key
     * @param c 
     */
    @Override
    public void keyPressed(int key, char c) {
        inputState.setKey(key, true);
    }
    
    /**
     * Updates keys[key] to false
     * @param key
     * @param c 
     */
    @Override
    public void keyReleased(int key, char c) {
        inputState.setKey(key, false);
    }    

}