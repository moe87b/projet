package model.data;

import java.util.Observable;

public class ModificationData extends Observable {

	public void update() {
		setChanged();
		notifyObservers();
	}

}
