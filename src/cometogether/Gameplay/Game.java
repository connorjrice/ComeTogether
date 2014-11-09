package cometogether.Gameplay;

import cometogether.Gameplay.Objects.Entity;
import cometogether.Graphics.GameGUI;
import cometogether.Input.InputState;
import cometogether.StartMenu.StartMenuState;
import java.util.ArrayList;
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
    private StartMenuState startMenuState;
    private UserState userState;
    private CollisionState collisionState;
    private InputState inputState;
    private GameGUI gameGUI;
    private ArrayList<Entity> entities;
    private int win, lose;
    private boolean inGame;


    public Game(String title) {
        super(title);
        this.win = 0;
        this.lose = 0;
    }
    
    /**
     * Init states: obstacleState, userState, collisionState, inputState, 
     *              graphicsState
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
        this.startMenuState = new StartMenuState(this);
        entities = new ArrayList<>();
        beginGame();

    }
    
    public void openStartMenu() {
        
    }
    
    public void beginGame() {
        this.obstacleState = new ObstacleState(this);
        this.userState = new UserState(this);
        this.collisionState = new CollisionState(this);
        this.inputState = new InputState(this);
        this.gameGUI = new GameGUI(this);
        userState.createUserRect();
        addEntities(userState.getEntities());
        obstacleState.createBarriers();
        addEntities(obstacleState.getEntities());
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
        gameGUI.drawWinLoss(g);
        for (Entity e : entities) {
            if (e.isVisible()) {
                e.render(gc, g);
            }
        }
    }
    
    
    public void addEntity(Entity e) {
        entities.add(e);
    }
    
    public void addEntities(Entity[] e) {
        for (Entity ent : e) {
            entities.add(ent);
        }
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
    public Entity[] getUserRects() {
        return userState.getEntities();
    }
    
    /**
     * @return Shape[] barriers that userRect collide with
     */
    public Entity[] getBarriers() {
        return obstacleState.getEntities();
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
     * @return CollisionState collisionState
     */
    public CollisionState getCollisionState() {
        return collisionState;
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
        return gameGUI.getFont();
    }
    
    public void toggleInGame() {
        inGame = !inGame;
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