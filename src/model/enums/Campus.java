package model.enums;

/**
 * 
 * @author mohamad Enumeration repr�sentant les diff�rents centres
 */

public enum Campus {

	BEYROUTH("Beyrouth"), NAHR_IBRAHIM("Nahr Ibrahim"), BAALBECK("Baalbeck"), TRIPOLI("Tripoli"), BICKFAYA("Bickfaya");

	private String nom;

	Campus(String nom) {
		this.nom = nom;
	}

	public String toString() {
		return this.nom;
	}

}
