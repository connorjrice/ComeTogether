package cometogether;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 *
 * @author Connor
 */
public class InputState {
    
    private Game g;
 
    public InputState(Game g) {
        this.g = g;
    }
    
       /**
     * Handles all keyboard input.
     * @param gc 
     */
    public void inputHandle(GameContainer gc) {
        if (g.getKeys()[Input.KEY_ESCAPE]) {
            System.exit(0);
        }
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            g.toggleMouseDynamic();
        }
        if (g.getMouseDynamic()) {
            g.getUserRects()[0].setCenterX(gc.getInput().getMouseX());
            g.getUserRects()[0].setCenterY(gc.getInput().getMouseY());
        } else {
            if (getMoveUp()) {
                moveUp();
            }
            if (getMoveDown()) {
                moveDown();
            }
            if (getMoveLeft()) {
                moveLeft();
            }
            if (getMoveRight()) {
                moveRight();
            }
        }
    }
    
    private boolean getMoveUp() {
        return g.getKeys()[Input.KEY_W] | g.getKeys()[Input.KEY_UP] &&
                g.getYBound(g.getUserRects()[0].getY(), true);
    }

    private boolean getMoveDown() {
        return g.getKeys()[Input.KEY_S] | g.getKeys()[Input.KEY_DOWN] &&
                g.getYBound(g.getUserRects()[0].getY(), false);
    }
    
    private boolean getMoveLeft() {
        return g.getKeys()[Input.KEY_A] | g.getKeys()[Input.KEY_LEFT] &&
                g.getXBound(g.getUserRects()[0].getX(), true);
    }
    
    private boolean getMoveRight() {
        return g.getKeys()[Input.KEY_D] | g.getKeys()[Input.KEY_RIGHT] &&
                g.getXBound(g.getUserRects()[0].getX(), false);
    }
    
    private void moveUp() {
        g.getUserRects()[0].setY(g.getUserRects()[0].getY()-1);
        g.getUserRects()[1].setY(g.getUserRects()[1].getY()-1);
    }
    
    private void moveDown() {
        g.getUserRects()[0].setY(g.getUserRects()[0].getY()+1);
        g.getUserRects()[1].setY(g.getUserRects()[1].getY()+1);  
    }
    
    private void moveLeft() {
        g.getUserRects()[0].setX(g.getUserRects()[0].getX()-1);
        g.getUserRects()[1].setX(g.getUserRects()[1].getX()+1);  
    }
    
    private void moveRight() {
        g.getUserRects()[0].setX(g.getUserRects()[0].getX()+1);
        g.getUserRects()[1].setX(g.getUserRects()[1].getX()-1);
    }
    
}
