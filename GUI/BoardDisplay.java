package GUI;

import BOARD_INFO.Board;
import BOARD_INFO.TILES.Tile;
import ENGINE.GameEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    private Tile sourceTile;
    private Tile destinationTile;
    private final GameEngine ChessEngine;
    private static String pieceIconPath = "icons/pieces/";

    /**
     * Creates a new JFrame that represents the chess board.
     * tableMenuBar is the menu users can select options like exit, swap orientation from, a helper method is used
     * after the board display is set up, a chess engine is created so the gui can communicate with the back end.
     */
    public BoardDisplay() {
        this.gameFrame = new JFrame("Chess app");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.ChessEngine = new GameEngine(new Board());
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    // helper method to populate menu
    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    // contains all menu options
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("Menu");
        // allows user to terminate game
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    /**
     * This class contains the logic for the chess board. Inside this class a list containing TilePanels
     * is created and displayed using a 8x8 GridLayout. Then each tile is looped through, creating a new TilePanel
     * with respective 0-63 ID.
     */
    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < 64; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        // method used to update the board whenever the user executes a move
        public void drawBoard(final GameEngine game) {
            removeAll();
            for (final TilePanel tilePanel : boardTiles) {
                tilePanel.drawTile(game);
                add(tilePanel);
                validate();
                repaint();
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

        /**
         * The constructor first sets each tile's ID to the ID passed in BoardPanel, then inverts that ID to properly
         * find xCoords and yCoords. After that is set up, each Tile gets a right and left mouse button listener to
         * handle user input. The backend had the move method created to take coordinates like A0 instead of 00, so
         * the retrieved x coordinates are added with 97, that makes it so 0 + 97 cast to a char is A, 1 + 97 is B etc
         */
        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId; // for color and board building logic

            // ran into an issue here where we logically approached the engine with
            // coords 0,0 in the bottom left of the board, but Swing builds tiles top to bottom
            // there is no easy way to fix this in Swing, but inverting the coordinates works
            int invertedID = Math.abs(tileId - 63);
            // example math: tileID 37 should be at x:5, y:4
            this.xCord = invertedID % 8;
            this.yCord = invertedID / 8;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            assignPieceIcon(ChessEngine);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    // right mouse deselects the current piece by setting those globals to null
                    if (isRightMouseButton(e)) {
                        sourceTile = null;
                        destinationTile = null;
                    }
                    // left mouse button selects.
                    else if (isLeftMouseButton(e)) {
                        if (sourceTile == null) { // if this is user's first click, then that is a source tile
                            sourceTile = ChessEngine.board.getTile(xCord, yCord);
                        }
                        else { // user has already selected a source tile, so this is a destination tile
                            destinationTile = ChessEngine.board.getTile(xCord, yCord);
                            // xcoords are input as chars for the backend. Conversion handled here.
                            String move = "";
                            move += (char) (sourceTile.getCoordX() + 97);
                            move += sourceTile.getCoordY() + 1;
                            move += (char)(destinationTile.getCoordX() + 97);
                            move += destinationTile.getCoordY() + 1;
                            ChessEngine.tryMove(move); // execute move
                            // reset selections
                            sourceTile = null;
                            destinationTile = null;
                        }

                    }
                    // updates the board each time a move is input.
                    SwingUtilities.invokeLater(() -> boardPanel.drawBoard(ChessEngine));
                }

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
         * proper .gif image
         */
        private void assignPieceIcon(final GameEngine game) {
            this.removeAll();
            if (game.board.getTile(this.xCord, this.yCord).isFull()) {
                try {
                    // file layout as follows : pieceIconPath in directory/ (WHITE/BLACK)PIECENAME.gif
                    final BufferedImage image = ImageIO.read(new File(pieceIconPath +
                            game.board.getTile(this.xCord, this.yCord).getPiece().getColor() +
                            game.board.getTile(this.xCord, this.yCord).getPiece().getClass().getSimpleName() + ".gif"));
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
        }
    }
}

