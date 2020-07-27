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
 * Une seance se déroule à une journée et une heure précise dans la semaine,
 * chauqe séance est unique
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
	 * L'heure de la journée
	 */
	private HeuresEnum heure;
	/**
	 * Liste des salles qui sont occupées pendant la séance
	 */
	private ArrayList<Salle> salles = new ArrayList<>();
	/**
	 * Liste des enseignants occupés pendant la séance
	 */
	private ArrayList<Enseignant> enseignants = new ArrayList<>();
	/**
	 * Les associations cours prof qui ont cours pendant la séance
	 */
	private ArrayList<Association> associationCoursProf = new ArrayList<>();
	/**
	 * Atrribution de chaque association à une salle
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
	 * Deux séances sont identiques si elles ont le même jour et la même heure
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
	 * Une représenation de la séance en chaine de caractère avec le jour et l'heure
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
