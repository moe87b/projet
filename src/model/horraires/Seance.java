package model.horraires;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.Association;
import model.Enseignant;
import model.Salle;
import model.enums.HeuresEnum;
import model.enums.JoursEnum;

/**
 * Une seance se d�roule � une journ�e et une heure pr�cise dans la semaine,
 * chauqe s�ance est unique
 * 
 * @author mohamad
 *
 */
public class Seance implements Serializable {

	/**
	 * Le jour de la semaine
	 */
	private JoursEnum jour;
	/**
	 * L'heure de la journ�e
	 */
	private HeuresEnum heure;
	/**
	 * Liste des salles qui sont occup�es pendant la s�ance
	 */
	private ArrayList<Salle> salles = new ArrayList<>();
	/**
	 * Liste des enseignants occup�s pendant la s�ance
	 */
	private ArrayList<Enseignant> enseignants = new ArrayList<>();
	/**
	 * Les associations cours prof qui ont cours pendant la s�ance
	 */
	private ArrayList<Association> associationCoursProf = new ArrayList<>();
	/**
	 * Atrribution de chaque association � une salle
	 */
	private HashMap<Association, Salle> map = new HashMap<>();

	/**
	 * Constructeur
	 * 
	 * @param j le jour
	 * @param h l'heure
	 */
	public Seance(JoursEnum j, HeuresEnum h) {
		this.jour = j;
		this.heure = h;

	}

	/**
	 * Retourne le jour
	 * 
	 * @return le jour
	 */
	public JoursEnum getJour() {
		return jour;
	}

	/**
	 * Retourne l'heure
	 * 
	 * @return l'heure
	 */
	public HeuresEnum getHeure() {
		return heure;
	}

	/**
	 * Deux s�ances sont identiques si elles ont le m�me jour et la m�me heure
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Seance))
			return false;
		if (obj == this)
			return true;
		if (obj == null)
			return false;

		Seance s = (Seance) obj;
		return s.getJour().equals(this.getJour()) && s.getHeure().equals(this.getHeure());

	}

	/**
	 * Une repr�senation de la s�ance en chaine de caract�re avec le jour et l'heure
	 */
	public String toString() {
		return this.jour + "-" + this.heure + "-";
	}

	/**
	 *
	 * @return la liste des enseignants
	 */
	public ArrayList<Enseignant> getEnseignants() {
		return enseignants;
	}

	/**
	 * 
	 * @return la liste des salles
	 */
	public ArrayList<Salle> getSalles() {
		return salles;
	}

	/**
	 * 
	 * @return la liste des associatinos enseignant classe
	 */
	public ArrayList<Association> getAssociationCoursProf() {
		return associationCoursProf;
	}

	/**
	 * 
	 * @return les allocations cours - salle
	 */
	public HashMap<Association, Salle> getMap() {
		return map;
	}

}
