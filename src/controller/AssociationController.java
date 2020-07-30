package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import model.Association;
import model.Enseignant;
import model.Salle;
import model.data.AssociationData;
import model.data.DataModel;
import model.enums.HeuresEnum;
import model.horraires.EmploiDuTemps;
import model.horraires.Seance;

public class AssociationController extends AbstractController<Association> {

	public AssociationController(DataModel<Association> data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verifier(Association t) {

		return true;

	}

	public boolean verifier(Association t, ArrayList<Seance> listeSeances) {

		// un prof ne peut pas avoir deux cours dans la même journée dans deux centres
		// différents
		HashMap<Association, Salle> hash = new HashMap<>();
		if (!verifierMemeJoursDeuxCentres(t.getEnseignant(), listeSeances, t)) {
			JOptionPane.showMessageDialog(null,
					"Le même prof ne peut pas avoir deux cours la même journée dans deux centres différents", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!verifierProfLibre(listeSeances, t)) {
			return false;
		}

		// verification que la classe n'a pas déjà un enseignant
		if (!verifierClasseSansEnseignant(t)) {
			return false;
		}

		// verification qu'il existe une salle disponible
		if (!verifierSalleLibre(listeSeances, t))
			return false;
		// si on sort de la boucle, ça veut dire qu'il existe des salles disponibles aux
		// heures demandées

		ArrayList<Salle> sallesSuffisantes = ((AssociationData) data)
				.getListeSallesParCentre(t.getClasse().getInscriptions(), t.getClasse().getMatiere().getCampus());

		// reservation de la salle
		for (Seance demande : listeSeances) {

			for (Salle salle : sallesSuffisantes) {
				if (!EmploiDuTemps.getInstance().getSeance(demande).getSalles().contains(salle)) {
					EmploiDuTemps.getInstance().getSeance(demande).getSalles().add(salle);
					EmploiDuTemps.getInstance().getSeance(demande).getMap().put(t, salle);
					break;
				}

			}

		}

		// reservation de l'enseignant
		for (Seance demande : listeSeances) {
			if (!EmploiDuTemps.getInstance().getSeance(demande).getEnseignants().contains(t.getEnseignant())) {
				EmploiDuTemps.getInstance().getSeance(demande).getEnseignants().add(t.getEnseignant());
			}
		}

		// ajout de la seance dans l'emploi du temps;
		for (Seance demande : listeSeances) {
			EmploiDuTemps.getInstance().getSeance(demande).getAssociationCoursProf().add(t);

		}

		// si on est en train d'ajouter une seance à une association existante, on
		// modifie l'emploie du temps
		// mais on n'ajoute pas une nouvelle
		for (Association s : data.getValues()) {
			if (s.getClasse().equals(t.getClasse()) && s.getEnseignant().equals(t.getEnseignant())) {
				EmploiDuTemps.ecrire();
				data.write();
				return false;
			}
		}

		EmploiDuTemps.ecrire();

		return true;

	}

	public void add(Association t, ArrayList<Seance> listeSeances) {

		if (t == null || t.getEnseignant() == null || t.getClasse() == null) {
			JOptionPane.showMessageDialog(null, "Aucune valeur sélectionnée", "Erreur", JOptionPane.ERROR_MESSAGE);
			Association.decrementCompteur();
			return;
		}

		/*
		 * for (Seance s : EmploiDuTemps.getInstance().getSeance()) {
		 * System.out.println(s.getMap()); }
		 */

		if (verifier(t, listeSeances))
			data.add(t);

		/*
		 * for (Seance s : EmploiDuTemps.getInstance().getSeance()) {
		 * System.out.println(s.getSalles() + " - " + s.getEnseignants()); }
		 */

	}

	private boolean verifierMemeJoursDeuxCentres(Enseignant e, ArrayList<Seance> listeSeances, Association as) {
		for (Seance demande : listeSeances) {
			if (demande.getHeure() == HeuresEnum.HEURE1) {
				Seance[] verfication = { new Seance(demande.getJour(), HeuresEnum.HEURE2),
						new Seance(demande.getJour(), HeuresEnum.HEURE3) };
				for (Seance ver : verfication) {
					/*for (Enseignant ens : EmploiDuTemps.getInstance().getSeance(ver).getEnseignants()) {
						if (ens.equals(e)) {
							return false;
						}
					}*/
					for(Association asso :  EmploiDuTemps.getInstance().getSeance(ver).getAssociationCoursProf())
					{
						if(asso.getClasse().getMatiere().getCampus() != as.getClasse().getMatiere().getCampus()
								&& asso.getEnseignant().equals(e)) return false;
					}

				}
			}

			if (demande.getHeure() == HeuresEnum.HEURE2) {
				Seance[] verfication = { new Seance(demande.getJour(), HeuresEnum.HEURE1),
						new Seance(demande.getJour(), HeuresEnum.HEURE3) };
				for (Seance ver : verfication) {
					/*for (Enseignant ens : EmploiDuTemps.getInstance().getSeance(ver).getEnseignants()) {
						if (ens.equals(e)) {
							return false;
						}
					}*/
					
					for(Association asso :  EmploiDuTemps.getInstance().getSeance(ver).getAssociationCoursProf())
					{
						if(asso.getClasse().getMatiere().getCampus() != as.getClasse().getMatiere().getCampus()
								&& asso.getEnseignant().equals(e)) return false;
					}

				}
			}
			if (demande.getHeure() == HeuresEnum.HEURE3) {
				Seance[] verfication = { new Seance(demande.getJour(), HeuresEnum.HEURE1),
						new Seance(demande.getJour(), HeuresEnum.HEURE2) };
				for (Seance ver : verfication) {
					/*for (Enseignant ens : EmploiDuTemps.getInstance().getSeance(ver).getEnseignants()) {
						if (ens.equals(e)) {
							return false;
						}
					}*/
					
					for(Association asso :  EmploiDuTemps.getInstance().getSeance(ver).getAssociationCoursProf())
					{
						if(asso.getClasse().getMatiere().getCampus() != as.getClasse().getMatiere().getCampus()
								&& asso.getEnseignant().equals(e)) return false;
					}

				}
			}
		}

		return true;
	}

	private boolean verifierProfLibre(ArrayList<Seance> listeSeances, Association t) {

		for (Seance demande : listeSeances) {

			if (EmploiDuTemps.getInstance().getSeance(demande).getEnseignants().contains(t.getEnseignant())) {
				JOptionPane.showMessageDialog(null, "Enseignant occupé" + demande.toString(), "Erreur",
						JOptionPane.ERROR_MESSAGE);
				return false;

			} // Vérifié que l'enseignant est libre;
				// si on sort de la boucle, ça veut dire que l'enseignant est disponilbe aux
				// heures demandées
		}
		return true;

	}

	private boolean verifierClasseSansEnseignant(Association t) {
		for (Association as : data.getValues()) {
			if (t.getClasse().equals(as.getClasse()) && !t.getEnseignant().equals(as.getEnseignant())) {
				JOptionPane.showMessageDialog(null, "Cette classe est déjà allouée à un autre enseignant", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				Association.decrementCompteur();

				return false;

			}

		}
		return true;

	}

	private boolean verifierSalleLibre(ArrayList<Seance> listeSeances, Association t) {
		ArrayList<Salle> sallesSuffisantes = ((AssociationData) data)
				.getListeSallesParCentre(t.getClasse().getInscriptions(), t.getClasse().getMatiere().getCampus());
		for (Seance demande : listeSeances) {
			int sallesParcoureues = 0;
			for (Salle salle : sallesSuffisantes) {
				if (EmploiDuTemps.getInstance().getSeance(demande).getSalles().contains(salle))
					sallesParcoureues++;
				else {
					sallesParcoureues--;

				}

			}
			if (sallesParcoureues == sallesSuffisantes.size()) {
				JOptionPane.showMessageDialog(null, "Pas de salle disponible " + demande);
				Association.decrementCompteur();
				return false;
			}

		}

		return true;
	}

	public void supprimer(Association a) {

	}

}
