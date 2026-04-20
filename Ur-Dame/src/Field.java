import enums.FieldType;

public class Field {

	private FieldType type;

	private final int x;
	private final int y;

	public Field(FieldType type, int x, int y) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public FieldType getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setType(FieldType type) {
		this.type = type;
	}
}
