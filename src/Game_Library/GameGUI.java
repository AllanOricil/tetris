/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public abstract class GameGUI implements Drawable {

    private Point pos;
    private Dimension size;
    private PosEnum relativePosition;
    private Color backgroundColor;
    private final int id;

    private static int counter = 0;

    public GameGUI() {
        pos = new Point(0,0);
        size = new Dimension(0,0);
        backgroundColor = Color.WHITE;

        id = counter;
        counter++;
        //System.out.println("IDAUX: " + counter);
    }

    @Override
    public abstract void draw(Graphics2D g2d);// {
        //DRAW THE BACKGROUND
        //g2d.setColor(getBackgroundColor());
        //g2d.fillRect(pos.x, pos.y, size.width, size.height);
    //}

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
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

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

    public PosEnum getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(PosEnum relativePosition) {
        this.relativePosition = relativePosition;
    }

}
