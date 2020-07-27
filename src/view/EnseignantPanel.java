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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Enseignant;
import view.customComponents.MLabel;

public class EnseignantPanel extends AbstractView<Enseignant> {
	private MLabel nomLabel = new MLabel("Nom: ");
	private JTextField nomField = new JTextField(15);
	private MLabel totalLabel = new MLabel("Nombre total d'enseignants: ");
	private MLabel totalValue = new MLabel("0");

	public EnseignantPanel() {

		JPanel left = new JPanel();
		left.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 10, 20, 0);

		left.add(idLabel, gbc);
		gbc.gridx++;
		left.add(idField, gbc);
		gbc.gridx--;

		gbc.gridy++;
		left.add(nomLabel, gbc);
		gbc.gridx++;
		left.add(nomField, gbc);

		gbc.insets = new Insets(0, 10, 20, 0);
		gbc.gridx--;
		gbc.gridy++;
		left.add(ajouter, gbc);
		gbc.gridx++;
		left.add(nouvelleSaisie, gbc);
		gbc.gridx++;

		JPanel right = new JPanel();
		right.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, -100, 0, 0);
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
		scroll.setBorder(BorderFactory.createTitledBorder("Enseignants"));

		TitledBorder border = BorderFactory.createTitledBorder("Insertion");
		border.setTitleJustification(TitledBorder.CENTER);
		left.setBorder(border);

		mainContainer.setLayout(new GridLayout(1, 2));
		mainContainer.add(left);
		mainContainer.add(right);
		addListeners();

	}

	@Override
	public void update(Observable o, Object arg) {
		updateJList();
		totalValue.setText(String.valueOf(controller.getValues().size()));

	}

	@Override
	public void addListeners() {
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = nomField.getText();
				Enseignant s = new Enseignant(nom);
				controller.add(s);
				resetFields();

			}
		});

		jlistData.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				disableFields();
				try {
					idField.setText(String.valueOf(jlistData.getSelectedValue().getId()));
					nomField.setText(jlistData.getSelectedValue().getNom());
				} catch (NullPointerException ee) {
					return;
				}

			}
		});

		nouvelleSaisie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetFields();
				enableFields();
			}
		});
	}

	@Override
	public void resetFields() {
		nomField.setText("");
		idField.setText("");

	}

	@Override
	public void disableFields() {
		nomField.setEnabled(false);

	}

	@Override
	public void enableFields() {
		nomField.setEnabled(true);
	}

	public void updateJList() {
		modelList.removeAllElements();
		for (Enseignant e : this.controller.getValues()) {
			this.modelList.addElement(e);
		}

	}

}