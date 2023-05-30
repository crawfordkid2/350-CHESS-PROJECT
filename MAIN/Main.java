package MAIN;

import java.io.*;
import java.net.*;
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

                while(!choice.equals("o") && !choice.equals("l")){
                    System.out.print("Invalid Input (L/O): ");
                    choice = input.nextLine().toLowerCase();
                }
                if(choice.equals("o")){
                    System.out.println("Server Address(Default - localhost): ");
                    String address = input.nextLine();
                    Player player = new Player();
                    player.connectToServer(address);
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

                    System.out.print("Enter move: ");
                    while(exit == false){

                        currMove = input.nextLine().toLowerCase();
                        if(currMove.equals("quit")){
                            return;
                        }
                        else{
                            player.csc.sendMove(currMove);

                        }
                        Thread t2 = new Thread( new Runnable(){
                            public void run(){
                                player.updateTurn();
                                player.updateTurn();
                            }
                        });
                        t2.start();
                }
            }
            else if(choice.equals("l")){
                GameEngine game = new GameEngine(new Board());
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