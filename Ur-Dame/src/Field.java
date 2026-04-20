import enums.FieldType;

public class Field {

	private FieldType type;


	public Field(FieldType type) {
		this.type = type;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}
}
