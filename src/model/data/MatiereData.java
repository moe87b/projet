package model.data;

import java.io.File;

import model.Matiere;

public class MatiereData extends DataModel<Matiere> {

	public MatiereData(File dataFile) {
		super(dataFile);
		updateCompteur();
	}

	private void updateCompteur() {
		int max = 0;
		for (Integer i : this.dataMap.keySet()) {
			if (i > max)
				max = i;
		}

		Matiere.updateCompteur(max);
	}

	@Override
	public void remove(Matiere m) {
		super.remove(m);
		setChanged();
		notifyObservers(m);

	}

}
