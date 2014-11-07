package cometogether.Input;

import cometogether.Game;
import org.newdawn.slick.Input;

/**
 *
 * @author Connor
 */
public class MouseState {
    
    private Game g;
    private InputState iS;
    private Input input;
    private int width, height;
    
    public MouseState(Game g, InputState iS) {
        this.g = g;
        this.iS = iS;
        this.input = g.getGameContainer().getInput();
        this.width = g.getWidth();
        this.height = g.getHeight();
    }
    
    /**
     * Handles mouse movement, including rotation. 
     */
    public void mouseHandle() {
        if (getMouseMovementAllowed()) {
            mouseHandler();
        }
    }
    
    private void mouseHandler() {
        iS.moveFirstShape();
        if (iS.getIsRotate()) {
            iS.rotateShapes();
        } else {
            iS.mirrorShapes();
        }
        updateMouseLocation();
    }
    
    /**
     * @return true if mouse position is within bounds of screen.
     */
    private boolean getMouseMovementAllowed() {
        return (25 < input.getMouseX() &&
                input.getMouseX() < width &&
                25 < input.getMouseY() &&
                input.getMouseY() < height
                );
    }
    
      private void updateMouseLocation() {
        iS.setLastMousePos(input.getMouseX(), input.getMouseY());
    }
    
}
