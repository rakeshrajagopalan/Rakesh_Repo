package scjppack;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import java.awt.event.*;

public class SwingTest extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel p1 = new JPanel();

	private JLabel l1 = new JLabel("User Name");

	private JTextField t1 = new JTextField(10);

	private JLabel l2 = new JLabel("Password");

	private JTextField t2 = new JTextField(10);

	private JButton b1 = new JButton("Login");

	private JButton b2 = new JButton("Cancel");

	SwingTest() {
		try {
			setTitle("Swing Test");
			setSize(200, 200);
			for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(laf.getName())) {
					UIManager.setLookAndFeel(laf.getClassName());
				}
			}
			createAndShowGUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void createAndShowGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		p1.add(b1);
		p1.add(b2);
		this.getContentPane().add(p1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (t1.getText().equalsIgnoreCase(t2.getText())) {
					JOptionPane.showMessageDialog(SwingTest.this, "Success");
				}
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		SwingTest st = new SwingTest();
		st.setVisible(true);
	}

}
