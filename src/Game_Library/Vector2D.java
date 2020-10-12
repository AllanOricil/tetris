package Game_Library;

import java.awt.geom.Point2D;

public class Vector2D {

    private int x;
    private int y;

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Point2D point) {
        this.x = (int) point.getX();
        this.y = (int) point.getY();
    }

    public Vector2D setVector(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
