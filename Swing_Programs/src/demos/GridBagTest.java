package demos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

public class GridBagTest extends JFrame {

	private static final long serialVersionUID = -7395411726275359033L;

	private JLabel nameLabel;

	private JTextField nameTextField;

	private JLabel ageLabel;

	private JTextField ageTextField;

	private JLabel dobLabel;

	private JComboBox<String> dateComboBox;

	private JComboBox<String> monthComboBox;

	private JComboBox<String> yearComboBox;

	private JLabel addressLabel;

	private JTextArea addressTextArea;

	private JPanel mainPanel;

	private JButton okButton;

	private JButton cancelButton;

	private JPanel buttonsPanel;

	private UndoManager undoManager;

	public GridBagTest() {

	}

	public void createAndShowGUI() {
		try {
			setTitle("Grid Bag Test");
			setSize(400, 300);
			setLayout(new BorderLayout());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			nameLabel = new JLabel("Name", JLabel.CENTER);
			nameTextField = new JTextField(10);
			nameTextField.setTransferHandler(new TransferHandler("foreground"));
			nameTextField.setDragEnabled(true);
			ageLabel = new JLabel("Age", JLabel.CENTER);
			ageTextField = new JTextField(10);
			dobLabel = new JLabel("DOB", JLabel.CENTER);
			Vector<String> dateList = new Vector<String>();
			for (int i = 1; i <= 31; i++) {
				dateList.add(String.valueOf(i));
			}
			dateComboBox = new JComboBox<String>(dateList);
			Vector<String> monthList = new Vector<String>();
			for (int i = 1; i <= 12; i++) {
				monthList.add(String.valueOf(i));
			}
			monthComboBox = new JComboBox<String>(monthList);
			Vector<String> yearList = new Vector<String>();
			for (int i = 1900; i <= 2012; i++) {
				yearList.add(String.valueOf(i));
			}
			yearComboBox = new JComboBox<String>(yearList);
			yearComboBox.setSelectedItem("1970");
			addressLabel = new JLabel("Address");
			addressTextArea = new JTextArea(10, 10);
			mainPanel = new JPanel();
			mainPanel.setLayout(new GridBagLayout());
			GridBagConstraints mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.anchor = GridBagConstraints.WEST;
			mainPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 1;
			mainPanelConstraints.gridx = 0;
			mainPanelConstraints.gridy = 0;
			mainPanel.add(nameLabel, mainPanelConstraints);
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 3;
			mainPanelConstraints.gridx = 1;
			mainPanelConstraints.gridy = 0;
			mainPanel.add(nameTextField, mainPanelConstraints);
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 1;
			mainPanelConstraints.gridx = 0;
			mainPanelConstraints.gridy = 1;
			mainPanel.add(ageLabel, mainPanelConstraints);
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 3;
			mainPanelConstraints.gridx = 1;
			mainPanelConstraints.gridy = 1;
			mainPanel.add(ageTextField, mainPanelConstraints);
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 1;
			mainPanelConstraints.gridx = 0;
			mainPanelConstraints.gridy = 2;
			mainPanel.add(dobLabel, mainPanelConstraints);
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 1;
			mainPanelConstraints.gridx = 1;
			mainPanelConstraints.gridy = 2;
			mainPanel.add(dateComboBox, mainPanelConstraints);
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 1;
			mainPanelConstraints.gridx = 2;
			mainPanelConstraints.gridy = 2;
			mainPanel.add(monthComboBox, mainPanelConstraints);
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 1;
			mainPanelConstraints.gridx = 3;
			mainPanelConstraints.gridy = 2;
			mainPanel.add(yearComboBox, mainPanelConstraints);
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 1;
			mainPanelConstraints.gridx = 0;
			mainPanelConstraints.gridy = 3;
			mainPanel.add(addressLabel, mainPanelConstraints);
			JScrollPane scroller = new JScrollPane(addressTextArea,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			addressTextArea.setWrapStyleWord(true);
			addressTextArea.setLineWrap(true);
			addressTextArea.setAutoscrolls(true);
			addressTextArea.setBorder(BorderFactory.createEtchedBorder());
			nameTextField.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					JComponent comp = (JComponent) e.getSource();
					TransferHandler handler = comp.getTransferHandler();
					handler.exportAsDrag(comp, e, TransferHandler.COPY);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					JComponent comp = (JComponent) e.getSource();
					TransferHandler handler = comp.getTransferHandler();
					handler.exportAsDrag(comp, e, TransferHandler.COPY);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JComponent comp = (JComponent) e.getSource();
					TransferHandler handler = comp.getTransferHandler();
					handler.exportAsDrag(comp, e, TransferHandler.COPY);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JComponent comp = (JComponent) e.getSource();
					TransferHandler handler = comp.getTransferHandler();
					handler.exportAsDrag(comp, e, TransferHandler.COPY);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					JComponent comp = (JComponent) e.getSource();
					TransferHandler handler = comp.getTransferHandler();
					handler.exportAsDrag(comp, e, TransferHandler.COPY);
				}
			});
			undoManager = new UndoManager();
			Document model = addressTextArea.getDocument();
			model.addUndoableEditListener(new UndoableEditListener() {
				@Override
				public void undoableEditHappened(UndoableEditEvent arg0) {
					undoManager.addEdit(arg0.getEdit());
				}
			});
			addressTextArea.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.isControlDown()
							&& arg0.getKeyCode() == KeyEvent.VK_Z) {
						undo();
					}
					if (arg0.isControlDown()
							&& arg0.getKeyCode() == KeyEvent.VK_Y) {
						redo();
					}
				}
			});
			mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.gridheight = 1;
			mainPanelConstraints.gridwidth = 3;
			mainPanelConstraints.gridx = 1;
			mainPanelConstraints.gridy = 3;
			mainPanel.add(scroller, mainPanelConstraints);
			JScrollPane mainPanelScroller = new JScrollPane(mainPanel,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			add(mainPanelScroller, BorderLayout.CENTER);
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					okButtonAction();
				}
			});
			cancelButton = new JButton("Exit");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					exitButtonAction();
				}
			});
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			buttonsPanel.add(okButton);
			buttonsPanel.add(cancelButton);
			add(buttonsPanel, BorderLayout.SOUTH);
			getRootPane().setDefaultButton(okButton);
			setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void okButtonAction() {

	}

	private void exitButtonAction() {
		System.exit(0);
	}

	private void undo() {
		if (undoManager.canUndo()) {
			undoManager.undo();
		}
	}

	private void redo() {
		if (undoManager.canRedo()) {
			undoManager.redo();
		}
	}

	public static void main(String[] args) {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new GridBagTest().createAndShowGUI();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
