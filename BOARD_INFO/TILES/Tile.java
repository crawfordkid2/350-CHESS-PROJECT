package BOARD_INFO.TILES;

import PIECES.Piece;

public abstract class Tile {
    
    int coord;

    public Tile(int coord) {
        this.coord = coord;
    }

    public abstract boolean isFull();

    public abstract Piece getPiece();
}
