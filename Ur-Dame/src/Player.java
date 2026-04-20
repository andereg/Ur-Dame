import enums.FieldType;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private boolean isWhite;

	private Player opponent;

	private List<Field> fields;

	public Player(String name, boolean isWhite) {
		this.name = name;
		this.isWhite = isWhite;
		fields = new ArrayList<>();
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
		if (fieldTo.getType() == FieldType.Empty) {

			var differenceY = fieldFrom.getY() - fieldTo.getY();
			var differenceX = fieldFrom.getX() - fieldTo.getX();

			// Can only move one step in any direction
			if (differenceX < -1 || differenceX > 1 || differenceY < -1 || differenceX > 1) return false;

			fieldTo.setType(fieldFrom.getType());
			fieldFrom.setType(FieldType.Empty);

			return true;
		}

		return false;
	}
}
