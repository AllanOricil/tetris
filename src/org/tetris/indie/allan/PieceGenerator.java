/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.indie.allan;

import Game_Library.Drawable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Random;
import static org.tetris.indie.allan.Configuration.GRID_BACK_GROUND_COLOR;
import static org.tetris.indie.allan.Configuration.PIECE_GENERATOR_NAME_COLOR;
import static org.tetris.indie.allan.Piece.PIECE_WIDTH;
import static org.tetris.indie.allan.Tetris.FONT_NAMES;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public final class PieceGenerator implements Drawable {

    private Piece next;

    public static int PG_WIDTH;
    public static int PG_HEIGHT;
    public static int PG_LEFT;
    public static int PG_RIGTH;
    public static int PG_TOP;
    public static int PG_BOTTOM;
    public static int PG_CENTER_X;
    public static int PG_CENTER_Y;
    public static int POS_PG_X;
    public static int POS_PG_Y;

    public PieceGenerator() {
        next = generateNext();
    }

    @Override
    public void draw(Graphics2D g2d) {
        Dimension size;
        FontMetrics metrics;
        int hgt;
        int adv;

        g2d.setColor(PIECE_GENERATOR_NAME_COLOR);
        g2d.setFont(FONT_NAMES);
        metrics = g2d.getFontMetrics();
        hgt = metrics.getHeight();
        adv = metrics.stringWidth("NEXT");
        size = new Dimension(adv, hgt + 2);
        g2d.drawString("NEXT", PG_RIGTH - size.width, POS_PG_Y);
        g2d.setColor(GRID_BACK_GROUND_COLOR);
        g2d.fillRoundRect(PG_LEFT, PG_TOP, PG_WIDTH, PG_HEIGHT, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(PG_LEFT, PG_TOP, PG_WIDTH, PG_HEIGHT, 10, 10);
        next.draw(g2d);
    }

    public Piece generateNext() {
        next = generate();
        return next;
    }

    public Piece getNext() {
        Piece current = new Piece(next);
        generateNext();
        return current;
    }

    public Piece generate() {
        Random rand = new Random();
        int num = rand.nextInt(7);

        if (num == 0) {
            return new Piece(PieceType.TPIECE,
                    PG_CENTER_X - PIECE_WIDTH / 2,
                    PG_CENTER_Y - PIECE_WIDTH);
        } else if (num == 1) {
            return new Piece(PieceType.ZPIECE,
                    PG_CENTER_X,
                    PG_CENTER_Y - PIECE_WIDTH / 2);
        } else if (num == 2) {
            return new Piece(PieceType.IPIECE,
                    PG_CENTER_X - PIECE_WIDTH,
                    PG_CENTER_Y - PIECE_WIDTH / 2);
        } else if (num == 3) {
            return new Piece(PieceType.LPIECE,
                    PG_CENTER_X - PIECE_WIDTH / 2,
                    PG_CENTER_Y - PIECE_WIDTH);
        } else if (num == 4) {
            return new Piece(PieceType.JPIECE,
                    PG_CENTER_X - PIECE_WIDTH / 2,
                    PG_CENTER_Y - PIECE_WIDTH);
        } else if (num == 5) {
            return new Piece(PieceType.SPIECE,
                    PG_CENTER_X - PIECE_WIDTH / 2,
                    PG_CENTER_Y - PIECE_WIDTH);
        } else if (num == 6) {
            return new Piece(PieceType.OPIECE,
                    PG_CENTER_X - PIECE_WIDTH,
                    PG_CENTER_Y - PIECE_WIDTH);
        } else {
            return null;
        }
    }

    public void setNext(Piece next) {
        this.next = next;
    }

}
