package model;

/**
 * Un objet repr�sentant une classe, qui fait le lien entre une mati�re et un
 * certain nom d'�tudiants inscrits
 * 
 * @author mohamad
 *
 */

public class Classe extends Model {

	/**
	 * La mati�re de la classe
	 */
	private Matiere matiere;
	/**
	 * Le nombre d'�tudiants inscrits � cette mati�re
	 */
	private int inscriptions;
	/**
	 * Le compteur est incr�ment� a� chaque instance pour servir de ID
	 */
	private static int COMPTEUR_CLASSE = 0;

	/**
	 * Le constructeur
	 * 
	 * @param m            la mati�re de cette classe
	 * @param inscriptions le nombre d'�tudiants inscrits
	 */
	public Classe(Matiere m, int inscriptions) {
		COMPTEUR_CLASSE++;
		this.id = COMPTEUR_CLASSE;
		this.matiere = m;
		this.inscriptions = inscriptions;

	}

	/**
	 * Une repr�sentation en chaine de caract�re de la classe avec le nom et le
	 * nombre d'inscriptions
	 */
	@Override
	public String toString() {
		return this.matiere + "-" + this.inscriptions + " �tudiants";
	}

	/**
	 * Deux classes sont identiques si elles ont la m�me mati�re
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Classe))
			return false;

		Classe c = (Classe) o;
		if (c.getId() == this.getId() || (c.getMatiere().equals(this.getMatiere())))
			return true;

		return false;

	}

	/**
	 * Met � jour le compteur
	 * 
	 * @param value Nouvelle valeur du compteur
	 */
	public static void setCompteur(int value) {
		Classe.COMPTEUR_CLASSE = value;
	}

	/**
	 * D�cremente le compteur de 1
	 */
	public static void decrementCompteur() {
		Classe.COMPTEUR_CLASSE--;
	}

	/**
	 * Retourne le nombre d'�tudiants inscrits
	 * 
	 * @return le nombre d'inscriptions
	 */
	public int getInscriptions() {
		return this.inscriptions;
	}

	/**
	 * Retourne la mati�re concern�e
	 * 
	 * @return la mati�re
	 * 
	 */
	public Matiere getMatiere() {
		return matiere;
	}

}
