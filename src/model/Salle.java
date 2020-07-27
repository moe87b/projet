package model;

import java.io.Serializable;

import model.enums.Campus;

/**
 * <b>Salle repr�sente un objet salle</b>
 * <p>
 * Un objet salle est caract�ris� par les informations suivantes
 * </p>
 * <ul>
 * <li>Un identifiant unique ID h�rit� de la classe m�re</li>
 * <li>Un nom compos� d'une lettre suivie de 3 chiffres</li>
 * <li>La capacite qui indique le nombre d'�tudiants maximal que peut contenir
 * la salle</li>
 * </ul>
 */
public class Salle extends Model implements Serializable {

	/**
	 * Le compteur est incr�ment� � chauqe nouvelle salle cr�� et attribu� au ID
	 */
	private static int COMPTEUR_SALLE = 0;
	/**
	 * Le nom est compos� d'une lettre suivie de 3 chiffres
	 */
	private String nom;
	/**
	 * La capacit� est un entier positif
	 */
	private int capacite;
	/**
	 * Le centre o� se trouve la salle
	 */
	private Campus campus;

	/**
	 * Constructeur Salle
	 * <p>
	 * A la cr�ation de la salle, le compteur est incr�menet� de 1 et le ID est
	 * attribu�. Le nom est transform� en majuscules
	 * </p>
	 * 
	 * @param nom      Le nom de la salle
	 * @param campus   Le campus o� se trouve la salle
	 * @param capacite La capacit� maximale
	 * @See {@link Model#id}
	 * 
	 */
	public Salle(String nom, Campus campus, int capacite) {

		COMPTEUR_SALLE++;
		this.id = COMPTEUR_SALLE;
		this.nom = nom.toUpperCase();
		this.campus = campus;
		this.capacite = capacite;

	}

	/**
	 * Retourne une chaine de caract�re repr�sentant une salle avec le nom et la
	 * capacit�
	 */
	@Override
	public String toString() {
		return this.nom + " - Capacaite: " + this.capacite;
	}

	/**
	 * Deux salles sont identiques si elles ont le m�me ID
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof Salle))
			return false;

		Salle s = (Salle) o;
		return s.getId() == this.getId();

	}

	/**
	 * retourne le campus
	 * 
	 * @return le campus de la salle
	 */
	public Campus getCampus() {
		return campus;
	}

	/**
	 * retourne la capacit� de la salle
	 * 
	 * @return la capacit� de la salle
	 */
	public int getCapacite() {
		return capacite;
	}

	/**
	 * return le nom de la salle
	 * 
	 * @return le nom de la salle
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Diminue de 1 la valeur du compteur
	 */
	public static void decrementCompteur() {
		Salle.COMPTEUR_SALLE--;
	}

	/**
	 * Mets � jour la valeur du compteur
	 * 
	 * @param la nouvelle valeur du compteur
	 */
	public static void setCompteur(int compteur) {
		Salle.COMPTEUR_SALLE = compteur;

	}

}
