
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameEntity;
import java.awt.Color;
import java.awt.Graphics2D;

public class GameColorPalette extends GameEntity {

    private final static Color[] PALETTE_COLORS = new Color[9];
    private final static Color COLOR_PALETTE_1 = new Color(0x98E1C9);
    private final static Color COLOR_PALETTE_2 = new Color(0x79C8D8);
    private final static Color COLOR_PALETTE_3 = new Color(0x7E2561);
    private final static Color COLOR_PALETTE_4 = new Color(0xC8D5EF);
    private final static Color COLOR_PALETTE_5 = new Color(0xF4E9D7);
    private final static Color COLOR_PALETTE_6 = new Color(0xDDB588);
    private final static Color COLOR_PALETTE_7 = new Color(0x696BD3);
    private final static Color COLOR_PALETTE_8 = new Color(0xD77592);
    private final static Color COLOR_PALETTE_9 = new Color(0xE29CC6);

    private Color color_choosen;
    private Color default_color;

    public static boolean draw;

    public static int PALETTE_WIDTH;
    public static int PALETTE_HEIGHT;
    public static int PALETTE_SELECTOR_HEIGHT;

    private final int[] t_x;
    private final int[] t_y;

    private static int rectSize;

    public GameColorPalette() {
        t_x = new int[3];
        t_y = new int[3];

        rectSize = PALETTE_WIDTH / PALETTE_COLORS.length;
        PALETTE_COLORS[0] = COLOR_PALETTE_1;
        PALETTE_COLORS[1] = COLOR_PALETTE_2;
        PALETTE_COLORS[2] = COLOR_PALETTE_3;
        PALETTE_COLORS[3] = COLOR_PALETTE_4;
        PALETTE_COLORS[4] = COLOR_PALETTE_5;
        PALETTE_COLORS[5] = COLOR_PALETTE_6;
        PALETTE_COLORS[6] = COLOR_PALETTE_7;
        PALETTE_COLORS[7] = COLOR_PALETTE_8;
        PALETTE_COLORS[8] = COLOR_PALETTE_9;

        t_x[0] = 5;
        t_x[1] = rectSize / 2;
        t_x[2] = rectSize - 5;
        t_y[0] = PALETTE_SELECTOR_HEIGHT;
        t_y[1] = 0;
        t_y[2] = PALETTE_SELECTOR_HEIGHT;
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (int i = 0; i < 9; i++) {
            g2d.setColor(PALETTE_COLORS[i]);
            g2d.fillRect(getPos().getX() + i * (rectSize), getPos().getY(), rectSize, PALETTE_HEIGHT);
        }

        g2d.setColor(Color.BLACK);
        g2d.drawRect(getPos().getX(), getPos().getY(), PALETTE_WIDTH, PALETTE_HEIGHT);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(t_x, t_y, t_x.length);
    }

    @Override
    public void update() {
    }

    public Color getColor_choosen() {
        return color_choosen;
    }

}
