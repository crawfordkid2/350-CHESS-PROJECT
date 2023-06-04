package GUI;

import BOARD_INFO.TILES.Tile;
import ENGINE.GameEngine;
import ENGINE.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

/**
 * GUI Class for our Chess Program
 * This class contains all necessary methods to display, update and handle input on the board
 */
public class BoardDisplay {

    private final Color lightTileColor = Color.decode("#FFFACD");
    private final Color darkTileColor = Color.decode("#593E1A");
    private final Color lightHighlightColor = Color.decode("#00FA00");
    private final Color darkHighlightColor = Color.decode("#003E00");
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    private Tile sourceTile;
    private Tile destinationTile;
    private final GameEngine ChessEngine;
    private static String pieceIconPath = "icons/pieces/";
    private boolean online;
    private boolean turnToggle = true;
    private int playerID;
    private ENUM.Color winner = ENUM.Color.EMPTY;
    private boolean gameDraw = false;

    /**
     * Creates a new JFrame that represents the chess board.
     * tableMenuBar is the menu users can select options like exit, swap orientation from, a helper method is used
     * after the board display is set up, a chess engine is created so the gui can communicate with the back end.
     * This constructor will also take the parameters online and playerID so that we can manipulate the appearance
     * of the board for player 2
     */
    public BoardDisplay(GameEngine engine, boolean online, int playerID) {
        this.gameFrame = new JFrame("Chess app");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.ChessEngine = engine;
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
        this.online = online;
        this.playerID = playerID;
    }

