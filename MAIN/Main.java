package MAIN;

import BOARD_INFO.Board;
import ENGINE.GameEngine;
public class Main {

    public static void main(String[] args) {
        GameEngine game = new GameEngine(new Board());
        game.board.printBoard();
    }
}