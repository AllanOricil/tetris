
package Game_Library;

import static Game_Library.GeneralConfiguration.GAME_FPS;
import java.awt.Graphics2D;

public class GameAnimation extends GameEntity {

    private GameSprite sprites[];
    private double animationLenghtTime;
    private int ticksEachSprite;
    private int contTicks;
    private int currentImage;
    private GameStateEnum state;

    public GameAnimation(Vector2D pos) {
        this.pos = pos;
        currentImage = 0;
        state = GameStateEnum.PAUSED;
    }

    /**
     * This method resume the animation
     */
    public void play() {
        if (state == GameStateEnum.RUNNING) {
            return;
        } else {
            state = GameStateEnum.RUNNING;
        }
    }

    /**
     * This method pause the animation
     */
    public void pause() {
        if (state == GameStateEnum.PAUSED) {
            state = GameStateEnum.RUNNING;
        } else {
            state = GameStateEnum.PAUSED;
        }
    }

    /**
     * This method reset the animation
     */
    public void stop() {
        if (state != GameStateEnum.STOPED) {
            contTicks = 0;
            currentImage = 0;
            state = GameStateEnum.STOPED;
        }
    }

    @Override
    public void update() {
        if (state == GameStateEnum.RUNNING) {
            contTicks++;

            if (contTicks == ticksEachSprite) {
                contTicks = 0;
                currentImage = currentImage % sprites.length;
                currentImage++;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(sprites[currentImage].getImage(), pos.getX(), pos.getY(), size.width, size.height, null);
    }

    public GameSprite[] getSprites() {
        return sprites;
    }

    public void setSprites(GameSprite[] sprites) {
        this.sprites = sprites;
    }

    public double getAnimationLenghtTime() {
        return animationLenghtTime;
    }

    public void setAnimationLenghtTime(double animationLenghtTime) {
        this.animationLenghtTime = animationLenghtTime;
        double timeEachSprite = this.animationLenghtTime / sprites.length;
        ticksEachSprite = (int) (timeEachSprite * GAME_FPS);
    }

    public int getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(int currentImage) {
        this.currentImage = currentImage;
    }

}
