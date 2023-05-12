package ENGINE;

import BOARD_INFO.Board;

public class GameEngine {
    public Board board;
    public int turn;

    public GameEngine(Board board){
        this.board = board;
        this.turn = 0;
    }

    public boolean tryMove(String moveString){
        return false;
    }

    public void restart(){
        this.board = new Board();
        this.turn = 0;
    }
}
