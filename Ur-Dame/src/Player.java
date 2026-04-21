import enums.FieldType;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private boolean isWhite;

	private Player opponent;

	private List<Field> fields;

	private Board board;

	public Player(String name, boolean isWhite) {
		this.name = name;
		this.isWhite = isWhite;
		fields = new ArrayList<>();
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void addField(Field field) {
		if (isWhite && field.getType() != FieldType.White) {
			return;
		}
		if (!isWhite && field.getType() != FieldType.Black) {
			return;
		}
		this.fields.add(field);
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public Player getOpponent() {
		return opponent;
	}

	public String getName() {
		return name;
	}

	public boolean isWhite() {
		return isWhite;
	}

	// Returns true if the move was successful, false otherwise
	public boolean move(Field fieldFrom, Field fieldTo) {
		var differenceY = fieldFrom.getY() - fieldTo.getY();
		var differenceX = fieldFrom.getX() - fieldTo.getX();

		// Can only move one step in any direction
		if (!(differenceX == -1 || differenceX == 1) || !(differenceY == -1 || differenceY == 1)) {
			return false;
		}

		if (fieldTo.getType() == FieldType.Empty) {
			fieldTo.setType(fieldFrom.getType());
			fieldFrom.setType(FieldType.Empty);

			return true;
		}

		FieldType enemyFieldType = isWhite ? FieldType.Black : FieldType.White;

		if (fieldTo.getType() == enemyFieldType) {
			var fieldBehind = board.getField(fieldTo.getX() - differenceX, fieldTo.getY() - differenceY);
			if (fieldBehind != null && fieldBehind.getType() == FieldType.Empty) {
				// Empty field behind enemy piece

				//Destroy enemy piece
				fieldTo.setType(FieldType.Empty);

				fieldFrom.setType(FieldType.Empty);

				// Move piece to field behind
				fieldBehind.setType(fieldFrom.getType());
				return true;
			}
		}

		return false;
	}
}
