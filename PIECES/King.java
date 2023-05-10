package PIECES;

import java.util.List;

import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class King extends Piece {

    public King(int posX, int posY, Color color) {
        super(posX, posY, color);
    }

    private Tile pos;
    public int value = 20;
    public boolean isChecked;
    public boolean canCastle = true;
    
    @Override
    public void move(int newX, int newY) {
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return;
        }

        int diffX = Math.abs(newX - this.posX);
        int diffY = Math.abs(newY - this.posY);

        if(diffX == 1 && diffY == 0 ||diffX == 0 && diffY == 1 ||diffX == 1 && diffY == 1){
            this.posX = newX;
            this.posY = newY;
        }
        else{
            System.out.print("Invalid King Move.");
        }
    }

    @Override
    public List<Move> findMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMoves'");
    }
}