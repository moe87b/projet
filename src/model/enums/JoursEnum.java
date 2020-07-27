package model.enums;

public enum JoursEnum {

	LUNDI("Lundi"), MARDI("Mardi"), MERCREDI("Mercredi"), JEUDI("Jeudi"), VENDREDI("Vendredi"), SAMEDI("Samedi");

	private String nom;

	JoursEnum(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;

	}

}
