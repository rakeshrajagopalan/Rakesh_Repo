package clientpack;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ExitScreen extends JFrame {

	private static final long serialVersionUID = 1623017422761677530L;
	
	public ExitScreen() {
		try {
			setTitle("ExitApplication?");
			setSize(300, 300);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			add(new JLabel("Incorrect UserID/Password"), BorderLayout.CENTER);
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
					ExitScreen.this.dispose();
				}
			});
			setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
