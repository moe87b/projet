package view.jours;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class HorrairesPanel extends JPanel {

	private JCheckBox selectedDay = new JCheckBox("");

	private JCheckBox heure1 = new JCheckBox("16H à 17H30");
	private JCheckBox heure2 = new JCheckBox("18h45 à 19h15");
	private JCheckBox heure3 = new JCheckBox("19h30 à 21h");

	public HorrairesPanel(String jour) {
		this.selectedDay.setText(jour);

		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 15);

		this.add(selectedDay);
		gbc.gridy++;

		gbc.gridx++;
		this.add(heure1, gbc);
		heure1.setEnabled(false);
		gbc.gridx++;
		this.add(heure2, gbc);
		heure2.setEnabled(false);
		gbc.gridx++;
		this.add(heure3, gbc);
		heure3.setEnabled(false);
		setBorder(BorderFactory.createEtchedBorder());
		addListeners();

	}

	private void addListeners() {
		selectedDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selectedDay.isSelected()) {
					heure1.setEnabled(true);
					heure2.setEnabled(true);
					heure3.setEnabled(true);
				} else {
					heure1.setEnabled(false);
					heure2.setEnabled(false);
					heure3.setEnabled(false);
				}

			}
		});
	}

	public boolean isDaySelected() {
		return selectedDay.isSelected();
	}

	public boolean isHeure1Selected() {
		if (!selectedDay.isSelected())
			return false;
		return heure1.isSelected();
	}

	public boolean isHeure2Selected() {
		if (!selectedDay.isSelected())
			return false;
		return heure2.isSelected();
	}

	public boolean isHeure3Selected() {
		if (!selectedDay.isSelected())
			return false;
		return heure3.isSelected();
	}

	public void reset() {
		heure1.setSelected(false);
		heure2.setSelected(false);
		heure3.setSelected(false);
		heure1.setEnabled(false);
		heure2.setEnabled(false);
		heure3.setEnabled(false);
		selectedDay.setSelected(false);

	}

}
