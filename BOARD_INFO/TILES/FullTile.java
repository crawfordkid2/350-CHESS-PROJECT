package BOARD_INFO.TILES;


import PIECES.Piece;

public class FullTile extends Tile {

    Piece piece;

    public FullTile(int coord, Piece piece) {
        super(coord);
        this.piece = piece;
    }

    @Override
    public boolean isFull() {return true;}

    @Override
    public Piece getPiece() {return this.piece;}
    
}
