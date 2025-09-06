import java.util.Scanner;

public class MainButClean {
    public static void main(String[] args) {
        // Initialize scanner and game variables
        Scanner scanner = new Scanner(System.in);
        String[] a = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String boardString = "     |     |     %n  %s  |  %s  |  %s  %n_____|_____|_____%n     |     |     %n  %s  |  %s  |  %s  %n_____|_____|_____ %n     |     |     %n  %s  |  %s  |  %s  %n     |     |     ";
        String board = String.format(boardString, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8]);
        int turn = 0;
        String currentPlayer = "O"; // Start with O
        boolean gameOver = false;

        while (!gameOver) {
            // Display the board at the start of the game
            if (turn == 0) {
                System.out.println("Welcome to Tic Tac Toe!");
                System.out.println(board);
            }

            while (true) {
                // Players's turn
                System.out.print(currentPlayer+"'s turn, Choose a number (1-9): ");
                int currentInput = scanner.nextInt();
                if (currentInput >= 1 && currentInput <= 9 && !a[currentInput - 1].equals("O") && !a[currentInput - 1].equals("X")) {
                    a[currentInput - 1] = currentPlayer;
                    turn++;
                    board = String.format(boardString, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8]);
                    System.out.println(board);
                    if (turn >= 9) break;
                    break;
                // Invalid input handling
                } else {
                    System.out.println("Invalid input, try again.");
                }
            }

            // Switch players
            if (currentPlayer.equals("O")) {
                currentPlayer = "X";
            } else {
                currentPlayer = "O";
            }

            // Check for a winner
            if (checkWinner(a) != null) {
                System.out.println(checkWinner(a) + " wins!");
                gameOver = true;
            } else if (turn >= 9) {
                System.out.println("It's a draw!");
                gameOver = true;
            }
        }
        scanner.close();
    }
    public static String checkWinner(String[] a) {
        // Rows
        if (a[0].equals(a[1]) && a[1].equals(a[2])) return a[0];
        if (a[3].equals(a[4]) && a[4].equals(a[5])) return a[3];
        if (a[6].equals(a[7]) && a[7].equals(a[8])) return a[6];

        // Columns
        if (a[0].equals(a[3]) && a[3].equals(a[6])) return a[0];
        if (a[1].equals(a[4]) && a[4].equals(a[7])) return a[1];
        if (a[2].equals(a[5]) && a[5].equals(a[8])) return a[2];

        // Diagonals
        if (a[0].equals(a[4]) && a[4].equals(a[8])) return a[0];
        if (a[2].equals(a[4]) && a[4].equals(a[6])) return a[2];

        return null; // no winner yet
    }
}
