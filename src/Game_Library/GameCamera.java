/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library;

import Game_Library.Graphical_User_Interface_Entities.GamePanel;
import Game_Library.Graphical_User_Interface_Entities.GameHUD;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GameCamera implements Drawable {

    public List<GameEntity> entitiesToRender; //the ones that are going to be drawn
    private GameScene scene;

    private GamePanel panel;
    private Point pos;
    private Dimension size;
    private Rectangle camera;
    private GameHUD hud;

    public GameCamera() {
        entitiesToRender = new ArrayList<>();
        pos = new Point(0, 0);
        size = new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_WIDTH);
        camera = new Rectangle(pos, size);
    }

    public GameCamera(GameScene scene) {
        this.scene = scene;
        panel = new GamePanel();
        entitiesToRender = new ArrayList<>();
        pos = new Point(0, 0);
        size = new Dimension(GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
        camera = new Rectangle(pos, size);
    }

    @Override
    public void draw(Graphics2D g2d) {
        //DRAW THE GAME ENTITIES THAT CAN BE SEEN IN THE SCENE
        panel.draw(g2d);
        if (!entitiesToRender.isEmpty()) {
            for (GameEntity entity : entitiesToRender) {
                System.out.println("X: " + entity.getPos().getX() + " Y: " + entity.getPos().getY());
                System.out.println("DX: " + entity.getSize().height + " DY: " + entity.getSize().width);
                entity.draw(g2d);
            }
        }
        //DRAW THE HUD OVER ALL THE ENTITIES
        if (hud != null) {
            hud.draw(g2d);
        }
        
        //Release memory
        entitiesToRender.clear();

    }

    public void update() {
        for (GameEntity entity : scene.getEntities()) {
            Point aux = new Point();
            aux.x = entity.getPos().getX();
            aux.y = entity.getPos().getY();
            if (!camera.contains(aux)) {
                entitiesToRender.remove(entity);
            } else {
                entitiesToRender.add(entity);
            }
        }
    }

    public List<GameEntity> getEntitiesToRender() {
        return entitiesToRender;
    }

    public void setEntitiesToRender(List<GameEntity> entitiesToRender) {
        this.entitiesToRender = entitiesToRender;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Rectangle getCamera() {
        return camera;
    }

    public void setCamera(Rectangle camera) {
        this.camera = camera;
    }

    public GameHUD getHud() {
        return hud;
    }

    public void setHud(GameHUD hud) {
        this.hud = hud;
    }

}
