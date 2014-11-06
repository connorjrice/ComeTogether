package cometogether;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.*;

/**
 *
 * @author Connor
 */
public class GUIState {
    
    private Game game;
    private TextField field;
 
    public GUIState(Game g) {
        this.game = g;
    }
    
    public void createGUI(GameContainer gc, Graphics g) {
        field = new TextField(gc, g.getFont(), game.getWidth()/2
                , game.getWidth()/2, 100, 100);
        field.setText("Test");
        field.render(gc, g);
    }
    
}
