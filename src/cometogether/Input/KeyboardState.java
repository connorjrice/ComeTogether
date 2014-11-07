package cometogether.Input;

import cometogether.Game;
import org.newdawn.slick.Input;

/**
 *
 * @author Connor
 */
public class KeyboardState {
    private Game g;
    private InputState iS;
    
    public KeyboardState(Game g, InputState iS) {
        this.g = g;
        this.iS = iS;
    }
    
    /**
     * Handles all movement initiated by the keyboard.
     */
    public void movementHandle() {
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
     * Handles non-movement related input initiated by keyboard.
     */
    public void inputHandle() {
        if (iS.getKey(Input.KEY_ESCAPE)) {
            g.exitGame();
        }
    }
    
    /**
     * @return true if allowed to move upwards using keyboard
     */
    private boolean getMoveUp() {
        return iS.getKey(Input.KEY_W)| iS.getKey(Input.KEY_UP)&&
                iS.getYBound(g.getUserRects()[0].getY(), true);
    }

    private boolean getMoveDown() {
        return iS.getKey(Input.KEY_S)| iS.getKey(Input.KEY_DOWN)&&
                iS.getYBound(g.getUserRects()[0].getY(), false);
    }
    
    private boolean getMoveLeft() {
        return iS.getKey(Input.KEY_A)| iS.getKey(Input.KEY_LEFT)&&
                iS.getXBound(g.getUserRects()[0].getX(), true);
    }
    
    private boolean getMoveRight() {
        return iS.getKey(Input.KEY_D)| iS.getKey(Input.KEY_RIGHT)&&
                iS.getXBound(g.getUserRects()[0].getX(), false);
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