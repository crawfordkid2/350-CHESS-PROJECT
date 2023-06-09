package BOARD_INFO.TILES;


import ENUM.Color;
import PIECES.Piece;

public class FullTile extends Tile {

    Piece piece;

    public FullTile(int coordX, int coordY,  Piece piece) {
        super(coordX, coordY);
        this.piece = piece;
    }

    @Override
    public boolean isFull() {return true;}

    @Override
    public Piece getPiece() {return this.piece;}
    
    @Override
    public Color getColor() {return this.piece.getColor();}
}
