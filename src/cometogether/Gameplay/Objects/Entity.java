package cometogether.Gameplay.Objects;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;

/**
 *
 * @author Connor
 */
public class Entity {
    
    private Shape shape;
    private Color color;
    private boolean visible;
    private AbstractComponent component;
    private Class c;
        
    public Entity(Shape shape, Color color, boolean visible) {
        this.shape = shape;
        this.color = color;
        this.visible = visible;
    }
    
    public Entity(AbstractComponent component) {
        this.component = component;
        this.visible = true;
    }
    
    public void render(GameContainer gc, Graphics g) {
        if (component == null) {
            g.setColor(color);
            g.draw(shape);
        } else {
            try {
                component.render(gc, g);
            } catch (SlickException ex) {
                Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public AbstractComponent getComponent() {
        return component;
    }
    
    public Shape getShape() {
        return shape;
    }
    
    public void setInvisible() {
        visible = false;
    }
    
    public void setVisible() {
        visible = true;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
}
