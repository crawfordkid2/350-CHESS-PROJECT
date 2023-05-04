package PIECES;

import ENUM.Color;
import BOARD_INFO.Tile;;

public class Queen extends Piece {

    public Queen(final Color color) {
        this.color = color;
    }

    private Tile pos;
    public int value = 10;
    
    @Override
    public void move() {

    }
}