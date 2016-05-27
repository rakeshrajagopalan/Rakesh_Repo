package shopapp.client.mdblogic;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shopapp.entities.UserDetails;

public class ShowGUI extends JFrame {

	private static final long serialVersionUID = 4262125152170193238L;

	private UserDetails userDetails;

	public ShowGUI(UserDetails userDetails) throws Exception {
		setTitle("Couriered!");
		this.userDetails = userDetails;
		constructGUI();
	}

	private void constructGUI() throws Exception {
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel("The courier details"));
		panel.add(new JLabel(userDetails.getUserAddress()));
		add(panel);
	}

}
