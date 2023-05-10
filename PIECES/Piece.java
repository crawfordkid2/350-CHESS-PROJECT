package PIECES;

import ENGINE.Move;
import ENUM.Color;
import java.util.List;

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

    public Color getColor(){
        return this.color;
    }

    public abstract List<Move> findMoves();

    public void move(int newX, int newY){}

    public void capture(){}

}



