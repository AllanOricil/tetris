/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Library;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.tetris.indie.allan.PlayerInput;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class GameKeyBoardAdapter extends KeyAdapter{
     
    private PlayerInput player;
    public GameKeyBoardAdapter(PlayerInput player){
        this.player = player;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //if (state.equals(GameStateEnum.RUNNING)) {
            player.keyPressed(e);
        //}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //if (state.equals(GameStateEnum.RUNNING)) {
            player.KeyReleased(e);
        //}
    }
}
