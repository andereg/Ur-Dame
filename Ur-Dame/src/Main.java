import java.util.Scanner;

public class Main {
	public static void main(String[] args) {


		var playerWhite = new Player("Player White", true);
		var playerBlack = new Player("Player Black", false);

		int dimension = 3;
		var board = new Board(dimension,dimension , playerWhite, playerBlack);

		System.out.println(board);

		Scanner scanner = new Scanner(System.in);

		while (!board.isGameFinished()) {
			Player currentPlayer = board.isWhiteTurn() ? playerWhite : playerBlack;

			System.out.println(currentPlayer.getName() + "'s turn.");
			System.out.println(currentPlayer.getName() + "Select a piece to move (format: x,y):");

			String selectedPieceInput = scanner.nextLine();

			if (!selectedPieceInput.matches("\\d+,\\d+")) {
				System.out.println("Invalid input format.");
				continue;
			}

			var selectedPiece = selectedPieceInput.split(",");
			var fieldFrom = board.getField(Integer.parseInt(selectedPiece[1]), Integer.parseInt(selectedPiece[0]));

			System.out.println(currentPlayer.getName() + "Select a target (format: x,y):");

			String targetInput = scanner.nextLine();

			if (!targetInput.matches("\\d+,\\d+")) {
				System.out.println("Invalid input format.");
				continue;
			}

			var target = targetInput.split(",");
			var fieldTo = board.getField(Integer.parseInt(target[1]), Integer.parseInt(target[0]));


			if (currentPlayer.move(fieldFrom, fieldTo)) {
				System.out.println("Move successful.");

				System.out.println(board);
				board.changeTurn();
			} else {
				System.out.println("Move failed, please try again.");
			}
		}
	}
}