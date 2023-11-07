import java.util.*;

public class TicTacToe {
    // Constants for the game
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        // Initialize the game board, scanner, and random generator
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        initializeBoard(board); // Set up an empty game board
        boolean isPlayerX = true; // Track whose turn it is
        boolean gameFinished = false; // Flag to check if the game is over

        printBoard(board); // Print the initial game board

        while (true) {
            if (isPlayerX) {
                playPlayerTurn(board, scanner); // Player's turn
            } else {
                playAITurn(board, random); // AI's turn (random move)
            }

            printBoard(board); // Display the updated game board

            if (checkGameStatus(board)) {
                gameFinished = true; // Check if the game is over
                char winner = isPlayerX ? PLAYER_X : PLAYER_O;
                if (winner == EMPTY) {
                    System.out.println("It's a draw!");
                } else {
                    System.out.println("Player " + winner + " wins!");
                }
                break; // Exit the game loop
            }

            isPlayerX = !isPlayerX; // Switch turns
        }

        scanner.close();
    }

    // Initialize the game board with empty spaces
    private static void initializeBoard(char[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    // Print the current state of the game board
    private static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print("| ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Check if the game is over
    private static boolean checkGameStatus(char[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] != EMPTY && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }

        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] != EMPTY && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }

        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    return false; // The game is still ongoing
                }
            }
        }

        return true; // It's a draw
    }

    // Player's turn
    private static void playPlayerTurn(char[][] board, Scanner scanner) {
        int row, col;
        while (true) {
            System.out.print("Enter row (0-2) and column (0-2) separated by space: ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (isValidMove(board, row, col)) {
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        board[row][col] = PLAYER_X;
    }

    // AI's turn (random move)
    private static void playAITurn(char[][] board, Random random) {
        System.out.println("AI's turn (Player O):");
        int row, col;
        do {
            row = random.nextInt(BOARD_SIZE);
            col = random.nextInt(BOARD_SIZE);
        } while (!isValidMove(board, row, col));
        board[row][col] = PLAYER_O;
    }

    // Check if the move is valid
    private static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY;
    }
}
