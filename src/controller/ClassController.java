package controller;

import javax.swing.JOptionPane;

import model.Classe;
import model.Salle;
import model.data.ClasseData;
import model.data.DataModel;

public class ClassController extends AbstractController<Classe> {

	public ClassController(DataModel<Classe> data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verifier(Classe t) {

		if (t.getInscriptions() <= 0) {
			Classe.decrementCompteur();
			JOptionPane.showMessageDialog(null, "Le nombre d'inscrits doit être un entier positif", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (data.contains(t)) {
			Classe.decrementCompteur();
			JOptionPane.showMessageDialog(null, "Cette classe existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		boolean salleDispo = false;
		for (Salle s : ((ClasseData) data).getListeSalles()) {
			if (s.getCapacite() >= t.getInscriptions() && s.getCampus() == t.getMatiere().getCampus())
				salleDispo = true;
		}

		if (!salleDispo) {
			Classe.decrementCompteur();
			JOptionPane.showMessageDialog(null, "Pas de salle suffisament grande à " + t.getMatiere().getCampus(),
					"Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;

	}

	public void supprimer(Classe c) {

	}

}
