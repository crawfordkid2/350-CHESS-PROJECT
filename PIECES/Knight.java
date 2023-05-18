package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.*;
import BOARD_INFO.TILES.Tile;
import ENGINE.Move;
import ENUM.Color;;

public class Knight extends Piece {

    public Knight(int posX, int posY, Color color) {
        super(posX, posY, color);
    }

    private Tile pos;
    public int value = 5;
    
    @Override
    public boolean move(int newX, int newY) {
        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }

        int diffX = Math.abs(newX - this.posX);
        int diffY = Math.abs(newY - this.posY);

        if(diffX == 1 && diffY == 2 || diffX == 2 && diffY == 1){
            return true;
        }
        else{
            System.out.println("Invalid Knight Move.");
            return false;
        }
    }

    @Override
    public List<Move> findMoves(Board b) {

        List<Move> moves = new ArrayList<>();

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