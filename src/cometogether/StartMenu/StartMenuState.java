package cometogether.StartMenu;

import cometogether.Gameplay.Game;
import cometogether.Gameplay.Objects.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author Connor
 */
public class StartMenuState implements ComponentListener {
    
    private Game g;
    private MouseOverArea[] areas;
    
    public StartMenuState(Game g) {
        this.g = g;
        createMouseAreas();
    }
    
    private void createMouseAreas() {
        areas = new MouseOverArea[1];
        Image image;
        try {
            image = new Image("begin.png");
            areas[0] = new MouseOverArea(g.getGameContainer(), image, 100, 100, this);
        } catch (SlickException ex) {
            Logger.getLogger(StartMenuState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Entity[] getEntities() {
        return new Entity[]{new Entity(areas[0])};
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == areas[0]) {
            System.out.println("here");
            g.beginGame();
            g.removeEntity(new Entity(areas[0]));
        }
    }
    
    
    
    
    
}
