package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AssociationController;
import model.Association;
import model.Classe;
import model.Enseignant;
import model.data.ClasseData;
import model.data.EnseignantData;
import model.enums.HeuresEnum;
import model.enums.JoursEnum;
import model.horraires.Seance;
import view.customComponents.EmploiDuTempsPanel;
import view.jours.HorrairesPanel;

public class AssociationPanel extends AbstractView<Association> {

	private JLabel classeLabel = new JLabel("Classe");
	private JLabel enseignantLabel = new JLabel("Enseignant");
	AssociationController controller;

	private JComboBox<Classe> classeCombo = new JComboBox<Classe>();
	private JComboBox<Enseignant> enseignantCombo = new JComboBox<Enseignant>();
	private HorrairesPanel lundi = new HorrairesPanel("Lundi");
	private HorrairesPanel mardi = new HorrairesPanel("mardi");
	private HorrairesPanel mercredi = new HorrairesPanel("mercredi");
	private HorrairesPanel jeudi = new HorrairesPanel("jeudi");
	private HorrairesPanel vendredi = new HorrairesPanel("vendredi");
	private HorrairesPanel samedi = new HorrairesPanel("samedi");

	private EmploiDuTempsPanel emploiDuTempsPanel = new EmploiDuTempsPanel();
	private AllocationsPanel allocationsPanel = new AllocationsPanel();

