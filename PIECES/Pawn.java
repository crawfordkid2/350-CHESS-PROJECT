package PIECES;

import java.util.List;

import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Pawn extends Piece {

    public Pawn(int pos, Color color) {
        super(pos, color);
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

    @Override
    public List<Move> findMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMoves'");
    }

}