package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Salle;
import model.enums.Campus;
import view.customComponents.MLabel;

public class SallePanel extends AbstractView<Salle> {

	private MLabel nomLabel = new MLabel("Nom: ");
	private MLabel campusLabel = new MLabel("Campus: ");
	private MLabel capacite = new MLabel("Capacite: ");
	private MLabel afficherParCampusLabel = new MLabel("Afficher par campus");
	private MLabel totalLabel = new MLabel("Nombre de salles affichées: ");
	private MLabel totalValueLabel = new MLabel("0");

	private JTextField nomField = new JTextField(15);
	private JTextField capaciteField = new JTextField(15);

	private JComboBox<Campus> campusComboBox = new JComboBox<Campus>();
	private JComboBox<String> afficherParCampus = new JComboBox<String>();

	public SallePanel() {
		JPanel left = new JPanel();
		left.setLayout(new GridBagLayout());

		for (Campus c : Campus.values()) {
			campusComboBox.addItem(c);
		}
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 10, 20, 0);

		left.add(idLabel, gbc);
		gbc.gridx++;

		left.add(idField, gbc);
		gbc.gridx--;

		gbc.gridy++;
		left.add(campusLabel, gbc);
		gbc.gridx++;
		left.add(campusComboBox, gbc);
		campusComboBox.setPreferredSize(new Dimension(180, 30));
		gbc.gridx--;

		gbc.gridy++;
		left.add(nomLabel, gbc);
		gbc.gridx++;
		left.add(nomField, gbc);
		gbc.gridx--;

		gbc.gridy++;
		left.add(capacite, gbc);
		gbc.gridx++;
		left.add(capaciteField, gbc);
		gbc.gridx--;

		gbc.gridy++;
		gbc.insets = new Insets(0, 20, 0, 0);

		left.add(ajouter, gbc);
		gbc.gridx++;

		left.add(nouvelleSaisie, gbc);
		gbc.gridx++;

		JPanel right = new JPanel();

		right.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;

		afficherParCampus.addItem("Tous");
		for (Campus camp : Campus.values()) {
			afficherParCampus.addItem(camp.toString());
		}

		gbc.gridwidth = 1;
		right.add(afficherParCampusLabel, gbc);
		gbc.gridx++;
		right.add(afficherParCampus, gbc);
		gbc.gridx--;

		gbc.gridy++;
		right.add(totalLabel, gbc);
		gbc.gridx++;
		gbc.insets = new Insets(0, 5, 0, 0);
		right.add(totalValueLabel, gbc);
		gbc.gridx--;

		gbc.insets = new Insets(0, 20, 0, 0);
		gbc.gridy++;

		scroll.setPreferredSize(new Dimension(300, 400));
		jlistData.setBorder(BorderFactory.createTitledBorder("Liste des salles"));
		gbc.gridwidth = 2;
		right.add(scroll, gbc);

		TitledBorder border = BorderFactory.createTitledBorder("Insertion");
		border.setTitleJustification(TitledBorder.CENTER);
		left.setBorder(border);

		mainContainer.setLayout(new GridLayout(1, 2));
		mainContainer.add(left);
		mainContainer.add(right);

		addListeners();
	}

	@Override
	public void addListeners() {
		ajouter.addActionListener(new AjouterListener());
		afficherParCampus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String value = String.valueOf(afficherParCampus.getSelectedItem());
				updateJList(value);
				// resetFields();
				// jlistData.setSelectedIndex(0);
				int index = afficherParCampus.getSelectedIndex();
				if (index > 0) {
					campusComboBox.setSelectedIndex(index - 1);
				} else
					campusComboBox.setSelectedIndex(0);
				// jlistData.setSelectedIndex(0);
				disableFields();

			}
		});
		jlistData.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				disableFields();
				Salle s = jlistData.getSelectedValue();
				try {
					idField.setText(String.valueOf(s.getId()));
					// campusComboBox.setSelectedItem(s.getCampus());
					nomField.setText(s.getNom());
					capaciteField.setText(String.valueOf(s.getCapacite()));
					campusComboBox.setSelectedItem(s.getCampus());
				} catch (NullPointerException e) // au cas où la liste est mise à jour mais elle est vide, aucun élément
													// n'est séléctionné
				{
					return;
				}
				disableFields();
			}
		});
		nouvelleSaisie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jlistData.clearSelection();
				resetFields();

			}
		});

		campusComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * int index = campusComboBox.getSelectedIndex();
				 * afficherParCampus.setSelectedIndex(index + 1);
				 */
				enableFields();

			}
		});

	}

	public void resetFields() {

		if (afficherParCampus.getSelectedIndex() > 0) {
			campusComboBox.setSelectedIndex(afficherParCampus.getSelectedIndex() - 1);
		} else

		{
			campusComboBox.setSelectedIndex(0);
			afficherParCampus.setSelectedIndex(0);
		}

		// jlistData.clearSelection();
		idField.setText("");
		nomField.setText("");
		capaciteField.setText("");
		enableFields();
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		updateJList(String.valueOf(afficherParCampus.getSelectedItem()));

	}

	public void updateJList(String value) {
		modelList.removeAllElements();
		int compteur = 0;
		for (Salle s : controller.getValues()) {
			if (value.equalsIgnoreCase("Tous")) {
				modelList.addElement(s);
				compteur++;
			} else {
				if (value.equalsIgnoreCase(s.getCampus().toString())) {
					modelList.addElement(s);
					compteur++;
				}
			}
		}

		totalValueLabel.setText(String.valueOf(compteur));

	}

	class AjouterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String nom = nomField.getText();
			Campus c = (Campus) campusComboBox.getSelectedItem();
			int capacite = 0;
			try {
				capacite = Integer.parseInt(capaciteField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "La capacité doit être un chiffre", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				jlistData.clearSelection();
				return;
			}
			Salle s;

			s = new Salle(nom, c, capacite);
			controller.add(s);

			resetFields();
		}

	}

	@Override
	public void disableFields() {
		super.disableFields();
		nomField.setEditable(false);
		campusComboBox.setEnabled(false);
		capaciteField.setEditable(false);

	}

	@Override
	public void enableFields() {
		super.enableFields();
		nomField.setEditable(true);
		campusComboBox.setEnabled(true);
		capaciteField.setEditable(true);
	}

}
