/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.indie.allan;

import Game_Library.GameCamera;
import Game_Library.GameStateEnum;
import Game_Library.GameScene;

import static org.tetris.indie.allan.TextFonts.*;
import static Game_Library.GameWindow.*;
import static org.tetris.indie.allan.Configuration.*;
import static org.tetris.indie.allan.Grid.*;
import static org.tetris.indie.allan.Piece.*;
import static org.tetris.indie.allan.PieceGenerator.*;
import static Game_Library.Graphical_User_Interface_Entities.GameColorPalette.*;
import static Game_Library.Graphical_User_Interface_Entities.GamePlayerLevelCounter.*;
import static Game_Library.Graphical_User_Interface_Entities.GameTimer.*;
import java.awt.Font;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class Tetris extends GameScene {

    public static int GAP_GRID_PIECE_GENERATOR;
    private final Grid grid;
    private final HeadsUpDisplay hud;
    public static Font FONT_NUMBERS;
    public static Font FONT_NAMES;

    public Tetris(PlayerInput player) {
        System.out.println("CRIOU");
        setGameProperties();
        super.setName("Level");
        hud = new HeadsUpDisplay();
        grid = new Grid(this);
        this.setPlayer(player);
        this.getEntities().add(grid);
        getCameras().get(0).setHud(hud);
    }

    private void setGameProperties() {
        int frame_width = WINDOW_WIDTH;
        int frame_height = WINDOW_HEIGHT;
        if (frame_width == 1920 && frame_height == 1080) {
            PIECE_WIDTH = 42;
            SQUARE_ARC_DIAMETER = 11;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_1080_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_1080_LETTERS;
        } else if (frame_width == 1600 && frame_height == 900) {
            PIECE_WIDTH = 34;
            SQUARE_ARC_DIAMETER = 9;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_900_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_900_LETTERS;
        } else if (frame_width == 1366 && frame_height == 768) {
            PIECE_WIDTH = 30;
            SQUARE_ARC_DIAMETER = 8;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_768_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_768_LETTERS;
        } else if (frame_width == 1280 && frame_height == 720) {
            System.out.println("PORRA");
            PIECE_WIDTH = 28;
            SQUARE_ARC_DIAMETER = 8;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_720_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_720_LETTERS;
        } else if (frame_width == 1024 && frame_height == 576) {
            PIECE_WIDTH = 22;
            SQUARE_ARC_DIAMETER = 6;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_576_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_576_LETTERS;
        } else if (frame_width == 960 && frame_height == 540) {
            PIECE_WIDTH = 20;
            SQUARE_ARC_DIAMETER = 6;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_540_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_540_LETTERS;
        } else if (frame_width == 848 && frame_height == 480) {
            PIECE_WIDTH = 18;
            SQUARE_ARC_DIAMETER = 5;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_480_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_480_LETTERS;
        } else if (frame_width == 720 && frame_height == 405) {
            PIECE_WIDTH = 16;
            SQUARE_ARC_DIAMETER = 4;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_405_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_405_LETTERS;
        } else if (frame_width == 640 && frame_height == 360) {
            PIECE_WIDTH = 14;
            SQUARE_ARC_DIAMETER = 4;
            FONT_NUMBERS = FONT_CHAMPAGNE_LIMOUSINES_360_NUMBERS;
            FONT_NAMES = FONT_CHAMPAGNE_LIMOUSINES_360_LETTERS;
        }

        GRID_WIDTH = NUM_BLOCKS_HORIZONTALLY * PIECE_WIDTH;
        GRID_HEIGHT = NUM_BLOCKS_VERTICALLY * PIECE_WIDTH;
        GRID_TOP = WINDOW_CENTER_Y - GRID_HEIGHT / 2;
        GRID_BOTTOM = WINDOW_CENTER_Y + GRID_HEIGHT / 2;
        GRID_LEFT = WINDOW_CENTER_X - GRID_WIDTH / 2;
        GRID_RIGHT = WINDOW_CENTER_X + GRID_WIDTH / 2;

        GAP_GRID_PIECE_GENERATOR = GAP_NUMBER_BLOCKS * PIECE_WIDTH;

        INITIAL_POS_PIECE_GRID_X = GRID_LEFT
                + ((NUM_BLOCKS_HORIZONTALLY / 2) - 1) * PIECE_WIDTH;
        INITIAL_POS_PIECE_GRID_Y = GRID_TOP - 3 * PIECE_WIDTH;

        POS_SCORE_X = GRID_LEFT;
        POS_SCORE_Y = GRID_TOP + PIECE_WIDTH;

        POS_TIMER_X = GRID_RIGHT + 10;
        POS_TIMER_Y = GRID_BOTTOM;

        POS_LEVEL_X = GRID_LEFT;
        POS_LEVEL_Y = GRID_BOTTOM;

        PIECE_DIST_INTERN_RIGHT = PIECE_WIDTH / 2;
        PIECE_DIST_INTERN_LEFT = PIECE_DIST_INTERN_RIGHT / 2;

        PG_WIDTH = PG_NUMBER_BLOCKS * PIECE_WIDTH;
        PG_HEIGHT = PG_WIDTH;
        PG_LEFT = GAP_GRID_PIECE_GENERATOR + GRID_RIGHT;
        PG_RIGTH = PG_LEFT + PG_WIDTH;
        PG_TOP = GRID_TOP;
        PG_BOTTOM = PG_TOP + PG_HEIGHT;
        PG_CENTER_X = PG_LEFT + PG_WIDTH / 2;
        PG_CENTER_Y = PG_TOP + PG_HEIGHT / 2;

        POS_PG_X = PG_CENTER_X;
        POS_PG_Y = PG_BOTTOM + PIECE_WIDTH;

        LEVELS_TO_DECREASE_TIME_TO_PIECE_GO_DOWN_AFTER = NUMBER_OF_LEVELS
                / (START_TIME_TO_PIECE_GO_DOWN - 2);

        PALETTE_WIDTH = 9 * PIECE_WIDTH;
        PALETTE_HEIGHT = PIECE_WIDTH;
        PALETTE_SELECTOR_HEIGHT = PIECE_WIDTH / 2;
    }

    @Override
    public void update() {
        if (getState() == GameStateEnum.RUNNING) {
            hud.getTimer().update();
            grid.update();
        }
        
        //Update each of the cameras presented in the scene
        for(GameCamera cam: getCameras()){
            cam.update();
        }
    }

    @Override
    public void setPlayer(PlayerInput player) {
        super.setPlayer(player);
        this.getPlayer().setGrid(grid);
        this.getPlayer().setCurrent(grid.getCurrent());
        grid.setPlayer(this.getPlayer());
    }

    public Grid getGrid() {
        return grid;
    }

    public HeadsUpDisplay getHud() {
        return hud;
    }
 
    
    public PlayerInput getPlayer(){
        return player;
    }
}
