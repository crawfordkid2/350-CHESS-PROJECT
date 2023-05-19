package PIECES;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
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
    public boolean move(Move move) {
        int newX = move.getNew().getCoordX();
        int newY = move.getNew().getCoordY();

        if(newX < 0 || newX > 7 || newY < 0 || newY > 7){
            System.out.print("Invalid Input, Out of Bounds");
            return false;
        }
        int diffX = Math.abs(newX - this.posX);
        int diffY = Math.abs(newY - this.posY);

        if(diffX == diffY){
            return true;
        }
        else{
            System.out.print("Invalid bishop move.");
            return false;
        }

    }

    @Override
    public List<Move> findMoves(Board b) {

        List<Move> moves = new ArrayList<>();
        
        // //Up/Right
        // for(int i = this.posX + 1, j = this.posY + 1; i < 8 && j < 8; i++, j++) {
        //     if(move(i, j) && (b.getTile(i, j).getColor() != this.color)) {
        //         moves.add(new Move(b.getTile(this.posX, this.posY), b.getTile(i, j)));
        //     }
        //     else {break;}
        // }

        // //Up/Left
        // for(int i = this.posX - 1, j = this.posY + 1; i >= 0 && j < 8; i--, j++) {
        //     if(move(i, j) && (b.getTile(i, j).getColor() != this.color)) {
        //         moves.add(new Move(b.getTile(this.posX, this.posY), b.getTile(i, j)));
        //     }
        //     else {break;}
        // }

        // //Down/Right
        // for(int i = this.posX + 1, j = this.posY - 1; i < 8 && j >= 0; i++, j--) {
        //     if(move(i, j) && (b.getTile(i, j).getColor() != this.color)) {
        //         moves.add(new Move(b.getTile(this.posX, this.posY), b.getTile(i, j)));
        //     }
        //     else {break;}
        // }

        // //Down/Left
        // for(int i = this.posX - 1, j = this.posY - 1; i >= 0 && j >= 0; i--, j--) {
        //     if(move(i, j) && (b.getTile(i, j).getColor() != this.color)) {
        //         moves.add(new Move(b.getTile(this.posX, this.posY), b.getTile(i, j)));
        //     }
        //     else {break;}
        // }
        
        return moves;
    }
}