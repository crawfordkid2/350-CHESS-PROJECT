package GUI;

import BOARD_INFO.Board;
import ENGINE.GameEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardDisplay {

    private final Color lightTileColor = Color.decode("#FFFACD");
    private final Color darkTileColor = Color.decode("#593E1A");
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    private final GameEngine ChessEngine;
    private static String pieceIconPath = "icons/pieces/";

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

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("Menu");
        final JMenuItem openPGN = new JMenuItem("Load PNG File (user will not see this)");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // this is here to help debug when we get further into the GUI implementation. Leave this alone
            }
        });

        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        final JMenuItem tutorialMenuItem = new JMenuItem("Tutorial");
        tutorialMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // figure out how to display a chess slide show
            }
        });
        fileMenu.add(tutorialMenuItem);
        fileMenu.add(openPGN);
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

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
    }

    private class TilePanel extends JPanel {

        private final int tileId;
        private final int xCord;
        private final int yCord;

        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId; // for color and board building logic

            // ran into an issue here where we logically approached the engine with
            // coords 0,0 in the bottom left of the board, but Swing builds tiles top to bottom
            // there is no easy way to fix this in Swing, but inverting the coordinates works
            int invertedID = Math.abs(tileId - 63);
            this.xCord = invertedID % 8;
            this.yCord = invertedID / 8;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            assignPieceIcon(ChessEngine);
            validate();
        }

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
                    e.printStackTrace();;
                }
            }
        }
        private void assignTileColor() {
            boolean isLight = ((tileId + tileId / 8) % 2 == 0);
            setBackground(isLight ? lightTileColor : darkTileColor);
        }
    }
}

