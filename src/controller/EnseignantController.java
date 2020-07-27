package controller;

import javax.swing.JOptionPane;

import model.Enseignant;
import model.data.DataModel;

public class EnseignantController extends AbstractController<Enseignant> {

	public EnseignantController(DataModel<Enseignant> data) {
		super(data);
	}

	@Override
	public boolean verifier(Enseignant t) {

		if (t.getNom().length() == 0 || t.getNom() == null) {
			JOptionPane.showMessageDialog(null, "Le nom ne peut pas être vide", "Nom invalide",
					JOptionPane.ERROR_MESSAGE);
			Enseignant.decrementCompteur();
			return false;
		}
		if (!t.getNom().matches("[a-zA-Z -]*")) {
			Enseignant.decrementCompteur();
			JOptionPane.showMessageDialog(null, "Le nom doit contenir uniquement des lettres et des espaces",
					"Nom invalide", JOptionPane.ERROR_MESSAGE);
		} else {
			data.add(t);
		}
		return false;
	}

	public void supprimer(Enseignant e) {

	}

}
