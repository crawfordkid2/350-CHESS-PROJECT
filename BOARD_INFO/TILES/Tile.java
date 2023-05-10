package BOARD_INFO.TILES;

import PIECES.Piece;

public abstract class Tile {
    
    int coordX;
    int coordY;

    public Tile(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public abstract boolean isFull();

    public abstract Piece getPiece();
}
