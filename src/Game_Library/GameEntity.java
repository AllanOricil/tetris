package Game_Library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public abstract class GameEntity implements Drawable {

    protected Vector2D pos;
    protected Dimension size;
    private Color backgroundColor;
    private int depth;
    private final int id;
    private GameAnimation animation;

    public static int numGameEntities = 0;
    public GameMouseAdapter mouseListener;
    public GameKeyBoardAdapter keyListener;

    public GameEntity() {
        pos = new Vector2D(0,0);
        size = new Dimension(100,100);
        backgroundColor = Color.WHITE;
        depth = 100;
        id = numGameEntities;
        numGameEntities++;
        //System.out.println("IDAUX: " + counter);
    }

    public Vector2D getPos() {
        return pos;
    }

    public void setPos(Vector2D pos) {
        this.pos.setX(pos.getX());
        this.pos.setY(pos.getY());
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Vector2D getCenter() {
        Vector2D center = new Vector2D();
        center.setX(pos.getX() + size.width / 2);
        center.setY(pos.getY() + size.height / 2);
        return center;
    }

    @Override
    public void draw(Graphics2D g2d) {
        //DRAW THE BACKGROUND
        g2d.setColor(getBackgroundColor());
        g2d.fillRect(pos.getX(),
                pos.getY(),
                size.width,
                size.height);
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void addKeyListener(KeyListener k) {
        keyListener = (GameKeyBoardAdapter) k;
    }

    public int getId() {
        return id;
    }

    public abstract void update();

    public static int getCounter() {
        return numGameEntities;
    }

    public GameAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(GameAnimation animation) {
        this.animation = animation;
    }

}
