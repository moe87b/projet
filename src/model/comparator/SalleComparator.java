package model.comparator;

import java.util.Comparator;

import model.Salle;

/**
 * Comparer deux salles de classes selon leur capacité
 * 
 *
 */
public class SalleComparator implements Comparator<Salle> {

	@Override
	public int compare(Salle o1, Salle o2) {

		if (o1.getCapacite() > o2.getCapacite())
			return 1;
		if (o1.getCapacite() < o2.getCapacite())
			return -1;
		return 0;

	}

}
