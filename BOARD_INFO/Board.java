package BOARD_INFO;

import PIECES.*;
import BOARD_INFO.TILES.*;
import ENUM.Color;

public class Board {
    
    private Tile[][] board; 
    private King whiteKing;
    private King blackKing;

    public Board() {
        this.board = initialize();
    }

    /* Second constructor to be used to make future positions for AI 

    public Board(Board prev) {
        
    }

    */

    public Tile[][] initialize() {
        Tile[][] builtBoard = new Tile[8][8];
        this.whiteKing = new King(4, 0, Color.WHITE, this);
        this.blackKing = new King(4, 7, Color.BLACK, this);

        builtBoard[0][7] = new FullTile(0, 7, new Rook(0, 7, Color.BLACK, this));
        builtBoard[1][7] = new FullTile(1, 7, new Knight(1, 7, Color.BLACK, this));
        builtBoard[2][7] = new FullTile(2, 7, new Bishop(2, 7, Color.BLACK, this));
        builtBoard[3][7] = new FullTile(3, 7, new Queen(3, 7, Color.BLACK, this));
        builtBoard[4][7] = new FullTile(4, 7, blackKing);
        builtBoard[5][7] = new FullTile(5, 7, new Bishop(5, 7, Color.BLACK, this));
        builtBoard[6][7] = new FullTile(6, 7, new Knight(6, 7, Color.BLACK, this));
        builtBoard[7][7] = new FullTile(7, 7, new Rook(7, 7, Color.BLACK, this));

        for (int i = 0; i < 8; i++) {
            builtBoard[i][6] = new FullTile(i, 6, new Pawn(i, 6, Color.BLACK, this));
            builtBoard[i][1] = new FullTile(i, 1, new Pawn(i, 1, Color.WHITE, this));
        }

        builtBoard[0][0] = new FullTile(0, 0 , new Rook(0, 0, Color.WHITE, this));
        builtBoard[1][0] = new FullTile(1, 0, new Knight(1, 0, Color.WHITE, this));
        builtBoard[2][0] = new FullTile(2, 0, new Bishop(2, 0, Color.WHITE, this));
        builtBoard[3][0] = new FullTile(3, 0, new Queen(3, 0, Color.WHITE, this));
        builtBoard[4][0] = new FullTile(4, 0, whiteKing);
        builtBoard[5][0] = new FullTile(5, 0, new Bishop(5, 0, Color.WHITE, this));
        builtBoard[6][0] = new FullTile(6, 0, new Knight(6, 0, Color.WHITE, this));
        builtBoard[7][0] = new FullTile(7, 0, new Rook(7, 0, Color.WHITE, this));

        for(int i = 0; i < 8; i++){
            for(int j = 2; j < 6; j++){
                builtBoard[i][j] = new EmptyTile(i, j);
            }
        }

        return builtBoard;
    }

    public void update() {
        
    }

    public Tile getTile(final int coordX, final int coordY) {
        return board[coordX][coordY];
    }

    public void setTile(final int coordX, final int coordY, final Tile tile){
        this.board[coordX][coordY] = tile;
    }

    public void printBoard(){
        for(int y = 7; y >= 0;y--)
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

    public String toString(){
        String boardString = "";
        for(int y = 7; y >= 0;y--)
        {
            boardString += "\n" + (y+1) + " | ";
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
                boardString += value + " ";
            }
        }

        boardString += "\n--------------------";
        boardString += "\n  | A B C D E F G H ";

        return boardString;
    }
}