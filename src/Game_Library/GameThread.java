package Game_Library;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.logging.Logger;
import org.tetris.indie.allan.PlayerInput;
import static Game_Library.GeneralConfiguration.*;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GameThread extends Canvas implements Runnable {

    private GameStateEnum GAME_STATE;

    private PlayerInput player;
    private static GameScene currentScene;
    private Thread gameThread;

    public GameThread() {
        GAME_STATE = GameStateEnum.RUNNING;

        player = new PlayerInput();
        //currentScene.setPlayer(player);
        addKeyListener(new GameKeyBoardAdapter(player));
    }

    public synchronized void start() {
        if (GAME_STATE == GameStateEnum.STOPED) {
            return;
        } else {
            GAME_STATE = GameStateEnum.RUNNING;
        }
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (GAME_STATE != GameStateEnum.RUNNING) {
            return;
        } else {
            GAME_STATE = GameStateEnum.STOPED;
        }
        try {
            gameThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameThread.class.getName()).log(null, null, ex);
        }
        System.exit(1);
    }

    public void renderGame(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //DRAW EVERYTHING THE CAMERAS CAN SEE
        for (GameCamera cam : currentScene.getCameras()) {
            cam.draw(g2d);
        }

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        renderGame(g);

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        requestFocus();
        long startTime;
        long endTime;
        int countFPS = 0;
        int countTimeToMovePieceDown = 0;
        double spaceTimePerFrame = (1 / GAME_FPS) * 1000;
        while (GAME_STATE == GameStateEnum.RUNNING) {
            //GET THE CURRENT TIME BEFORE START THE GAME UPDATES AND RENDER
            startTime = System.currentTimeMillis();

            if (currentScene != null) {
                //UPDATE ALL THE SCENES
                currentScene.update();

                //THEN RENDER THESE SCENES
                render();
            }
            //GET THE TIME AFTER THE GAME UPDATES AND RENDER
            endTime = System.currentTimeMillis();

            //CALCULATE THE TIME THAT HAS BEEN SPENT TO UPDATE AND RENDER THE SCENE
            long delta = endTime - startTime;

            //IF THE TIME SPENT TO RENDER THE SCENE IS LESS THAN THE TIME 
            //TO RENDER ONE SCENE(DETERMINED BY THE FPS OF THE GAME) THEN 
            //TO RENDER THE NEXT SCENE THE GAMETHREAD HAS TO SLEEP EXACTLY
            //THE AMOUNT OF TIME LEFT TO RENDER THE PREVIOUS SCENE.
            //BUT, IF THE SCENE HAS TAKEN MORE TIME TO RENDER ITSELF, 
            //THE GAMETHREAD HAS TO SLEEP UNTIL THE NEXT SCENE STARTS
            if (delta < spaceTimePerFrame) { //if the frame is to fast
                try {
                    Thread.sleep((long) (spaceTimePerFrame - delta));
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(null, null, ex);
                }
            } else {
                try {//if the frame wasnt fast enought, render the late frame and wait until the next one
                    render();
                    Thread.sleep((long) (delta - spaceTimePerFrame));
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(null, null, ex);
                }
            }

        }
        //IF THE GAMETHREAD IS NO LONGER RUNNING, KILL THE GAMETHREAD AND 
        //EXIT THE APPLICATION
        stop();
    }

    public static void exit() {
        System.exit(1);
    }

    public GameStateEnum getState() {
        return GAME_STATE;
    }

    public void setState(GameStateEnum state) {
        this.GAME_STATE = state;
    }

    public PlayerInput getPlayer() {
        return player;
    }

    public void setPlayer(PlayerInput player) {
        this.player = player;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    protected GameScene getCurrentScene() {
        return currentScene;
    }

    protected void setCurrentScene(GameScene currentScene) {
        this.currentScene = currentScene;
    }

}
