package view;

import java.awt.BorderLayout;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.AbstractController;
import model.Model;
import view.customComponents.MLabel;

public abstract class AbstractView<T extends Model> implements Observer {

	protected AbstractController<T> controller;
	protected JPanel mainContainer = new JPanel();
	protected MLabel idLabel = new MLabel("ID: ");
	protected JTextField idField = new JTextField(15);

	protected JList<T> jlistData = new JList<T>();
	protected DefaultListModel<T> modelList = new DefaultListModel<T>();
	protected JScrollPane scroll = new JScrollPane(jlistData);

	protected JButton ajouter = new JButton("Ajouter");
	protected JButton nouvelleSaisie = new JButton("Nouvelle saisie");
	private JScrollPane totalScrollPane = new JScrollPane(mainContainer);

	public AbstractView() {
		mainContainer.setLayout(new BorderLayout());
		jlistData.setModel(modelList);
		idField.setEditable(false);
		idField.setToolTipText("Le ID est généré automatiquement");

	}

	public abstract void addListeners();

	public JPanel getMainContainer() {
		return this.mainContainer;
	}

	public abstract void resetFields();

	public void setController(AbstractController<T> controller) {
		this.controller = controller;
	}

	public void disableFields() {
		ajouter.setEnabled(false);
	}

	public void enableFields() {
		ajouter.setEnabled(true);
	}

	public JScrollPane getTotalScrollPane() {
		return totalScrollPane;
	}

	public AbstractController<T> getController() {
		return controller;
	}

}
