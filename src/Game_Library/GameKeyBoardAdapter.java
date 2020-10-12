
package Game_Library;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.tetris.indie.allan.PlayerInput;

public class GameKeyBoardAdapter extends KeyAdapter {

    private PlayerInput player;

    public GameKeyBoardAdapter(PlayerInput player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // if (state.equals(GameStateEnum.RUNNING)) {
        player.keyPressed(e);
        // }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // if (state.equals(GameStateEnum.RUNNING)) {
        player.KeyReleased(e);
        // }
    }
}
