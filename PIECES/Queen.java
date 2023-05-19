package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Queen extends Piece {

    public Queen(int posX, int posY, Color color) {
        super(posX, posY, color);
    }

    private Tile pos;
    public int value = 10;
    
    @Override
    public boolean move(int newX, int newY) {
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }
        int diffX = Math.abs(newX - this.posX);
        int diffY = Math.abs(newY - this.posY);

        if(newX != this.posX && newY == this.posY || newX == this.posX && newY != this.posY || diffX == diffY){
            return true;
        }
        else{
            System.out.println("Invalid Queen Move.");
            return false;
        }
    }

    @Override
    public List<Move> findMoves(Board b) {
        
        List<Move> moves = new ArrayList<>();

        // TODO Auto-generated method stub
        return moves;
    }
}