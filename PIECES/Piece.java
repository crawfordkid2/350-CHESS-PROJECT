package PIECES;

import ENGINE.Move;
import ENUM.Color;

import java.util.ArrayList;
import java.util.List;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.*;

public abstract class Piece {
    
    protected Color color;
    protected int posX;
    protected int posY;
    protected int value; 
    public boolean firstMove = true;
    protected Board board;

    public Piece(int posX, int posY, final Color color, Board board) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.board = board;
    }

    // For Pawn promotions, can set firstmove to false
    public Piece(int posX, int posY, final Color color, Board board, boolean firstMove) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.board = board;
        this.firstMove = firstMove;
    }

    public Color getColor() {
        return this.color;
    }

    public List<Move> findMoves(Board board)
    {
        Tile fromTile = this.board.getTile(this.posX, this.posY);
        Tile toTile;
        List<Move> validMoves = new ArrayList<Move>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                toTile = this.board.getTile(i, j);
                Move testMove = new Move(fromTile, toTile);
                
                if(toTile.getColor() != this.getColor()){
                    if(this.move(testMove, board) && testMove.moveCheck(board) != this.color){
                        validMoves.add(testMove);
                    }
                }
            }
        }

        return validMoves;
    }

    public abstract boolean move(Move move, Board board);

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

        //Different if diagonal
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



