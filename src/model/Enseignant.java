package model;

/**
 * Classe représentant un enseignant
 * 
 * @author mohamad
 *
 */
public class Enseignant extends Model {
	/**
	 * Le compteur est incrémenté à chaque instance pour créer un identifiant unique
	 */
	private static int COMPTEUR_ENSEIGNANT = 0;
	/**
	 * Le nom de l'enseignant
	 */
	private String nom;

	/**
	 * Le constructeur
	 * <p>
	 * Le compteur est incrémenté et attirbué à l'identifiant
	 * 
	 * @param nom Le nom de l'enseignant
	 */
	public Enseignant(String nom) {

		COMPTEUR_ENSEIGNANT++;
		this.id = COMPTEUR_ENSEIGNANT;
		this.nom = nom;

	}

	/**
	 * Décremente le compteur de 1
	 */
	public static void decrementCompteur() {
		Enseignant.COMPTEUR_ENSEIGNANT--;
	}

	/**
	 * Mets à jour le compteur
	 * 
	 * @param value nouvelle valeur du compteur
	 */
	public static void updateCompteur(int value) {
		Enseignant.COMPTEUR_ENSEIGNANT = value;
	}

	/**
	 * Retourne une chaine de caractère représentant un enseignant, avec son nom
	 */
	@Override
	public String toString() {
		return this.nom;
	}

	/**
	 * Deux enseignants sont identiques si ils ont le même ID
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Enseignant))
			return false;

		Enseignant e = (Enseignant) o;
		return e.id == this.id;
	}

	public String getNom() {
		return nom;
	}

}
