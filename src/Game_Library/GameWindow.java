package Game_Library;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import static Game_Library.GeneralConfiguration.DEFAULT_SCREEN_HEIGHT;
import static Game_Library.GeneralConfiguration.DEFAULT_SCREEN_WIDTH;
import static Game_Library.GeneralConfiguration.WINDOW_FULLSCREEN_MODE;
import static Game_Library.GeneralConfiguration.WINDOW_WINDOWED_MODE;

/**
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GameWindow {

    private final JFrame frame;
    private final GameThread gameThread;

    private String name;
    private Dimension size;
    private boolean fullScreenMode;
    private boolean windowedMode;

    public static int WINDOW_CENTER_X;
    public static int WINDOW_CENTER_Y;
    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;

    /**
     *
     * @param name - Name of the game. It will be displayed in the top left hand
     * corner of the screen, if it is not in full screen
     */
    public GameWindow(String name) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double fullScreenWidthSize = screenSize.getWidth();
        double fullScreenHeightSize = screenSize.getHeight();

        windowedMode = WINDOW_WINDOWED_MODE;
        fullScreenMode = WINDOW_FULLSCREEN_MODE;
        frame = new JFrame(name);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        frame.setUndecorated(!windowedMode);
        if (!fullScreenMode) {
            frame.setSize(new Dimension((int) fullScreenWidthSize,
                    (int) fullScreenHeightSize));
            //frame.setSize(1920,1080);
        } else {
            frame.setSize(new Dimension(DEFAULT_SCREEN_WIDTH,
                    DEFAULT_SCREEN_HEIGHT));
        }
        WINDOW_WIDTH = frame.getSize().width;
        WINDOW_HEIGHT = frame.getSize().height;
        WINDOW_CENTER_X = WINDOW_WIDTH / 2;
        WINDOW_CENTER_Y = WINDOW_HEIGHT / 2;

        gameThread = new GameThread();
    }

    public void startGame() {
        frame.add(gameThread);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameThread.exit();
            }
        });
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        gameThread.start();
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public boolean isFullScreenMode() {
        return fullScreenMode;
    }

    public void setFullScreenMode(boolean fullScreenMode) {
        this.fullScreenMode = fullScreenMode;
    }

    public boolean isWindowedMode() {
        return windowedMode;
    }

    public void setWindowedMode(boolean windowedMode) {
        this.windowedMode = windowedMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public void addGameScene(GameScene scene) {
        gameThread.setCurrentScene(scene);
    }

    public GameThread getGameThread() {
        return gameThread;
    }
    
    
}
