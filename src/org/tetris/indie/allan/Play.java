/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.indie.allan;

import Game_Library.GameWindow;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class Play {

    public static void main(String[] args) {
        // TODO code application logic here
        GameWindow gameWindow = new GameWindow("Game Window");
        Tetris tetris = new Tetris(gameWindow.getGameThread().getPlayer());
        gameWindow.addGameScene(tetris);
        gameWindow.startGame();
    }

}
