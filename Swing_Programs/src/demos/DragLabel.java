package demos;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class DragLabel {

	public DragLabel() {

	}

	public void createAndShowGUI() throws Exception {
		JFrame frame = new JFrame("Drag Label");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		JTextField label = new JTextField("Hello World");
		label.setTransferHandler(new TextFieldTransferHandler());
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				dragAction(event);
			}

		});
		JTextField field = new JTextField();
		field.setDropMode(DropMode.INSERT);
		frame.add(label, BorderLayout.CENTER);
		frame.add(field, BorderLayout.NORTH);
		frame.setVisible(true);
	}

	private void dragAction(MouseEvent event) {
		JComponent comp = (JComponent) event.getSource();
		TransferHandler handler = comp.getTransferHandler();
		handler.exportAsDrag(comp, event, TransferHandler.COPY);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new DragLabel().createAndShowGUI();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}

class TextFieldTransferHandler extends TransferHandler {
	@Override
	public boolean importData(TransferHandler.TransferSupport info) {
		try {
			JTextField field = (JTextField) info.getComponent();
			String existingText = field.getText();
			String newText = (String) info.getTransferable().getTransferData(
					DataFlavor.stringFlavor);
			field.setText(existingText + newText);
			field.repaint();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public boolean canImport(TransferHandler.TransferSupport info) {
		// Check for String flavor
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return false;
		}
		return true;
	}

	protected Transferable createTransferable(JComponent c) {
		return new StringSelection(((JTextField) c).getText());
	}

	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY;
	}

}