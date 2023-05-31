package MULTIPLAYER;

import java.io.*;
import java.net.*;

import ENGINE.GameEngine;

public class Player {
    public ClientSideConnection csc;
    public int playerID;
    public String boardString;
    public int turns;
    public GameEngine game;

    public Player(GameEngine game){
        this.game = game;
    }

    public void connectToServer(){
        csc = new ClientSideConnection();
    }

    public void updateTurn(){
        String update = csc.receiveMove();
        game.tryMove(update);
        game.board.printBoard();
        System.out.println("Enter move: ");
    }

    

    public class ClientSideConnection{

        private Socket socket;
        public DataInputStream dataIn;
        public DataOutputStream dataOut;

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
            }
            catch(IOException ex){
                System.out.println("IOException in receiveMove().");
            }

            return newBoard;
        }
    }
}