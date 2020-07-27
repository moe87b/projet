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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.MatiereController;
import model.Matiere;
import model.enums.Campus;
import view.customComponents.MLabel;

public class MatierePanel extends AbstractView<Matiere> {

	private MLabel codeLabel = new MLabel("Code: ");
	private MLabel campusLabel = new MLabel("Campus: ");
	private MLabel afficherParCampusLabel = new MLabel("Afficher par campus");
	private MLabel totalLabel = new MLabel("Nombre de matières affichées: ");
	private MLabel totalValueLabel = new MLabel("0");

	private JTextField codeField = new JTextField(15);
	private JComboBox<Campus> campusComboBox = new JComboBox<Campus>();
	private JComboBox<String> afficherParCampus = new JComboBox<String>();

	public MatierePanel() {
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
		idField.setEditable(false);
		gbc.gridx--;

		gbc.gridy++;
		left.add(campusLabel, gbc);
		gbc.gridx++;
		left.add(campusComboBox, gbc);
		campusComboBox.setPreferredSize(new Dimension(180, 30));
		gbc.gridx--;

		gbc.gridy++;
		left.add(codeLabel, gbc);
		gbc.gridx++;
		left.add(codeField, gbc);
		gbc.gridx--;

		gbc.gridy++;
		left.add(ajouter, gbc);

		gbc.gridx++;
		left.add(nouvelleSaisie, gbc);

		JPanel right = new JPanel();

		right.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);

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
		right.add(totalValueLabel, gbc);
		gbc.gridx--;

		gbc.gridy++;

		scroll.setPreferredSize(new Dimension(300, 400));
		jlistData.setBorder(BorderFactory.createTitledBorder("Liste des matières"));
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

	public JPanel getMainContainer() {
		return this.mainContainer;
	}

	@Override
	public void addListeners() {
		ajouter.addActionListener(new AjouterListener());
		afficherParCampus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateJList(String.valueOf(afficherParCampus.getSelectedItem()));
				int index = afficherParCampus.getSelectedIndex();
				if (index > 0) {
					campusComboBox.setSelectedIndex(index - 1);
				} else
					campusComboBox.setSelectedIndex(0);
				disableFields();
			}
		});

		jlistData.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				disableFields();
				Matiere m = jlistData.getSelectedValue();
				try {
					idField.setText(String.valueOf(m.getId()));
					campusComboBox.setSelectedItem(m.getCampus());
					codeField.setText(m.getCode());
					// capaciteField.setText(String.valueOf(s.getCapacite()));
				} catch (NullPointerException ee) // au cas où la liste est mise à jour mais elle est vide, aucun
													// élément
													// n'est séléctionné
				{
					return;
				}
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
			public void actionPerformed(ActionEvent e) {
				/*
				 * int index = campusComboBox.getSelectedIndex();
				 * afficherParCampus.setSelectedIndex(index + 1);
				 */
				enableFields();

			}
		});

	}

	class AjouterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String code = codeField.getText();
			Campus c = (Campus) campusComboBox.getSelectedItem();

			Matiere m;

			m = new Matiere(code, c);
			controller.add(m);

			resetFields();
		}
	}

	public void resetFields() {
		// campusComboBox.setSelectedIndex(0);

		if (afficherParCampus.getSelectedIndex() > 0) {
			campusComboBox.setSelectedIndex(afficherParCampus.getSelectedIndex() - 1);
		} else {
			campusComboBox.setSelectedIndex(0);
			afficherParCampus.setSelectedIndex(0);
		}

		// jlistData.clearSelection();
		codeField.setText("");
		idField.setText("");
		enableFields();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateJList(String.valueOf(afficherParCampus.getSelectedItem()));

	}

	public void setController(MatiereController c) {
		this.controller = c;
	}

	public void updateJList(String value) {
		modelList.removeAllElements();
		int compteur = 0;
		for (Matiere m : controller.getValues()) {
			if (value.equalsIgnoreCase("Tous")) {
				modelList.addElement(m);
				compteur++;
			} else {
				if (value.equalsIgnoreCase(m.getCampus().toString())) {
					modelList.addElement(m);
					compteur++;
				}
			}
		}

		totalValueLabel.setText(String.valueOf(compteur));

	}

	@Override
	public void disableFields() {
		super.disableFields();
		codeField.setEditable(false);
		campusComboBox.setEnabled(false);

	}

	@Override
	public void enableFields() {
		super.enableFields();
		codeField.setEditable(true);
		campusComboBox.setEnabled(true);

	}

}
