package BOARD_INFO.TILES;

import ENUM.Color;
import PIECES.Piece;

public abstract class Tile {
    
    int coordX;
    int coordY;
    Color color;

    public Tile(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public abstract boolean isFull();

    public abstract Piece getPiece();

    public abstract Color getColor();

    public int getCoordX() {
        return this.coordX;
    }

    public int getCoordY() {
        return this.coordY;
    }
}
