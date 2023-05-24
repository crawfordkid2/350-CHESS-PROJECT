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
    private String boardString;
    private int turns;

    public void connectToServer(){
        csc = new ClientSideConnection();
    }

    public void startReceivingMoves(){
        Thread t = new Thread(new Runnable(){
            public void run(){
                while(true){
                    System.out.println(csc.receiveMove());
                }
            }
        });
        t.start();
    }

    public void updateTurn(){
        String update = csc.receiveMove();
        System.out.println(update);
        System.out.println("Enter move: ");
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
                playerID = dataIn.readInt();
                System.out.println("Connected as Player " + playerID);
            }
            catch(IOException ex){
                System.out.println("IOException from CSC constructor");
            }
        }

        public void sendMove(String move){
            try{
                dataOut.writeUTF(move);
                dataOut.flush();
            }
            catch(IOException ex){
                System.out.println("IOException in sendMove().");
            }
        }

        public String receiveMove(){
            String newBoard = "";
            try{
                newBoard = dataIn.readUTF();
                //System.out.println(newBoard);
            }
            catch(IOException ex){
                System.out.println("IOException in receiveMove().");
            }

            return newBoard;
        }
    }

    public static void main(String[] args) {
        
            if (args.length == 0) {
                Main m = new Main();
            m.connectToServer();
            try{
                m.boardString = m.csc.dataIn.readUTF();
            }
            catch (IOException ex){
                System.out.println("IOException in board print.");
            }
            if(m.playerID == 1){
                m.otherPlayer = 2;
            }
            else{
                m.otherPlayer = 1;
                Thread t = new Thread( new Runnable(){
                    public void run(){
                        m.updateTurn();
                    }
                });
                t.start();
            }
            
            System.out.println(m.boardString);
            boolean exit = false;
            Scanner input = new Scanner(System.in);
            String currMove = "";
            System.out.print("Enter move: ");
            while(exit == false){
                
                currMove = input.nextLine().toLowerCase();
                if(currMove.equals("quit")){
                    return;
                }
                else{
                    m.csc.sendMove(currMove);
                    
                }
                //m.startReceivingMoves();
                Thread t2 = new Thread( new Runnable(){
                    public void run(){
                        m.updateTurn();
                        m.updateTurn();
                    }
                });
                t2.start();
                // if(currMove.equals("quit")){
                //     return;
                // }
                // else if(currMove.equals("restart")){
                //     game.restart();
                // }
                // else if(game.tryMove(currMove)){
                //     game.board.printBoard();
                // }
                // else{
                //     System.out.println("Try Again");
                // }
            }
        }
        else {
            new BoardDisplay();
        }
    }
}