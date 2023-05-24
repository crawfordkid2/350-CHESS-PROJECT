package MULTIPLAYER;

import java.io.*;
import java.net.*;

public class GameServer{
    private ServerSocket socket;
    private int numPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;

    public GameServer(){
        this.numPlayers = 0;

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

        public ServerSideConnection(Socket s, int id){
            this.socket = s;
            this.playerID = id;
            try{
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            }
            catch(IOException ex){
                System.out.println("IOException in SSC constructor.");
            }
        }

        public void run(){
            try {
                dataOut.writeInt(playerID);
                dataOut.flush();
                while(true){

                }
            }
            catch(IOException ex){
                System.out.println("IOException in run() SSC");
            }
        }
    }

    public static void main(String[] args){
        GameServer server = new GameServer();
        server.acceptConnections();
    }
}