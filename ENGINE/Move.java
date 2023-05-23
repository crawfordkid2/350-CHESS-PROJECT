package ENGINE;

import BOARD_INFO.*;
import BOARD_INFO.TILES.Tile;
import PIECES.Pawn;
import PIECES.Piece;

public class Move {

    protected Tile prevPos;
    protected Tile newPos;
    protected boolean capture;
    protected boolean check;
    

    public Move(Tile fromPos, Tile toPos) {
        prevPos = fromPos;
        newPos = toPos;
    }

    
    public Tile getPrev() {
        return prevPos;
    }

    public Tile getNew() {
        return newPos;
    }
    
    public void makeMove(Move m) {
        if ((prevPos.getPiece() instanceof Pawn) && Math.abs(newPos.getCoordY() - prevPos.getCoordY()) == 2) {
            Pawn curr = (Pawn) prevPos.getPiece();
            curr.doublePawn();
        }

        Piece toMove = prevPos.getPiece();
        // NOT DONE

    }

}
