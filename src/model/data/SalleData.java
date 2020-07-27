package model.data;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import model.Salle;

public class SalleData extends DataModel<Salle> implements Observer {

	public SalleData(File dataFile) {
		super(dataFile);
		updateCompteur();

	}

	private void updateCompteur() {
		int max = 0;
		for (Integer i : this.dataMap.keySet()) {
			if (i > max)
				max = i;
		}

		Salle.setCompteur(max);
	}

	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof AssociationData) {

			update();

		}

	}

	public String toString() {
		return "hello world";
	}

}
