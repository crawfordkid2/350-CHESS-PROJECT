package ENGINE;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.EmptyTile;
import BOARD_INFO.TILES.FullTile;
import BOARD_INFO.TILES.Tile;
import ENUM.Color;
import PIECES.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GameEngine {
    public Board board;
    public int turn;
    public Color checkStatus = Color.EMPTY;
    private Piece trash = null;


    private static final HashMap<Character, Integer> files;
    private static final HashMap<Character, Integer> ranks;

    static {

        final char[] rank = {'8', '7', '6', '5', '4', '3', '2', '1'};
        final char[] file = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        final HashMap<Character, Integer> rMap = new HashMap<>();
        final HashMap<Character, Integer> fMap = new HashMap<>();

        for (Integer i = 0; i < 8; i++) {
            rMap.put(Character.valueOf(rank[i]), i);
            fMap.put(Character.valueOf(file[i]), i);
        }

        ranks = rMap;
        files = fMap;
    }
    

    public GameEngine(Board board){
        this.board = board;
        this.turn = 0;
    }
    //Added character checks, could be more efficient but it works
    public boolean tryMove(String moveString){
        if(!files.containsKey(Character.valueOf(moveString.charAt(0)))) {
            System.out.println("Invalid from column");
            return false;
        }
        else if (!ranks.containsKey(Character.valueOf(moveString.charAt(1)))) {
            System.out.println("Invalid from rank");
            return false;
        }
        else if(moveString.length() == 2){
            checkValidMoves(moveString.charAt(0)-97, moveString.charAt(1)-49);
            return false;
        }
        else if(moveString.length() != 4){
            System.out.println("Enter a 4 character input xyxy");
            return false;
        }
        else if(!files.containsKey(Character.valueOf(moveString.charAt(2)))) {
            System.out.println("Invalid to column");
            return false;
        }
        else if (!ranks.containsKey(Character.valueOf(moveString.charAt(3)))) {
            System.out.println("Invalid to rank");
            return false;
        }
        Move.isCheck(board);
        int xFrom = moveString.charAt(0)-97;
        int yFrom = moveString.charAt(1)-49;
        int xTo = moveString.charAt(2)-97;
        int yTo = moveString.charAt(3)-49;

        Piece piece = this.board.getTile(xFrom, yFrom).getPiece();
        Tile fromTile = this.board.getTile(xFrom, yFrom);
        Tile toTile = this.board.getTile(xTo, yTo);

        //Checks if piece exists and if there is either an empty tile or an opponent on the new tile. 
        if(piece != null && (toTile.getPiece() == null || toTile.getColor() != piece.getColor()) && ((piece.getColor() == Color.BLACK && this.turn % 2 == 1) || (piece.getColor() == Color.WHITE && this.turn % 2 == 0))){
            Move curr = new Move(fromTile, toTile);
            if(piece.move(curr, board)){
                trash = toTile.getPiece();
                this.board.setTile(xTo, yTo, new FullTile(xTo, yTo, piece));
                this.board.setTile(xFrom, yFrom, new EmptyTile(xFrom, yFrom));
                piece.setPos(xTo, yTo);

                if (piece instanceof King) {
                    curr.castleCheck(this.board);
                }

                if (piece instanceof Pawn) {
                    curr.isPromotion(this.board);
                }

                if (checkHandler(fromTile, toTile, piece, turn, curr)) {
                    piece.setPos(xFrom, yFrom);
                    System.out.println("Invalid, check \n");
                    return false;
                }
                
                piece.firstMove = false;
                this.turn++;
                return true;
            }
        }

        return false;
    }

    public boolean checkHandler(Tile from, Tile to, Piece piece, int turn, Move test) {
        Color curCheck = Move.isCheck(this.board);
        if (curCheck != Color.EMPTY && checkStatus == curCheck) {
            replacePiece(from, to, trash, piece);
            return true;
        }
        else if ((this.turn % 2 == 1 && curCheck == Color.BLACK) || (this.turn % 2 == 0 && curCheck == Color.WHITE)) {
            replacePiece(from, to, trash, piece);
            curCheck = Color.EMPTY;
            return true;
        }
        else {
            return false;
        }
    }

    public Color checkmateHandler() {
        
        checkStatus = Move.isCheck(this.board);
        
        switch(checkStatus) {
            case BLACK:
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        Tile fromTile = this.board.getTile(i, j);
                        if(fromTile.getColor() == Color.BLACK && !(((fromTile.getPiece()).findMoves(this.board)).isEmpty())) {
                            return Color.EMPTY;
                        }
                    }
                }
                return Color.WHITE;
            case WHITE:
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        Tile fromTile = this.board.getTile(i, j);
                        if(fromTile.getColor() == Color.WHITE && !(((fromTile.getPiece()).findMoves(this.board)).isEmpty())) {
                            return Color.EMPTY;
                        }
                    }
                }
                return Color.BLACK;
            case EMPTY:
                return Color.EMPTY;
        }
        return Color.EMPTY;
    }

    private void replacePiece(Tile from, Tile to, Piece trash, Piece piece) {
        if (trash != null) {
            this.board.setTile(from.getCoordX(), from.getCoordY(), new FullTile(from.getCoordX(), from.getCoordY(), piece));
            this.board.setTile(to.getCoordX(), to.getCoordY(), new FullTile(to.getCoordX(), to.getCoordY(), trash));
            trash = null;
        }
        else {
            this.board.setTile(from.getCoordX(), from.getCoordY(), new FullTile(from.getCoordX(), from.getCoordY(), piece));
            this.board.setTile(to.getCoordX(), to.getCoordY(), new EmptyTile(to.getCoordX(), to.getCoordY()));
        }
    }

    
    public boolean drawChecker() {
        int total = countPieces();
        if(checkStatus == Color.EMPTY && total > 2) {
            switch(this.turn % 2) {
                case 0:
                    for(int i = 0; i < 8; i++){
                        for(int j = 0; j < 8; j++){
                            Tile fromTile = this.board.getTile(i, j);
                            if(fromTile.getColor() == Color.WHITE && !(((fromTile.getPiece()).findMoves(this.board)).isEmpty())) {
                                return false;
                            }
                        }
                    }
                    return true;

                case 1:
                    for(int i = 0; i < 8; i++){
                        for(int j = 0; j < 8; j++){
                            Tile fromTile = this.board.getTile(i, j);
                            if(fromTile.getColor() == Color.BLACK && !(((fromTile.getPiece()).findMoves(this.board)).isEmpty())) {
                                return false;
                            }
                        }
                    }
                    return true;
            }
        }
        else if (total <= 2) {
            return true;
        }
        return false;
    }
    
    public int countPieces() {
        int totalPieces = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((this.board.getTile(i, j)) instanceof FullTile) {
                    totalPieces++;
                }
            }
        }
        return totalPieces;
    }

    public List<Move> checkValidMoves(int fromX, int fromY){
        Piece piece = this.board.getTile(fromX, fromY).getPiece();
        List<Move> realMoves = new ArrayList<Move>();
        if(piece != null){
            List<Move> validMoves = piece.findMoves(board);
            for (Move move : validMoves) {
                realMoves.add(move);
                System.out.println(move.getNew().getCoordX() + ", " + move.getNew().getCoordY());
        }
    }
    return realMoves;
}

    public void restart(){
        this.board = new Board();
        this.turn = 0;
        board.printBoard();
    }
}
