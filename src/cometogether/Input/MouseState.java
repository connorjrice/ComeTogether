package cometogether.Input;

import cometogether.Game;
import cometogether.Gameplay.CollisionState;
import org.newdawn.slick.Input;

/**
 *
 * @author Connor
 */
public class MouseState {
    
    private Game game;
    private CollisionState collisionState;
    private InputState inputState;
    private Input input;
    private int width, height;
    
    public MouseState(Game _game, InputState _inputState) {
        this.game = _game;
        this.inputState = _inputState;
        this.collisionState = game.getCollisionState();
        this.input = game.getGameContainer().getInput();
        this.width = game.getWidth();
        this.height = game.getHeight();
    }
    
    /**
     * Handles mouse movement, including rotation. 
     */
    public void mouseHandle() {
        if (oldGetMouseMovementAllowed()) {
            mouseHandler();
        }
    }
    
    private void mouseHandler() {
        inputState.moveFirstShape();
        if (inputState.getIsRotate()) {
            inputState.rotateShapes();
        } else {
            inputState.mirrorShapes();
        }
        updateMouseLocation();
    }
    
    /**
     * @return true if mouse position is within bounds of screen.
     */
    private boolean oldGetMouseMovementAllowed() {
        return (25 < input.getMouseX() &&
                input.getMouseX() < width &&
                25 < input.getMouseY() &&
                input.getMouseY() < height
                );
    }
    
    
    
      private void updateMouseLocation() {
        inputState.setLastMousePos(input.getMouseX(), input.getMouseY());
    }
    
}
