package model.data;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import model.Salle;

/**
 * 
 * Classe contenant les données d'une salle héritant de la super classe
 * abstraite DataModel et implémente Observer
 *
 */
public class SalleData extends DataModel<Salle> implements Observer {

	/**
	 * Le constructeur
	 * 
	 * @param dataFile le fichier de données
	 */
	public SalleData(File dataFile) {
		super(dataFile);
		updateCompteur();

	}

	/**
	 * Méthode statique pou mettre à jour le compteur lorsque les informations sont
	 * lues pour éviter de réinitialiser le compteur à chaque ouverture du programme
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
	 * Mettre à jour à partir des objets observés
	 */
	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof AssociationData) {

			update();

		}

	}

}
