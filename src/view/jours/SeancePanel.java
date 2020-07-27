package view.jours;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Association;
import model.Salle;
import view.customComponents.MLabel;

public class SeancePanel extends JPanel {

	private JPanel mainContainer = new JPanel();
	private JScrollPane scroll = new JScrollPane(mainContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	public SeancePanel() {
		this.add(scroll);
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createBevelBorder(10));
		scroll.setPreferredSize(new Dimension(150, 150));

	}

	public void addSeance(Association a, Salle s) {
		JPanel inner = new JPanel();
		inner.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		inner.add(new MLabel(a.getClasse().getMatiere().getCode()), gbc);
		gbc.gridy++;
		inner.add(new MLabel(a.getEnseignant().getNom()), gbc);
		gbc.gridy++;
		inner.add(new MLabel(s.getNom()), gbc);
		gbc.gridy++;
		inner.add(new MLabel("        "), gbc);

		mainContainer.add(inner);
	}

	public void reset() {
		mainContainer.removeAll();
		mainContainer.revalidate();
		mainContainer.repaint();

	}

}
