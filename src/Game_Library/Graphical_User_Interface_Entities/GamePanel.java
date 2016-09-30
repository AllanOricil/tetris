/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameEntity;
import Game_Library.Vector2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import static Game_Library.GameWindow.WINDOW_HEIGHT;
import static Game_Library.GameWindow.WINDOW_WIDTH;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GamePanel extends GameEntity {

    private GameEntity entity;

    public GamePanel() {
        pos = new Vector2D(0, 0);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackgroundColor(Color.GRAY);
    }

    public GamePanel(Vector2D pos, Dimension size) {
        this.pos = pos;
        this.size = size;
        setBackgroundColor(Color.GRAY);
    }

    public GamePanel(GameEntity entity) {
        pos = new Vector2D(0, 0);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackgroundColor(Color.GRAY);
        this.entity = entity;
        centralizeEntity();
        this.entity.update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(getBackgroundColor());
        g2d.fillRect(pos.getX(), pos.getY(), size.width, size.height);
        if (entity != null) {
            entity.draw(g2d);
        }
    }

    @Override
    public void update() {
        centralizeEntity();
    }

    private void centralizeEntity() {
        Vector2D posAux = new Vector2D();
        posAux.setX(getCenter().getX() - entity.getSize().width / 2);
        posAux.setY(getCenter().getY() - entity.getSize().height / 2);
        entity.setPos(posAux);
    }

    public void setEntity(GameEntity entity) {
        this.entity = entity;
        this.entity.setPos(pos);
        this.entity.update();
    }
    
    public void setBackGroundColor(Color color){
        super.setBackgroundColor(color);
    }
    
}