	public AssociationPanel() {

		JPanel left = new JPanel();
		JPanel top = new JPanel();
		top.add(classeLabel);
		classeCombo.setPreferredSize(new Dimension(180, 30));
		top.add(classeCombo);
		enseignantCombo.setPreferredSize(new Dimension(180, 30));
		top.add(enseignantLabel);
		top.add(enseignantCombo);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0, 1));
		center.add(lundi);
		center.add(mardi);
		center.add(mercredi);
		center.add(jeudi);
		center.add(vendredi);
		center.add(samedi);

		JPanel south = new JPanel();

		left.setLayout(new BorderLayout());

		left.add(center, BorderLayout.CENTER);
		left.add(south, BorderLayout.SOUTH);
		left.setBorder(BorderFactory.createTitledBorder("Choix des horraires"));

		/*
		 * JPanel droite = new JPanel(); droite.add(new JLabel("Hello world"));
		 * mainContainer.setLayout(new BorderLayout()); mainContainer.add(droite,
		 * BorderLayout.EAST);
		 */
		JPanel centre = new JPanel();
		centre.setLayout(new GridBagLayout());
		GridBagConstraints centregbc = new GridBagConstraints();
		centregbc.gridx = 0;
		centregbc.gridy = 0;
		centre.add(allocationsPanel, centregbc);
		centregbc.gridy++;
		centre.add(ajouter, centregbc);
		ajouter.setPreferredSize(new Dimension(400, 50));

		mainContainer.add(top, BorderLayout.NORTH);
		mainContainer.add(left, BorderLayout.WEST);
		mainContainer.add(emploiDuTempsPanel, BorderLayout.EAST);
		mainContainer.add(centre, BorderLayout.CENTER);

		addListeners();

	}

	public void addListeners() {
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Classe c = (Classe) classeCombo.getSelectedItem();
				Enseignant en = (Enseignant) enseignantCombo.getSelectedItem();

				ArrayList<Seance> listeSeance = new ArrayList<Seance>();

				if (lundi.isDaySelected()) {

					if (lundi.isHeure1Selected())
						listeSeance.add(new Seance(JoursEnum.LUNDI, HeuresEnum.HEURE1));
					if (lundi.isHeure2Selected())
						listeSeance.add(new Seance(JoursEnum.LUNDI, HeuresEnum.HEURE2));
					if (lundi.isHeure3Selected())
						listeSeance.add(new Seance(JoursEnum.LUNDI, HeuresEnum.HEURE3));
				}
				if (mardi.isDaySelected()) {
					if (mardi.isHeure1Selected())
						listeSeance.add(new Seance(JoursEnum.MARDI, HeuresEnum.HEURE1));
					if (mardi.isHeure2Selected())
						listeSeance.add(new Seance(JoursEnum.MARDI, HeuresEnum.HEURE2));
					if (mardi.isHeure3Selected())
						listeSeance.add(new Seance(JoursEnum.MARDI, HeuresEnum.HEURE3));
				}
				if (mercredi.isDaySelected()) {
					if (mercredi.isHeure1Selected())
						listeSeance.add(new Seance(JoursEnum.MERCREDI, HeuresEnum.HEURE1));
					if (mercredi.isHeure2Selected())
						listeSeance.add(new Seance(JoursEnum.MERCREDI, HeuresEnum.HEURE2));
					if (mercredi.isHeure3Selected())
						listeSeance.add(new Seance(JoursEnum.MERCREDI, HeuresEnum.HEURE3));
				}
				if (jeudi.isDaySelected()) {
					if (jeudi.isHeure1Selected())
						listeSeance.add(new Seance(JoursEnum.JEUDI, HeuresEnum.HEURE1));
					if (jeudi.isHeure2Selected())
						listeSeance.add(new Seance(JoursEnum.JEUDI, HeuresEnum.HEURE2));
					if (jeudi.isHeure3Selected())
						listeSeance.add(new Seance(JoursEnum.JEUDI, HeuresEnum.HEURE3));
				}
				if (vendredi.isDaySelected()) {
					if (vendredi.isHeure1Selected())
						listeSeance.add(new Seance(JoursEnum.VENDREDI, HeuresEnum.HEURE1));
					if (vendredi.isHeure2Selected())
						listeSeance.add(new Seance(JoursEnum.VENDREDI, HeuresEnum.HEURE2));
					if (vendredi.isHeure3Selected())
						listeSeance.add(new Seance(JoursEnum.VENDREDI, HeuresEnum.HEURE3));
				}
				if (samedi.isDaySelected()) {
					if (samedi.isHeure1Selected())
						listeSeance.add(new Seance(JoursEnum.SAMEDI, HeuresEnum.HEURE1));
					if (samedi.isHeure2Selected())
						listeSeance.add(new Seance(JoursEnum.SAMEDI, HeuresEnum.HEURE2));
					if (samedi.isHeure3Selected())
						listeSeance.add(new Seance(JoursEnum.SAMEDI, HeuresEnum.HEURE3));
				}

				lundi.reset();
				mardi.reset();
				mercredi.reset();
				jeudi.reset();
				vendredi.reset();
				samedi.reset();
				int index = enseignantCombo.getSelectedIndex();

				Association a = new Association(en, c);
				controller.add(a, listeSeance);
				enseignantCombo.setSelectedIndex(index);
			}
		});

		enseignantCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				afficherHorrairesEnseignant();
			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof ClasseData) {
			classeCombo.removeAllItems();
			ClasseData cd = (ClasseData) arg0;
			for (Classe cls : cd.getValues()) {
				classeCombo.addItem(cls);
			}
		}

		if (arg0 instanceof EnseignantData) {
			enseignantCombo.removeAllItems();
			EnseignantData ed = (EnseignantData) arg0;
			for (Enseignant e : ed.getValues()) {
				enseignantCombo.addItem(e);
			}
		}
		allocationsPanel.reset();
		for (Association s : controller.getValues()) {
			allocationsPanel.addRow(new String[] { s.getClasse().toString(), s.getEnseignant().toString() });
		}

		Enseignant e = (Enseignant) enseignantCombo.getSelectedItem();
		emploiDuTempsPanel.setEdtps(e);

	}

	public void afficherHorrairesEnseignant() {

		Enseignant e = (Enseignant) enseignantCombo.getSelectedItem();
		emploiDuTempsPanel.setEdtps(e);
	}

	@Override
	public void resetFields() {
		// TODO Auto-generated method stub

	}

	public void setController(AssociationController controller) {
		// TODO Auto-generated method stub
		this.controller = controller;
	}

	public AssociationController getController() {
		return controller;
	}
}
