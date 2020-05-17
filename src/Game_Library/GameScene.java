/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library;

import Game_Library.Graphical_User_Interface_Entities.GameMenu;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.tetris.indie.allan.PlayerInput;


/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public abstract class GameScene{

    private final List<GameEntity> entities;
    public PlayerInput player;
    private GameStateEnum gameState;
    private String name;
    private List<GameCamera> cameras;
    private final int id;
    
    private static int numGameScenes = 0;
    
    /** 
     * Creates a scene
     */
    public GameScene() {
        cameras = new ArrayList<>();
        cameras.add(new GameCamera(this));
        entities = new ArrayList<>();
        gameState = GameStateEnum.RUNNING;
        id = numGameScenes;
        numGameScenes++;
    }
    
    /**
     * Creates a scene using a name
     * @param name - Name of the scene
     */
    public GameScene(String name) {
        cameras = new ArrayList<>();
        cameras.add(new GameCamera(this));
        this.name = name;
        entities = new ArrayList<>();
        gameState = GameStateEnum.RUNNING;
        id = numGameScenes;
        numGameScenes++;
    }
    
    /**
     * Creates a scene using another scene
     * @param gameScene - Game scene
     */
    public GameScene(GameScene gameScene){
        id = gameScene.getId();
        name = gameScene.getName();
        entities = gameScene.getEntities();
        gameState = gameScene.getState();
        cameras = gameScene.getCameras();
    }
    
    public abstract void update();

    public void addEntity(GameEntity obj) {
        entities.add(obj);
        sortEntities();
    }

    private void sortEntities() {
        Comparator<GameEntity> c;
        c = (GameEntity t, GameEntity t1) -> t.getDepth() - t1.getDepth();
        entities.sort(c);
    }

    public int getId() {
        return id;
    }

    public List<GameEntity> getEntities() {
        return entities;
    }

    public PlayerInput getPlayer() {
        return player;
    }

    public void setPlayer(PlayerInput player) {
        this.player = player;
    }

    public GameStateEnum getState() {
        return gameState;
    }

    public void setState(GameStateEnum state) {
        this.gameState = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GameCamera> getCameras() {
        return cameras;
    }

    public void setCameras(List<GameCamera> cameras) {
        this.cameras = cameras;
    }
}
