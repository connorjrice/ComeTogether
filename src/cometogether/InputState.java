package cometogether;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

/**
 * Input handling
 * @author Connor Rice
 */
public class InputState {
    
    private Game g;
    private GameContainer gameContainer;
    private int width, height;
    private int initialX, initialY;
    private int lastMouseX, lastMouseY;
    private int lastX, lastY;
    private MouseListener mL;
    private boolean isRotate;
    private boolean mouseDynamic;
 
    public InputState(Game g) {
        this.g = g;
        this.gameContainer = g.getGameContainer();
        this.width = g.getWidth();
        isRotate = false;
        this.height = g.getHeight() - 25; // Modified for edge detection
        this.mouseDynamic = false;
    }

    /**
     * Handles all keyboard/mouse/touch input.
     */
    public void inputHandle() {
        if (g.getKeys()[Input.KEY_ESCAPE]) {
            g.exitGame();
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_SPACE)) {
            toggleMouseDynamic();
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_R)) {
            toggleRotate();
        }
        if (getMouseDynamic()) {
            mouseHandle();
        } else {
            keyboardHandle();
        }
    }
    
    private void keyboardHandle() {
        if (getMoveUp()) {
            moveUpKeyboard();
        }
        if (getMoveDown()) {
            moveDownKeyboard();
        }
        if (getMoveLeft()) {
            moveLeftKeyboard();
        }
        if (getMoveRight()) {
            moveRightKeyboard();
        } 
    }
    
    /**
     * Handles mouse movement, including rotation.
     * @param gc 
     */
    private void mouseHandle() {
        if (getMouseMovementAllowed()) {
            mouseHandler();
        }
    }
    
    private void mouseHandler() {
        g.getUserRects()[0].setCenterX(gameContainer.getInput().getMouseX());
        g.getUserRects()[0].setCenterY(gameContainer.getInput().getMouseY());
        if (isRotate) {
            checkForMouseMovement();
        } else {
            g.getUserRects()[1].setCenterX(gameContainer.getWidth()
                    -gameContainer.getInput().getMouseX());
            g.getUserRects()[1].setCenterY(gameContainer.getInput().getMouseY());
        }
    }
    
    private void checkForMouseMovement() {
        int curMouseX = gameContainer.getInput().getMouseX(); 
        int curMouseY = gameContainer.getInput().getMouseY();
        
        if (lastMouseX != curMouseX) {
            g.getUserRects()[1].setCenterX(g.getUserRects()[1].getCenterX()+(lastMouseX-curMouseX));
            g.getUserRects()[1].setCenterY(g.getUserRects()[1].getCenterY()+(lastMouseY-curMouseY));
            lastMouseX = curMouseX;
            lastMouseY = curMouseY;
        }
    }
    
    /**
     * Working method that jumps the secondary square (bad)
     * @param gc 
     */
    private void badMouseRotation(GameContainer gc) {
        g.getUserRects()[0].setCenterX(gc.getInput().getMouseX());
        g.getUserRects()[0].setCenterY(gc.getInput().getMouseY());
        if (isRotate) {
            g.getUserRects()[1].setCenterX(gc.getWidth()-gc.getInput().getMouseX());
            g.getUserRects()[1].setCenterY(gc.getHeight()-gc.getInput().getMouseY());
        } else {
            g.getUserRects()[1].setCenterX(gc.getWidth()-gc.getInput().getMouseX());
            g.getUserRects()[1].setCenterY(gc.getInput().getMouseY());
        }
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
    
    /**
     * @return true if mouse position is within bounds of screen.
     */
    private boolean getMouseMovementAllowed() {
        return (25 < gameContainer.getInput().getMouseX() &&
                gameContainer.getInput().getMouseX() < width &&
                25 < gameContainer.getInput().getMouseY() &&
                gameContainer.getInput().getMouseY() < height
                );
    }
    
    /**
     * @return true if mouse/touch movement is enabled.
     */
    private boolean getMouseDynamic() {
        return mouseDynamic;
    }
    

    
/*    private int getMouseChangeX() {
        if (gameContainer.getInput().getMouseX() == lastMouseX) {
            return 0;
        } else {
            if (gameContainer.getInput().getMouseX() > lastMouseX) {
                return lastMouseX-gameContainer.getInput().getMouseX();
            } else {
                return lastMouseX+gameContainer.getInput().getMouseX();               
            }

        }
    }
    
    private int getMouseChangeY() {
        if (gameContainer.getInput().getMouseY() == lastMouseY) {
            return 0;
        } else {
            if (gameContainer.getInput().getMouseY() > lastMouseY) {
                return lastMouseY-gameContainer.getInput().getMouseY();
            } else {
                return lastMouseY+gameContainer.getInput().getMouseY();               
            }
        }
    } */
    
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
    
    private boolean getMoveUp() {
        return g.getKeys()[Input.KEY_W] | g.getKeys()[Input.KEY_UP] &&
                getYBound(g.getUserRects()[0].getY(), true);
    }

    private boolean getMoveDown() {
        return g.getKeys()[Input.KEY_S] | g.getKeys()[Input.KEY_DOWN] &&
                getYBound(g.getUserRects()[0].getY(), false);
    }
    
    private boolean getMoveLeft() {
        return g.getKeys()[Input.KEY_A] | g.getKeys()[Input.KEY_LEFT] &&
                getXBound(g.getUserRects()[0].getX(), true);
    }
    
    private boolean getMoveRight() {
        return g.getKeys()[Input.KEY_D] | g.getKeys()[Input.KEY_RIGHT] &&
                getXBound(g.getUserRects()[0].getX(), false);
    }
    
    private void moveUpKeyboard() {
        g.getUserRects()[0].setY(g.getUserRects()[0].getY()-1);
        g.getUserRects()[1].setY(g.getUserRects()[1].getY()-1);
    }
    
    private void moveDownKeyboard() {
        g.getUserRects()[0].setY(g.getUserRects()[0].getY()+1);
        g.getUserRects()[1].setY(g.getUserRects()[1].getY()+1);  
    }
    
    private void moveLeftKeyboard() {
        g.getUserRects()[0].setX(g.getUserRects()[0].getX()-1);
        g.getUserRects()[1].setX(g.getUserRects()[1].getX()+1);  

    }
    
    private void moveRightKeyboard() {
        g.getUserRects()[0].setX(g.getUserRects()[0].getX()+1);
        g.getUserRects()[1].setX(g.getUserRects()[1].getX()-1);
    }
 }