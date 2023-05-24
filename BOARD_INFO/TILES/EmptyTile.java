package BOARD_INFO.TILES;

import ENUM.Color;
import PIECES.Piece;

public class EmptyTile extends Tile {

    public EmptyTile(int coordX, int coordY) {
        super(coordX, coordY);
    }

    @Override
    public boolean isFull() {return false;}

    @Override
    public Piece getPiece() {return null;}

    @Override
    public Color getColor() {return Color.EMPTY;}
    
}
