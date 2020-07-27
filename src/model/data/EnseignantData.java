package model.data;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import model.Enseignant;

public class EnseignantData extends DataModel<Enseignant> implements Observer {

	public EnseignantData(File dataFile) {
		super(dataFile);
		updateCompteur();

	}

	public void updateCompteur() {
		int max = 0;
		for (Integer i : this.dataMap.keySet()) {
			if (i > max)
				max = i;
		}

		Enseignant.updateCompteur(max);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		update();

	}

}
