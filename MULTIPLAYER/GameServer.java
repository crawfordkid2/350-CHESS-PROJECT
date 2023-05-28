package MULTIPLAYER;

import java.io.*;
import java.net.*;

import ENGINE.GameEngine;
import BOARD_INFO.*;

public class GameServer{
    private ServerSocket socket;
    private int numPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;

    private GameEngine game;

    public GameServer(){
        this.numPlayers = 0;
        this.game = new GameEngine(new Board());
        try{
            this.socket = new ServerSocket(27015);
        }
        catch (IOException ex) {
            System.out.println("IOException from GameServer Constructor.");
        }
    }

    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections....");
            while (this.numPlayers < 2){
                Socket s = socket.accept();
                numPlayers++;
                System.out.println("Player " + numPlayers + " has connected.");
                ServerSideConnection ssc = new ServerSideConnection(s, numPlayers);
                if(numPlayers == 1){
                    player1 = ssc;
                }
                else{
                    player2 = ssc;
                }
                Thread t = new Thread(ssc);
                t.start();
            }
        }
        catch(IOException ex){
            System.out.println("IOExceptions from acceptConnections.");
        }
    }

    private class ServerSideConnection implements Runnable{
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private int playerID;
        private int turns;

        public ServerSideConnection(Socket s, int id){
            this.socket = s;
            this.playerID = id;
            this.turns = 0;
            try{
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            }
            catch(IOException ex){
                System.out.println("IOException in SSC constructor.");
            }
        }

        public void run(){
            String currMove = "";
            String output = "";
            try {
                dataOut.writeInt(playerID);
                dataOut.writeUTF(game.board.toString());
                dataOut.flush();

                while(true){
                    if(playerID == 1){
                        currMove = dataIn.readUTF();
                        System.out.println("Player 1 Move: " + currMove);
                        output = checkMove(currMove);
                        player2.sendMove(output);
                    }
                    else if(playerID == 2){
                        currMove = dataIn.readUTF();
                        System.out.println("Player 2 Move: " + currMove);
                        output = checkMove(currMove);
                        player1.sendMove(output);
                    }
                    dataOut.writeUTF(output);
                    dataOut.flush();
                }
            }
            catch(IOException ex){
                System.out.println("IOException in run() SSC");
            }
        }

        //TODO
        public String checkMove(String currMove){
            if(currMove.equals("quit")){
                return "";
            }
            else if(game.tryMove(currMove)){
                this.turns++;
                return game.board.toString();
            }
            else{
                return "Try Again";
            }
        }
        public void sendMove(String move){
            try{
                dataOut.writeUTF(move);
                dataOut.flush();
            }
            catch(IOException ex){
                System.out.println("IOException in sendMove() server.");
            }
        }
    }

    public static void main(String[] args){
        GameServer server = new GameServer();
        server.acceptConnections();
    }
}