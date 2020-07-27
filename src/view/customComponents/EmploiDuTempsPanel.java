package view.customComponents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Enseignant;
import model.enums.HeuresEnum;
import model.horraires.EmploiDuTemps;
import model.horraires.Seance;

public class EmploiDuTempsPanel extends JPanel {

	private MLabel lundiLabel = new MLabel("Lundi");
	private MLabel mardiLabel = new MLabel("Mardi");
	private MLabel mercrediLabel = new MLabel("Mercredi");
	private MLabel jeudiLabel = new MLabel("Jeudi");
	private MLabel vendrediLabel = new MLabel("Vendredi");
	private MLabel samediLabel = new MLabel("Samedi");

	private MLabel lundiH1Label = new MLabel("16h à 17h30");
	private MLabel lundiH2Label = new MLabel("17h45 à 19h15");
	private MLabel lundiH3Label = new MLabel("19h30 à 21h");

	private MLabel mardiH1Label = new MLabel("16h à 17h30");
	private MLabel mardiH2Label = new MLabel("17h45 à 19h15");
	private MLabel mardiH3Label = new MLabel("19h30 à 21h");

	private MLabel mercrediH1Label = new MLabel("16h à 17h30");
	private MLabel mercrediH2Label = new MLabel("17h45 à 19h15");
	private MLabel mercrediH3Label = new MLabel("19h30 à 21h");

	private MLabel jeudiH1Label = new MLabel("16h à 17h30");
	private MLabel jeudiH2Label = new MLabel("17h45 à 19h15");
	private MLabel jeudiH3Label = new MLabel("19h30 à 21h");

	private MLabel vendrediH1Label = new MLabel("16h à 17h30");
	private MLabel vendrediH2Label = new MLabel("17h45 à 19h15");
	private MLabel vendrediH3Label = new MLabel("19h30 à 21h");

	private MLabel samediH1Label = new MLabel("16h à 17h30");
	private MLabel samediH2Label = new MLabel("17h45 à 19h15");
	private MLabel samediH3Label = new MLabel("19h30 à 21h");

	/* valeurs */
	private ValueLabel lundiH1Value = new ValueLabel("libre");
	private ValueLabel lundiH2Value = new ValueLabel("libre");
	private ValueLabel lundiH3Value = new ValueLabel("libre");

	private ValueLabel mardiH1Value = new ValueLabel("libre");
	private ValueLabel mardiH2Value = new ValueLabel("libre");
	private ValueLabel mardiH3Value = new ValueLabel("libre");

	private ValueLabel mercrediH1Value = new ValueLabel("libre");
	private ValueLabel mercrediH2Value = new ValueLabel("libre");
	private ValueLabel mercrediH3Value = new ValueLabel("libre");

	private ValueLabel jeudiH1Value = new ValueLabel("libre");
	private ValueLabel jeudiH2Value = new ValueLabel("libre");
	private ValueLabel jeudiH3Value = new ValueLabel("libre");

	private ValueLabel vendrediH1Value = new ValueLabel("libre");
	private ValueLabel vendrediH2Value = new ValueLabel("libre");
	private ValueLabel vendrediH3Value = new ValueLabel("libre");

	private ValueLabel samediH1Value = new ValueLabel("libre");
	private ValueLabel samediH2Value = new ValueLabel("libre");
	private ValueLabel samediH3Value = new ValueLabel("libre");

