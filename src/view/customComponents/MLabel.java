package view.customComponents;

import javax.swing.JLabel;

public class MLabel extends JLabel {

	public MLabel(String text) {
		super(text);
		setFont(getFont().deriveFont(15f));
	}

}
