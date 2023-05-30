package ENGINE;

import BOARD_INFO.*;
import BOARD_INFO.TILES.EmptyTile;
import BOARD_INFO.TILES.FullTile;
import BOARD_INFO.TILES.Tile;
import PIECES.*;
import ENUM.Color;

public class Move {

    protected Tile prevPos;
    protected Tile newPos;
    protected boolean capture;
    protected boolean check;
    

    public Move(Tile fromPos, Tile toPos) {
        prevPos = fromPos;
        newPos = toPos;
    }

    public boolean moveChecker(Move move, Board board) {
        return true;
    }

    // Affirms ability to castle, could rework to return move
    public boolean canCastleLeft(Tile tile, Board board) {
        if(tile.getPiece() instanceof PIECES.King && (tile.getPiece()).firstMove) {
            switch((tile.getPiece()).getColor()) {
                case WHITE:
                    Tile leftWhite = board.getTile(0, 0);
                    if(leftWhite.getPiece() instanceof PIECES.Rook && leftWhite.getColor() == Color.WHITE && (leftWhite.getPiece()).firstMove) {
                        for(int i = 3; i > 0; i--) {
                            if (board.getTile(i, 0) instanceof FullTile) {
                                return false;
                            }
                        }
                        return true;
                    };
                    break;
                case BLACK:
                    Tile leftBlack = board.getTile(0, 7);
                    if(leftBlack.getPiece() instanceof PIECES.Rook && leftBlack.getColor() == Color.BLACK && (leftBlack.getPiece()).firstMove) {
                        for(int i = 3; i > 0; i--) {
                            if (board.getTile(i, 7) instanceof FullTile) {
                                return false;
                            }
                        }
                        return true;
                    };
                case EMPTY:
                    return false;
            }
        }

        return false;
    }

    public boolean canCastleRight(Tile tile, Board board) {
        if(tile.getPiece() instanceof PIECES.King && (tile.getPiece()).firstMove) {
            switch(tile.getColor()) {
                case WHITE:
                    Tile rightWhite = board.getTile(0, 0);
                    if(rightWhite.getPiece() instanceof PIECES.Rook && rightWhite.getColor() == Color.WHITE && (rightWhite.getPiece()).firstMove) {
                        for(int i = 5; i < 7; i++) {
                            if (board.getTile(i, 0) instanceof FullTile) {
                                return false;
                            }
                        }
                        return true;
                    };
                    break;
                case BLACK:
                    Tile rightBlack = board.getTile(0, 7);
                    if(rightBlack.getPiece() instanceof PIECES.Rook && rightBlack.getColor() == Color.BLACK && (rightBlack.getPiece()).firstMove) {
                        for(int i = 5; i < 7; i++) {
                            if (board.getTile(i, 7) instanceof FullTile) {
                                return false;
                            }
                        }
                        return true;
                    };
                case EMPTY:
                    return false;
            }
        }

        return false;
    }

    // Moves rook if a castling has taken place
    public void castleCheck(Board board) {
        int diffX = Math.abs((newPos.getCoordX()) - (prevPos.getCoordX()));
        if (prevPos.getPiece() instanceof King && diffX > 1) {
            switch (prevPos.getColor()) {
                case WHITE:
                    if (newPos.getCoordX() == 2) {
                        board.setTile(3, 0, new FullTile(3, 0, (board.getTile(0, 0)).getPiece()));
                        board.setTile(0, 0, new EmptyTile(0, 0));
                        ((board.getTile(3, 0)).getPiece()).setPos(3, 0);
                    }
                    else {
                        board.setTile(5, 0, new FullTile(5, 0, (board.getTile(7, 0)).getPiece()));
                        board.setTile(7, 0, new EmptyTile(7, 0));
                        ((board.getTile(5, 0)).getPiece()).setPos(5, 0);
                    }
                    break;
                case BLACK:
                    if (newPos.getCoordX() == 2) {
                        board.setTile(3, 7, new FullTile(3, 7, (board.getTile(0, 7)).getPiece()));
                        board.setTile(0, 7, new EmptyTile(0, 7));
                        ((board.getTile(3, 7)).getPiece()).setPos(3, 7);
                    }
                    else {
                        board.setTile(5, 7, new FullTile(5, 7, (board.getTile(7, 7)).getPiece()));
                        board.setTile(7, 7, new EmptyTile(7, 7));
                        ((board.getTile(5, 7)).getPiece()).setPos(5, 7);
                    }
                    break;
                case EMPTY:
                    break;
            }
        }
    }

    // Too be added once we figure out how we want to prompt the promotion
    public void isPromotion(Board board) {
        if((this.prevPos.getPiece()) instanceof PIECES.Pawn) {
            switch((this.prevPos.getPiece()).getColor()) {
                case WHITE:
                    if(this.newPos.getCoordY() == 7) {
                        board.setTile(this.newPos.getCoordX(), this.newPos.getCoordY(), new FullTile(this.newPos.getCoordX(), this.newPos.getCoordY(), new Queen(this.newPos.getCoordX(), this.newPos.getCoordY(), Color.WHITE, board, false)));
                    };
                    break;
                case BLACK:
                    if(this.newPos.getCoordY() == 0) {
                        board.setTile(this.newPos.getCoordX(), this.newPos.getCoordY(), new FullTile(this.newPos.getCoordX(), this.newPos.getCoordY(), new Queen(this.newPos.getCoordX(), this.newPos.getCoordY(), Color.BLACK, board, false)));
                    };
                    break;
                case EMPTY:
                    break;
            }
        }
    }

    public Tile getPrev() {
        return prevPos;
    }

    public Tile getNew() {
        return newPos;
    }
    
    // public void makeMove(Move m) {
    //     if ((prevPos.getPiece() instanceof Pawn) && Math.abs(newPos.getCoordY() - prevPos.getCoordY()) == 2) {
    //         Pawn curr = (Pawn) prevPos.getPiece();
    //         curr.doublePawn();
    //     }

    //     Piece toMove = prevPos.getPiece();
    //     // NOT DONE

    // }

}
