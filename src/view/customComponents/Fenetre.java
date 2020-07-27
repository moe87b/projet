package view.customComponents;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import model.Association;
import model.Classe;
import model.Enseignant;
import model.Matiere;
import model.Salle;
import model.data.AssociationData;
import model.data.ClasseData;
import model.data.EnseignantData;
import model.data.MatiereData;
import model.data.SalleData;
import model.horraires.EmploiDuTemps;
import view.AssociationPanel;
import view.ClassePanel;
import view.EnseignantPanel;
import view.MatierePanel;
import view.ModificationPanel;
import view.SallePanel;

public class Fenetre extends JFrame {
	private JTabbedPane panes = new JTabbedPane();
	private SallePanel sallePanel = new SallePanel();
	private MatierePanel matierePanel = new MatierePanel();
	private EnseignantPanel enseignantPanel = new EnseignantPanel();
	private ClassePanel classePanel = new ClassePanel();
	private AssociationPanel associationPanel = new AssociationPanel();
	private FinalDisplayPanel affichagePanel = new FinalDisplayPanel();
	private ModificationPanel modificationPanel = new ModificationPanel();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichiers = new JMenu("Fichier");
	private JMenuItem workspace = new JMenuItem("Workspace");
	private JMenu outils = new JMenu("Outils");
	private JMenuItem annuler = new JMenuItem("Annuler");
	private JMenuItem restaurer = new JMenuItem("Restaurer");
	private TitledBorder border = BorderFactory.createTitledBorder("Workspace: Default");
	// private FinalPanel finalPan
	// = new
	// FinalPanel();

	public Fenetre(String titre) {
		super(titre);

		fichiers.add(workspace);
		menuBar.add(fichiers);
		menuBar.add(outils);
		outils.add(restaurer);
		outils.add(annuler);
		setJMenuBar(menuBar);

		border.setTitleJustification(TitledBorder.CENTER);
		panes.setBorder(border);

		panes.add(sallePanel.getMainContainer(), "Salles");
		panes.add(matierePanel.getMainContainer(), "Matières");
		panes.add(enseignantPanel.getMainContainer(), "Enseignants");
		panes.add(classePanel.getMainContainer(), "Classes");
		panes.add(associationPanel.getTotalScrollPane(), "Horraires");
		panes.add(affichagePanel, "Affichage");
		panes.add(modificationPanel.getMainContainer(), "Modifier");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		// setResizable(false);
		setContentPane(panes);

		addListeners();

	}

	public void addListeners() {
		workspace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setCurrentDirectory(
						new File("C:\\Users\\mohamad\\Documents\\Coding projects\\Java\\Projet\\data"));

				if (chooser.showOpenDialog(Fenetre.this) == JFileChooser.APPROVE_OPTION) {

					// border.setTitle("Workspace: " + chooser.getSelectedFile().getName());

					TitledBorder newBorder = BorderFactory
							.createTitledBorder("Workspace: " + chooser.getSelectedFile().getName());
					newBorder.setTitleJustification(TitledBorder.CENTER);
					panes.setBorder(newBorder);

					File newFichierTemps = new File(chooser.getSelectedFile().getPath() + "/fichierTemps.txt");
					File newFichierSalle = new File(chooser.getSelectedFile().getPath() + "/fichierSalles.txt");
					File newFichierMatiere = new File(chooser.getSelectedFile().getPath() + "/fichierMatiere.txt");
					File newfichierEnseignants = new File(
							chooser.getSelectedFile().getPath() + "/fichierEnseignants.txt");
					File newFichierClasse = new File(chooser.getSelectedFile().getPath() + "/fichierClasse.txt");
					File newFichierAssociation = new File(
							chooser.getSelectedFile().getPath() + "/fichierAssociation.txt");

					EmploiDuTemps.ecrire();
					EmploiDuTemps.reset();
					EmploiDuTemps.setFile(newFichierTemps);
					if (newFichierTemps.exists())
						EmploiDuTemps.read();
					EmploiDuTemps.ecrire();

					try {
						if (!newFichierTemps.exists())
							newFichierTemps.createNewFile();

						if (!newFichierSalle.exists()) {
							newFichierSalle.createNewFile();
							Salle.setCompteur(0);
						}

						if (!newFichierMatiere.exists()) {
							newFichierMatiere.createNewFile();
							Matiere.updateCompteur(0);
						}

						if (!newfichierEnseignants.exists()) {
							newfichierEnseignants.createNewFile();
							Enseignant.updateCompteur(0);
						}

						if (!newFichierClasse.exists()) {
							newFichierClasse.createNewFile();
							Classe.setCompteur(0);
						}

						if (!newFichierAssociation.exists()) {
							newFichierAssociation.createNewFile();
							Association.updateCompteur(0);
						}

					} catch (IOException io) {
					}

					SalleData salleData = (SalleData) Fenetre.this.getSallePanel().getController().getData();
					salleData.changeDirectory(newFichierSalle);
					salleData.setDataMap((salleData.read()));

					MatiereData matiereData = (MatiereData) Fenetre.this.getMatierePanel().getController().getData();
					matiereData.changeDirectory(newFichierMatiere);
					matiereData.setDataMap(matiereData.read());

					EnseignantData enseignantData = (EnseignantData) Fenetre.this.getEnseignantPanel().getController()
							.getData();
					enseignantData.changeDirectory(newfichierEnseignants);
					enseignantData.setDataMap(enseignantData.read());

					ClasseData classData = (ClasseData) Fenetre.this.getClassePanel().getController().getData();
					classData.changeDirectory(newFichierClasse);
					classData.setDataMap(classData.read());

					AssociationData associationData = (AssociationData) Fenetre.this.getAssociationPanel()
							.getController().getData();
					associationData.changeDirectory(newFichierAssociation);
					associationData.setDataMap(associationData.read());

				} else {
					System.out.println("No Selection ");
				}

			}
		});

		restaurer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int confirmation = JOptionPane.showConfirmDialog(null,
						"Etes vous sur de vouloir supprimer toutes les données dans l'espace de travail courant ?",
						"Attention", JOptionPane.YES_NO_OPTION);

				if (confirmation == JOptionPane.NO_OPTION)
					return;

				SalleData salleData = (SalleData) Fenetre.this.getSallePanel().getController().getData();
				salleData.reset();

				MatiereData matiereData = (MatiereData) Fenetre.this.getMatierePanel().getController().getData();
				matiereData.reset();

				EnseignantData enseignantData = (EnseignantData) Fenetre.this.getEnseignantPanel().getController()
						.getData();
				enseignantData.reset();

				ClasseData classData = (ClasseData) Fenetre.this.getClassePanel().getController().getData();
				classData.reset();

				AssociationData associationData = (AssociationData) Fenetre.this.getAssociationPanel().getController()
						.getData();
				associationData.reset();

			}
		});

		annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String selectedPane = panes.getTitleAt(panes.getSelectedIndex());
				System.out.println(selectedPane);

			}
		});
	}

	public SallePanel getSallePanel() {
		return sallePanel;
	}

	public MatierePanel getMatierePanel() {
		return matierePanel;
	}

	public EnseignantPanel getEnseignantPanel() {
		return enseignantPanel;
	}

	public ClassePanel getClassePanel() {
		return this.classePanel;
	}

	public AssociationPanel getAssociationPanel() {
		return associationPanel;
	}

	public FinalDisplayPanel getAffichagePanel() {
		return affichagePanel;
	}

	public ModificationPanel getModificationPanel() {
		return modificationPanel;
	}

	public JTabbedPane getPanes() {
		return panes;
	}

	public JMenuItem getWorkspace() {
		return workspace;
	}

}