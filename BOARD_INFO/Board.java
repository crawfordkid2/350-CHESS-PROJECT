package BOARD_INFO;

import PIECES.*;
import BOARD_INFO.TILES.*;
import ENUM.Color;

public class Board {
    
     private Tile[][] board; 

    public Board() {
        this.board = initialize();
    }

    /* Second constructor to be used to make future positions for AI 

    public Board(Board prev) {
        
    }

    */

    public Tile[][] initialize() {
        Tile[][] builtBoard = new Tile[8][8];

        builtBoard[7][0] = new FullTile(7, 0, new Rook(7, 0, Color.WHITE));
        builtBoard[7][1] = new FullTile(7, 1, new Knight(7, 1, Color.WHITE));
        builtBoard[7][2] = new FullTile(7, 2, new Bishop(7, 2, Color.WHITE));
        builtBoard[7][3] = new FullTile(7, 3, new Queen(7, 3, Color.WHITE));
        builtBoard[7][4] = new FullTile(7, 4, new King(7, 4, Color.WHITE));
        builtBoard[7][5] = new FullTile(7, 5, new Bishop(7, 5, Color.WHITE));
        builtBoard[7][6] = new FullTile(7, 6, new Knight(7, 6, Color.WHITE));
        builtBoard[7][7] = new FullTile(7, 7, new Rook(7, 7, Color.WHITE));

        for (int i = 0; i < 8; i++) {
            builtBoard[6][i] = new FullTile(6, i, new Pawn(6, i, Color.WHITE));
            builtBoard[1][i] = new FullTile(1, i, new Pawn(1, i, Color.BLACK));
        }

        builtBoard[0][0] = new FullTile(0, 0 , new Rook(0, 0, Color.BLACK));
        builtBoard[0][1] = new FullTile(0, 1, new Knight(0, 1, Color.BLACK));
        builtBoard[0][2] = new FullTile(0, 2, new Bishop(0, 2, Color.BLACK));
        builtBoard[0][3] = new FullTile(0, 3, new Queen(0, 3, Color.BLACK));
        builtBoard[0][4] = new FullTile(0, 4, new King(0, 4, Color.BLACK));
        builtBoard[0][5] = new FullTile(0, 5, new Bishop(0, 5, Color.BLACK));
        builtBoard[0][6] = new FullTile(0, 6, new Knight(0, 6, Color.BLACK));
        builtBoard[0][7] = new FullTile(0, 7, new Rook(0, 7, Color.BLACK));


       // for (int i = 16; i < 48; i++) {
        //     builtBoard[i][] = new EmptyTile(i);
        // }
        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                builtBoard[i][j] = new EmptyTile(i, j);
            }
        }
        return builtBoard;
    }

    public void update() {
        
    }

    public Tile getTile(int coordX, int coordY) {
        return board[coordX][coordY];
    }

    public void printBoard(){
        for(int i = 0; i < 8; i++){
            System.out.print("\n");
            for(int j = 0; j < 8;j++ ){
                String value = ".";
                Tile currTile = this.board[i][j];
                if(currTile.isFull()){

                    if(currTile.getPiece().getClass() == Pawn.class){
                        value = "p";
                    }
                    else if(currTile.getPiece().getClass() == Rook.class){
                        value = "r";
                    }
                    else if(currTile.getPiece().getClass() == Knight.class){
                        value = "n";
                    }
                    else if(currTile.getPiece().getClass() == Bishop.class){
                        value = "b";
                    }
                    else if(currTile.getPiece().getClass() == Queen.class){
                        value = "q";
                    }
                    else if(currTile.getPiece().getClass() == King.class){
                        value = "k";
                    }

                    if(currTile.getPiece().getColor() == Color.BLACK){
                        value = value.toUpperCase();
                    }
                }
                System.out.print(value + " ");
            }
        }
    }
}


