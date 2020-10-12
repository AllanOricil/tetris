
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameGUI;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameHUD extends GameGUI {

    private List<GameGUI> hudEntities;

    public GameHUD() {
        hudEntities = new ArrayList<>();
        setPos(new Point(0, 0));
        setSize(new Dimension(0, 0));
    }

    public GameHUD(Point pos, Dimension size) {
        hudEntities = new ArrayList<>();
        setPos(pos);
        setSize(size);
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (GameGUI entity : hudEntities) {
            entity.draw(g2d);
        }
    }

    public List<GameGUI> getHudEntities() {
        return hudEntities;
    }

    public void setHudEntities(List<GameGUI> hudEntities) {
        this.hudEntities = hudEntities;
    }

}
