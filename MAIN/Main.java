package MAIN;

import java.util.Scanner;

import BOARD_INFO.Board;
import ENGINE.GameEngine;
public class Main {

    public static void main(String[] args) {
        GameEngine game = new GameEngine(new Board());
        game.board.printBoard();
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        String currMove = "";

        while(exit == false){
            System.out.print("Enter move: ");
            currMove = input.nextLine();
            if(game.tryMove(currMove)){
                
            }
            else{
                System.out.println("Try Again");
            }
        }
    }
}