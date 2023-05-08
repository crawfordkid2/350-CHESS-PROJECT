package PIECES;

import java.util.List;

import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Rook extends Piece {

    public Rook(int pos, Color color) {
        super(pos, color);
    }

    private Tile pos;
    public int value = 8;
    public boolean canCastle = true;

    @Override
    public void move() {

    }

    @Override
    public List<Move> findMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMoves'");
    }
}