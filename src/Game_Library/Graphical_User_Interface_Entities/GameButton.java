/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library.Graphical_User_Interface_Entities;

import Game_Library.GameGUI;
import java.awt.Graphics2D;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GameButton extends GameGUI{

    public GameLabel label;
    
    private boolean activate;

    public GameButton(String text) {
        this.label = new GameLabel(text);
        activate = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawString(label.getText(), getPos().x, getPos().y);
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

}
