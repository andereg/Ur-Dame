import enums.FieldType;

public class Board {
	public Field[][] board;

	private Player PlayerWhite;
	private Player PlayerBlack;

	private boolean isWhiteTurn = true;
	private boolean gameFinished = false;

	public Board(int height, int width, Player playerWhite, Player playerBlack) {
		this.PlayerWhite = playerWhite;
		this.PlayerBlack = playerBlack;

	    this.PlayerWhite.setOpponent(this.PlayerBlack);
	    this.PlayerBlack.setOpponent(this.PlayerWhite);

		generateBoard(height, width);
	}

	private void setField(int x, int y, Field field) {
		this.board[x][y] = field;
		if (field.getType() == FieldType.White) {
			this.PlayerWhite.addField(field);
		} else if (field.getType() == FieldType.Black) {
			this.PlayerBlack.addField(field);
		}
	}

	public Field getField(int x, int y) {
		return this.board[x][y];
	}

	public void changeTurn() {
		this.isWhiteTurn = !this.isWhiteTurn;
	}

	private void generateBoard(int height, int width) {
		this.board = new Field[height][width];

		var middleHeight = height / 2;
		var middleWidth = width / 2;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i < middleHeight) {
					this.setField(i, j, new Field(FieldType.Black));
				} else if (i == middleHeight) {
					if (j < middleWidth) {
						this.setField(i, j, new Field(FieldType.Black));
					} else if (j == middleWidth) {
						this.setField(i, j, new Field(FieldType.Empty));
					} else {
						this.setField(i, j, new Field(FieldType.White));
					}
				}
				else  {
					this.setField(i, j, new Field(FieldType.White));
				}
			}
		}
	}

	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}

	public boolean isGameFinished() {
		return gameFinished;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		// Print column headers
		sb.append("  ");
		for (int j = 0; j < this.board[0].length; j++) {
			sb.append(j + " ");
		}
		sb.append("\n");

		for (int i = 0; i < this.board.length; i++) {
			sb.append(i + " ");
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j].getType() == FieldType.Empty)  {
					sb.append(". ");
				} else if (this.board[i][j].getType() == FieldType.Black) {
					sb.append("B ");
				} else if (this.board[i][j].getType() == FieldType.White) {
					sb.append("W ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
