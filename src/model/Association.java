package model;

/**
 * Clase représentant l'allocation d'un enseignant à une classe
 * 
 * @author mohamad
 *
 */
public class Association extends Model {
	/**
	 * Le compteur est incrémenté à chaque nouvelle instance et sert de ID
	 */
	private static int COMPTEUR_ASSOCIATION = 0;
	/**
	 * L'enseignant chargé de le classe
	 */
	private Enseignant enseignant;
	/**
	 * La classe concernée
	 */
	private Classe classe;

	/**
	 * Constructeur
	 * 
	 * @param e L'enseignant
	 * @param c La classe
	 */
	public Association(Enseignant e, Classe c) {
		COMPTEUR_ASSOCIATION++;
		this.id = COMPTEUR_ASSOCIATION;
		this.enseignant = e;
		this.classe = c;

	}

	/**
	 * Deux associations sont identiques si elles ont le même enseignant et la même
	 * classe ou si elles sont le même identifiant
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return false;
		if (!(o instanceof Association))
			return false;

		Association a = (Association) o;
		return (a.classe.equals(this.classe) && a.enseignant.equals(this.enseignant) || this.id == a.id);
	}

	/**
	 * Mets à jour le compteur
	 * 
	 * @param value nouvelle valeur du compteur
	 */
	public static void updateCompteur(int value) {
		COMPTEUR_ASSOCIATION = value;
	}

	/**
	 * Décremente de 1 le compteur
	 */
	public static void decrementCompteur() {
		COMPTEUR_ASSOCIATION--;
	}

	/**
	 * Retourne l'enseignant
	 * 
	 * @return l'enseignant
	 */
	public Enseignant getEnseignant() {
		return enseignant;
	}

	/**
	 * Retourne la classe
	 * 
	 * @return la classe
	 */
	public Classe getClasse() {
		return classe;
	}

	/**
	 * Retourne une chaine de caractère représentant une association avec la classe
	 * et l'enseignant
	 */
	@Override
	public String toString() {
		return classe.toString() + "-" + enseignant.toString();
	}

	/**
	 * Retourne l'indentifiant
	 */
	@Override
	public int getId() {
		return super.getId();
	}
}
