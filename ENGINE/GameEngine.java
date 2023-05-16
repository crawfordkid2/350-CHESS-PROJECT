package ENGINE;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.EmptyTile;
import BOARD_INFO.TILES.FullTile;
import BOARD_INFO.TILES.Tile;
import PIECES.*;

public class GameEngine {
    public Board board;
    public int turn;

    public GameEngine(Board board){
        this.board = board;
        this.turn = 0;
    }

    public boolean tryMove(String moveString){
        if(moveString.length() != 4){
            System.out.println("Enter a 4 character input xyxy");
            return false;
        }
        int xFrom = moveString.charAt(0)-97;
        int yFrom = moveString.charAt(1)-49;
        int xTo = moveString.charAt(2)-97;
        int yTo = moveString.charAt(3)-49;

        Piece piece = this.board.getTile(xFrom, yFrom).getPiece();
        Tile toTile = this.board.getTile(xTo, yTo);

        //Checks if piece exists and if there is either an empty tile or an opponent on the new tile. 
        if(piece != null && (toTile.getPiece() == null || toTile.getPiece().getColor() != piece.getColor())){
            if(piece.move(xTo, yTo)){
                this.board.setTile(xTo, yTo, new FullTile(xTo, yTo, piece));
                this.board.setTile(xFrom, yFrom, new EmptyTile(xFrom, yFrom));
                return true;
            }
        }

        return false;
    }

    public void restart(){
        this.board = new Board();
        this.turn = 0;
        board.printBoard();
    }
}
