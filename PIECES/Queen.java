package PIECES;

import java.util.List;

import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Queen extends Piece {

    public Queen(int pos, Color color) {
        super(pos, color);
    }

    private Tile pos;
    public int value = 10;
    
    @Override
    public void move() {

    }

    @Override
    public List<Move> findMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMoves'");
    }
}