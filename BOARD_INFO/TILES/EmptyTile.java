package BOARD_INFO.TILES;

import PIECES.Piece;

public class EmptyTile extends Tile {

    public EmptyTile(int coordX, int coordY) {
        super(coordX, coordY);
    }

    @Override
    public boolean isFull() {return false;}

    @Override
    public Piece getPiece() {return null;}
    
}
