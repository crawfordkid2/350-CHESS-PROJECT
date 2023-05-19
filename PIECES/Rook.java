package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Rook extends Piece {

    public Rook(int posX, int posY, Color color) {
        super(posX, posY, color);
    }

    private Tile pos;
    public int value = 8;
    public boolean canCastle = true;

    @Override
    public boolean move(Move move) {
        int newX = move.getNew().getCoordX();
        int newY = move.getNew().getCoordY();
        
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }

        if(newX != this.posX && newY == this.posY || newX == this.posX && newY != this.posY){
            return true;
        }
        else{
            System.out.println("Invalid Rook Move.");
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