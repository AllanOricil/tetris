/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameGUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import static org.tetris.indie.allan.Tetris.FONT_NAMES;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GameLabel extends GameGUI {

    private String text;
    private Color fontColor;
    private Font font;

    public GameLabel(String text) {
        this.text = text;
        fontColor = Color.WHITE;
        font = FONT_NAMES;
        super.setPos(new Point(0,0));
    }

    public GameLabel(String text, Color color, Font font) {
        this.text = text;
        setBackgroundColor(color);
        this.font = font;
        super.setPos(new Point(0,0));
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(getBackgroundColor());
        g2d.setFont(font);
        g2d.drawString(text, getPos().x, getPos().y);
    }

    public void setSize() {
        Rectangle frc;
        frc = getStringBounds(this.text, font, getPos().x, getPos().y);
        super.setSize(new Dimension(frc.width, frc.height));
    }

    @Override
    public void setPos(Point pos) {
        super.setPos(pos);
        setSize();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public static Rectangle getStringBounds(String str, Font font, float x, float y) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,
                true, true);
        GlyphVector gv = font.createGlyphVector(frc, str);
        return gv.getPixelBounds(null, x, y);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

}
