package model.data;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import model.Salle;

/**
 * 
 * Classe contenant les donn�es d'une salle h�ritant de la super classe
 * abstraite DataModel et impl�mente Observer
 *
 */
public class SalleData extends DataModel<Salle> implements Observer {

	/**
	 * Le constructeur
	 * 
	 * @param dataFile le fichier de donn�es
	 */
	public SalleData(File dataFile) {
		super(dataFile);
		updateCompteur();

	}

	/**
	 * M�thode statique pou mettre � jour le compteur lorsque les informations sont
	 * lues pour �viter de r�initialiser le compteur � chaque ouverture du programme
	 */
	private void updateCompteur() {
		int max = 0;
		for (Integer i : this.dataMap.keySet()) {
			if (i > max)
				max = i;
		}

		Salle.setCompteur(max);
	}

	/**
	 * Mettre � jour � partir des objets observ�s
	 */
	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof AssociationData) {

			update();

		}

	}

}