    /**
     * This method creates the options at the top of the window. Inside this method are calls to createFileMenu() and
     * createPreferencesMenu() to reduce code clutter
     */
    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        tableMenuBar.add(createPreferencesMenu());
        return tableMenuBar;
    }

    /**
     * This helper method will be used in createTableMenuBar() to display a preferences option for the user.
     * The preferences menu will contain an option for the user to flip the board orientation. In a two player
     * game the board orientation will automatically flip for player 2, however this gives them the option
     * to display the board however they please
     */
    private JMenu createPreferencesMenu() {
        final JMenu preferences = new JMenu("Preferences");
        final JMenuItem flipBoardItem = new JMenuItem("Flip Board");
        flipBoardItem.addActionListener(e -> {
            try {
                boardPanel.flipBoardOrientation();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        preferences.add(flipBoardItem);
        return preferences;
    }

    /**
     * This helper method will be used in createTableMenuBar() to display a menu option for the user.
     * all this contains as of now is an option to exit the program
     */
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("Menu");
        // allows user to terminate game
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    /**
     * get and set update are used to communicate moves made on the GUI with the game server
     */
    public String getUpdate(){
        return this.boardPanel.getUpdate();
    }

    public void setUpdate(String update){
        this.boardPanel.setUpdate(update);
    }

    // keeps track of turns so player 1 cannot move while it's player 2's turn
    public void setTurnToggle(boolean toggle){
        this.turnToggle = toggle;
    }
    

    /**
     * This class contains the logic for the chess board. Inside this class a list containing TilePanels
     * is created and displayed using a 8x8 GridLayout. Then each tile is looped through, creating a new TilePanel
     * with respective 0-63 ID.
     */
    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;
        private String update = "";

        // populates boardTiles list and sets them to display as a 8x8 grid
        BoardPanel() {
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < 64; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            if (playerID == 2) { // auto flip board for player 2
                Collections.reverse(boardPanel.boardTiles);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        // method used to update the board whenever the user executes a move
        public void drawBoard(final GameEngine game) throws InterruptedException {
            removeAll();
            for (final TilePanel tilePanel : boardTiles) {
                tilePanel.drawTile(game);
                add(tilePanel);
                validate();
                repaint();
            }
            winner = ChessEngine.checkmateHandler();
            gameDraw = ChessEngine.drawChecker();
            endGame(gameDraw, winner);
        }

        public void flipBoardOrientation() throws InterruptedException {
            Collections.reverse(boardPanel.boardTiles);
            boardPanel.drawBoard(ChessEngine);
        }
        
        public String getUpdate(){
            return this.update;
        }

        public void setUpdate(String update){
            SwingUtilities.invokeLater(() -> {
                try {
                    boardPanel.drawBoard(ChessEngine);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            this.update = update;
        }

        public void showValidMoves(int fromX, int fromY){
            List<Move> validMoves = ChessEngine.checkValidMoves(fromX, fromY);
            for(Move move : validMoves){
                int x = move.getNew().getCoordX();
                int y = move.getNew().getCoordY();
                // we need to do some math on the coordinates because Swing populates the grid starting from
                // the top left, but logically the coordinates start from the bottom left. This caused the board
                // to originally display black pieces on the bottom, and they were mirrored horizontally.
                this.boardTiles.get(Math.abs(((7-y) * 8 + x))).isHighlighted = true;
            }
        }
        public void unHighlight(){
            for (TilePanel tilePanel : boardTiles) {
                tilePanel.isHighlighted = false;
            }
        }

        public void endGame(boolean draw, ENUM.Color winner) throws InterruptedException {
            if(winner != ENUM.Color.EMPTY){
                JOptionPane.showMessageDialog(null, "Checkmate: " + winner.name() + " wins.\n");
                Thread.sleep(4000); // allows user to see the message for 4 seconds, then terminates the program
                System.exit(0);
            }
            else if(draw){
                JOptionPane.showMessageDialog(null, "Draw");
                Thread.sleep(4000); // allows user to see the message for 4 seconds, then terminates the program
                System.exit(0);
            }
        }

        
    }

    /**
     * This class contains the logic for each individual Tile. Each tile has an integer tileId, then an xCord, yCord
     * to allow easier communication with the back end.
     */
    private class TilePanel extends JPanel {

        private final int tileId;
        private final int xCord;
        private final int yCord;
        public boolean isHighlighted = false;

        /**
         * The constructor first sets each tile's ID to the ID passed in BoardPanel, then inverts that ID to properly
         * find xCoords and yCoords. After that is set up, each Tile gets a right and left mouse button listener to
         * handle user input. The backend had the move method created to take coordinates like A0 instead of 00, so
         * the retrieved x coordinates are added with 97, that makes it so 0 + 97 cast to a char is A, 1 + 97 is B etc
         */
        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId; // for color and board building logic

            // example math: tileId 37
            // xCord = 37 % 8 = 5
            // yCord = 7 - (37 / 8) = 3
            this.xCord = tileId % 8;
            this.yCord = 7-(tileId / 8); // originally black pieces displayed on the bottom. 7-(math) inverts the y coords

            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            assignPieceIcon(ChessEngine);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    // right mouse deselects the current piece by setting those globals to null
                    if (isRightMouseButton(e) && turnToggle) {
                        sourceTile = null;
                        destinationTile = null;
                        boardPanel.unHighlight();
                    }
                    // left mouse button selects.
                    else if (isLeftMouseButton(e) && turnToggle) {
                        Tile currTile = ChessEngine.board.getTile(xCord, yCord);
                        // if this is user's first click, then that is a source tile. Extra conditions
                        // ensure moves are only made when it is that players turn.
                        if (sourceTile == null && (currTile.getColor() == ENUM.Color.BLACK && ChessEngine.turn % 2 == 1)
                                || (currTile.getColor() == ENUM.Color.WHITE && ChessEngine.turn % 2 == 0)) {
                            boardPanel.unHighlight();
                            sourceTile = ChessEngine.board.getTile(xCord, yCord);
                            isHighlighted = true;
                            boardPanel.showValidMoves(xCord, yCord);
                        }
                        else { // user has already selected a source tile, so this is a destination tile
                            destinationTile = ChessEngine.board.getTile(xCord, yCord);
                            // xcoords are input as chars for the backend. Conversion handled here.
                            String move = "";
                            move += (char) (sourceTile.getCoordX() + 97);
                            move += sourceTile.getCoordY() + 1;
                            move += (char)(destinationTile.getCoordX() + 97);
                            move += destinationTile.getCoordY() + 1;
                            if(ChessEngine.tryMove(move) && online){
                                boardPanel.setUpdate(move);;
                            } // execute move
                            // reset selections
                            sourceTile = null;
                            destinationTile = null;
                            boardPanel.unHighlight();
                        }

                    }
                    // updates the board each time a move is input.
                    SwingUtilities.invokeLater(() -> {
                        try {
                            boardPanel.drawBoard(ChessEngine);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }

                // these must be implemented per MouseListener API, however we have no use for these in the program
                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            validate();
        }

        // This driver method helps display the proper tile color, and the proper piece icon.
        public void drawTile(final GameEngine game) {
            assignTileColor();
            assignPieceIcon(game);
            validate();
            repaint();
        }

        /**
         * This method assigns each tile piece icon. The way I handled this was looping through each tile, if it
         * was full I created a BufferedImage by reading the piece color and name, then directing the imageIO to the
         * proper .png image. Please note that the icons should be in a folder in the project directory
         */
        private void assignPieceIcon(final GameEngine game) {
            this.removeAll();
            if (game.board.getTile(this.xCord, this.yCord).isFull()) {
                try {
                    // file layout as follows : pieceIconPath in directory/ (WHITE/BLACK)PIECENAME.png
                    final BufferedImage image = ImageIO.read(new File(pieceIconPath +
                    game.board.getTile(this.xCord, this.yCord).getPiece().getColor() +
                    game.board.getTile(this.xCord, this.yCord).getPiece().getClass().getSimpleName() + ".png"));
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // assigns Tile Color. Math is strange because we need the first tile in the first row to be dark, first tile
        // second row to be light.
        private void assignTileColor() {
            boolean isLight = ((tileId + tileId / 8) % 2 == 0);
            setBackground(isLight ? lightTileColor : darkTileColor);
            if(isHighlighted) { // handles highlighted colors
                setBackground(isLight? lightHighlightColor : darkHighlightColor);
            }
        }
    }
}

