package demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class TableTest {

	private JFrame frame;

	private JPanel panel;

	private JTable table;

	private JPanel buttonsPanel;

	private JButton button;

	private JButton addButton;

	public TableTest() {

	}

	public void createAndShowGUI() throws Exception {
		frame = new JFrame();
		frame.setTitle("Table Test");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		List<String> colNames = new ArrayList<String>();
		colNames.add("Name");
		colNames.add("Age");
		colNames.add("City");
		colNames.add("Employed");
		List<List<Object>> rowData = new ArrayList<List<Object>>();
		List<Object> rowData1 = new ArrayList<Object>();
		rowData1.add("Rakesh");
		rowData1.add("27");
		rowData1.add("Chennai");
		rowData1.add(true);
		rowData.add(rowData1);
		List<Object> rowData2 = new ArrayList<Object>();
		rowData2.add("Shrikar");
		rowData2.add("1");
		rowData2.add("Bangalore");
		rowData2.add(false);
		rowData.add(rowData2);
		List<Object> rowData3 = new ArrayList<Object>();
		rowData3.add("Anish");
		rowData3.add("3");
		rowData3.add("Texas");
		rowData3.add(false);
		rowData.add(rowData3);
		table = new JTable();
		table.setAutoCreateColumnsFromModel(true);
		table.setModel(new TempTableModel(colNames, rowData));
		TableCellRenderer renderer = new EvenOddRenderer();
		table.setDefaultRenderer(Object.class, renderer);
		table.setPreferredScrollableViewportSize(new Dimension(400, 200));
		panel.add(table.getTableHeader());
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		changeEditor(table, table.getColumnModel().getColumn(2));
		JScrollPane scroller = new JScrollPane(table);
		panel.add(scroller);
		frame.add(panel, BorderLayout.CENTER);
		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addButton = new JButton("Add");
		button = new JButton("Exit");
		buttonsPanel.add(addButton);
		buttonsPanel.add(button);
		frame.add(buttonsPanel, BorderLayout.SOUTH);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		frame.getRootPane().setDefaultButton(addButton);
		frame.setVisible(true);
	}

	private void changeEditor(JTable table, TableColumn cityColumn) {
		Vector<String> cityList = new Vector<String>();
		cityList.add("Chennai");
		cityList.add("Banglaore");
		cityList.add("Texas");
		cityList.add("Unspecified");
		JComboBox<String> cityComboBox = new JComboBox<String>(cityList);
		cityColumn.setCellEditor(new DefaultCellEditor(cityComboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		cityColumn.setCellRenderer(renderer);
	}

	private void updateTable() {
		List<Object> temp = new ArrayList<Object>();
		temp.add("Vikram");
		temp.add("30");
		temp.add("Not Specified");
		temp.add(false);
		((TempTableModel) table.getModel()).addRow(temp);
		table.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new TableTest().createAndShowGUI();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}

class EvenOddRenderer implements TableCellRenderer {

	public static final DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = tableRenderer.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		Color background, foreground;
		if (row % 2 == 0) {
			foreground = Color.BLUE;
			background = Color.WHITE;
		} else {
			foreground = Color.WHITE;
			background = Color.BLUE;
		}
		renderer.setForeground(foreground);
		renderer.setBackground(background);
		return renderer;
	}

}

// MODEL
class TempTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -4711312742440170993L;

	private List<String> colNames;

	private List<List<Object>> rowData;

	public TempTableModel(List<String> colNames, List<List<Object>> rowData) {
		this.colNames = colNames;
		this.rowData = rowData;
	}

	public List<List<Object>> getRowData() {
		return rowData;
	}

	@Override
	public int getRowCount() {
		System.out.println("Total rows: " + rowData.size());
		return rowData.size();
	}

	@Override
	public int getColumnCount() {
		System.out.println("Total columns: " + colNames.size());
		return colNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object colValue = rowData.get(rowIndex).get(columnIndex);
		return colValue;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public String getColumnName(int col) {
		return colNames.get(col);
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		rowData.get(row).set(col, value);
		System.out.println("setValueAt: Row: " + row + " col " + col);
		fireTableCellUpdated(row, col);
	}

	public void addRow(List<Object> rowToAdd) {
		rowData.add(rowToAdd);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

}