	public EmploiDuTempsPanel() {

		this.setLayout(new GridLayout(0, 1));

		// LUNDI
		JPanel lundiPanel = new JPanel();
		lundiPanel.setLayout(new GridBagLayout());
		GridBagConstraints lgbc = new GridBagConstraints();
		lgbc.gridx = 0;
		lgbc.gridy = 0;
		lundiPanel.add(lundiLabel);
		lgbc.insets = new Insets(0, 0, 0, 20);
		lgbc.gridx++;
		lgbc.gridy++;

		lundiPanel.add(lundiH1Label, lgbc);
		lgbc.gridx++;
		lundiPanel.add(lundiH2Label, lgbc);
		lgbc.gridx++;
		lundiPanel.add(lundiH3Label, lgbc);
		lgbc.gridx++;

		lgbc.gridy++;
		lgbc.gridx = 1;
		lundiPanel.add(lundiH1Value, lgbc);
		lgbc.gridx++;
		lundiPanel.add(lundiH2Value, lgbc);
		lgbc.gridx++;
		lundiPanel.add(lundiH3Value, lgbc);
		lgbc.gridx++;
		lundiPanel.setBorder(BorderFactory.createEtchedBorder());
		add(lundiPanel);

		// MARDI
		JPanel mardiPanel = new JPanel();
		mardiPanel.setLayout(new GridBagLayout());
		GridBagConstraints magbc = new GridBagConstraints();
		magbc.gridx = 0;
		magbc.gridy = 0;
		mardiPanel.add(mardiLabel);
		magbc.insets = new Insets(0, 0, 0, 20);
		magbc.gridx++;
		magbc.gridy++;

		mardiPanel.add(mardiH1Label, magbc);
		magbc.gridx++;
		mardiPanel.add(mardiH2Label, magbc);
		magbc.gridx++;
		mardiPanel.add(mardiH3Label, magbc);
		magbc.gridx++;

		magbc.gridy++;
		magbc.gridx = 1;
		mardiPanel.add(mardiH1Value, magbc);
		magbc.gridx++;
		mardiPanel.add(mardiH2Value, magbc);
		magbc.gridx++;
		mardiPanel.add(mardiH3Value, magbc);
		magbc.gridx++;
		mardiPanel.setBorder(BorderFactory.createEtchedBorder());
		add(mardiPanel);

		// MERCREDI
		JPanel mercrediPanel = new JPanel();
		mercrediPanel.setLayout(new GridBagLayout());
		GridBagConstraints megbc = new GridBagConstraints();
		megbc.gridx = 0;
		megbc.gridy = 0;
		mercrediPanel.add(mercrediLabel);
		megbc.insets = new Insets(0, 0, 0, 20);
		megbc.gridx++;
		megbc.gridy++;

		mercrediPanel.add(mercrediH1Label, megbc);
		megbc.gridx++;
		mercrediPanel.add(mercrediH2Label, megbc);
		megbc.gridx++;
		mercrediPanel.add(mercrediH3Label, megbc);
		megbc.gridx++;

		megbc.gridy++;
		megbc.gridx = 1;
		mercrediPanel.add(mercrediH1Value, megbc);
		megbc.gridx++;
		mercrediPanel.add(mercrediH2Value, megbc);
		megbc.gridx++;
		mercrediPanel.add(mercrediH3Value, megbc);
		megbc.gridx++;
		mercrediPanel.setBorder(BorderFactory.createEtchedBorder());
		add(mercrediPanel);

		// JEUDI
		JPanel jeudiPanel = new JPanel();
		jeudiPanel.setLayout(new GridBagLayout());
		GridBagConstraints jgbc = new GridBagConstraints();
		jgbc.gridx = 0;
		jgbc.gridy = 0;
		jeudiPanel.add(jeudiLabel);
		jgbc.insets = new Insets(0, 0, 0, 20);
		jgbc.gridx++;
		jgbc.gridy++;

		jeudiPanel.add(jeudiH1Label, jgbc);
		jgbc.gridx++;
		jeudiPanel.add(jeudiH2Label, jgbc);
		jgbc.gridx++;
		jeudiPanel.add(jeudiH3Label, jgbc);
		jgbc.gridx++;

		jgbc.gridy++;
		jgbc.gridx = 1;
		jeudiPanel.add(jeudiH1Value, jgbc);
		jgbc.gridx++;
		jeudiPanel.add(jeudiH2Value, jgbc);
		jgbc.gridx++;
		jeudiPanel.add(jeudiH3Value, jgbc);
		jgbc.gridx++;
		jeudiPanel.setBorder(BorderFactory.createEtchedBorder());
		add(jeudiPanel);

		// VENDREDI
		JPanel vendrediPanel = new JPanel();
		vendrediPanel.setLayout(new GridBagLayout());
		GridBagConstraints vgbc = new GridBagConstraints();
		vgbc.gridx = 0;
		vgbc.gridy = 0;
		vendrediPanel.add(vendrediLabel);
		vgbc.insets = new Insets(0, 0, 0, 20);
		vgbc.gridx++;
		vgbc.gridy++;

		vendrediPanel.add(vendrediH1Label, vgbc);
		vgbc.gridx++;
		vendrediPanel.add(vendrediH2Label, vgbc);
		vgbc.gridx++;
		vendrediPanel.add(vendrediH3Label, vgbc);
		vgbc.gridx++;

		vgbc.gridy++;
		vgbc.gridx = 1;
		vendrediPanel.add(vendrediH1Value, vgbc);
		vgbc.gridx++;
		vendrediPanel.add(vendrediH2Value, vgbc);
		vgbc.gridx++;
		vendrediPanel.add(vendrediH3Value, vgbc);
		vgbc.gridx++;
		vendrediPanel.setBorder(BorderFactory.createEtchedBorder());
		add(vendrediPanel);

		// SAMEDI
		JPanel samediPanel = new JPanel();
		samediPanel.setLayout(new GridBagLayout());
		GridBagConstraints sgbc = new GridBagConstraints();
		sgbc.gridx = 0;
		sgbc.gridy = 0;
		samediPanel.add(samediLabel);
		sgbc.insets = new Insets(0, 0, 0, 20);
		sgbc.gridx++;
		sgbc.gridy++;

		samediPanel.add(samediH1Label, sgbc);
		sgbc.gridx++;
		samediPanel.add(samediH2Label, sgbc);
		sgbc.gridx++;
		samediPanel.add(samediH3Label, sgbc);
		sgbc.gridx++;

		sgbc.gridy++;
		sgbc.gridx = 1;
		samediPanel.add(samediH1Value, sgbc);
		sgbc.gridx++;
		samediPanel.add(samediH2Value, sgbc);
		sgbc.gridx++;
		samediPanel.add(samediH3Value, sgbc);
		sgbc.gridx++;
		samediPanel.setBorder(BorderFactory.createEtchedBorder());
		add(samediPanel);

		setBorder(BorderFactory.createTitledBorder("Emploi du temps de l'enseignant"));

	}

