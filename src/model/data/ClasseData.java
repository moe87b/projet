package model.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import model.Classe;
import model.Matiere;
import model.Salle;

/**
 * 
 * Salle contenant une collection des différentes salles de classe
 * 
 */
public class ClasseData extends DataModel<Classe> implements Observer {

	/**
	 * Liste de matières afin de savoir quelles sont les matières disponibles lors
	 * de la création d'une classe
	 */
	private ArrayList<Matiere> listeMatieres = new ArrayList<Matiere>();
	/**
	 * Liste de salles afin de savoir quelles sont les capacités des salles lors de
	 * la création d'une classe
	 */
	private ArrayList<Salle> listeSalles = new ArrayList<Salle>();

	/**
	 * Le constructeur
	 * 
	 * @param dataFile fichier de données
	 */
	public ClasseData(File dataFile) {
		super(dataFile);
		updateCompteur();
	}

	/**
	 * Mettre à jour le compteur lors de la lecture, pour ne pas qu'il se
	 * réinitialise à chaque éxécution du programme
	 */
	private void updateCompteur() {
		int max = 0;
		for (Integer i : this.dataMap.keySet()) {
			if (i > max)
				max = i;
		}

		Classe.setCompteur(max);
	}

	/**
	 * Mise à jour des informations que sont la liste des salles et des matières à
	 * travers observer
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof MatiereData) {
			MatiereData md = (MatiereData) arg0;
			listeMatieres.clear();
			for (Matiere m : md.getValues()) {
				listeMatieres.add(m);
			}
		}

		if (arg0 instanceof SalleData) {
			SalleData sd = (SalleData) arg0;
			listeSalles.clear();
			for (Salle s : sd.getValues()) {
				listeSalles.add(s);
			}
		}

		/*
		 * Lorsqu'un matière est supprimée, il faut supprimer la classe associée
		 */
		try {
			Matiere m = (Matiere) arg1;
			for (Classe cl : dataMap.values()) {
				if (cl.getMatiere().equals(m))
					dataMap.remove(cl.getId());
			}
		} catch (ClassCastException ex) {
		}

		setChanged();
		notifyObservers();
		write();

	}

	/**
	 * 
	 * @return la liste des matières
	 */
	public Collection<Matiere> getListeMatieres() {
		return listeMatieres;
	}

	/**
	 * Vérifie si la classe figure dans la liste
	 */
	@Override
	public boolean contains(Classe c) {

		for (Classe cl : this.dataMap.values()) {

			if (c.getMatiere().equals(cl.getMatiere()))
				return true;

		}
		return false;
	}

	/**
	 * 
	 * @return la liste des salles
	 */
	public ArrayList<Salle> getListeSalles() {
		return listeSalles;
	}

}
