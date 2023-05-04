package PIECES;

import ENUM.Color;
import BOARD_INFO.Tile;;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        this.color = color;
    }

    private Tile pos;
    public int value = 5;
    
    @Override
    public void move() {

    }
}