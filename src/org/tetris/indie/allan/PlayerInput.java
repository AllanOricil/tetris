package org.tetris.indie.allan;

import java.awt.event.KeyEvent;
import static org.tetris.indie.allan.Grid.GRID_BOTTOM;
import static org.tetris.indie.allan.Grid.GRID_LEFT;
import static org.tetris.indie.allan.Grid.GRID_RIGHT;
import static org.tetris.indie.allan.Piece.PIECE_WIDTH;

/**
 *
 * @author Allan Oricil - UNIFEI - Graduando em Engenharia de Computacao
 */
public class PlayerInput {

    private Piece current;
    private Grid grid;
    private int keyPressed;

    public void keyPressed(KeyEvent keyPressed) {
        
        this.keyPressed = keyPressed.getKeyCode();
        System.out.println("CURRENT:" + current);
        if (current != null && !colisionCheck()) {
            if (this.keyPressed == KeyEvent.VK_A || this.keyPressed == KeyEvent.VK_LEFT) {
                current.moveLeft();
            } else if (this.keyPressed == KeyEvent.VK_D || this.keyPressed == KeyEvent.VK_RIGHT) {
                current.moveRight();
            }
        }

        if (current != null && !grid.colisionCheck()) {
            if (this.keyPressed == KeyEvent.VK_SPACE) {
                current.moveDown();
            }
        }
        
    }

    public void KeyReleased(KeyEvent keyPressed) {
        this.keyPressed = keyPressed.getKeyCode();
    }

    private boolean colisionCheck() {
        double futureX;
        int aux = 0;
        if (keyPressed == KeyEvent.VK_Q) {
            current.leftRotation();
            for (Block cb : current.getBlocks()) {
                if (cb.getPos().getX() < GRID_LEFT || cb.getPos().getX() > GRID_RIGHT) {
                    current.rightRotation();
                    return true;
                }
                for (Block gd : grid.getGridBlocks()) {
                    if (cb.getPos().getX() == gd.getPos().getX()
                            && cb.getPos().getY() == gd.getPos().getY()) {
                        current.rightRotation();
                        return true;
                    }
                }
                if (cb.getPos().getY() >= GRID_BOTTOM) {
                    current.rightRotation();
                    return true;
                }
            }
        } else if (keyPressed == KeyEvent.VK_E) {
            current.rightRotation();
            for (Block cb : current.getBlocks()) {
                if (cb.getPos().getX() < GRID_LEFT || cb.getPos().getX() > GRID_RIGHT) {
                    current.leftRotation();
                    return true;
                }
                for (Block gd : grid.getGridBlocks()) {
                    if (cb.getPos().getX() == gd.getPos().getX()
                            && cb.getPos().getY() == gd.getPos().getY()) {
                        current.leftRotation();
                        return true;
                    }
                }
                if (cb.getPos().getY() >= GRID_BOTTOM) {
                    current.leftRotation();
                    return true;
                }
            }
        }

        if (keyPressed == KeyEvent.VK_A || keyPressed == KeyEvent.VK_LEFT) {
            aux = -1 * PIECE_WIDTH;
        } else if (keyPressed == KeyEvent.VK_D || keyPressed == KeyEvent.VK_RIGHT) {
            aux = PIECE_WIDTH;
        }

        //COLISION WITH THE RIGT AND LEFT SIDES OF THE GRID PIECES
        for (Block cb : current.getBlocks()) {
            futureX = cb.getPos().getX() + aux;
            if (futureX > GRID_RIGHT - aux || futureX < GRID_LEFT) {
                return true;
            }
        }
        //COLISION WITH THE RIGHT AND LEFT SIDES OF THE GRID
        for (Block cb : current.getBlocks()) {
            futureX = cb.getPos().getX() + aux;
            for (Block gd : grid.getGridBlocks()) {
                if (futureX == gd.getPos().getX()
                        && cb.getPos().getY() == gd.getPos().getY()) {
                    return true;
                }
            }
        }

        return false;
    }

    public void setCurrent(Piece current) {
        this.current = current;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getKeyPressed() {
        return keyPressed;
    }

}
