package PIECES;

import ENGINE.Move;
import ENUM.Color;
import java.util.List;

public abstract class Piece {
    
    protected Color color;
    protected int pos;
    protected int value; 

    public Piece(int pos, final Color color) {
        this.pos = pos;
        this.color = color;
    }

    public abstract List<Move> findMoves();

    public void move(){}

    public void capture(){}

}



