package dev.m3v;
import java.util.Scanner;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class MainBot extends ListenerAdapter {
    public static void main(String[] args) throws Exception {
        String token = System.getenv("DISCORD_BOT_TOKEN");
        if (token == null || token.isBlank()) {
            System.err.println("ERROR: Please set DISCORD_BOT_TOKEN environment variable.");
            System.exit(1);
        } else {
            JDABuilder.createDefault(token, GatewayIntent.MESSAGE_CONTENT)
            .setActivity(Activity.playing("Boter, Kaas en Eieren"))
            .addEventListeners(new MainBot())
            .build();
        }
    }

    public static void playGame() {
        // Initialize scanner and game variables
        Scanner scanner = new Scanner(System.in);
        String[] boardState = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int turn = 0;
        String currentPlayer = "O"; // Start with O
        boolean gameOver = false;

        while (!gameOver) {
            // Display the board at the start of the game
            if (turn == 0) {
                System.out.println("Welcome to Tic Tac Toe!");
                System.out.println(printBoard(boardState));
            }
            
            while (true) {
                // Input handling with exception catching
                try {
                    // Players's turn
                    System.out.print(currentPlayer+"'s turn, Choose a number (1-9): ");
                    int currentInput = scanner.nextInt();
                    if (currentInput >= 1 && currentInput <= 9 && !boardState[currentInput - 1].equals("O") && !boardState[currentInput - 1].equals("X")) {
                        boardState[currentInput - 1] = currentPlayer;
                        System.out.println(printBoard(boardState));
                        turn++;
                        break;
                    // Invalid input handling
                    } else {
                        System.out.println("Invalid input, try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input, try again.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Switch players
            if (currentPlayer.equals("O")) {
                currentPlayer = "X";
            } else {
                currentPlayer = "O";
            }

            // Check for a winner
            if (checkWinner(boardState) != null) {
                System.out.println(checkWinner(boardState) + " wins!");
                gameOver = true;
            } else if (turn >= 9) {
                System.out.println("It's a draw!");
                gameOver = true;
            }
        }
        scanner.close();
    }

    private static String checkWinner(String[] boardState) {
        // Rows
        if (boardState[0].equals(boardState[1]) && boardState[1].equals(boardState[2])) return boardState[0];
        if (boardState[3].equals(boardState[4]) && boardState[4].equals(boardState[5])) return boardState[3];
        if (boardState[6].equals(boardState[7]) && boardState[7].equals(boardState[8])) return boardState[6];

        // Columns
        if (boardState[0].equals(boardState[3]) && boardState[3].equals(boardState[6])) return boardState[0];
        if (boardState[1].equals(boardState[4]) && boardState[4].equals(boardState[7])) return boardState[1];
        if (boardState[2].equals(boardState[5]) && boardState[5].equals(boardState[8])) return boardState[2];

        // Diagonals
        if (boardState[0].equals(boardState[4]) && boardState[4].equals(boardState[8])) return boardState[0];
        if (boardState[2].equals(boardState[4]) && boardState[4].equals(boardState[6])) return boardState[2];

        return null; // no winner yet
    }

    private static String printBoard(String[] boardState) {
        String boardString = "     |     |     %n  %s  |  %s  |  %s  %n_____|_____|_____%n     |     |     %n  %s  |  %s  |  %s  %n_____|_____|_____ %n     |     |     %n  %s  |  %s  |  %s  %n     |     |     ";
        return String.format(boardString, boardState[0], boardState[1], boardState[2], boardState[3], boardState[4], boardState[5], boardState[6], boardState[7], boardState[8]);
    }
}
