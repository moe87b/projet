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

import model.Classe;
import model.Matiere;
import model.data.ClasseData;
import view.customComponents.MLabel;

public class ClassePanel extends AbstractView<Classe> {

	private MLabel matiereLabel = new MLabel("Matière");
	private MLabel nombreLabel = new MLabel("Nombre d'étudiants: ");
	private MLabel totalLabel = new MLabel("Nombre total de matières: ");
	private MLabel totalValue = new MLabel("0");

	private JTextField nombreField = new JTextField(15);

	private JComboBox<Matiere> matiereComboBox = new JComboBox<Matiere>();

	public ClassePanel() {
		JPanel left = new JPanel();
		left.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 20, 10);

		left.add(idLabel, gbc);
		gbc.gridx++;
		left.add(idField, gbc);
		gbc.gridx--;

		gbc.gridy++;
		left.add(matiereLabel, gbc);
		gbc.gridx++;
		matiereComboBox.setPreferredSize(new Dimension(180, 30));
		left.add(matiereComboBox, gbc);

		gbc.gridx--;
		gbc.gridy++;

		left.add(nombreLabel, gbc);
		gbc.gridx++;
		left.add(nombreField, gbc);

		gbc.gridx--;
		gbc.gridy++;

		left.add(ajouter, gbc);
		gbc.gridx++;
		left.add(nouvelleSaisie, gbc);
		gbc.gridx--;

		gbc.gridy++;

		gbc.gridwidth = 3;

		JPanel nombreDePlacesPanel = new JPanel();
		nombreDePlacesPanel.setLayout(new GridBagLayout());
		GridBagConstraints placeConstraints = new GridBagConstraints();
		placeConstraints.gridx = 0;
		placeConstraints.gridy = 0;
		placeConstraints.insets = new Insets(0, 20, 10, 0);

		gbc.gridy++;
		left.add(nombreDePlacesPanel, gbc);

		JPanel right = new JPanel();
		right.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, -80, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		right.add(totalLabel, gbc);
		gbc.gridx++;
		right.add(totalValue, gbc);

		gbc.gridx--;
		gbc.gridy++;

		gbc.insets = new Insets(0, 0, 0, 0);

		scroll.setPreferredSize(new Dimension(300, 400));
		right.add(scroll, gbc);
		scroll.setBorder(BorderFactory.createTitledBorder("Classes"));

		TitledBorder border = BorderFactory.createTitledBorder("Insertion");
		border.setTitleJustification(TitledBorder.CENTER);
		left.setBorder(border);

		mainContainer.setLayout(new GridLayout(1, 2));
		mainContainer.add(left);
		mainContainer.add(right);
		addListeners();

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		matiereComboBox.removeAllItems();

		ClasseData cd = (ClasseData) arg0;

		for (Matiere m : cd.getListeMatieres()) {
			matiereComboBox.addItem(m);
		}
		updateJlist();
		totalValue.setText(String.valueOf(controller.getValues().size()));
		// updateNombreDePlaces(cd.getListeSalles());

	}

	/*
	 * private void updateNombreDePlaces(ArrayList<Salle> listeSalles) {
	 * 
	 * int beyrouth = 0; int tripoli = 0; int baalbak = 0; int bikfaya = 0; int
	 * nahrIbrahim = 0;
	 * 
	 * for (Salle s : listeSalles) { if (s.getCampus() == Campus.BEYROUTH) beyrouth
	 * += s.getCapacite();
	 * 
	 * if (s.getCampus() == Campus.TRIPOLI) tripoli += s.getCapacite();
	 * 
	 * if (s.getCampus() == Campus.BICKFAYA) bikfaya += s.getCapacite();
	 * 
	 * if (s.getCampus() == Campus.BAALBECK) baalbak += s.getCapacite();
	 * 
	 * if (s.getCampus() == Campus.NAHR_IBRAHIM) nahrIbrahim += s.getCapacite();
	 * 
	 * }
	 * 
	 * for (Classe c : this.controller.getValues()) { if (c.getMatiere().getCampus()
	 * == Campus.BEYROUTH) beyrouth -= c.getInscriptions();
	 * 
	 * if (c.getMatiere().getCampus() == Campus.TRIPOLI) tripoli -=
	 * c.getInscriptions();
	 * 
	 * if (c.getMatiere().getCampus() == Campus.BAALBECK) baalbak -=
	 * c.getInscriptions();
	 * 
	 * if (c.getMatiere().getCampus() == Campus.BICKFAYA) bikfaya -=
	 * c.getInscriptions();
	 * 
	 * if (c.getMatiere().getCampus() == Campus.NAHR_IBRAHIM) nahrIbrahim -=
	 * c.getInscriptions();
	 * 
	 * }
	 * 
	 * beyrouthValue.setText(String.valueOf(beyrouth));
	 * nahrValue.setText(String.valueOf(nahrIbrahim));
	 * blbValue.setText(String.valueOf(baalbak));
	 * tripoliValue.setText(String.valueOf(tripoli));
	 * bikFayaValue.setText(String.valueOf(bikfaya)); }
	 */

	@Override
	public void addListeners() {
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Matiere m = (Matiere) matiereComboBox.getSelectedItem();

				int nombre = 0;
				try {
					nombre = Integer.parseInt(nombreField.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Le nombre d'inscrit doit être un entier positif", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Classe c = new Classe(m, nombre);
				controller.add(c);
				resetFields();

			}
		});

		jlistData.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				disableFields();
				try {
					Classe c = jlistData.getSelectedValue();
					idField.setText(String.valueOf(c.getId()));
					nombreField.setText(String.valueOf(c.getInscriptions()));
					matiereComboBox.setSelectedItem(c.getMatiere());
				} catch (NullPointerException ee) {
				}
			}
		});

		nouvelleSaisie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jlistData.clearSelection();
				resetFields();

			}
		});
	}

	private void updateJlist() {
		modelList.removeAllElements();
		for (Classe c : controller.getValues()) {
			modelList.addElement(c);
		}
	}

	@Override
	public void enableFields() {
		super.enableFields();
		nombreField.setEnabled(true);
		matiereComboBox.setEnabled(true);
	}

	@Override
	public void disableFields() {
		super.disableFields();
		nombreField.setEnabled(false);
		matiereComboBox.setEnabled(false);

	}

	@Override
	public void resetFields() {

		nombreField.setText("");
		idField.setText("");
		enableFields();
	}

}
