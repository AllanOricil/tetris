
package org.tetris.indie.allan;

import Game_Library.GameWindow;

public class Play {

    public static void main(String[] args) {
        // TODO code application logic here
        GameWindow gameWindow = new GameWindow("Game Window");
        Tetris tetris = new Tetris(gameWindow.getGameThread().getPlayer());
        gameWindow.addGameScene(tetris);
        gameWindow.startGame();
    }

}
