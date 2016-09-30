/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GameSceneManager {
    
    private List<GameScene> gameScenes;
    private int current;
    
    public GameSceneManager(){
        gameScenes = new ArrayList<>();
        current = 0;
    }
    
    public GameSceneManager(List<GameScene> scenes){
        gameScenes = new ArrayList<>();
        gameScenes.addAll(scenes);
        current = 0;
    }

    public List<GameScene> getGameScenes() {
        return gameScenes;
    }

    public void setGameScenes(List<GameScene> gameScenes) {
        this.gameScenes = gameScenes;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    
    public void add(GameScene obj){
        gameScenes.add(obj);
    }
    
    public void update(){
        gameScenes.get(current).update();
    }
    
}

