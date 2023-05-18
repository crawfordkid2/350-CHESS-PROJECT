package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class King extends Piece {

    public King(int posX, int posY, Color color) {
        super(posX, posY, color);
    }

    private Tile pos;
    public int value = 20;
    public boolean isChecked = false;
    public boolean canCastle = true;
    
    @Override
    public boolean move(int newX, int newY) {
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }

        int diffX = Math.abs(newX - this.posX);
        int diffY = Math.abs(newY - this.posY);

        if(diffX == 1 && diffY == 0 ||diffX == 0 && diffY == 1 ||diffX == 1 && diffY == 1){
            return true;
        }
        else{
            System.out.println("Invalid King Move.");
            return false;
        }
    }

    //DOES NOT CURRENTLY CHECK IF MOVE PUTS KING IN A CHECK
    @Override
    public List<Move> findMoves(Board b) {

        List<Move> moves = new ArrayList<>();

        if (this.canCastle = true) {
            //check if rank is clear between rook, to implement, add move if so
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                if(move(i, j) && (b.getTile(i, j).getColor() != this.color)) {
                    moves.add(new Move(b.getTile(this.posX, this.posY), b.getTile(i, j)));
                }
            }
        }
        return moves;

        
    }
}