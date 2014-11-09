package cometogether.Input;

import cometogether.Gameplay.Game;
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
    private Input input;
    private int width, height;
    private boolean[] keys;
    private int lastMouseX, lastMouseY;
    private boolean isRotate;
    private boolean mouseDynamic;
 
    public InputState(Game g) {
        this.g = g;
        this.gameContainer = g.getGameContainer();
        this.input = gameContainer.getInput();
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
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            toggleMouseDynamic();
        }
        if (input.isKeyPressed(Input.KEY_R)) {
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
        g.getUserRects()[0].getShape().setCenterX(input.getMouseX());
        g.getUserRects()[0].getShape().setCenterY(input.getMouseY());
    }
    
    public void rotateShapes() {
        g.getUserRects()[1].getShape().setCenterX(g.getUserRects()[1].getShape().getCenterX()+(lastMouseX-input.getMouseX()));
        g.getUserRects()[1].getShape().setCenterY(g.getUserRects()[1].getShape().getCenterY()+(lastMouseY-input.getMouseY()));
    }
    
    public void mirrorShapes() {
        g.getUserRects()[1].getShape().setCenterX(g.getUserRects()[1].getShape().getCenterX()-(input.getMouseX()-lastMouseX));
        g.getUserRects()[1].getShape().setCenterY(g.getUserRects()[1].getShape().getCenterY()+(input.getMouseY()-lastMouseY));
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
        if (g.getUserRects()[0].getShape().contains(input.getMouseX(), 
                input.getMouseY()) || 
                g.getUserRects()[1].getShape().contains(input.getMouseX(), 
                input.getMouseY())) {
            mouseDynamic = !mouseDynamic;
            lastMouseX = input.getMouseX();
            lastMouseY = input.getMouseY();
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