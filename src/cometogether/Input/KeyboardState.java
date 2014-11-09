package cometogether.Input;

import cometogether.Gameplay.Game;
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
                iS.getYBound(g.getUserRects()[0].getShape().getY(), true);
    }

    private boolean getMoveDown() {
        return iS.getKey(Input.KEY_S)| iS.getKey(Input.KEY_DOWN)&&
                iS.getYBound(g.getUserRects()[0].getShape().getY(), false);
    }
    
    private boolean getMoveLeft() {
        return iS.getKey(Input.KEY_A)| iS.getKey(Input.KEY_LEFT)&&
                iS.getXBound(g.getUserRects()[0].getShape().getX(), true);
    }
    
    private boolean getMoveRight() {
        return iS.getKey(Input.KEY_D)| iS.getKey(Input.KEY_RIGHT)&&
                iS.getXBound(g.getUserRects()[0].getShape().getX(), false);
    }
    
    private void moveUpKeyboard() {
        g.getUserRects()[0].getShape().setY(g.getUserRects()[0].getShape().getY()-1);
        g.getUserRects()[1].getShape().setY(g.getUserRects()[1].getShape().getY()-1);
    }
    
    private void moveDownKeyboard() {
        g.getUserRects()[0].getShape().setY(g.getUserRects()[0].getShape().getY()+1);
        g.getUserRects()[1].getShape().setY(g.getUserRects()[1].getShape().getY()+1);  
    }
    
    private void moveLeftKeyboard() {
        g.getUserRects()[0].getShape().setX(g.getUserRects()[0].getShape().getX()-1);
        g.getUserRects()[1].getShape().setX(g.getUserRects()[1].getShape().getX()+1);  

    }
    
    private void moveRightKeyboard() {
        g.getUserRects()[0].getShape().setX(g.getUserRects()[0].getShape().getX()+1);
        g.getUserRects()[1].getShape().setX(g.getUserRects()[1].getShape().getX()-1);
    }
    
}