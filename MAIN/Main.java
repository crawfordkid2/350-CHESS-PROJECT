package MAIN;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import BOARD_INFO.Board;
import ENGINE.GameEngine;
import GUI.BoardDisplay;

public class Main {
    public ClientSideConnection csc;
    private int playerID;
    private int otherPlayer;

    public void connectToServer(){
        csc = new ClientSideConnection();
    }

    private class ClientSideConnection{

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;

    public ClientSideConnection(){
        try{
            socket = new Socket("localhost", 27015);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException ex){
            System.out.println("IOException from CSC constructor");
        }
    }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.connectToServer();
        if (args.length == 0) {
            GameEngine game = new GameEngine(new Board());
            game.board.printBoard();
            boolean exit = false;
            Scanner input = new Scanner(System.in);
            String currMove = "";

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
        else {
            new BoardDisplay();
        }
    }
}