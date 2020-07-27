package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AllocationsPanel extends JPanel {

	private String[] columnNames = { "Classe", "Enseignant" };
	private Object[][] data = { { "", "" } };
	private DefaultTableModel model = new DefaultTableModel(data, columnNames);
	private JTable table = new JTable(model);
	private JScrollPane scroll = new JScrollPane(table);

	public AllocationsPanel() {
		reset();
		table.setFillsViewportHeight(true);
		this.add(scroll);

	}

	public void addRow(Object[] data) {
		model.addRow(data);

	}

	public void reset() {
		int rowCount = model.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}

	}

}
