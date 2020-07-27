package model.data;

import java.io.File;

import model.Matiere;

/**
 * Classe contenant une collection des diff�rentes mati�res cr�ees
 * 
 *
 */
public class MatiereData extends DataModel<Matiere> {

	/**
	 * Le constructeur
	 * 
	 * @param dataFile le fichier de donn�es
	 */
	public MatiereData(File dataFile) {
		super(dataFile);
		updateCompteur();
	}

	/**
	 * Mettre � jour le compteur apr�s chaque lecture
	 */
	private void updateCompteur() {
		int max = 0;
		for (Integer i : this.dataMap.keySet()) {
			if (i > max)
				max = i;
		}

		Matiere.updateCompteur(max);
	}

}
