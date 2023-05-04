package PIECES;

import ENUM.Color;
import BOARD_INFO.Tile;;

public class Rook extends Piece {

    public Rook(final Color color) {
        this.color = color;
    }

    private Tile pos;
    public int value = 8;
    public boolean canCastle = true;

    @Override
    public void move() {

    }
}