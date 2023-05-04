package PIECES;

import ENUM.Color;
import BOARD_INFO.Tile;;

public class Knight extends Piece {

    public Knight(final Color color) {
        this.color = color;
    }

    private Tile pos;
    public int value = 5;
    
    @Override
    public void move() {

    }
}