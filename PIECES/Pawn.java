package PIECES;

import java.util.List;

import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Pawn extends Piece {

    public Pawn(int posX, int posY, Color color) {
        super(posX, posY, color);
    }

    private Tile pos;
    public int value = 1;
    
    @Override
    public boolean move(int newX, int newY) {
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }

        if(this.color == Color.BLACK){
            if(newY == this.posY-1 && newX == this.posX){
                this.posY = newY;
                return true;
            }
            else{
                System.out.println("Invalid Pawn Move");
                return false;
            }
        }
        else if(this.color == Color.WHITE){
            if(newY == this.posY+1 && newX == this.posX){
                this.posY = newY;
                return true;
            }
            else{
                System.out.println("Invalid Pawn Move");
                return false;
            }
        }
        return false;
    }

    public void twoSpaceMove() {

    }

    public void enPassant() {
        
    }

    @Override
    public List<Move> findMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMoves'");
    }

}