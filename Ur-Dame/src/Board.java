import enums.FieldType;

public class Board {
	public Field[][] board;

	public Board() {
		generateBoard(7, 7);
	}

	private void setField(int x, int y, Field field) {
		this.board[x][y] = field;
	}

	private void generateBoard(int height, int width) {
		this.board = new Field[width][height];

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.board.length; i++) {
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
