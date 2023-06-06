CHESS APP JAVA README

This is a 100% Java chess application which supports terminal and GUI for move inputs, and Local or Multiplayer play.

We have made it so there is almost no setup required for users trying to run this application. First you must ensure you have Java 17 installed on your machine. 

JAVA INSTALLATION GUIDE:

MACOS: If you have home-brew, run brew install openjdk@17

Windows/Non-Homebrew Mac: https://www.oracle.com/java/technologies/downloads/#java17

Once you have installed the proper version of java you can download the source code. Everything on the GitHub is required for the program to run.

Navigate to the source code directory in your terminal and do the following:

For Local Play: 
	- java -jar ChessMain.jar
	- Enter L for local play and you're in! GUI will automatically pop up, and if the terminal 	will be printing the board as well.

For Multiplayer Play:
	- Whoever is hosting should execute java -jar ChessMultiplayer.jar, then execute java -jar 	ChessMain.jar
	- Player 2 only needs to run java -jar ChessMain.jar
	- Enter O to opt for Online play, enter the IP address (Public IP of whoever ran 		ChessMultiplayer.jar)



DEVELOPERS:

BOARD_INFO contains all files related to the Chess Board. In this directory there are classes which define Tiles and the actual Chess Board. The Board Class contains the method to initialize and print the board.

ENGINE contains all files related to the Chess Engine. This directory contains the main logic of the chess game. ChessEngine and Move classes will work together to handle all rules of chess like valid moves, win conditions, restarting the board etc.

ENUM contains a simple enum class which defines the color of pieces, WHITE, BLACK and EMPTY for tiles which don't contain a piece

PIECES contains the abstract class piece which all the chess pieces inherit from. This contains information about piece position, collision checking and piece promotion

Main & Multiplayer contain the required logic for all these classes to work together. Changes to Main and Multiplayer will most likely have cascading errors

GUI: Contains everything related to the GUI. BoardDisplay contains everything required to make the GUI run