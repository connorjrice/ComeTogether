package cometogether;

import org.newdawn.slick.gui.*;

/**
 *
 * @author Connor
 */
public class GUIState {
    
    private Game g;
    private TextField field;
 
    public GUIState(Game g) {
        this.g = g;
        createGUI();
    }
    
    public void createGUI() {
        field = new TextField(g.getGameContainer(), g.getFont(), g.getWidth()/2
                , g.getWidth()/2, 100, 100);
        field.setText("Test");
    }
    
}
