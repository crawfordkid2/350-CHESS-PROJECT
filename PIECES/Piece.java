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
    protected Board board;

    public Piece(int posX, int posY, final Color color, Board board) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.board = board;
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

    public boolean collsionCheck(Move move){
        int prevX = move.getPrev().getCoordX();
        int prevY = move.getPrev().getCoordY();
        int newX = move.getNew().getCoordX();
        int newY = move.getNew().getCoordY();

        
        Tile currTile = null;

        //Different if diagnal
        if(prevX == newX || prevY == newY){
            int xMin = Math.min(prevX, newX);
            int yMin = Math.min(prevY, newY);
            int xMax = Math.max(prevX, newX);
            int yMax = Math.max(prevY, newY);
            for(int i = xMin; i <= xMax; i++){
                for(int j = yMin; j <= yMax; j++){
                    currTile = this.board.getTile(i, j);

                    if(i == newX && j == newY){
                        if(currTile.getColor() == this.color){
                            return false;
                        }
                    }
                    else if(i == prevX && j == prevY){
                        continue;
                    }
                    else if(currTile.getColor() != Color.EMPTY){
                        return false;
                    }
                }
            }
        }
        else{
            int i = prevX;
            int j = prevY;
            int xSign = (prevX > newX) ? -1 : 1;
            int ySign = (prevY > newY) ? -1 : 1;
            while(i != newX && j != newY){
                currTile = this.board.getTile(i, j);
                
                if(i == newX && j == newY){
                    if(currTile.getColor() == this.color){
                        return false;
                    }
                }
                else if(i == prevX && j == prevY){
                    i += xSign;
                    j += ySign;
                    continue;
                }
                else if(currTile.getClass() != EmptyTile.class){
                    return false;
                }
                i += xSign;
                j += ySign;
            }
        }
        
        return true;
    }

}



