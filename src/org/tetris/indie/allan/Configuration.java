/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.indie.allan;

import java.awt.Color;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public abstract class Configuration {

    /**
     * Default game color configuration
     */
    public final static Color PIECE_T_COLOR = new Color(253, 157, 255);
    public final static Color PIECE_Z_COLOR = new Color(255, 100, 100);
    public final static Color PIECE_I_COLOR = new Color(0, 193, 193);
    public final static Color PIECE_L_COLOR = new Color(255, 202, 130);
    public final static Color PIECE_J_COLOR = new Color(122, 189, 243);
    public final static Color PIECE_S_COLOR = new Color(0, 176, 106);
    public final static Color PIECE_O_COLOR = new Color(254, 250, 165);
    public final static Color GRID_COLOR = Color.WHITE;
    public final static Color SCORE_NUMBER_COLOR = Color.WHITE;
    public final static Color SCORE_NAME_COLOR = Color.WHITE;
    public final static Color TIMER_NUMBER_COLOR = Color.WHITE;
    public final static Color TIMER_NAME_COLOR = Color.WHITE;
    public final static Color PIECE_GENERATOR_NAME_COLOR = Color.WHITE;
    public final static Color BACKGROUND_COLOR = new Color(130, 130, 130);
    public final static Color GRID_BACK_GROUND_COLOR = new Color(180, 180, 180);
    public final static Color LEVEL_NAME_COLOR = Color.WHITE;
    public final static Color LEVEL_NUMBER_COLOR = Color.WHITE;
    public final static Color MENU_NAME_COLOR = Color.WHITE;

    /**
     * Default grid configuration
     */
    public final static int NUM_BLOCKS_HORIZONTALLY = 10;
    public final static int NUM_BLOCKS_VERTICALLY = 20;
    public final static int GAP_NUMBER_BLOCKS = 2;
    public final static int PG_NUMBER_BLOCKS = 5;

    public final static int ADJUST_LEFT = -1;
    public final static int ADJUST_RIGHT = 1;
    
    /**
     * Default score reward
     */
    public final static int POINTS_PER_LINE = 100;
    public final static int POINTS_PER_COMBO = 30;

    /**
     * Other default game configurations
     */
    public final static int NUMBER_OF_LEVELS = 100;
    public final static double TIME_TO_FIRM_PIECE = 25;
    public final static int START_TIME_TO_PIECE_GO_DOWN = 15;

    public static int POS_SCORE_X;
    public static int POS_SCORE_Y;
}
