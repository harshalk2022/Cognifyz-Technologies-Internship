import java.util.*;

public class TicTacToe {
	private static final int SIZE = 3;
	private final String[][] board = new String[SIZE][SIZE];
	private final List<String> players = Arrays.asList("O", "X");
	private int currentPlayerIndex = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		do {
			TicTacToe game = new TicTacToe();

			while (true) {
				System.out.println("###### Player " + (game.currentPlayerIndex + 1) + " ######");
				System.out.print("Enter Row (1-3): ");
				int row = sc.nextInt() - 1;
				System.out.print("Enter Col (1-3): ");
				int col = sc.nextInt() - 1;

				if (!game.makeMove(row, col))
					continue;

				game.printBoard();
				if (!game.checkGameState().equals("PROGRESS"))
					break;
			}

			System.out.println("\nDo you want to play again? (yes/no)");
			String response = sc.next().trim().toLowerCase();

			if (!response.equals("yes")) {
				System.out.println("Thanks for playing!");
				break;
			}
		} while (true);

		sc.close();

	}

	public TicTacToe() {
		for (int i = 0; i < SIZE; i++) {
			Arrays.fill(board[i], "-");
		}
	}

	public boolean makeMove(int row, int col) {
		if (row < 0 || row >= SIZE) {
			System.out.println("\nINVALID MOVE : Please Enter Row number between 1 to 3\n");
			return false;
		} else if (col < 0 || col >= SIZE) {
			System.out.println("\nINVALID MOVE : Please Enter Col number between 1 to 3\n");
			return false;
		} else if (!board[row][col].equals("-")) {
			System.out.println("\nINVALID MOVE : Place Alredy Taken\n");
			return false;
		} else {
			board[row][col] = players.get(currentPlayerIndex);
			System.out.println("######################");
			return true;
		}
	}

	public String checkGameState() {
		if (checkWinner()) {
			System.out.println("Player " + (currentPlayerIndex + 1) + " won the match!");
			return "WON";
		} else if (isDraw()) {
			System.out.println("Match Drawn");
			return "DRAW";
		} else {
			currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
			return "PROGRESS";
		}
	}

	private boolean checkWinner() {
		// check rows,, cols, and diagonals
		for (int i = 0; i < SIZE; i++) {

			Boolean rowCheck = checkLine(board[i][0], board[i][1], board[i][2]);
			Boolean colCheck = checkLine(board[0][i], board[1][i], board[2][i]);

			if (rowCheck || colCheck)
				return true;

		}

		Boolean leftDiagonalCheck = checkLine(board[0][0], board[1][1], board[2][2]);
		Boolean rightDiagonalCheck = checkLine(board[0][2], board[1][1], board[2][0]);

		return leftDiagonalCheck || rightDiagonalCheck;

	}

	private boolean checkLine(String a, String b, String c) {
		return (!a.equals("-")) && a.equals(b) && b.equals(c);
	}

	private boolean isDraw() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j].equals("-"))
					return false;
			}
		}
		return true;
	}

	public void printBoard() {
		System.out.println("\nCurrent Board:");
		System.out.println();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + board[i][j] + " ");
				if (j < SIZE - 1)
					System.out.print("|");
			}
			System.out.println();
			if (i < SIZE - 1)
				System.out.println("-----------");
		}
		System.out.println();
	}

}
