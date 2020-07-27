package model.data;

import java.io.File;

import model.Matiere;

/**
 * Classe contenant une collection des différentes matières créees
 * 
 *
 */
public class MatiereData extends DataModel<Matiere> {

	/**
	 * Le constructeur
	 * 
	 * @param dataFile le fichier de données
	 */
	public MatiereData(File dataFile) {
		super(dataFile);
		updateCompteur();
	}

	/**
	 * Mettre à jour le compteur après chaque lecture
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
