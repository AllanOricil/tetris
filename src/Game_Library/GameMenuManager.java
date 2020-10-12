
package Game_Library;

import Game_Library.Graphical_User_Interface_Entities.GameMenu;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import static org.tetris.indie.allan.TetrisButtonEntities.*;
import static org.tetris.indie.allan.Menus.*;

public class GameMenuManager implements Drawable {

    private final List<GameMenu> menus;
    private int current;

    public GameMenuManager() {
        menus = new ArrayList<>();

        gm1.addButton(b1);
        gm1.addButton(b2);
        gm2.addButton(b3);
        gm2.addButton(b4);
        gm3.addButton(b5);
        gm3.addButton(b6);
        gm3.addButton(b7);

        menus.add(gm1);
        menus.add(gm4);
        menus.add(gm2);
        menus.add(gm3);

        current = 0;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (current < menus.size()) {
            menus.get(current).draw(g2d);
        }
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

}
