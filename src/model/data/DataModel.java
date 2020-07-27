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

public abstract class DataModel<T extends Model> extends Observable {

	protected HashMap<Integer, T> dataMap;
	protected File dataFile;

	public DataModel(File dataFile) {
		this.dataFile = dataFile;
		this.dataMap = read();
		update();

	}

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

	public Collection<T> getValues() {
		Collection<T> dataValues = dataMap.values();
		return dataValues;
	}

	public void add(T t) {

		dataMap.put(t.getId(), t);
		write();
		update();
	}

	public void remove(T t) {
		dataMap.remove(t.getId());
		setChanged();
		notifyObservers(t);
		update();
	}

	public void update() {
		setChanged();
		notifyObservers();
		write();
	}

	@Override
	public void addObserver(Observer obs) {
		super.addObserver(obs);
		update();

	}

	public boolean contains(T t) {
		return this.dataMap.containsKey(t.getId());

	}

	public T get(int id) {
		return dataMap.get(id);
	}

	public T get(T t) {
		return dataMap.get(t.getId());
	}

	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}

	public void changeDirectory(File f) {
		write();
		dataMap.clear();
		updateNoWrite();// pour ne pas effacer les données

		this.dataFile = f;

	}

	public void updateNoWrite() {
		setChanged();
		notifyObservers();
	}

	public void setDataMap(HashMap<Integer, T> dataMap) {
		this.dataMap = dataMap;
		update();
	}

	public void reset() {

		dataMap.clear();
		update();
	}

}
