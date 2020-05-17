/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.indie.allan;

import Game_Library.GameScene;
import Game_Library.GameSceneManager;
import java.util.List;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class Scenes {
    
    public static GameSceneManager sceneManager;
//    private static GameScene initial;
//    private static GameScene principal;
//    private static GameScene options;
//    private static GameScene dificulty;
//    private static GameScene design;
//    private static GameScene background;
//    private static GameScene pieces;
//    private static GameScene grid;
    public Tetris game;
    
    public Scenes(){

//        initial = new GameScene("initial");
//        principal = new GameScene("principal");
//        options = new GameScene("options");
//        dificulty = new GameScene("dificulty");
//        design = new GameScene("design");
//        background = new GameScene("background");
//        pieces = new GameScene("pieces");
//        grid = new GameScene("grid");
        //THIS ONE CONTROLS THE GAME THAT IS BEING PLAYED
        
                
        sceneManager = new GameSceneManager();
  
//        sceneManager.add(initial);
//        sceneManager.add(principal);
//        sceneManager.add(options);
//        sceneManager.add(dificulty);
//        sceneManager.add(design);
//        sceneManager.add(background);
//        sceneManager.add(pieces);
//        sceneManager.add(grid);
        sceneManager.add(game);
    }

    public static List<GameScene> getScenes() {
        return sceneManager.getGameScenes();
    }

    public static void setSceneManager(GameSceneManager sceneManager) {
        Scenes.sceneManager = sceneManager;
    }
    
 
}
