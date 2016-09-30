/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.indie.allan;

import Game_Library.GameEntity;
import java.awt.Color;
import java.awt.Graphics2D;
import static org.tetris.indie.allan.Grid.SQUARE_ARC_DIAMETER;
import static org.tetris.indie.allan.Piece.PIECE_DIST_INTERN_LEFT;
import static org.tetris.indie.allan.Piece.PIECE_DIST_INTERN_RIGHT;
import static org.tetris.indie.allan.Piece.PIECE_WIDTH;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class Block extends GameEntity{

    private int matrixPos;
    private Color color;

    @Override
    public void draw(Graphics2D g2d) {
        int x = getPos().getX();
        int y = getPos().getY();
        
        g2d.fillRect(x + PIECE_DIST_INTERN_LEFT,
                y + PIECE_DIST_INTERN_LEFT,
                PIECE_WIDTH - PIECE_DIST_INTERN_RIGHT,
                PIECE_WIDTH - PIECE_DIST_INTERN_RIGHT);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + PIECE_DIST_INTERN_LEFT,
                y + PIECE_DIST_INTERN_LEFT,
                PIECE_WIDTH - PIECE_DIST_INTERN_RIGHT,
                PIECE_WIDTH - PIECE_DIST_INTERN_RIGHT);
        g2d.drawRoundRect(x, y, PIECE_WIDTH,
                PIECE_WIDTH,
                SQUARE_ARC_DIAMETER,
                SQUARE_ARC_DIAMETER);
    }

    public int getMatrixPos() {
        return matrixPos;
    }

    public void setMatrixPos(int matrixPos) {
        this.matrixPos = matrixPos;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
