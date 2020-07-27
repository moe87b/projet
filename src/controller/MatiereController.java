package controller;

import javax.swing.JOptionPane;

import model.Matiere;
import model.data.DataModel;

public class MatiereController extends AbstractController<Matiere> {

	public MatiereController(DataModel<Matiere> data) {
		super(data);
	}

	@Override
	public boolean verifier(Matiere t) {

		String nomRegexp = "^[a-zA-Z]{3}\\d{3}$";

		if (!t.getCode().matches(nomRegexp)) {
			JOptionPane.showMessageDialog(null, "Le nom doit être composé de 3 lettres suivies de 3 chiffres",
					"Nom invalide", JOptionPane.ERROR_MESSAGE);
			Matiere.decrementCompteur();
			return false;
		}

		for (Matiere m : this.data.getValues()) {
			if (m.getCode().equalsIgnoreCase(t.getCode()) && m.getCampus().equals(t.getCampus())) {
				JOptionPane.showMessageDialog(null, "Le nom de la matière doit être unique dans le centre",
						"Nom dupliqué", JOptionPane.ERROR_MESSAGE);
				Matiere.decrementCompteur();
				return false;

			}
		}

		return true;

	}

}
