
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameGUI;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import static org.tetris.indie.allan.Configuration.POS_SCORE_X;
import static org.tetris.indie.allan.Configuration.POS_SCORE_Y;
import static org.tetris.indie.allan.Configuration.SCORE_NAME_COLOR;
import static org.tetris.indie.allan.Configuration.SCORE_NUMBER_COLOR;
import static org.tetris.indie.allan.Tetris.FONT_NAMES;
import static org.tetris.indie.allan.Tetris.FONT_NUMBERS;

public class GameScore extends GameGUI {

    private final int numberDigits = 6;
    private int score;

    public GameScore() {
        setPos(new Point(POS_SCORE_X, POS_SCORE_Y));
        score = 0;
    }

    public GameScore(int score) {
        this.score = score;
    }

    public void setPoints(int points) {
        score += points;
        if (score > 999999) {
            score = 999999;
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public void draw(Graphics2D g2d) {
        String s = String.valueOf(this.score);
        Dimension size;
        FontMetrics metrics;
        int hgt;
        int adv;
        int length = s.length();
        for (int i = 0; i < (6 - length); i++) {
            s = "0" + s;
        }
        g2d.setColor(SCORE_NAME_COLOR);
        g2d.setFont(FONT_NAMES);
        metrics = g2d.getFontMetrics();
        hgt = metrics.getHeight();
        adv = metrics.stringWidth("SCORE");
        size = new Dimension(adv + 10, hgt + 2);
        g2d.drawString("SCORE", getPos().x - size.width, getPos().y);
        // System.out.println("POSX:" + getPos().x + " POSY:"+ getPos().y);
        g2d.setColor(SCORE_NUMBER_COLOR);
        g2d.setFont(FONT_NUMBERS);
        metrics = g2d.getFontMetrics();
        adv = metrics.stringWidth(s);
        size.setSize(adv + 10, hgt + 2);
        g2d.drawString(s, getPos().x - size.width, getPos().y + size.height);
    }

}
