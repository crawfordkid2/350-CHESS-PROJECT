package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Bishop extends Piece {

    public Bishop(int posX, int posY, Color color, Board board) {
        super(posX, posY, color, board);
    }

    private Tile pos;
    public int value = 5;
    
    @Override
    public boolean move(Move move) {
        int newX = move.getNew().getCoordX();
        int newY = move.getNew().getCoordY();

        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }
        int diffX = Math.abs(newX - this.posX);
        int diffY = Math.abs(newY - this.posY);

        if(diffX == diffY){
            return collsionCheck(move);
        }
        else{
            //System.out.print("Invalid bishop move.");
            return false;
        }

    }
}