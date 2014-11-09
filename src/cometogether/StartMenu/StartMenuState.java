package cometogether.StartMenu;

import cometogether.Gameplay.Game;

/**
 *
 * @author Connor
 */
public class StartMenuState {
    
    private Game g;
    
    public StartMenuState(Game g) {
        this.g = g;
        g.toggleInGame();
    }
    
    
    
}
