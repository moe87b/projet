package model;

import java.io.Serializable;

/**
 * <b>Model est la super classe abstraite qui représente englobe un comporement
 * commun aux objets qui seront utilisés</b>
 * <p>
 * Les objets de Model sont caractérisées pas un identifiant unique ID et chaque
 * objet qui en hérite va définir ses propres champs
 * </p>
 * 
 * 
 *
 */

public abstract class Model implements Serializable {

	/**
	 * Un identifiant unique pour chaque object crée
	 * 
	 * @see Model#getId()
	 * 
	 */
	protected int id;

	/*
	 * retourne le id
	 */
	public int getId() {
		return id;
	}
	/*
	 * retourne un représentation en chaine de caractère de l'objet
	 * 
	 */

	@Override
	public abstract String toString();

	@Override
	public abstract boolean equals(Object o);

}
