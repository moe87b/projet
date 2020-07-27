package model;

import java.io.Serializable;

/**
 * <b>Model est la super classe abstraite qui repr�sente englobe un comporement
 * commun aux objets qui seront utilis�s</b>
 * <p>
 * Les objets de Model sont caract�ris�es pas un identifiant unique ID et chaque
 * objet qui en h�rite va d�finir ses propres champs
 * </p>
 * 
 * 
 *
 */

public abstract class Model implements Serializable {

	/**
	 * Un identifiant unique pour chaque object cr�e
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
	 * retourne un repr�sentation en chaine de caract�re de l'objet
	 * 
	 */

	@Override
	public abstract String toString();

	@Override
	public abstract boolean equals(Object o);

}
