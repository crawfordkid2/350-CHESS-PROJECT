package MAIN;

import java.io.*;
import java.util.Scanner;

import BOARD_INFO.Board;
import ENGINE.GameEngine;
import GUI.BoardDisplay;
import MULTIPLAYER.Player;

public class Main {
    

    public static void main(String[] args) {

            if (args.length == 0) {
                boolean exit = false;
                String currMove = "";
                System.out.print("Local or Online (L/O): ");
                Scanner input = new Scanner(System.in);
                String choice = input.nextLine().toLowerCase();
                int turn = 0;

                while(!choice.equals("o") && !choice.equals("l")){
                    System.out.print("Invalid Input (L/O): ");
                    choice = input.nextLine().toLowerCase();
                }
                if(choice.equals("o")){
                    System.out.println("Server Address(Default - localhost): ");
                    String address = input.nextLine();
                    GameEngine game = new GameEngine(new Board());
                    String lastMove = "";
                    Player player = new Player(game);
                    player.connectToServer(address);
                    BoardDisplay gui = new BoardDisplay(game, true, player.playerID);
                    try{
                        player.boardString = player.csc.dataIn.readUTF();
                    }
                    catch (IOException ex){
                        System.out.println("IOException in board print.");
                    }
                    if(player.playerID != 1){
                        Thread t = new Thread( new Runnable(){
                            public void run(){
                                player.updateTurn();
                            }
                        });
                        t.start();
                    }
                    System.out.println(player.boardString);
                    currMove = "";
                    System.out.print("Enter move: ");
                    if(player.playerID == 2 ){
                        gui.setTurnToggle(false);
                    }
                    while(exit == false){
                        while((game.turn % 2 == 0 && player.playerID == 2) || (game.turn % 2 == 1 && player.playerID == 1) )
                        {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                            }
                        }

                        currMove = "";
                        gui.setUpdate(currMove);
                        
                        gui.setTurnToggle(true);
                        while(currMove.equals("")){
                            currMove = gui.getUpdate();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        
                        System.out.println(currMove);
                        player.csc.sendMove(currMove);
                        Thread t2 = new Thread( new Runnable(){
                            public void run(){
                                player.updateTurn();
                                player.updateTurn();
                            }
                        });
                        t2.start();
                        gui.setTurnToggle(false);
                }
            }
            else if(choice.equals("l")){
                GameEngine game = new GameEngine(new Board());
                new BoardDisplay(game, false, 0);
                
                game.board.printBoard();
                while(exit == false){
                    System.out.print("Enter move: ");
                    currMove = input.nextLine().toLowerCase();
                    if(currMove.equals("quit")){
                        return;
                    }
                    else if(currMove.equals("restart")){
                        game.restart();
                    }
                    else if(game.tryMove(currMove)){
                        game.board.printBoard();
                    }
                    else{
                        System.out.println("Try Again");
                    }
                }
            }
        }
        else {
            new BoardDisplay();
        }
    }
}