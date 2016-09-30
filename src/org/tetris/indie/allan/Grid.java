/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.indie.allan;

import Game_Library.Vector2D;
import Game_Library.GameStateEnum;
import Game_Library.GameEntity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import static org.tetris.indie.allan.Configuration.GRID_BACK_GROUND_COLOR;
import static org.tetris.indie.allan.Configuration.NUM_BLOCKS_HORIZONTALLY;
import static org.tetris.indie.allan.Configuration.NUM_BLOCKS_VERTICALLY;
import static org.tetris.indie.allan.Configuration.POINTS_PER_COMBO;
import static org.tetris.indie.allan.Configuration.POINTS_PER_LINE;
import static org.tetris.indie.allan.Configuration.TIME_TO_FIRM_PIECE;
import java.awt.Dimension;
import static org.tetris.indie.allan.Piece.PIECE_WIDTH;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class Grid extends GameEntity {

    private final List<Block> gridBlocks;
    private final int[] number_blocks_line;
    private final PieceGenerator generator;
    private Piece current;
    private PlayerInput player;
    private Tetris scene;
    private double count_time;
    private boolean fixPiece;

    public static int GRID_WIDTH;
    public static int GRID_HEIGHT;
    public static int GRID_TOP;
    public static int GRID_BOTTOM;
    public static int GRID_LEFT;
    public static int GRID_RIGHT;
    public static int SQUARE_ARC_DIAMETER;

    public Grid(Tetris scene) {     
        setDepth(1);
        fixPiece = false;
        generator = new PieceGenerator();
        this.scene = scene;
        this.current = new Piece(generator.getNext());
        gridBlocks = new ArrayList<>();
        number_blocks_line = new int[NUM_BLOCKS_VERTICALLY + 1];
        setPos(new Vector2D(GRID_LEFT, GRID_TOP));
        setSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
    }

    @Override
    public void draw(Graphics2D g2d) {
        //DRAW THE NEXT PIECE
        generator.draw(g2d);
        g2d.setColor(new Color(150, 150, 150));

        //DRAW THE GRID
        for (int i = 0; i < NUM_BLOCKS_VERTICALLY; i++) {
            for (int j = 0; j < NUM_BLOCKS_HORIZONTALLY; j++) {
                g2d.setColor(GRID_BACK_GROUND_COLOR);
                g2d.fillRoundRect(GRID_LEFT + j * PIECE_WIDTH,
                        GRID_TOP + i * PIECE_WIDTH,
                        PIECE_WIDTH, PIECE_WIDTH,
                        SQUARE_ARC_DIAMETER,
                        SQUARE_ARC_DIAMETER);
                g2d.setColor(Color.WHITE);
                g2d.drawRoundRect(GRID_LEFT + j * PIECE_WIDTH,
                        GRID_TOP + i * PIECE_WIDTH,
                        PIECE_WIDTH, PIECE_WIDTH,
                        SQUARE_ARC_DIAMETER,
                        SQUARE_ARC_DIAMETER);
            }
        }
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(GRID_LEFT, GRID_TOP,
                GRID_WIDTH,
                GRID_HEIGHT,
                SQUARE_ARC_DIAMETER,
                SQUARE_ARC_DIAMETER);

        //DRAW BLOCKS THAT ARE IN THE GRID
        gridBlocks.stream().forEach((b) -> {
            g2d.setColor(b.getColor());
            b.draw(g2d);
        });
        current.draw(g2d);
    }

    @Override
    public void update() {
        fixPiece = colisionCheck();
        if (fixPiece == true) {
            if (count_time == TIME_TO_FIRM_PIECE) {
                setGridBlocks();
                manageGrid();
                count_time = 0;
            }
            count_time++;
        } else {
            current.update();
        }
    }

    public void setGridBlocks() {
        for (int i = 0; i < 4; i++) {
            gridBlocks.add(current.getBlocks()[i]);
        }
    }

    public boolean colisionTopCheck() {
        for (int i = (gridBlocks.size() - 1); i >= 0; i--) {
            if (gridBlocks.get(i).getPos().getY() < GRID_TOP) {
                return true;
            }
        }
        return false;
    }

    public boolean colisionCheck() {
        double futureY;
        //CHECK COLISION WITH THE BOTTOM OF THE GRID
        for (Block cb : current.getBlocks()) {
            futureY = cb.getPos().getY() + PIECE_WIDTH;
            if (futureY >= GRID_BOTTOM) {
                return true;
            }
        }

        //CHECK COLISION WITH OTHER PIECES FOR THE GOING DOWN MOVEMENT
        for (Block cb : current.getBlocks()) {
            futureY = cb.getPos().getY() + PIECE_WIDTH;
            for (int i = (gridBlocks.size() - 1); i >= 0; i--) {
                Vector2D gbPos = gridBlocks.get(i).getPos();
                if (futureY == gbPos.getY() && cb.getPos().getX() == gbPos.getX()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void manageGrid() {
        int count_combos = 0;
        if (!gridBlocks.isEmpty()) {
            organizeGridBlocks();
        }

        if (colisionTopCheck()) {
            scene.setState(GameStateEnum.PAUSED);
        } else {
            setCurrent(generator.getNext());

            for (int i = 0; i < number_blocks_line.length; i++) {
                if (number_blocks_line[i] == NUM_BLOCKS_HORIZONTALLY) {
                    deleteGridLine(i);
                    count_combos++;
                }
            }

            if (count_combos > 0) {
                if (count_combos != 1) {
                    scene.getHud().getScore().setPoints(count_combos - 1 * POINTS_PER_COMBO
                            + POINTS_PER_LINE * count_combos);
                } else {
                    scene.getHud().getScore().setPoints(POINTS_PER_LINE * count_combos);
                }
            }

            for (int i = 0; i < number_blocks_line.length; i++) {
                number_blocks_line[i] = 0;
            }
        }
    }

    private void deleteGridLine(int line) {
        int deleteFirstIndex = 0;
        for (int i = (number_blocks_line.length - 1); i > line; i--) {
            deleteFirstIndex += number_blocks_line[i];
        }
        for (int i = 0; i < NUM_BLOCKS_HORIZONTALLY; i++) {
            gridBlocks.remove(deleteFirstIndex);
        }
        moveGridDown(deleteFirstIndex);
        scene.getHud().getLevel().update();
    }

    private void moveGridDown(int deleteFirstIndex) {
        for (int i = deleteFirstIndex; i < gridBlocks.size(); i++) {
            gridBlocks.get(i).getPos().setY(gridBlocks.get(i).getPos().getY()
                    + PIECE_WIDTH);
        }
    }

    private void organizeGridBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = (NUM_BLOCKS_VERTICALLY); i >= 0; i--) {
            for (Block b : gridBlocks) {
                if (b.getPos().getY() == ((GRID_TOP - PIECE_WIDTH)
                        + (i * PIECE_WIDTH))) {
                    blocks.add(b);
                    if (number_blocks_line[i] < NUM_BLOCKS_HORIZONTALLY) {
                        number_blocks_line[i]++;
                    }
                }
            }
        }
        gridBlocks.clear();
        gridBlocks.addAll(blocks);
    }

    public Piece getCurrent() {
        return current;
    }

    public void setCurrent(Piece current) {
        this.current = current;
        player.setCurrent(this.current);
    }

    public PlayerInput getPlayer() {
        return player;
    }

    public void setPlayer(PlayerInput player) {
        this.player = player;
    }

    public List<Block> getGridBlocks() {
        return gridBlocks;
    }

    public void setScene(Tetris scene) {
        this.scene = scene;
    }

}
