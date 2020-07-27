package view.customComponents;

import java.awt.Color;

public class ValueLabel extends MLabel {

	public ValueLabel(String text) {
		super(text);
		if (text.equalsIgnoreCase("libre"))
			this.setForeground(Color.green);
		else
			this.setForeground(Color.red);
		repaint();
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		super.setText(text);
		if (text.equalsIgnoreCase("Occupé"))
			this.setForeground(Color.red);
		if (text.equalsIgnoreCase("Libre"))
			this.setForeground(Color.green);

	}

}
