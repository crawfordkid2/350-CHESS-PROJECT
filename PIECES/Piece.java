package PIECES;

import ENGINE.Move;
import ENUM.Color;
import java.util.List;

import BOARD_INFO.Board;

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

    public abstract boolean move(int newX, int newY);

    public void capture(){}

    public String getPos(Piece piece) {
        return String.valueOf(this.posX) + String.valueOf(this.posY);
    }

    public void setPos(int newX, int newY) {
        this.posX = newX;
        this.posY = newY;
    }

}



