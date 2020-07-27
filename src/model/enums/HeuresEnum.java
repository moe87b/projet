package model.enums;

public enum HeuresEnum {

	HEURE1("16h à  17h30"), HEURE2("17h45 à 19h15"), HEURE3("19h30 à 21h");

	private String value;

	HeuresEnum(String value) {
		this.value = value;

	}

	public String toString() {
		return this.value;
	}

}
