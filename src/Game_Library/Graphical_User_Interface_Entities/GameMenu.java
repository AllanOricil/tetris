
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameGUI;
import Game_Library.Positions;
import Game_Library.Vector2D;
import static Game_Library.GameWindow.WINDOW_CENTER_X;
import static Game_Library.GameWindow.WINDOW_CENTER_Y;
import static Game_Library.GameWindow.WINDOW_WIDTH;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import static org.tetris.indie.allan.Configuration.MENU_NAME_COLOR;
import static org.tetris.indie.allan.Tetris.FONT_NUMBERS;

public class GameMenu extends GameGUI {

    private GameLabel title;
    private Color buttonsColor;
    private Font buttonsFont;
    private List<GameButton> buttons;
    public Positions titleLocation;
    public Positions location;
    public GamePanel background;

    private static final int spaceBetweenButtons = 10;
    private static final int spaceBetweenLineAndButtons = 10;
    private static final int middleLineStroke = 5;

    public GameMenu(String title) {
        this.title = new GameLabel(title, Color.BLACK, FONT_NUMBERS);
        buttonsFont = FONT_NUMBERS;
        buttonsColor = MENU_NAME_COLOR;
        setBackgroundColor(Color.GRAY);
        background = new GamePanel(new Vector2D(0, 0), new Dimension(WINDOW_WIDTH, WINDOW_WIDTH));
        titleLocation = Positions.NORTH;
        location = Positions.CENTER;
        buttons = new ArrayList<>();
    }

    public GameMenu(String title, ArrayList<String> string) {
        this.title = this.title = new GameLabel(title, Color.BLACK, FONT_NUMBERS);
        buttonsFont = FONT_NUMBERS;
        buttonsColor = MENU_NAME_COLOR;
        setBackgroundColor(Color.GRAY);
        background = new GamePanel(new Vector2D(0, 0), new Dimension(WINDOW_WIDTH, WINDOW_WIDTH));
        titleLocation = Positions.NORTH;
        location = Positions.CENTER;
        buttons = new ArrayList<>();
        string.stream().forEach(this::addButton);
    }

    public GameMenu(GameLabel title, ArrayList<String> string) {
        this.title = title;
        buttonsFont = FONT_NUMBERS;
        buttonsColor = MENU_NAME_COLOR;
        setBackgroundColor(Color.GRAY);
        background = new GamePanel(new Vector2D(0, 0), new Dimension(WINDOW_WIDTH, WINDOW_WIDTH));
        titleLocation = Positions.NORTH;
        location = Positions.CENTER;
        buttons = new ArrayList<>();
        string.stream().forEach(this::addButton);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x;
        // DRAW THE BACKGROUND
        background.draw(g2d);
        // DRAW THE TITLE
        title.draw(g2d);
        // DRAW BUTTONS
        g2d.setColor(buttonsColor);
        g2d.setFont(buttonsFont);
        for (GameButton button : buttons) {
            button.draw(g2d);
        }
        // DRAW THE MIDDLE LINE
        g2d.setStroke(new BasicStroke(middleLineStroke));
        x = title.getPos().x + title.getSize().width + middleLineStroke + spaceBetweenLineAndButtons;
        g2d.drawLine(x, getPos().y, x, getPos().y + getSize().height);
    }

    public void setSize() {
        int top;
        int botton;
        int centerX;
        int centerY;
        Rectangle fontRect;
        Point posAux = new Point();

        // GET THE TITLE FONT SIZE
        fontRect = GameLabel.getStringBounds(title.getText(), title.getFont(), 0, 0);
        title.setSize(fontRect.getSize());

        // GET THE BUTTONS FONT SIZE
        for (GameButton button : buttons) {
            fontRect = GameLabel.getStringBounds(button.label.getText(), buttonsFont, 0, 0);
            button.setSize(fontRect.getSize());
        }

        // DETERMINE THE TOTAL WIDTH OF THE MENU
        getSize().width = title.getSize().width + getLargeButtonWidth() + 2 * middleLineStroke
                + 2 * spaceBetweenLineAndButtons;
        centerX = getSize().width / 2;

        // DETERMINE THE TOTAL HEIGHT OF THE MENU
        if (!buttons.isEmpty()) {
            getSize().height = ((buttons.size() - 1) * spaceBetweenButtons)
                    + (buttons.size() * buttons.get(0).getSize().height);
        } else {
            getSize().height = 0;
        }

        // SET THE POSITION OF THE MENU IN THE SCREEN
        setLocation();

        // DETERMINE THE CENTER, TOP AND BOTTON OF THE MENU
        centerY = getSize().height / 2;
        botton = getPos().y + getSize().height;
        top = getPos().y;

        // SET THE TITLES POSITION
        posAux.x = getPos().x + middleLineStroke - spaceBetweenLineAndButtons;

        switch (titleLocation) {
            case SOULTH:
                posAux.y = botton;
                break;
            case NORTH:
                posAux.y = top + title.getSize().height;
                break;
        }
        title.setPos(posAux);

        // SET THE BUTTONS POSITION
        for (int i = 0; i < buttons.size(); i++) {
            GameButton button = buttons.get(i);
            posAux.x = getPos().x + 2 * middleLineStroke + spaceBetweenLineAndButtons + title.getSize().width;
            int tam = i * (button.getSize().height + spaceBetweenButtons);
            posAux.y = top + tam + button.getSize().height;
            button.setPos(posAux);
        }

    }

    public void addButton(String s) {
        buttons.add(new GameButton(s));
        setSize();
    }

    public void addButton(GameButton b) {
        buttons.add(b);
        setSize();
    }

    private int getLargeButtonWidth() {
        int largeWidth = 0;
        for (GameButton button : buttons) {
            if (button.getSize().width > largeWidth) {
                largeWidth = button.getSize().width;
            }
        }
        return largeWidth;
    }

    private void setLocation() {
        switch (location) {
            case CENTER:
                getPos().x = WINDOW_CENTER_X - getSize().width / 2;
                getPos().y = WINDOW_CENTER_Y - getSize().height / 2;
                break;
            case WEST:
                getPos().x = 0;
                getPos().y = WINDOW_CENTER_Y - getSize().height / 2;
                break;
            case EAST:
                getPos().x = WINDOW_WIDTH - getSize().width;
                getPos().y = WINDOW_CENTER_Y - getSize().height / 2;
                break;
        }
    }

    public GameLabel getTitle() {
        return title;
    }

    public void setTitle(GameLabel title) {
        this.title = title;
    }

    public Color getButtonsColor() {
        return buttonsColor;
    }

    public void setButtonsColor(Color buttonsColor) {
        this.buttonsColor = buttonsColor;
    }

    public Font getButtonsFont() {
        return buttonsFont;
    }

    public void setButtonsFont(Font buttonsFont) {
        this.buttonsFont = buttonsFont;
    }

    public List<GameButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<GameButton> buttons) {
        this.buttons = buttons;
    }

}
