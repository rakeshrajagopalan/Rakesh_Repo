package testpack;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.*;

public class EventDispatcherExample extends JPanel {
	private static final long serialVersionUID = -6670324727077830092L;
	boolean running;
	int red = 0;
	int blue = 1;
	int s = 2;
	int thread;
	ColorTableModel colorTableModel = new ColorTableModel();
	Thread colorShadeThread;

	public EventDispatcherExample() {
		JTable table = new JTable(colorTableModel);
		table.setRowHeight(100);
		table.setDefaultRenderer(Object.class, new ColorRenderer());
		add(table);
		ButtonGroup value = new ButtonGroup();
		JButton button1 = new JButton("Red");
		value.add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				thread = red;
			}
		});
		JButton button2 = new JButton("Blue");
		value.add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				thread = blue;
			}
		});
		add(button1);
		add(button2);

		this.running = true;
		this.colorShadeThread = new Thread(new RandomColorShadeRunnable());
		this.colorShadeThread.start();
	}

	private class ColorTableModel extends AbstractTableModel {
		private Color[][] colors = new Color[3][3];

		public ColorTableModel() {
			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					colors[i][j] = Color.white;
				}
			}
		}

		public int getRowCount() {
			return s;
		}

		public int getColumnCount() {
			return s;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			return colors[rowIndex][columnIndex];
		}

		public void generateRandomColor(int type) {
			Random random = new Random(System.currentTimeMillis());
			final int row = random.nextInt(s);
			final int column = random.nextInt(s);
			final Color color;
			if (type == red) {
				color = new Color(random.nextInt(200), 0, 0);
			} else if (type == blue) {
				color = new Color(0, 0, random.nextInt(200));
			} else {
				color = new Color(random.nextInt(200), random.nextInt(200),
						random.nextInt(200));
			}

			if (SwingUtilities.isEventDispatchThread()) {
				colors[row][column] = color;
				fireTableCellUpdated(row, column);
			} else {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						colors[row][column] = color;
						fireTableCellUpdated(row, column);
					}
				});
			}
		}
	}

	private class ColorRenderer implements TableCellRenderer {
		private JLabel label;

		public ColorRenderer() {
			label = new JLabel();
			label.setOpaque(true);
			label.setPreferredSize(new Dimension(102, 102));
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			label.setBackground((Color) value);
			return label;
		}
	}

	private class RandomColorShadeRunnable implements Runnable {
		public void run() {
			while (running) {
				colorTableModel.generateRandomColor(thread);
				try {
					Thread.sleep(600);
				} catch (Exception e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Event Dispatch Example");
		frame.add(new EventDispatcherExample());
		frame.setSize(450, 300);
		frame.setVisible(true);
	}
}
