import java.io.File;

import com.formdev.flatlaf.FlatDarculaLaf;

import controller.AssociationController;
import controller.ClassController;
import controller.EnseignantController;
import controller.MatiereController;
import controller.SalleController;
import model.data.AssociationData;
import model.data.ClasseData;
import model.data.EnseignantData;
import model.data.MatiereData;
import model.data.SalleData;
import model.horraires.EmploiDuTemps;
import view.customComponents.Fenetre;

public class Main {

	public static void main(String[] args) {
		FlatDarculaLaf.install();

		/*
		 * try { UIManager.setLookAndFeel(new FlatDarculaLaf()); } catch (Exception ex)
		 * { System.err.println("Failed to initialize LaF"); }
		 */

		Fenetre fen = new Fenetre("NFP121");

		File fichierEmploiDuTemps = new File("data/default/fichierTemps.txt");
		EmploiDuTemps.setFile(fichierEmploiDuTemps);
		EmploiDuTemps.read();

		File ficherSalles = new File("data/default/fichierSalles.txt");
		SalleData salleData = new SalleData(ficherSalles);
		SalleController salleController = new SalleController(salleData);
		fen.getSallePanel().setController(salleController);
		salleData.addObserver(fen.getSallePanel());

		File fichierMatiere = new File("data/default/fichierMatieres.txt");
		MatiereData matiereData = new MatiereData(fichierMatiere);
		MatiereController matiereController = new MatiereController(matiereData);
		fen.getMatierePanel().setController(matiereController);
		matiereData.addObserver(fen.getMatierePanel());

		File fichierEnseignants = new File("data/default/fichierEnseignants.txt");
		EnseignantData enseignantData = new EnseignantData(fichierEnseignants);
		EnseignantController enseignantController = new EnseignantController(enseignantData);
		fen.getEnseignantPanel().setController(enseignantController);
		enseignantData.addObserver(fen.getEnseignantPanel());

		File fichierClasses = new File("data/default/fichierClasses.txt");
		ClasseData classData = new ClasseData(fichierClasses);
		ClassController classeController = new ClassController(classData);
		// ClassePanel classPanel = new ClassePanel();
		salleData.addObserver(classData);
		fen.getClassePanel().setController(classeController);
		classData.addObserver(fen.getClassePanel());
		matiereData.addObserver(classData);

		File fichierAssociation = new File("data/default/fichierAssociations.txt");
		if (!fichierAssociation.exists()) {
			EmploiDuTemps.reset();
			EmploiDuTemps.ecrire();
		}

		AssociationData associationData = new AssociationData(fichierAssociation);
		salleData.addObserver(associationData);
		associationData.addObserver(salleData);
		AssociationController associationController = new AssociationController(associationData);

		// AssociationPanel associationPanel = new AssociationPanel();
		fen.getAssociationPanel().setController(associationController);
		classData.addObserver(fen.getAssociationPanel());
		enseignantData.addObserver(fen.getAssociationPanel());
		associationData.addObserver(fen.getAssociationPanel());
		associationData.addObserver(enseignantData);
		associationData.addObserver(fen.getAffichagePanel());
		associationData.addObserver(fen.getModificationPanel());
		salleData.addObserver(fen.getModificationPanel());
		fen.getModificationPanel().getModificationData().addObserver(fen.getAffichagePanel());

		/*
		 * fen.getWorkspace().addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { JFileChooser chooser =
		 * new JFileChooser();
		 * chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 * chooser.setCurrentDirectory( new
		 * File("C:\\Users\\mohamad\\Documents\\Coding projects\\Java\\Projet\\data"));
		 * 
		 * if (chooser.showOpenDialog(fen) == JFileChooser.APPROVE_OPTION) {
		 * 
		 * fen.getPanes().setBorder( BorderFactory.createTitledBorder("Workspace: " +
		 * chooser.getSelectedFile().getName()));
		 * 
		 * File newFichierTemps = new File(chooser.getSelectedFile().getPath() +
		 * "/fichierTemps.txt"); File newFichierSalle = new
		 * File(chooser.getSelectedFile().getPath() + "/fichierSalles.txt"); File
		 * newFichierMatiere = new File(chooser.getSelectedFile().getPath() +
		 * "/fichierMatiere.txt"); File newfichierEnseignants = new File(
		 * chooser.getSelectedFile().getPath() + "/fichierEnseignants.txt"); File
		 * newFichierClasse = new File(chooser.getSelectedFile().getPath() +
		 * "/fichierClasse.txt"); File newFichierAssociation = new File(
		 * chooser.getSelectedFile().getPath() + "/fichierAssociation.txt");
		 * 
		 * EmploiDuTemps.ecrire(); EmploiDuTemps.reset();
		 * EmploiDuTemps.setFile(newFichierTemps); if (newFichierTemps.exists())
		 * EmploiDuTemps.read(); EmploiDuTemps.ecrire();
		 * 
		 * try { if (!newFichierTemps.exists()) newFichierTemps.createNewFile();
		 * 
		 * if (!newFichierSalle.exists()) newFichierSalle.createNewFile();
		 * 
		 * if (!newFichierMatiere.exists()) newFichierMatiere.createNewFile();
		 * 
		 * if (!newfichierEnseignants.exists()) newfichierEnseignants.createNewFile();
		 * 
		 * if (!newFichierClasse.exists()) newFichierClasse.createNewFile();
		 * 
		 * if (!newFichierAssociation.exists()) newFichierAssociation.createNewFile();
		 * 
		 * } catch (IOException io) { }
		 * 
		 * salleData.changeDirectory(newFichierSalle);
		 * salleData.setDataMap(salleData.read());
		 * 
		 * matiereData.changeDirectory(newFichierMatiere);
		 * matiereData.setDataMap(matiereData.read());
		 * 
		 * enseignantData.changeDirectory(newfichierEnseignants);
		 * enseignantData.setDataMap(enseignantData.read());
		 * 
		 * classData.changeDirectory(newFichierClasse);
		 * classData.setDataMap(classData.read());
		 * 
		 * associationData.changeDirectory(newFichierAssociation);
		 * associationData.setDataMap(associationData.read());
		 * 
		 * } else { System.out.println("No Selection "); }
		 * 
		 * } });
		 */

		fen.setVisible(true);

	}

}
