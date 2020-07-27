package model;

/**
 * Un objet représentant une classe, qui fait le lien entre une matière et un
 * certain nom d'étudiants inscrits
 * 
 * @author mohamad
 *
 */

public class Classe extends Model {

	/**
	 * La matière de la classe
	 */
	private Matiere matiere;
	/**
	 * Le nombre d'étudiants inscrits à cette matière
	 */
	private int inscriptions;
	/**
	 * Le compteur est incrémenté aà chaque instance pour servir de ID
	 */
	private static int COMPTEUR_CLASSE = 0;

	/**
	 * Le constructeur
	 * 
	 * @param m            la matière de cette classe
	 * @param inscriptions le nombre d'étudiants inscrits
	 */
	public Classe(Matiere m, int inscriptions) {
		COMPTEUR_CLASSE++;
		this.id = COMPTEUR_CLASSE;
		this.matiere = m;
		this.inscriptions = inscriptions;

	}

	/**
	 * Une représentation en chaine de caractère de la classe avec le nom et le
	 * nombre d'inscriptions
	 */
	@Override
	public String toString() {
		return this.matiere + "-" + this.inscriptions + " étudiants";
	}

	/**
	 * Deux classes sont identiques si elles ont la même matière
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
	 * Met à jour le compteur
	 * 
	 * @param value Nouvelle valeur du compteur
	 */
	public static void setCompteur(int value) {
		Classe.COMPTEUR_CLASSE = value;
	}

	/**
	 * Décremente le compteur de 1
	 */
	public static void decrementCompteur() {
		Classe.COMPTEUR_CLASSE--;
	}

	/**
	 * Retourne le nombre d'étudiants inscrits
	 * 
	 * @return le nombre d'inscriptions
	 */
	public int getInscriptions() {
		return this.inscriptions;
	}

	/**
	 * Retourne la matière concernée
	 * 
	 * @return la matière
	 * 
	 */
	public Matiere getMatiere() {
		return matiere;
	}

}
