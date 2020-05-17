/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.indie.allan;

import Game_Library.Vector2D;
import Game_Library.GameEntity;
import java.awt.Color;
import java.awt.Graphics2D;
import static org.tetris.indie.allan.Configuration.ADJUST_LEFT;
import static org.tetris.indie.allan.Configuration.ADJUST_RIGHT;
import static org.tetris.indie.allan.Configuration.PIECE_I_COLOR;
import static org.tetris.indie.allan.Configuration.PIECE_J_COLOR;
import static org.tetris.indie.allan.Configuration.PIECE_L_COLOR;
import static org.tetris.indie.allan.Configuration.PIECE_O_COLOR;
import static org.tetris.indie.allan.Configuration.PIECE_S_COLOR;
import static org.tetris.indie.allan.Configuration.PIECE_T_COLOR;
import static org.tetris.indie.allan.Configuration.PIECE_Z_COLOR;
import static org.tetris.indie.allan.Configuration.START_TIME_TO_PIECE_GO_DOWN;
import static org.tetris.indie.allan.Grid.GRID_LEFT;
import static org.tetris.indie.allan.Grid.GRID_RIGHT;
import static org.tetris.indie.allan.PieceType.OPIECE;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class Piece extends GameEntity{

    private Color color;
    private Block[] blocks;
    private Vector2D center;
    private PieceType type;

    private int count_time_elapsed;
    
    public static int PIECE_WIDTH;
    public static int INITIAL_POS_PIECE_GRID_X;
    public static int INITIAL_POS_PIECE_GRID_Y;
    public static int PIECE_DIST_INTERN_RIGHT;
    public static int PIECE_DIST_INTERN_LEFT;
    public static int TIME_PIECE_GO_DOWN;

    public Piece(Piece piece) {
        createPiece(piece.type,
                INITIAL_POS_PIECE_GRID_X,
                INITIAL_POS_PIECE_GRID_Y);
    }

    public Piece(PieceType type) {
        createPiece(type,
                INITIAL_POS_PIECE_GRID_X,
                INITIAL_POS_PIECE_GRID_Y);
    }

    public Piece(PieceType type, int x, int y) {
        createPiece(type, x, y);
    }
    
    
    @Override
    public void draw(Graphics2D g2d) {
        for (Block block : blocks) {
            g2d.setColor(color);
            block.draw(g2d);
        }
    }

    @Override
    public void update() {
        if (count_time_elapsed == TIME_PIECE_GO_DOWN) {
            moveDown();
            count_time_elapsed = 0;
        }
        count_time_elapsed++;
    }

    private void createPiece(PieceType type, int x, int y) {
        TIME_PIECE_GO_DOWN = START_TIME_TO_PIECE_GO_DOWN;
        center = new Vector2D(x, y);
        blocks = new Block[4];
        blocks[0] = new Block();
        blocks[1] = new Block();
        blocks[2] = new Block();
        blocks[3] = new Block();
        this.type = type;
        constructPiece();
    }

    private void constructPiece() {
        switch (type) {
            case TPIECE:
                color = PIECE_T_COLOR;
                CBMiddleCenter(blocks[0]);
                CBLeftBottom(blocks[1]);
                CBMiddleBottom(blocks[2]);
                CBRightBottom(blocks[3]);
                break;
            case ZPIECE:
                color = PIECE_Z_COLOR;
                CBMiddleCenter(blocks[0]);
                CBLeftCenter(blocks[1]);
                CBLeftBottom(blocks[2]);
                CBMiddleTop(blocks[3]);
                break;
            case IPIECE:
                color = PIECE_I_COLOR;
                CBMiddleCenter(blocks[0]);
                CBLeftCenter(blocks[1]);
                CBRightCenter(blocks[2]);
                CBMiddleRight(blocks[3]);
                break;
            case LPIECE:
                color = PIECE_L_COLOR;
                CBRightCenter(blocks[0]);
                CBRightBottom(blocks[1]);
                CBMiddleBottom(blocks[2]);
                CBLeftBottom(blocks[3]);
                break;
            case JPIECE:
                color = PIECE_J_COLOR;
                CBLeftCenter(blocks[0]);
                CBLeftBottom(blocks[1]);
                CBMiddleBottom(blocks[2]);
                CBRightBottom(blocks[3]);
                break;
            case SPIECE:
                color = PIECE_S_COLOR;
                CBMiddleCenter(blocks[0]);
                CBRightCenter(blocks[1]);
                CBMiddleBottom(blocks[2]);
                CBLeftBottom(blocks[3]);
                break;
            case OPIECE:
                color = PIECE_O_COLOR;
                CBMiddleCenter(blocks[0]);
                CBMiddleBottom(blocks[1]);
                CBRightCenter(blocks[2]);
                CBRightBottom(blocks[3]);
                break;
        }
        setColor();
    }

    public void setPiecePosition(int x, int y) {
        center.setVector(x, y);
        constructPiece();
    }

    private void setColor() {
        for (Block block : blocks) {
            block.setColor(color);
        }
    }

    public void moveDown() {
        for (int i = 0; i < 4; i++) {
            blocks[i].getPos().setY(blocks[i].getPos().getY() + PIECE_WIDTH);
        }
        center.setY(center.getY() + PIECE_WIDTH);
    }

    protected void moveRight() {
        center.setX(center.getX() + PIECE_WIDTH);
        for (int i = 0; i < 4; i++) {
            blocks[i].getPos().setX(blocks[i].getPos().getX() + PIECE_WIDTH);
        }
    }

    protected void moveLeft() {
        center.setX(center.getX() - PIECE_WIDTH);
        for (int i = 0; i < 4; i++) {
            blocks[i].getPos().setX(blocks[i].getPos().getX() - PIECE_WIDTH);
        }
    }

    protected void rightRotation() {
        if (type != OPIECE) {
            for (int i = 0; i < 4; i++) {
                int blockMatrixPos = blocks[i].getMatrixPos();
                if (blockMatrixPos == 0) {
                    CBRightTop(blocks[i]);
                } else if (blockMatrixPos == 1) {
                    CBRightCenter(blocks[i]);
                } else if (blockMatrixPos == 2) {
                    CBRightBottom(blocks[i]);
                } else if (blockMatrixPos == 3) {
                    CBMiddleTop(blocks[i]);
                } else if (blockMatrixPos == 4) {
                    CBMiddleCenter(blocks[i]);
                } else if (blockMatrixPos == 5) {
                    CBMiddleBottom(blocks[i]);
                } else if (blockMatrixPos == 6) {
                    CBLeftTop(blocks[i]);
                } else if (blockMatrixPos == 7) {
                    CBLeftCenter(blocks[i]);
                } else if (blockMatrixPos == 8) {
                    CBLeftBottom(blocks[i]);
                } else if (blockMatrixPos == 9) {
                    CBMiddleRight(blocks[i]);
                } else if (blockMatrixPos == 10) {
                    CBMiddleDown(blocks[i]);
                } else if (blockMatrixPos == 11) {
                    CBMiddleLeft(blocks[i]);
                } else if (blockMatrixPos == 12) {
                    CBMiddleUp(blocks[i]);
                }
            }
            adjustPiece();
        }
    }

    protected void leftRotation() {
        if (type != OPIECE) {
            for (int i = 0; i < 4; i++) {
                int blockMatrixPos = blocks[i].getMatrixPos();
                if (blockMatrixPos == 0) {
                    CBLeftBottom(blocks[i]);
                } else if (blockMatrixPos == 1) {
                    CBLeftCenter(blocks[i]);
                } else if (blockMatrixPos == 2) {
                    CBLeftTop(blocks[i]);
                } else if (blockMatrixPos == 3) {
                    CBMiddleBottom(blocks[i]);
                } else if (blockMatrixPos == 4) {
                    CBMiddleCenter(blocks[i]);
                } else if (blockMatrixPos == 5) {
                    CBMiddleTop(blocks[i]);
                } else if (blockMatrixPos == 6) {
                    CBRightBottom(blocks[i]);
                } else if (blockMatrixPos == 7) {
                    CBRightCenter(blocks[i]);
                } else if (blockMatrixPos == 8) {
                    CBRightTop(blocks[i]);
                } else if (blockMatrixPos == 9) {
                    CBMiddleLeft(blocks[i]);
                } else if (blockMatrixPos == 10) {
                    CBMiddleUp(blocks[i]);
                } else if (blockMatrixPos == 11) {
                    CBMiddleRight(blocks[i]);
                } else if (blockMatrixPos == 12) {
                    CBMiddleDown(blocks[i]);
                }
            }
            adjustPiece();
        }
    }

    private void adjustPiece() {
        Vector2D pos;
        int adjust = isPieceOut();
        if (adjust != 0) {
            for (int i = 0; i < 4; i++) {
                pos = blocks[i].getPos();
                pos.setX(pos.getX() + adjust * PIECE_WIDTH);
            }
        }
    }

    private int isPieceOut() {
        for (int i = 0; i < 4; i++) {
            if (blocks[i].getPos().getX() < GRID_LEFT) {
                return ADJUST_RIGHT;
            }
            if (blocks[i].getPos().getX() >= GRID_RIGHT) {
                return ADJUST_LEFT;
            }
        }
        return 0;
    }

    private void CBLeftTop(Block block) {
        block.setMatrixPos(0);
        block.getPos().setVector(center.getX() - PIECE_WIDTH, center.getY() - PIECE_WIDTH);
    }

    private void CBLeftCenter(Block block) {
        block.setMatrixPos(3);
        block.getPos().setVector(center.getX() - PIECE_WIDTH, center.getY());
    }

    private void CBLeftBottom(Block block) {
        block.setMatrixPos(6);
        block.getPos().setVector(center.getX() - PIECE_WIDTH, center.getY() + PIECE_WIDTH);
    }

    private void CBMiddleTop(Block block) {
        block.setMatrixPos(1);
        block.getPos().setVector(center.getX(), center.getY() - PIECE_WIDTH);
    }

    private void CBMiddleCenter(Block block) {
        block.setMatrixPos(4);
        block.getPos().setVector(center.getX(), center.getY());
    }

    private void CBMiddleBottom(Block block) {
        block.setMatrixPos(7);
        block.getPos().setVector(center.getX(), center.getY() + PIECE_WIDTH);
    }

    private void CBRightTop(Block block) {
        block.setMatrixPos(2);
        block.getPos().setVector(center.getX() + PIECE_WIDTH, center.getY() - PIECE_WIDTH);
    }

    private void CBRightCenter(Block block) {
        block.setMatrixPos(5);
        block.getPos().setVector(center.getX() + PIECE_WIDTH, center.getY());
    }

    private void CBRightBottom(Block block) {
        block.setMatrixPos(8);
        block.getPos().setVector(center.getX() + PIECE_WIDTH, center.getY() + PIECE_WIDTH);
    }

    private void CBMiddleUp(Block block) {
        block.setMatrixPos(9);
        block.getPos().setVector(center.getX(), center.getY() - 2 * PIECE_WIDTH);
    }

    private void CBMiddleRight(Block block) {
        block.setMatrixPos(10);
        block.getPos().setVector(center.getX() + 2 * PIECE_WIDTH, center.getY());
    }

    private void CBMiddleDown(Block block) {
        block.setMatrixPos(11);
        block.getPos().setVector(center.getX(), center.getY() + 2 * PIECE_WIDTH);
    }

    private void CBMiddleLeft(Block block) {
        block.setMatrixPos(12);
        block.getPos().setVector(center.getX() - 2 * PIECE_WIDTH, center.getY());
    }

    public Block[] getBlocks() {
        return blocks;
    }
}
