package model.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import model.Classe;
import model.Matiere;
import model.Salle;

public class ClasseData extends DataModel<Classe> implements Observer {

	private ArrayList<Matiere> listeMatieres = new ArrayList<Matiere>();
	private ArrayList<Salle> listeSalles = new ArrayList<Salle>();

	public ClasseData(File dataFile) {
		super(dataFile);
		updateCompteur();
	}

	private void updateCompteur() {
		int max = 0;
		for (Integer i : this.dataMap.keySet()) {
			if (i > max)
				max = i;
		}

		Classe.setCompteur(max);
	}

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

	public Collection<Matiere> getListeMatieres() {
		return listeMatieres;
	}

	@Override
	public boolean contains(Classe c) {

		for (Classe cl : this.dataMap.values()) {

			if (c.getMatiere().equals(cl.getMatiere()))
				return true;

		}
		return false;
	}

	public ArrayList<Salle> getListeSalles() {
		return listeSalles;
	}

}
