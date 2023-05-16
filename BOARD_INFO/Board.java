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

        builtBoard[0][7] = new FullTile(0, 7, new Rook(0, 7, Color.BLACK));
        builtBoard[1][7] = new FullTile(1, 7, new Knight(1, 7, Color.BLACK));
        builtBoard[2][7] = new FullTile(2, 7, new Bishop(2, 7, Color.BLACK));
        builtBoard[3][7] = new FullTile(2, 7, new King(3, 7, Color.BLACK));
        builtBoard[4][7] = new FullTile(4, 7, new Queen(4, 7, Color.BLACK));
        builtBoard[5][7] = new FullTile(5, 7, new Bishop(5, 7, Color.BLACK));
        builtBoard[6][7] = new FullTile(6, 7, new Knight(6, 7, Color.BLACK));
        builtBoard[7][7] = new FullTile(7, 7, new Rook(7, 7, Color.BLACK));

        for (int i = 0; i < 8; i++) {
            builtBoard[i][6] = new FullTile(i, 6, new Pawn(i, 6, Color.BLACK));
            builtBoard[i][1] = new FullTile(i, 1, new Pawn(i, 1, Color.WHITE));
        }

        builtBoard[0][0] = new FullTile(0, 0 , new Rook(0, 0, Color.WHITE));
        builtBoard[1][0] = new FullTile(1, 0, new Knight(1, 0, Color.WHITE));
        builtBoard[2][0] = new FullTile(2, 0, new Bishop(2, 0, Color.WHITE));
        builtBoard[3][0] = new FullTile(3, 0, new King(3, 0, Color.WHITE));
        builtBoard[4][0] = new FullTile(4, 0, new Queen(4, 0, Color.WHITE));
        builtBoard[5][0] = new FullTile(5, 0, new Bishop(5, 0, Color.WHITE));
        builtBoard[6][0] = new FullTile(6, 0, new Knight(6, 0, Color.WHITE));
        builtBoard[7][0] = new FullTile(7, 0, new Rook(7, 0, Color.WHITE));

        for(int i = 0; i < 8; i++){
            for(int j = 2; j < 6; j++){
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

    public void setTile(int coordX, int coordY, Tile tile){
        this.board[coordX][coordY] = tile;
    }

    public void printBoard(){
        for(int y = 7; y >= 0;y-- )
        {
            System.out.print("\n" + (y+1) + " | ");
            for(int x = 0; x <= 7; x++){
                String value = ".";
                Tile currTile = this.board[x][y];
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

        System.out.println("\n--------------------");
        System.out.println("  | A B C D E F G H ");
    }
}