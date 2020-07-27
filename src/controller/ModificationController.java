package controller;

import model.Association;
import model.data.DataModel;

public class ModificationController extends AbstractController<Association> {

	public ModificationController(DataModel<Association> data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verifier(Association t) {
		// TODO Auto-generated method stub
		return false;
	}

	public void supprimer(Association a) {

	}
}
