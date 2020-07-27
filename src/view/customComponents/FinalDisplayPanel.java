package view.customComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Association;
import model.Salle;
import model.enums.Campus;
import model.enums.HeuresEnum;
import model.enums.JoursEnum;
import model.horraires.EmploiDuTemps;
import model.horraires.Seance;
import view.jours.SeancePanel;

public class FinalDisplayPanel extends JPanel implements Observer {

	private JPanel center = new JPanel();
	private JPanel top = new JPanel();
	private MLabel campusLabel = new MLabel("Campus: ");
	private JComboBox<Campus> campusComboBox = new JComboBox<>();
	private MLabel lundiLabel = new MLabel("Lundi");
	private MLabel mardiLabel = new MLabel("mardi");
	private MLabel mercrediLabel = new MLabel("Mercredi");
	private MLabel jeudiLabel = new MLabel("Jeudi");
	private MLabel vendrediLabel = new MLabel("Vendredi");
	private MLabel samediLabel = new MLabel("Samedi");

	private MLabel heure1Label = new MLabel(HeuresEnum.HEURE1.toString());
	private MLabel heure2Label = new MLabel(HeuresEnum.HEURE2.toString());
	private MLabel heure3Label = new MLabel(HeuresEnum.HEURE3.toString());

	private SeancePanel lundih1 = new SeancePanel();
	private SeancePanel lundih2 = new SeancePanel();
	private SeancePanel lundih3 = new SeancePanel();

	private SeancePanel mardih1 = new SeancePanel();
	private SeancePanel mardih2 = new SeancePanel();
	private SeancePanel mardih3 = new SeancePanel();

	private SeancePanel mercredih1 = new SeancePanel();
	private SeancePanel mercredih2 = new SeancePanel();
	private SeancePanel mercredih3 = new SeancePanel();

	private SeancePanel jeudih1 = new SeancePanel();
	private SeancePanel jeudih2 = new SeancePanel();
	private SeancePanel jeudih3 = new SeancePanel();

	private SeancePanel vendredih1 = new SeancePanel();
	private SeancePanel vendredih2 = new SeancePanel();
	private SeancePanel vendredih3 = new SeancePanel();

	private SeancePanel samedih1 = new SeancePanel();
	private SeancePanel samedih2 = new SeancePanel();
	private SeancePanel samedih3 = new SeancePanel();

