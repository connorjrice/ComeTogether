package cometogether;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Connor Rice
 */

public class Game extends BasicGame {
    
    private GameContainer gC;
    private ObstacleState oS;
    private InputState iS;
    private AngelCodeFont aF;
    private boolean keys[];
    private boolean mouseDynamic;
    private int win, lose;
    private Shape[] userRects;
    private Shape[] barriers;

    public Game(String title) {
        super(title);
        this.win = 0;
        this.lose = 0;
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        this.gC = gc;
        this.oS = new ObstacleState(this);
        this.iS = new InputState(this);
        this.aF = new AngelCodeFont("arial.fnt", "arial_0.tga");
        this.keys = new boolean[256];
        this.mouseDynamic = false;
        createUserRect();
        setInitialUserRectPosition();
        this.barriers = oS.getBarriers();

    }
    


    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        iS.inputHandle(gc);
        checkCollisions(gc);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        initialRender(g);
        drawString(20, 20, "Win: " + win); 
        drawString(20, 50, "Lose: " + lose);
    }
    
    /**
     * Draws the user rectangles and barriers.
     * @param g 
     */
    private void initialRender(Graphics g) {
        g.setColor(Color.red);
        g.draw(userRects[0]);
        g.setColor(Color.green);
        g.draw(userRects[1]);
        g.setColor(Color.blue);
        for (Shape s : barriers) {
            g.draw(s);
        }
    }
    
    private void createUserRect() {
        this.userRects = new Shape[] {new Rectangle(50, 50, 50, 50),
            new Rectangle(50, 50, 50, 50)};
    }

    
    private void setInitialUserRectPosition() {
        userRects[0].setLocation(10, gC.getHeight()/2);
        userRects[1].setLocation(gC.getWidth()-60, gC.getHeight()/2);
    }
    
    /**
     * Uses the AngelCodeFont to draw a string at the specified position.
     * @param x
     * @param y
     * @param text 
     */
    private void drawString(int x, int y, String text) {
        aF.drawString(x, y, text);
    }
    
     
    private void checkCollisions(GameContainer gc) {
        if (checkCollision(userRects[0]) | checkCollision(userRects[1])) {
            try {
                lose++;
                gc.reinit();
            } catch (SlickException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (userRects[0].intersects(userRects[1])) {
            try {
                win++;
                gc.reinit();
            } catch (SlickException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private boolean checkCollision(Shape s) {
        for (Shape barrier : barriers) {
            if (s.intersects(barrier)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getYBound(float y, boolean high) {
        if (high) {
            return y > 1;
        } else {
            return y < gC.getHeight() - 51;
        }
    }
    
    public boolean getXBound(float x, boolean right) {
        if (right) {
            return x > 1;
        } else {
            return x < gC.getWidth()-51;
        }
    }
    
    public boolean[] getKeys() {
        return keys;
    }
    
    public int getWin() {
        return win;
    }
    
    public int getHeight() {
        return gC.getHeight();
    }
    
    public int getWidth() {
        return gC.getWidth();
    }
    
    public Shape[] getUserRects() {
        return userRects;
    }
    
    public void toggleMouseDynamic() {
        mouseDynamic = !mouseDynamic;
    }
    
    public boolean getMouseDynamic() {
        return mouseDynamic;
    }
    
    @Override
    public void keyPressed(int key, char c) {
        keys[key] = true;
    }
    
    @Override
    public void keyReleased(int key, char c) {
        keys[key] = false;
    }
    
    
}
