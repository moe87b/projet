package model.data;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import model.Enseignant;

/**
 * 
 * Classe contenant une collection des objets enseignants cr�es
 *
 */
public class EnseignantData extends DataModel<Enseignant> implements Observer {

	/**
	 * Le constructeur
	 * 
	 * @param dataFile le fichier de donn�es
	 */
	public EnseignantData(File dataFile) {
		super(dataFile);
		updateCompteur();

	}

	/**
	 * Mise � jour du compteur de la classe Enseignant
	 */
	public void updateCompteur() {
		int max = 0;
		for (Integer i : this.dataMap.keySet()) {
			if (i > max)
				max = i;
		}

		Enseignant.updateCompteur(max);
	}

	/**
	 * Mise � jour � partir des objets observ�s
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		update();

	}

}
