package com.java8.chap01;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingTest extends JFrame {
	
	private static final long serialVersionUID = -1646468L;

	public SwingTest() {
		setSize(500,200);
		setTitle("Java 8 Lambda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		constructFrame();
	}
	
	private void constructFrame() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton button = new JButton("Click Me");
		JLabel label = new JLabel();
		button.addActionListener((ActionEvent) -> label.setText("Inside Lambda"));
		panel.add(button);
		panel.add(label);
		add(panel);
	}
	
	public static void main(String[] args) {
		new SwingTest().setVisible(true);
	}
}
