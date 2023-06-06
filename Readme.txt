# Chess App Java README

This is a Java chess application that supports both terminal and GUI for move inputs, as well as local or multiplayer play.

## Java Installation Guide

To run this application, make sure you have Java 17 installed on your machine. Use the following instructions based on your operating system:

### macOS

If you have Homebrew, run the following command to install OpenJDK 17:

```
brew install openjdk@17
```

### Windows / Non-Homebrew macOS

Visit the Oracle website at [https://www.oracle.com/java/technologies/downloads/#java17](https://www.oracle.com/java/technologies/downloads/#java17) and download the appropriate Java 17 installer for your system.

## Running the Application

Once you have installed the proper version of Java, follow these steps:

1. Download the source code from GitHub. All files are required for the program to run.
2. Navigate to the source code directory in your terminal.

### Local Play

For local play, execute the following command:

```
java -jar ChessMain.jar
```

Then enter "L" to start local play. The GUI will automatically pop up, and the terminal will display the board.

### Multiplayer Play

For multiplayer play, follow these steps:

1. The player hosting the game should execute the following command:

```
java -jar ChessMultiplayer.jar
```

2. Next, the host should execute the following command:

```
java -jar ChessMain.jar
```

3. Player 2 only needs to run the following command:

```
java -jar ChessMain.jar
```

4. Enter "O" to opt for online play and provide the IP address (the public IP of the host who ran ChessMultiplayer.jar).

## Developers

The project structure is organized as follows:

- **BOARD_INFO**: Contains files related to the Chess Board. This directory includes classes that define Tiles and the actual Chess Board. The Board Class contains methods to initialize and print the board.

- **ENGINE**: Contains files related to the Chess Engine. This directory includes the main logic of the chess game. The ChessEngine and Move classes work together to handle all rules of chess, such as valid moves, win conditions, and board restarts.

- **ENUM**: Contains a simple enum class that defines the color of pieces: WHITE, BLACK, and EMPTY for tiles without a piece.

- **PIECES**: Contains the abstract class "Piece," from which all the chess pieces inherit. This class contains information about piece positions, collision checking, and piece promotion.

- **Main & Multiplayer**: Contain the required logic for all the classes to work together. Changes to these files may result in cascading errors.

- **GUI**: Contains everything related to the GUI. The BoardDisplay class includes everything required to make the GUI run.

Feel free to explore and modify the code as needed for your purposes.

---

I have reformatted and cleaned up the README for your Java chess app. Let me know if you need any further assistance!
