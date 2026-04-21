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

		this.PlayerWhite.setBoard(this);
		this.PlayerBlack.setBoard(this);

		generateBoard(height, width);
	}

	private void setField(int x, int y, Field field) {
		this.board[y][x] = field;
		if (field.getType() == FieldType.White) {
			this.PlayerWhite.addField(field);
		} else if (field.getType() == FieldType.Black) {
			this.PlayerBlack.addField(field);
		}
	}

	public Field getField(int x, int y) {
		if (x < 0 || x >= this.board[0].length || y < 0 || y >= this.board.length) {
			return null;
		}
		return this.board[x][y];
	}

	public void changeTurn() {
		this.isWhiteTurn = !this.isWhiteTurn;
	}

	private void generateBoard(int height, int width) {
		this.board = new Field[height][width];

		var middleHeight = height / 2;
		var middleWidth = width / 2;

		boolean twoEmptyFields = height % 2 == 0 || width % 2 == 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i < middleHeight) {
					if (twoEmptyFields && i == middleHeight - 1 && j == middleWidth - 1) {
						this.setField(j, i, new Field(FieldType.Empty, i , j));
					} else this.setField(j, i, new Field(FieldType.Black, i , j));
				} else if (i == middleHeight && !twoEmptyFields) {
					if (j < middleWidth) {
						this.setField(j, i, new Field(FieldType.Black, i , j));
					} else if (j == middleWidth) {
						this.setField(j, i, new Field(FieldType.Empty, i , j));
					} else {
						this.setField(j, i, new Field(FieldType.White, i , j));
					}
				}
				else if (twoEmptyFields){
					if (i == middleHeight && j == middleWidth ) {
						this.setField(j, i, new Field(FieldType.Empty, i , j));
					}
					else {
						this.setField(j, i, new Field(FieldType.White, i , j));
					}
				}
				else  {
					this.setField(j, i, new Field(FieldType.White, i , j));
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

	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}

	public boolean checkIfCurrentPlayerIsGameOver(){
		var selectedFieldType = isWhiteTurn ? FieldType.White : FieldType.Black;

		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j].getType() == selectedFieldType) {
					var field = this.board[i][j];

					for (int x = -1; x <= 1; x++) {
						for (int y = -1; y <= 1; y++) {
							if (x == 0 && y == 0) {
								continue;
							}
							var targetField = getField(field.getX() + x, field.getY() + y);
							if (targetField != null){
								if (targetField.getType() == FieldType.Empty) {
									// There is an empty field
									return false;
								} else if (targetField.getType() != selectedFieldType) {
									var fieldBehind = getField(targetField.getX() + x, targetField.getY() + y);
									if (fieldBehind != null && fieldBehind.getType() == FieldType.Empty) {
										// There is an enemy piece that can be jumped over
										return false;
									}
								}
							}
						}
					}
				}
			}
		}

		return true;
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
