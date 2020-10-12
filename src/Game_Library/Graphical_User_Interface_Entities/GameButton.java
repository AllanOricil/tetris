
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameGUI;
import java.awt.Graphics2D;

public class GameButton extends GameGUI {

    public GameLabel label;

    private boolean activate;

    public GameButton(String text) {
        this.label = new GameLabel(text);
        activate = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawString(label.getText(), getPos().x, getPos().y);
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

}
