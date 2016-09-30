/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameGUI;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import static org.tetris.indie.allan.Configuration.TIMER_NAME_COLOR;
import static org.tetris.indie.allan.Configuration.TIMER_NUMBER_COLOR;
import static org.tetris.indie.allan.Tetris.FONT_NAMES;
import static org.tetris.indie.allan.Tetris.FONT_NUMBERS;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GameTimer extends GameGUI {

    public int seconds;
    public int minutes;
    public int miliSeconds;

    private boolean running = true;

    public static int POS_TIMER_X;
    public static int POS_TIMER_Y;

    public GameTimer() {
        seconds = 0;
        minutes = 0;
        miliSeconds = 0;
    }

    private void setTimer() {
        if (miliSeconds > 59) {
            miliSeconds = 0;
            seconds++;
        }
        if (seconds > 59) {
            seconds = 0;
            minutes++;
        }
    }

    protected void start() {
        if (!running) {
            running = true;
        }
    }

    protected void stop() {
        if (running) {
            running = false;
        }
    }

    protected void reset() {
        seconds = 0;
        minutes = 0;
        miliSeconds = 0;
    }

    @Override
    public void draw(Graphics2D g2d) {
        String sec = String.valueOf(this.seconds);
        String min = String.valueOf(this.minutes);
        String mSec = String.valueOf(this.miliSeconds);
        Dimension size;
        FontMetrics metrics;
        int hgt;
        int adv;
        if (sec.length() == 1) {
            sec = "0" + sec;
        }
        if (min.length() == 1) {
            min = "0" + min;
        }
        if (mSec.length() == 1) {
            mSec = "0" + mSec;
        }
        g2d.setColor(TIMER_NAME_COLOR);
        g2d.setFont(FONT_NAMES);
        metrics = g2d.getFontMetrics();
        adv = metrics.stringWidth("TIMER");
        hgt = metrics.getHeight();
        size = new Dimension(adv + 10, hgt + 2);
        g2d.setColor(TIMER_NUMBER_COLOR);
        g2d.drawString("TIMER", POS_TIMER_X, POS_TIMER_Y - size.height);
        g2d.setFont(FONT_NUMBERS);
        String time = "" + min + " : " + sec + " : " + mSec;
        metrics = g2d.getFontMetrics();
        adv = metrics.stringWidth(time);
        size.setSize(adv + 10, hgt + 2);
        g2d.setColor(TIMER_NUMBER_COLOR);
        g2d.drawString(time, POS_TIMER_X, POS_TIMER_Y);
    }

    public void update() {
        miliSeconds ++;
        setTimer();
    }

}
