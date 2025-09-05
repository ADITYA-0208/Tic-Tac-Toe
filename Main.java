import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        char currentPlayer = 'X';

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Player X and Player O take turns.");
        displayBoard(game);

        while (!game.isGameOver()) {
            System.out.println("Player " + currentPlayer + ", enter row and column (1-3):");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            while (row < 0 || row > 2 || col < 0 || col > 2 || game.board[row][col] != ' ') {
                System.out.println("Invalid move! Enter row and column (1-3) again:");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            }

            game.makeMove(row, col, currentPlayer);
            System.out.println("Player " + currentPlayer + " played:");
            displayBoard(game);

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        char winner = game.hasWon('X') ? 'X' : game.hasWon('O') ? 'O' : 'T';
        if (winner == 'T') {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Player " + winner + " wins!");
        }

        scanner.close();
    }

    private static void displayBoard(TicTacToe game) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(game.board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
