package model;

import java.io.Serializable;

import model.enums.Campus;

/**
 * <b>Salle représente un objet salle</b>
 * <p>
 * Un objet salle est caractérisé par les informations suivantes
 * </p>
 * <ul>
 * <li>Un identifiant unique ID hérité de la classe mère</li>
 * <li>Un nom composé d'une lettre suivie de 3 chiffres</li>
 * <li>La capacite qui indique le nombre d'étudiants maximal que peut contenir
 * la salle</li>
 * </ul>
 */
public class Salle extends Model implements Serializable {

	/**
	 * Le compteur est incrémenté à chauqe nouvelle salle créé et attribué au ID
	 */
	private static int COMPTEUR_SALLE = 0;
	/**
	 * Le nom est composé d'une lettre suivie de 3 chiffres
	 */
	private String nom;
	/**
	 * La capacité est un entier positif
	 */
	private int capacite;
	/**
	 * Le centre où se trouve la salle
	 */
	private Campus campus;

	/**
	 * Constructeur Salle
	 * <p>
	 * A la création de la salle, le compteur est incrémeneté de 1 et le ID est
	 * attribué. Le nom est transformé en majuscules
	 * </p>
	 * 
	 * @param nom      Le nom de la salle
	 * @param campus   Le campus où se trouve la salle
	 * @param capacite La capacité maximale
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
	 * Retourne une chaine de caractère représentant une salle avec le nom et la
	 * capacité
	 */
	@Override
	public String toString() {
		return this.nom + " - Capacaite: " + this.capacite;
	}

	/**
	 * Deux salles sont identiques si elles ont le même ID
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
	 * retourne la capacité de la salle
	 * 
	 * @return la capacité de la salle
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
	 * Mets à jour la valeur du compteur
	 * 
	 * @param la nouvelle valeur du compteur
	 */
	public static void setCompteur(int compteur) {
		Salle.COMPTEUR_SALLE = compteur;

	}

}
