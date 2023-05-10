package PIECES;

import java.util.List;

import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Bishop extends Piece {

    public Bishop(int posX, int posY, Color color) {
        super(posX, posY, color);
    }

    private Tile pos;
    public int value = 5;
    
    @Override
    public void move(int newX, int newY) {
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return;
        }
        int diffX = Math.abs(newX - this.posX);
        int diffY = Math.abs(newY - this.posY);

        if(diffX == diffY){
            this.posY = newY;
            this.posX = newX;
        }
        else{
            System.out.print("Invalid bishop move.");
        }

    }

    @Override
    public List<Move> findMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMoves'");
    }
}