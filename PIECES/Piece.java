package PIECES;

import ENGINE.Move;
import ENUM.Color;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.*;

public abstract class Piece {
    
    protected Color color;
    protected int posX;
    protected int posY;
    protected int value; 

    public Piece(int posX, int posY, final Color color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public abstract List<Move> findMoves(Board b);

    public abstract boolean move(Move move);

    public void capture(){}

    public String getPos(Piece piece) {
        return String.valueOf(this.posX) + String.valueOf(this.posY);
    }

    public void setPos(int newX, int newY) {
        this.posX = newX;
        this.posY = newY;
    }

    public boolean collsionCheck(Move move, Board board){
        int prevX = move.getPrev().getCoordX();
        int prevY = move.getPrev().getCoordY();
        int newX = move.getNew().getCoordX();
        int newY = move.getNew().getCoordY();

        int xMin = Math.min(prevX, newX);
        int yMin = Math.min(prevY, newY);
        int xMax = Math.max(prevX, newX);
        int yMax = Math.max(prevY, newY);
        Tile currTile = null;

        for(int i = xMin; i <= xMax; i++){
            for(int j = yMin; j <= yMax; j++){
                currTile = board.getTile(i, j);

                if(i == newX && j == newY){
                    if(currTile.getColor() == this.color){
                        return false;
                    }
                }
                else if(currTile.getColor() != null){
                    return false;
                }
            }
        }
        
        return true;
    }

}



