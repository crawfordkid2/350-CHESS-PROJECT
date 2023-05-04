package PIECES;

import ENUM.Color;
import BOARD_INFO.Tile;;

public class King extends Piece {

    public King(final Color color) {
        this.color = color;
    }

    private Tile pos;
    public int value = 20;
    public boolean isChecked;
    public boolean canCastle = true;
    
    @Override
    public void move() {

    }
}