	public void setEdtps(Enseignant e) {

		for (Seance s : EmploiDuTemps.getInstance().getSeance()) {

			if (s.getEnseignants().contains(e)) {
				if (s.getJour().getNom().equalsIgnoreCase("lundi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						lundiH1Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE2)
						lundiH2Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE3)
						lundiH3Value.setText("Occupé");
				}

				if (s.getJour().getNom().equalsIgnoreCase("mardi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						mardiH1Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE2)
						mardiH2Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE3)
						mardiH3Value.setText("Occupé");
				}

				if (s.getJour().getNom().equalsIgnoreCase("mercredi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						mercrediH1Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE2)
						mercrediH2Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE3)
						mercrediH3Value.setText("Occupé");
				}

				if (s.getJour().getNom().equalsIgnoreCase("jeudi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						jeudiH1Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE2)
						jeudiH2Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE3)
						jeudiH3Value.setText("Occupé");
				}
				if (s.getJour().getNom().equalsIgnoreCase("vendredi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						vendrediH1Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE2)
						vendrediH2Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE3)
						vendrediH3Value.setText("Occupé");
				}
				if (s.getJour().getNom().equalsIgnoreCase("samedi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						samediH1Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE2)
						samediH2Value.setText("Occupé");
					if (s.getHeure() == HeuresEnum.HEURE3)
						samediH3Value.setText("Occupé");
				}

			} else {

				if (s.getJour().getNom().equalsIgnoreCase("lundi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						lundiH1Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE2)
						lundiH2Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE3)
						lundiH3Value.setText("Libre");
				}

				if (s.getJour().getNom().equalsIgnoreCase("mardi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						mardiH1Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE2)
						mardiH2Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE3)
						mardiH3Value.setText("Libre");
				}

				if (s.getJour().getNom().equalsIgnoreCase("mercredi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						mercrediH1Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE2)
						mercrediH2Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE3)
						mercrediH3Value.setText("Libre");
				}

				if (s.getJour().getNom().equalsIgnoreCase("jeudi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						jeudiH1Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE2)
						jeudiH2Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE3)
						jeudiH3Value.setText("Libre");
				}
				if (s.getJour().getNom().equalsIgnoreCase("vendredi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						vendrediH1Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE2)
						vendrediH2Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE3)
						vendrediH3Value.setText("Libre");
				}
				if (s.getJour().getNom().equalsIgnoreCase("samedi")) {
					if (s.getHeure() == HeuresEnum.HEURE1)
						samediH1Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE2)
						samediH2Value.setText("Libre");
					if (s.getHeure() == HeuresEnum.HEURE3)
						samediH3Value.setText("Libre");

				}

			}
		}

	}

	public void reset() {
		lundiH1Value.setText("Libre");
		lundiH2Value.setText("Libre");
		lundiH3Value.setText("Libre");

		mardiH1Value.setText("Libre");
		mardiH2Value.setText("Libre");
		mardiH3Value.setText("Libre");

		mercrediH1Value.setText("Libre");
		mercrediH2Value.setText("Libre");
		mercrediH3Value.setText("Libre");

		jeudiH1Value.setText("Libre");
		jeudiH2Value.setText("Libre");
		jeudiH3Value.setText("Libre");

		vendrediH1Value.setText("Libre");
		vendrediH2Value.setText("Libre");
		vendrediH3Value.setText("Libre");

		samediH1Value.setText("Libre");
		samediH2Value.setText("Libre");
		samediH3Value.setText("Libre");

	}

}
