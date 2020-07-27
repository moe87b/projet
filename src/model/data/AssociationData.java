package model.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import model.Association;
import model.Salle;
import model.comparator.SalleComparator;
import model.enums.Campus;
import model.horraires.EmploiDuTemps;
import model.horraires.Seance;

/**
 * Classe contenant une collection des objets association créés
 * 
 *
 */
public class AssociationData extends DataModel<Association> implements Observer {
	/**
	 * Une liste des salles disponibles.
	 */
	private ArrayList<Salle> listeSalles = new ArrayList<Salle>();

	/**
	 * Le constructeur
	 * 
	 * @param dataFile le fichier de données
	 */
	public AssociationData(File dataFile) {
		super(dataFile);
		updateCompteur();

	}

	/**
	 * Mettre à jour le compteur après la lecture des fichiers
	 */
	private void updateCompteur() {
		int max = 0;
		for (Integer i : dataMap.keySet()) {
			if (i > max)
				max = i;

		}

		Association.updateCompteur(max);
	}

	/**
	 * Mise à jour selon les objets observés
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg0 instanceof SalleData) {
			SalleData sd = (SalleData) arg0;
			listeSalles.clear();
			for (Salle s : sd.getValues()) {
				listeSalles.add(s);
			}
		}

		if (arg1 instanceof Salle) {
			Salle s = (Salle) arg1;
			for (Seance seance : EmploiDuTemps.getInstance().getSeance()) {
				for (Entry entry : seance.getMap().entrySet()) {
					if (entry.getValue().equals(s)) {
						Association a = (Association) entry.getKey();
						dataMap.remove(a.getId());
					}
				}
			}
			write();
		}

		Collections.sort(listeSalles, new SalleComparator());

	}

	public ArrayList<Salle> getListeSalles() {
		return listeSalles;
	}

	/**
	 * 
	 * @param minimumSize capacité des salles
	 * @param c           le campus concerné
	 * @return une liste des salles du campus qui ont la capacité demandée
	 */
	public ArrayList<Salle> getListeSallesParCentre(int minimumSize, Campus c) {
		ArrayList<Salle> listeReduite = new ArrayList<Salle>();
		for (Salle s : listeSalles) {
			if (s.getCapacite() >= minimumSize && s.getCampus().equals(c))
				listeReduite.add(s);
		}

		return listeReduite;

	}

	@Override
	public void add(Association a) {
		super.add(a);

	}

}
