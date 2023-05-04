package PIECES;

import ENUM.Color;
import BOARD_INFO.Tile;;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        this.color = color;
    }

    private Tile pos;
    public int value = 1;
    
    @Override
    public void move() {

    }

    public void twoSpaceMove() {

    }

    public void enPassant() {
        
    }

}