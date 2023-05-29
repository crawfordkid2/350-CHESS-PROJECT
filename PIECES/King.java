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
    public boolean move(Move move) {
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
        else{
            //System.out.println("Invalid King Move.");
            return false;
        }
    }
}