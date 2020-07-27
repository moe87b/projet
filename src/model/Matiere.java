package model;

import model.enums.Campus;

/**
 * <b>Repr�sente une matii�re</b>
 * <p>
 * la mati�re est caract�ris�e par:
 * <ul>
 * <li>Un identifiant unique</li>
 * <li>Un code compos� de 3 lettres suivies de 3 chiffres</li>
 * <li>Un campus</li>
 * </ul>
 * 
 * @author mohamad
 *
 */
public class Matiere extends Model {

	/**
	 * Le code est compos� de 3 lettres suivies de 3 chiffres
	 */
	private String code;
	/**
	 * La campus o� cette mati�re est disponible
	 */
	private Campus campus;
	/*
	 * Le compteur est incr�ment� � chaque instance pour attribuer un ID
	 */
	private static int COMPTEUR_MATIERE = 0;

	/**
	 * Constructeur
	 * <p>
	 * A la cr�ation le compteur est incr�ment� automatiquement. Le code est
	 * transform� en majuscules
	 * 
	 * @param code   Le code de la mati�re
	 * @param campus Le campus de o� la mati�re est disponible
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
	 * Retourne une repr�sentation en chaine de caract�re de la mati�re, avec le
	 * code et le campus
	 */
	@Override
	public String toString() {
		return this.code + "-" + this.campus;
	}

	/**
	 * Deux mati�res sont identique si elles ont le m�me ID ou si elles ont le m�me
	 * code et le m�me campus
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
	 * Retourne le code de la mati�re
	 * 
	 * @return le code de la mati�re
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Effectue une d�cr�mentation sur le compteur
	 */
	public static void decrementCompteur() {
		COMPTEUR_MATIERE--;
	}

	/**
	 * Mets � jour la valeur du compteur
	 * 
	 * @param value la nouvelle valeur du compteur
	 */
	public static void updateCompteur(int value) {
		COMPTEUR_MATIERE = value;
	}

}
