package model;

import model.enums.Campus;

/**
 * <b>Représente une matiière</b>
 * <p>
 * la matière est caractérisée par:
 * <ul>
 * <li>Un identifiant unique</li>
 * <li>Un code composé de 3 lettres suivies de 3 chiffres</li>
 * <li>Un campus</li>
 * </ul>
 * 
 * @author mohamad
 *
 */
public class Matiere extends Model {

	/**
	 * Le code est composé de 3 lettres suivies de 3 chiffres
	 */
	private String code;
	/**
	 * La campus où cette matière est disponible
	 */
	private Campus campus;
	/*
	 * Le compteur est incrémenté à chaque instance pour attribuer un ID
	 */
	private static int COMPTEUR_MATIERE = 0;

	/**
	 * Constructeur
	 * <p>
	 * A la création le compteur est incrémenté automatiquement. Le code est
	 * transformé en majuscules
	 * 
	 * @param code   Le code de la matière
	 * @param campus Le campus de où la matière est disponible
	 * @See {@link Model#id}
	 */
	public Matiere(String code, Campus campus) {
		super();
		COMPTEUR_MATIERE++;
		this.id = COMPTEUR_MATIERE;
		this.code = code.toUpperCase();
		this.campus = campus;
	}

	/**
	 * Retourne une représentation en chaine de caractère de la matière, avec le
	 * code et le campus
	 */
	@Override
	public String toString() {
		return this.code + "-" + this.campus;
	}

	/**
	 * Deux matières sont identique si elles ont le même ID ou si elles ont le même
	 * code et le même campus
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Matiere))
			return false;

		Matiere m = (Matiere) o;
		if (m.getId() == this.getId() || (this.code.equalsIgnoreCase(m.code) && this.campus == m.campus))
			return true;

		return false;
	}

	/**
	 * Retourne le campus
	 * 
	 * @return le campus
	 */
	public Campus getCampus() {
		return campus;
	}

	/**
	 * Retourne le code de la matière
	 * 
	 * @return le code de la matière
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Effectue une décrémentation sur le compteur
	 */
	public static void decrementCompteur() {
		COMPTEUR_MATIERE--;
	}

	/**
	 * Mets à jour la valeur du compteur
	 * 
	 * @param value la nouvelle valeur du compteur
	 */
	public static void updateCompteur(int value) {
		COMPTEUR_MATIERE = value;
	}

}
