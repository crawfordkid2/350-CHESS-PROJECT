package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.*;
import ENGINE.Move;
import ENUM.Color;

public class Pawn extends Piece {

    public Pawn(int posX, int posY, Color color, Board board) {
        super(posX, posY, color, board);
    }

    private Tile pos;
    public int value = 1;
    
    @Override
    public boolean move(Move move) {
        int newX = move.getNew().getCoordX();
        int newY = move.getNew().getCoordY();
        Tile newTile = move.getNew();

        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }
        // firstmove = false is temporary implementation, will need to be changed once Move list is working
        if(this.color == Color.BLACK){
            if(newY == this.posY-1 && newX == this.posX && newTile.getColor() == Color.EMPTY){
                return true;
            }
            else if(newY == this.posY-2 && newX == this.posX && this.firstMove && newTile.getColor() == Color.EMPTY){
                return true;
            }
            else if(newY == this.posY-1 && (newX == this.posX-1 || newX == this.posX+1) && newTile.getColor() != Color.EMPTY && newTile.getColor() != this.color){
                return collsionCheck(move);
            }
            else{
//                System.out.println("Invalid Pawn Move");
                return false;
            }
        }
        else if(this.color == Color.WHITE){
            if(newY == this.posY+1 && newX == this.posX && newTile.getColor() == Color.EMPTY){
                return true;
            }
            else if(newY == this.posY+2 && newX == this.posX && this.firstMove && newTile.getColor() == Color.EMPTY){
                return true;
            }
            else if(newY == this.posY+1 && (newX == this.posX-1 || newX == this.posX+1) && newTile.getColor() != Color.EMPTY && newTile.getColor() != this.color){
                return collsionCheck(move);
            }
            else{
//                System.out.println("Invalid Pawn Move");
                return false;
            }
        }
        return false;
    }

    public void doublePawn() {
        this.firstMove = false;
    }

    public void enPassant() {
        
    }

}