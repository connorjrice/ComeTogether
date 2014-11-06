package cometogether;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 *
 * @author Connor Rice
 */
public class InputState {
    
    private Game g;
    private boolean mouseRotate;
    private boolean mouseDynamic;
 
    public InputState(Game g) {
        this.g = g;
        this.mouseDynamic = false;
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
            toggleMouseDynamic(gc);
        }
        if (gc.getInput().isKeyPressed(Input.KEY_R)) {
            mouseRotate = !mouseRotate;
        }
        if (getMouseDynamic()) {
            mouseHandle(gc);
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
    
    private void mouseHandle(GameContainer gc) {
        int x = gc.getInput().getMouseX();
        int y = gc.getInput().getMouseY();

            g.getUserRects()[0].setCenterX(gc.getInput().getMouseX());
            g.getUserRects()[0].setCenterY(gc.getInput().getMouseY());
            if (mouseRotate) {
                g.getUserRects()[1].setCenterX(gc.getWidth()-gc.getInput().getMouseX());
                g.getUserRects()[1].setCenterY(gc.getHeight()-gc.getInput().getMouseY());
            } else {
                g.getUserRects()[1].setCenterX(gc.getWidth()-gc.getInput().getMouseX());
                g.getUserRects()[1].setCenterY(gc.getInput().getMouseY());
            }
    }
    
    public void toggleMouseDynamic(GameContainer gc) {
        if (g.getUserRects()[0].contains(gc.getInput().getMouseX(), 
                gc.getInput().getMouseY())) {
            System.out.println("includes");
            mouseDynamic = !mouseDynamic;        
        } else if (g.getUserRects()[1].contains(gc.getInput().getMouseX(), 
                gc.getInput().getMouseY())) {
            mouseDynamic = !mouseDynamic;        
        }
        
    }
    
    private boolean getMouseDynamic() {
        return mouseDynamic;
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
