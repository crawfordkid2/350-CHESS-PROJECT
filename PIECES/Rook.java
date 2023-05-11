package PIECES;

import java.util.List;

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
    public void move(int newX, int newY) {
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return;
        }

        if(newX != this.posX && newY == this.posY || newX == this.posX && newY != this.posY){
            this.posX = newX;
            this.posY = newY;
        }
        else{
            System.out.print("Invalid Rook Move.");
        }
    }

    @Override
    public List<Move> findMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMoves'");
    }
}