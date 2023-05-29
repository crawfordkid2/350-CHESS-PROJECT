package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Rook extends Piece {

    public Rook(int posX, int posY, Color color, Board board) {
        super(posX, posY, color, board);
    }

    private Tile pos;
    public int value = 8;
    public boolean canCastle = true;

    @Override
    public boolean move(Move move, Board board) {
        int newX = move.getNew().getCoordX();
        int newY = move.getNew().getCoordY();
        
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }

        if(newX != this.posX && newY == this.posY || newX == this.posX && newY != this.posY){
            return collsionCheck(move);
        }
        else{
            //System.out.println("Invalid Rook Move.");
            return false;
        }
    }
}