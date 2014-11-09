package cometogether.Gameplay.Objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor
 */
public class Entity {
    
    private Shape shape;
    private Color color;
    private boolean visible;
        
    public Entity(Shape shape, Color color, boolean visible) {
        this.shape = shape;
        this.color = color;
        this.visible = visible;
    }
    
    public void render(GameContainer gc, Graphics g) {
        g.setColor(color);
        g.draw(shape);
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