	public FinalDisplayPanel() {

		for (Campus C : Campus.values()) {
			campusComboBox.addItem(C);
		}

		setLayout(new BorderLayout());
		top.add(campusLabel);
		campusComboBox.setPreferredSize(new Dimension(180, 30));
		top.add(campusComboBox);
		add(top, BorderLayout.NORTH);

		center.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, -200, 0, -200);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 2;
		gbc.weighty = 2;
		center.add(new MLabel("               "), gbc);
		gbc.gridx++;
		center.add(lundiLabel, gbc);
		gbc.gridx++;
		center.add(mardiLabel, gbc);
		gbc.gridx++;
		center.add(mercrediLabel, gbc);
		gbc.gridx++;
		center.add(jeudiLabel, gbc);
		gbc.gridx++;
		center.add(vendrediLabel, gbc);
		gbc.gridx++;
		center.add(samediLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy++;

		center.add(heure1Label, gbc);
		gbc.gridx++;
		center.add(lundih1, gbc);
		gbc.gridx++;
		center.add(mardih1, gbc);
		gbc.gridx++;
		center.add(mercredih1, gbc);
		gbc.gridx++;
		center.add(jeudih1, gbc);
		gbc.gridx++;
		center.add(vendredih1, gbc);
		gbc.gridx++;
		center.add(samedih1, gbc);

		gbc.gridx = 0;
		gbc.gridy++;

		center.add(heure2Label, gbc);
		gbc.gridx++;
		center.add(lundih2, gbc);
		gbc.gridx++;
		center.add(mardih2, gbc);
		gbc.gridx++;
		center.add(mercredih2, gbc);
		gbc.gridx++;
		center.add(jeudih2, gbc);
		gbc.gridx++;
		center.add(vendredih2, gbc);
		gbc.gridx++;
		center.add(samedih2, gbc);

		gbc.gridx = 0;
		gbc.gridy++;

		center.add(heure3Label, gbc);
		gbc.gridx++;
		center.add(lundih3, gbc);
		gbc.gridx++;
		center.add(mardih3, gbc);
		gbc.gridx++;
		center.add(mercredih3, gbc);
		gbc.gridx++;
		center.add(jeudih3, gbc);
		gbc.gridx++;
		center.add(vendredih3, gbc);
		gbc.gridx++;
		center.add(samedih3, gbc);

		add(center, BorderLayout.CENTER);

		campusComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateAffichage();
			}
		});

		/*
		 * GridLayout gridLayout = new GridLayout(4, 7); gridLayout.setHgap(5);
		 * gridLayout.setVgap(5); setLayout(gridLayout); add(new
		 * MLabel("               "));
		 * 
		 * add(lundiLabel);
		 * 
		 * add(mardiLabel);
		 * 
		 * add(mercrediLabel);
		 * 
		 * add(jeudiLabel);
		 * 
		 * add(vendrediLabel);
		 * 
		 * add(samediLabel);
		 * 
		 * add(heure1Label);
		 * 
		 * add(lundih1);
		 * 
		 * add(mardih1);
		 * 
		 * add(mercredih1);
		 * 
		 * add(jeudih1);
		 * 
		 * add(vendredih1);
		 * 
		 * add(samedih1);
		 * 
		 * add(heure2Label);
		 * 
		 * add(lundih2);
		 * 
		 * add(mardih2);
		 * 
		 * add(mercredih2);
		 * 
		 * add(jeudih2);
		 * 
		 * add(vendredih2);
		 * 
		 * add(samedih2);
		 * 
		 * add(heure3Label);
		 * 
		 * add(lundih3);
		 * 
		 * add(mardih3);
		 * 
		 * add(mercredih3);
		 * 
		 * add(jeudih3);
		 * 
		 * add(vendredih3);
		 * 
		 * add(samedih3);
		 */

	}

	@Override
	public void update(Observable arg0, Object arg1) {

		updateAffichage();

	}

	public void updateAffichage() {
		resetAll();
		Campus c = (Campus) campusComboBox.getSelectedItem();
		for (Seance s : EmploiDuTemps.getInstance().getSeance()) {
			for (Entry e : s.getMap().entrySet()) {
				Association a = (Association) e.getKey();
				if (a.getClasse().getMatiere().getCampus() == c) {
					if (s.getJour() == JoursEnum.LUNDI) {
						if (s.getHeure() == HeuresEnum.HEURE1)
							lundih1.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE2)
							lundih2.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE3)
							lundih3.addSeance(a, (Salle) e.getValue());
					}

					if (s.getJour() == JoursEnum.MARDI) {
						if (s.getHeure() == HeuresEnum.HEURE1)
							mardih1.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE2)
							mardih2.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE3)
							mardih3.addSeance(a, (Salle) e.getValue());
					}
					if (s.getJour() == JoursEnum.MERCREDI) {
						if (s.getHeure() == HeuresEnum.HEURE1)
							mercredih1.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE2)
							mercredih2.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE3)
							mercredih3.addSeance(a, (Salle) e.getValue());
					}
					if (s.getJour() == JoursEnum.JEUDI) {
						if (s.getHeure() == HeuresEnum.HEURE1)
							jeudih1.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE2)
							jeudih2.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE3)
							jeudih3.addSeance(a, (Salle) e.getValue());
					}
					if (s.getJour() == JoursEnum.VENDREDI) {
						if (s.getHeure() == HeuresEnum.HEURE1)
							vendredih1.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE2)
							vendredih2.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE3)
							vendredih3.addSeance(a, (Salle) e.getValue());
					}
					if (s.getJour() == JoursEnum.SAMEDI) {
						if (s.getHeure() == HeuresEnum.HEURE1)
							samedih1.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE2)
							samedih2.addSeance(a, (Salle) e.getValue());
						if (s.getHeure() == HeuresEnum.HEURE3)
							samedih3.addSeance(a, (Salle) e.getValue());
					}

				}
			}
		}

	}

	private void resetAll() {
		lundih1.reset();
		lundih2.reset();
		lundih3.reset();

		mardih1.reset();
		mardih2.reset();
		mardih3.reset();

		mercredih1.reset();
		mercredih2.reset();
		mercredih3.reset();

		jeudih1.reset();
		jeudih2.reset();
		jeudih3.reset();

		vendredih1.reset();
		vendredih2.reset();
		vendredih3.reset();

		samedih1.reset();
		samedih2.reset();
		samedih3.reset();

	}

}