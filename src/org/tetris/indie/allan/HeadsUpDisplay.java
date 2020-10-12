
package org.tetris.indie.allan;

import Game_Library.Graphical_User_Interface_Entities.GamePlayerLevelCounter;
import Game_Library.Graphical_User_Interface_Entities.GameTimer;
import Game_Library.Graphical_User_Interface_Entities.GameScore;
import Game_Library.Graphical_User_Interface_Entities.GameHUD;

public class HeadsUpDisplay extends GameHUD {

    private final GameScore score;
    private final GameTimer timer;
    private final GamePlayerLevelCounter level;

    public HeadsUpDisplay() {
        score = new GameScore();
        level = new GamePlayerLevelCounter();
        timer = new GameTimer();
        getHudEntities().add(score);
        getHudEntities().add(level);
        getHudEntities().add(timer);
    }

    public GameScore getScore() {
        return score;
    }

    public GameTimer getTimer() {
        return timer;
    }

    public GamePlayerLevelCounter getLevel() {
        return level;
    }

}
