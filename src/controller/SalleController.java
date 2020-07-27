package controller;

import javax.swing.JOptionPane;

import model.Salle;
import model.data.SalleData;

public class SalleController extends AbstractController<Salle> {

	public SalleController(SalleData data) {
		super(data);

	}

	@Override
	public boolean verifier(Salle t) {

		String nomRegexp = "^[a-zA-Z]{1}\\d{3}$";

		if (!t.getNom().matches(nomRegexp)) {
			JOptionPane.showMessageDialog(null, "Le nom doit êter composé d'un lettre suivies de 3 chiffres",
					"Nom invalide", JOptionPane.ERROR_MESSAGE);
			Salle.decrementCompteur();
			return false;
		}

		if (t.getCapacite() <= 0) {
			JOptionPane.showMessageDialog(null, "La capacite doit être un entier positif", "Nom invalide",
					JOptionPane.ERROR_MESSAGE);
			Salle.decrementCompteur();
			return false;
		}

		for (Salle s : this.data.getValues()) {
			if (s.getNom().equalsIgnoreCase(t.getNom()) && s.getCampus() == t.getCampus()) {
				JOptionPane.showMessageDialog(null, "Le nom de la salle doit être unique dans le centre",
						"Nom dupliqué", JOptionPane.ERROR_MESSAGE);
				Salle.decrementCompteur();
				return false;

			}
			if (s.getNom().equalsIgnoreCase(t.getNom()) && s.getCampus() != t.getCampus()) {
				int reponse = JOptionPane.showConfirmDialog(null,
						"Ce nom de salle est déjà utilisé dans d'autres centres, et" + " peut prêter"
								+ " à confusion. Ajouter quand-même ?",
						"Attention", JOptionPane.YES_NO_OPTION);
				if (reponse == JOptionPane.NO_OPTION)
					return false;
				else
					return true;

			}
		}

		return true;
	}

}
