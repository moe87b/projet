package controller;

import java.util.Collection;

import model.Model;
import model.data.DataModel;

public abstract class AbstractController<T extends Model> {

	protected DataModel<T> data;

	public AbstractController(DataModel<T> data) {
		this.data = data;

	}

	public void add(T t) {
		if (verifier(t))
			data.add(t);
	}

	public abstract boolean verifier(T t);

	public Collection<T> getValues() {
		return data.getValues();
	}

	public DataModel<T> getData() {
		return data;
	}

}
