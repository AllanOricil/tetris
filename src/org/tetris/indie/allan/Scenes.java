
package org.tetris.indie.allan;

import Game_Library.GameScene;
import Game_Library.GameSceneManager;
import java.util.List;

public class Scenes {

    public static GameSceneManager sceneManager;
    public Tetris game;

    public Scenes() {
        sceneManager = new GameSceneManager();
        sceneManager.add(game);
    }

    public static List<GameScene> getScenes() {
        return sceneManager.getGameScenes();
    }

    public static void setSceneManager(GameSceneManager sceneManager) {
        Scenes.sceneManager = sceneManager;
    }

}
