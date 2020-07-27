package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Association;
import model.Salle;
import model.data.ModificationData;
import model.data.SalleData;
import model.horraires.EmploiDuTemps;
import model.horraires.Seance;
import view.customComponents.MLabel;

public class ModificationPanel extends AbstractView<Association> {

	private MLabel classeLabel = new MLabel("Classe: ");
	private MLabel enseignantLabel = new MLabel("Enseignant: ");
	private MLabel salleLabel = new MLabel("Salle: ");
	private JComboBox<Seance> seanceComboBox = new JComboBox<Seance>();
	private MLabel classeValue = new MLabel("Aucune valeur selectionée");
	private MLabel enseignantValue = new MLabel("Aucune valeur selectionnée");

	private JComboBox<Salle> salleComboBox = new JComboBox<Salle>();
	private ArrayList<Salle> listeSalles = new ArrayList<>();

	private ModificationData modificationData = new ModificationData();

	public ModificationPanel() {

		mainContainer.setLayout(new GridBagLayout());
		GridBagConstraints mainGbc = new GridBagConstraints();

		for (Seance s : EmploiDuTemps.getInstance().getSeance()) {
			seanceComboBox.addItem(s);
		}

		mainGbc.gridx = 0;
		mainGbc.gridy = 0;

		JPanel left = new JPanel();
		left.setLayout(new GridBagLayout());
		GridBagConstraints leftGbc = new GridBagConstraints();
		leftGbc.insets = new Insets(0, 15, 15, 0);
		leftGbc.gridx = 0;
		leftGbc.gridy = 0;
		left.add(classeLabel, leftGbc);
		leftGbc.gridx++;
		left.add(classeValue, leftGbc);
		leftGbc.gridx--;
		leftGbc.gridy++;
		left.add(enseignantLabel, leftGbc);
		leftGbc.gridx++;
		left.add(enseignantValue, leftGbc);
		leftGbc.gridx--;
		leftGbc.gridy++;
		left.add(salleLabel, leftGbc);
		leftGbc.gridx++;
		left.add(salleComboBox, leftGbc);
		leftGbc.gridx--;
		leftGbc.gridy++;
		leftGbc.gridwidth = 2;
		ajouter.setText("modifier");
		left.add(ajouter, leftGbc);

		JPanel right = new JPanel();
		right.setLayout(new GridBagLayout());
		GridBagConstraints rightGbd = new GridBagConstraints();
		rightGbd.gridx = 0;
		rightGbd.gridy = 0;
		rightGbd.gridy++;
		jlistData.setBorder(BorderFactory.createTitledBorder("Classes allouées"));
		scroll.setPreferredSize(new Dimension(300, 400));
		salleComboBox.setPreferredSize(new Dimension(180, 30));
		right.add(seanceComboBox, rightGbd);
		rightGbd.gridy++;
		right.add(scroll, rightGbd);
		mainContainer.add(right);
		mainContainer.add(left, mainGbc);
		mainGbc.insets = new Insets(0, 100, 100, 0);
		mainGbc.gridx++;
		mainContainer.add(right, mainGbc);

		addListeners();
	}

	@Override
	public void addListeners() {
		jlistData.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				salleComboBox.removeAllItems();
				Association a = jlistData.getSelectedValue();

				try {
					String classe = a.getClasse().getMatiere().getCode() + " " + a.getClasse().getMatiere().getCampus();

					classeValue.setText(classe);

					String enseignant = a.getEnseignant().getNom();
					enseignantValue.setText(enseignant);

					Seance selection = (Seance) seanceComboBox.getSelectedItem();
					Seance donnee = EmploiDuTemps.getInstance().getSeance(selection);
					Salle salle = (Salle) donnee.getMap().get(a);
					salleComboBox.addItem(salle);

					for (Salle ss : listeSalles) {
						if (ss.getCampus() == a.getClasse().getMatiere().getCampus()) {
							salleComboBox.addItem(ss);
						}
					}

				} catch (NullPointerException ee) {
				}

			}

		});

		seanceComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Seance selection = (Seance) seanceComboBox.getSelectedItem();
				updateJlistData(EmploiDuTemps.getInstance().getSeance(selection));

			}
		});

		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Salle nouvelle = (Salle) salleComboBox.getSelectedItem();
				Seance seance = EmploiDuTemps.getInstance().getSeance((Seance) seanceComboBox.getSelectedItem());
				Association a = jlistData.getSelectedValue();
				if (seance.getSalles().contains(nouvelle))
					JOptionPane.showMessageDialog(null, "La salle n'est pas disponible", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				else if (a.getClasse().getInscriptions() > nouvelle.getCapacite())
					JOptionPane.showMessageDialog(null, "Pas de place dans la salle sélectionée", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				else {
					System.out.println(seance.getSalles());
					Salle asupprimer = seance.getMap().get(a);
					seance.getSalles().remove(asupprimer);
					seance.getSalles().add(nouvelle);
					seance.getMap().put(a, nouvelle);
					EmploiDuTemps.ecrire();
					modificationData.update();
					JOptionPane.showMessageDialog(null, "Salle modifiée", "Salle modifiée",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		updateJlistData((Seance) seanceComboBox.getSelectedItem());

		if (arg0 instanceof SalleData) {
			SalleData sd = (SalleData) arg0;
			listeSalles.clear();
			for (Salle s : sd.getValues()) {
				listeSalles.add(s);

			}

		}

	}

	private void updateJlistData(Seance s) {
		// System.out.println(s.getMap());
		modelList.clear();
		for (Object o : s.getMap().keySet()) {
			Association a = (Association) o;
			modelList.addElement(a);

		}

	}

	@Override
	public void resetFields() {
		// TODO Auto-generated method stub

	}

	public ModificationData getModificationData() {
		return modificationData;
	}

}
