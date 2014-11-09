package cometogether.LevelDesign;

import cometogether.Gameplay.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.TextField;

/**
 *
 * @author Connor
 */
public class LevelDesignGUI {
    
    private Game g;
    private GameContainer gC;
    private TextField nameField;
    
    public LevelDesignGUI(Game g) {
        this.g = g;
        gC = g.getGameContainer();
    }
    
    public void nameField() {
        nameField = new TextField(gC, g.getFont(), 200, 200, 200, 200, (AbstractComponent source) -> {
            nameField.setFocus(true);
        });
        
    }
    
}
