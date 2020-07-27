package model;

/**
 * Classe repr�sentant un enseignant
 * 
 * @author mohamad
 *
 */
public class Enseignant extends Model {
	/**
	 * Le compteur est incr�ment� � chaque instance pour cr�er un identifiant unique
	 */
	private static int COMPTEUR_ENSEIGNANT = 0;
	/**
	 * Le nom de l'enseignant
	 */
	private String nom;

	/**
	 * Le constructeur
	 * <p>
	 * Le compteur est incr�ment� et attirbu� � l'identifiant
	 * 
	 * @param nom Le nom de l'enseignant
	 */
	public Enseignant(String nom) {

		COMPTEUR_ENSEIGNANT++;
		this.id = COMPTEUR_ENSEIGNANT;
		this.nom = nom;

	}

	/**
	 * D�cremente le compteur de 1
	 */
	public static void decrementCompteur() {
		Enseignant.COMPTEUR_ENSEIGNANT--;
	}

	/**
	 * Mets � jour le compteur
	 * 
	 * @param value nouvelle valeur du compteur
	 */
	public static void updateCompteur(int value) {
		Enseignant.COMPTEUR_ENSEIGNANT = value;
	}

	/**
	 * Retourne une chaine de caract�re repr�sentant un enseignant, avec son nom
	 */
	@Override
	public String toString() {
		return this.nom;
	}

	/**
	 * Deux enseignants sont identiques si ils ont le m�me ID
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
