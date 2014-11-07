package cometogether.Input;

import cometogether.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 * Input handling
 * TODO: Fix bug where clicking on square sets location to center
 * @author Connor Rice
 */
public class InputState {
    
    private Game g;
    private GameContainer gameContainer;
    private KeyboardState keyboardState;
    private MouseState mouseState;
    private int width, height;
    private boolean[] keys;
    private int lastMouseX, lastMouseY;
    private boolean isRotate;
    private boolean mouseDynamic;
 
    public InputState(Game g) {
        this.g = g;
        this.gameContainer = g.getGameContainer();
        this.keyboardState = new KeyboardState(g, this);
        this.mouseState = new MouseState(g, this);
        this.width = g.getWidth();
        this.keys = new boolean[256];
        isRotate = false;
        this.height = g.getHeight() - 25; // Modified for edge detection
        this.mouseDynamic = false;
    }

    /**
     * Handles all keyboard/mouse/touch input.
     */
    public void inputHandle() {
        if (gameContainer.getInput().isKeyPressed(Input.KEY_SPACE)) {
            toggleMouseDynamic();
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_R)) {
            toggleRotate();
        }
        if (getMouseDynamic()) {
            mouseState.mouseHandle();
        } else {
            keyboardState.movementHandle();
        }
        keyboardState.inputHandle();

    }
    
    public void moveFirstShape() {
        g.getUserRects()[0].setCenterX(gameContainer.getInput().getMouseX());
        g.getUserRects()[0].setCenterY(gameContainer.getInput().getMouseY());
    }
    
    public void rotateShapes() {
        g.getUserRects()[1].setCenterX(g.getUserRects()[1].getCenterX()+(lastMouseX-gameContainer.getInput().getMouseX()));
        g.getUserRects()[1].setCenterY(g.getUserRects()[1].getCenterY()+(lastMouseY-gameContainer.getInput().getMouseY()));
    }
    
    public void mirrorShapes() {
        g.getUserRects()[1].setCenterX(g.getUserRects()[1].getCenterX()-(gameContainer.getInput().getMouseX()-lastMouseX));
        g.getUserRects()[1].setCenterY(g.getUserRects()[1].getCenterY()+(gameContainer.getInput().getMouseY()-lastMouseY));
    }
    
    /**
     * Toggles between rotation and equivalent motion.
     */
    private void toggleRotate() {
        isRotate = !isRotate;
    }
    
    /**
     * Toggles between mouse/touch movement and keyboard.
     * @param gc GameContainer
     */
    public void toggleMouseDynamic() {
        if (g.getUserRects()[0].contains(gameContainer.getInput().getMouseX(), 
                gameContainer.getInput().getMouseY()) || 
                g.getUserRects()[1].contains(gameContainer.getInput().getMouseX(), 
                gameContainer.getInput().getMouseY())) {
            mouseDynamic = !mouseDynamic;
            lastMouseX = gameContainer.getInput().getMouseX();
            lastMouseY = gameContainer.getInput().getMouseY();
        }
    }
    
    public boolean getIsRotate() {
        return isRotate;
    }
    
    /**
     * @return true if mouse/touch movement is enabled.
     */
    private boolean getMouseDynamic() {
        return mouseDynamic;
    }
    
    public void setLastMousePos(int lastX, int lastY) {
        lastMouseX = lastX;
        lastMouseY = lastY;
    }

    public boolean getYBound(float y, boolean high) {
        if (high) {
            return y > 1;
        } else {
            return y < gameContainer.getHeight() - 51;
        }
    }
    
    public boolean getXBound(float x, boolean right) {
        if (right) {
            return x > 1;
        } else {
            return x < gameContainer.getWidth()-51;
        }
    }
    
    public void setKey(int i, boolean b) {
        keys[i] = b;
    }
    
    public boolean getKey(int i) {
        return keys[i];
    }
 
 }