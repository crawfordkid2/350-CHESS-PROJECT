package BOARD_INFO;

import PIECES.*;
import BOARD_INFO.TILES.*;
import ENUM.Color;

public class Board {
    
    private Tile[] board; 

    public Board() {
        this.board = initialize();
    }

    /* Second constructor to be used to make future positions for AI 

    public Board(Board prev) {
        
    }

    */

    public Tile[] initialize() {
        Tile[] builtBoard = new Tile[64];

        builtBoard[0] = new FullTile(0, new Rook(0, Color.WHITE));
        builtBoard[1] = new FullTile(1, new Knight(1, Color.WHITE));
        builtBoard[2] = new FullTile(2, new Bishop(2, Color.WHITE));
        builtBoard[3] = new FullTile(3, new Queen(3, Color.WHITE));
        builtBoard[4] = new FullTile(4, new King(4, Color.WHITE));
        builtBoard[5] = new FullTile(5, new Bishop(5, Color.WHITE));
        builtBoard[6] = new FullTile(6, new Knight(6, Color.WHITE));
        builtBoard[7] = new FullTile(7, new Rook(7, Color.WHITE));

        for (int i = 8; i < 16; i++) {
            builtBoard[i] = new FullTile(i, new Pawn(i, Color.WHITE));
        }

        builtBoard[56] = new FullTile(56, new Rook(56, Color.BLACK));
        builtBoard[57] = new FullTile(57, new Knight(57, Color.BLACK));
        builtBoard[58] = new FullTile(58, new Bishop(58, Color.BLACK));
        builtBoard[59] = new FullTile(59, new Queen(59, Color.BLACK));
        builtBoard[60] = new FullTile(60, new King(60, Color.BLACK));
        builtBoard[61] = new FullTile(61, new Bishop(61, Color.BLACK));
        builtBoard[62] = new FullTile(62, new Knight(62, Color.BLACK));
        builtBoard[63] = new FullTile(63, new Rook(63, Color.BLACK));

        for (int i = 48; i < 56; i++) {
            builtBoard[i] = new FullTile(i, new Pawn(i, Color.BLACK));
        }

        for (int i = 16; i < 48; i++) {
            builtBoard[i] = new EmptyTile(i);
        }
        return builtBoard;
    }

    public void update() {
        
    }

    public Tile getTile(int coord) {
        return board[coord];
    }
}


