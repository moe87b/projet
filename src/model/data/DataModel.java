package model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;

/**
 * 
 * @author mohamad Class abstraite repr�sentant le mod�le, qui stock les
 *         informations dans un HashMap pendant l'execution et dans un fichier
 *         texte.
 * @param <T>
 */
public abstract class DataModel<T extends Model> extends Observable {
	/**
	 * Collection d'objets avec un entier en guise de cl�
	 */
	protected HashMap<Integer, T> dataMap;
	/**
	 * fichier de stockage des informations
	 */
	protected File dataFile;

	/**
	 * Constructeur
	 * 
	 * @param dataFile le fichier
	 * 
	 */
	public DataModel(File dataFile) {
		this.dataFile = dataFile;
		this.dataMap = read();
		update();

	}

	/**
	 * Lecture des informations stock�es dans le fichier puis les places dans une
	 * collection
	 * 
	 * @return la hashmap avec les informations lues
	 */

	public HashMap<Integer, T> read() {

		HashMap<Integer, T> dataMapRead = new HashMap<Integer, T>();
		FileInputStream fis;
		ObjectInput ois;
		try {
			fis = new FileInputStream(this.dataFile);
			ois = new ObjectInputStream(fis);

			dataMapRead = (HashMap<Integer, T>) ois.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			return dataMapRead;
		} catch (IOException e) {
			return dataMapRead;
		}
		return dataMapRead;
	}

	/**
	 * Ecriture des informations de la collection dans le fichier
	 */
	public void write() {
		FileOutputStream fos;
		ObjectOutput oos;

		try {
			fos = new FileOutputStream(this.dataFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(dataMap);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return Une collection des valeurs contenues sans les cl�s
	 */
	public Collection<T> getValues() {
		Collection<T> dataValues = dataMap.values();
		return dataValues;
	}

	/**
	 * Ajouter un objet � la collection
	 * 
	 * @param t L'objet � ajouter
	 */
	public void add(T t) {

		dataMap.put(t.getId(), t);
		write();
		update();
	}

	/**
	 * Supprimer un objet de la collection
	 * 
	 * @param t l'objet � supprimer
	 */
	public void remove(T t) {
		dataMap.remove(t.getId());
		setChanged();
		notifyObservers(t);
		update();
	}

	/**
	 * Mise � jour de classe � partir du patron Observer
	 */
	public void update() {
		setChanged();
		notifyObservers();
		write();
	}

	/**
	 * Ajouter un observer � la classe
	 */
	@Override
	public void addObserver(Observer obs) {
		super.addObserver(obs);
		update();

	}

	/**
	 * 
	 * @param t Objet � v�rifier
	 * @return true si l'objet figure dans la liste, false sinon
	 */
	public boolean contains(T t) {
		return this.dataMap.containsKey(t.getId());

	}

	/**
	 * Obetnir un objet sp�cifique � partir de l'id
	 * 
	 * @param id l'id de l'objet � r�cup�rer
	 * @return l'objet demadn�
	 */
	public T get(int id) {
		return dataMap.get(id);
	}

	/**
	 * Retourne un objet t � partir de sa r�f�rence
	 * 
	 * @param t l'objet demand�
	 * @return L'objet � retourner
	 */
	public T get(T t) {
		return dataMap.get(t.getId());
	}

	/*
	 * public void setDataFile(File dataFile) { this.dataFile = dataFile; }/*
	 * 
	 */

	/**
	 * Changement du fichier de donn�es
	 * 
	 * @param f le nouveau fichier
	 */
	public void changeDirectory(File f) {
		write();
		dataMap.clear();
		updateNoWrite();// pour ne pas effacer les donn�es

		this.dataFile = f;

	}

	/**
	 * Mise � jour sans �crire dans le fichier
	 */
	public void updateNoWrite() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Changement de la collection
	 * 
	 * @param dataMap nouvelle collection
	 */
	public void setDataMap(HashMap<Integer, T> dataMap) {
		this.dataMap = dataMap;
		update();
	}

	public HashMap<Integer, T> getDataMap() {
		return dataMap;
	}

	/**
	 * Suppression de toutes les donn�es dans la collection
	 */
	public void reset() {

		dataMap.clear();
		update();
	}

}
