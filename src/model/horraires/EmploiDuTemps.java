package model.horraires;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;

import model.enums.HeuresEnum;
import model.enums.JoursEnum;

/**
 * Classe contenant les 18 seances de la semaine dans un tableau, avec une
 * instance unique accessible
 * 
 * @author mohamad
 *
 */
public class EmploiDuTemps extends Observable implements Serializable {

	/**
	 * La liste de toutes les s�ances dans la semaine
	 */
	private static Seance[] seances = new Seance[18]; // 3 Seances par jour du lundi au samedi;

	/**
	 * L'unique instance accessible
	 */
	private static EmploiDuTemps edt = new EmploiDuTemps();
	/**
	 * Le fichier o� sont sauvegard�es les donn�es
	 */
	private static File file;

	/**
	 * <b>Le constructeur</b>
	 * <p>
	 * 18 seances sont cr�es avec les diff�rents jours de la semaine et les
	 * diff�rentes heures, 3 heures par jour, et 6 jour par semaine
	 */
	private EmploiDuTemps() {
		int index = 0;
		for (JoursEnum j : JoursEnum.values()) {
			for (HeuresEnum h : HeuresEnum.values()) {
				Seance s = new Seance(j, h);
				seances[index] = s;
				index++;

			}

		}

	}

	/**
	 * 
	 * @return L'unique instance d'emploi du temps
	 */
	public static EmploiDuTemps getInstance() {
		return edt;
	}

	/**
	 * 
	 * @param s une seance demand�e
	 * @return la seance demand�e dans l'emploi du temps
	 */
	public Seance getSeance(Seance s) {
		for (Seance sc : seances) {
			if (sc.equals(s))
				return sc;
		}

		return null;
	}

	/**
	 * 
	 * @return la liste de toutes les s�ances
	 */
	public Seance[] getSeance() {
		return seances;
	}

	/**
	 * Ecrit l'instance unique dans le fichier
	 * 
	 * @See {@link EmploiDuTemps#file}
	 */
	public static void ecrire() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(seances);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Lecture des valeurs depuis le fichier
	 * 
	 * @See {@link EmploiDuTemps#file}
	 */
	public static void read() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			seances = (Seance[]) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}

	}

	/**
	 * R�initialise toutes les s�ances
	 */
	public static void reset() {
		for (Seance s : seances) {
			s.getSalles().clear();
			s.getEnseignants().clear();
			s.getMap().clear();
		}
	}

	/**
	 * Mise � jour du ficher de lecture et d'�criture
	 * 
	 * @param f le nouveau fichier
	 */
	public static void setFile(File f) {
		file = f;
	}

}