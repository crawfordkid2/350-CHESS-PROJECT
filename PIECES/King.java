package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class King extends Piece {

    public King(int posX, int posY, Color color, Board board) {
        super(posX, posY, color, board);
    }

    private Tile pos;
    public int value = 20;
    public boolean isChecked = false;
    public boolean canCastle = true;
    
    @Override
    public boolean move(Move move, Board board) {
        int newX = move.getNew().getCoordX();
        int newY = move.getNew().getCoordY();

        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }

        int diffX = Math.abs(newX - this.posX);
        int diffY = Math.abs(newY - this.posY);

        if(diffX == 1 && diffY == 0 ||diffX == 0 && diffY == 1 ||diffX == 1 && diffY == 1){
            return collsionCheck(move);
        }

        else if (firstMove) {
            if ((move.getNew()).getCoordY() == 0 && color == Color.WHITE || (move.getNew()).getCoordY() == 7 && color == Color.BLACK) {
                switch ((move.getNew()).getCoordX()) {
                    case 2:
                        return move.canCastleLeft(move.getPrev(), board);
                    case 6:
                        return move.canCastleRight(move.getPrev(), board);
                    default:
                        return false;
                }
            }
        }
        
        return false;
    }
}