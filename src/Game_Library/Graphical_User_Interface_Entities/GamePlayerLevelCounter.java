
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameGUI;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import static org.tetris.indie.allan.Configuration.LEVEL_NAME_COLOR;
import static org.tetris.indie.allan.Configuration.LEVEL_NUMBER_COLOR;
import static org.tetris.indie.allan.Configuration.NUMBER_OF_LEVELS;
import static org.tetris.indie.allan.Configuration.START_TIME_TO_PIECE_GO_DOWN;
import static org.tetris.indie.allan.Piece.TIME_PIECE_GO_DOWN;
import static org.tetris.indie.allan.Tetris.FONT_NAMES;
import static org.tetris.indie.allan.Tetris.FONT_NUMBERS;

public class GamePlayerLevelCounter extends GameGUI {

    private int level;

    public static int POS_LEVEL_X;
    public static int POS_LEVEL_Y;
    public static int LEVELS_TO_DECREASE_TIME_TO_PIECE_GO_DOWN_AFTER;

    public GamePlayerLevelCounter() {
        level = 0;
    }

    @Override
    public void draw(Graphics2D g2d) {
        String s = String.valueOf(this.level);
        Dimension size;
        FontMetrics metrics;
        int hgt;
        int adv;
        int length = s.length();
        for (int i = 0; i < (3 - length); i++) {
            s = "0" + s;
        }
        g2d.setColor(LEVEL_NAME_COLOR);
        g2d.setFont(FONT_NAMES);
        metrics = g2d.getFontMetrics();
        hgt = metrics.getHeight();
        adv = metrics.stringWidth("LEVEL");
        size = new Dimension(adv + 10, hgt + 2);
        g2d.drawString("LEVEL", POS_LEVEL_X - size.width, POS_LEVEL_Y - size.height);
        g2d.setColor(LEVEL_NUMBER_COLOR);
        g2d.setFont(FONT_NUMBERS);
        metrics = g2d.getFontMetrics();
        adv = metrics.stringWidth(s);
        size.setSize(adv + 10, hgt + 2);
        g2d.drawString(s, POS_LEVEL_X - size.width, POS_LEVEL_Y);
    }

    public void update() {
        level++;
        if (level % LEVELS_TO_DECREASE_TIME_TO_PIECE_GO_DOWN_AFTER == 0) {
            setTImePieceGoDown();
        }
        if (level > NUMBER_OF_LEVELS) {
            level = NUMBER_OF_LEVELS;
        }
    }

    public int getLevel() {
        return level;
    }

    private void setTImePieceGoDown() {
        int value = level / LEVELS_TO_DECREASE_TIME_TO_PIECE_GO_DOWN_AFTER;
        TIME_PIECE_GO_DOWN = START_TIME_TO_PIECE_GO_DOWN - value;
    }

}
