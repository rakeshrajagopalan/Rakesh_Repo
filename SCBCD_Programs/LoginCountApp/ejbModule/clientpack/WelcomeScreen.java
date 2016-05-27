package clientpack;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import entitybeantest.EntityBeanClass;

public class WelcomeScreen extends JFrame {

	private static final long serialVersionUID = 726891325764209927L;

	public WelcomeScreen(EntityBeanClass entityClass) {
		try {
			setTitle("WelcomeScreen!");
			setSize(300, 300);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			add(new JLabel("Welcome " + entityClass.getName()),
					BorderLayout.NORTH);
			add(new JLabel("Your visit count is: "
					+ entityClass.getVisitCount()), BorderLayout.CENTER);
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JButton button1 = new JButton("Exit");
			JButton button2 = new JButton("Back");
			panel.add(button2);
			panel.add(button1);
			add(panel, BorderLayout.SOUTH);
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new LoginClass().setVisible(true);
					WelcomeScreen.this.dispose();
				}
			});
			setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
