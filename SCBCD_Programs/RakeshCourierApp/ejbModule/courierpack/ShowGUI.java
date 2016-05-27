package courierpack;

import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowGUI extends JFrame {

	private static final long serialVersionUID = 4262125152170193238L;

	public ShowGUI(List<String> userDetails) throws Exception {
		setTitle("Couriered!");
		constructGUI(userDetails);
	}

	private void constructGUI(List<String> userDetails) throws Exception {
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		String name = userDetails.get(0);
		String address = userDetails.get(1);
		String items = userDetails.get(2);
		String transactionID = userDetails.get(3);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("The courier details: "));
		panel.add(new JLabel("Transaction ID: " + transactionID));
		panel.add(new JLabel("Name: " + name));
		panel.add(new JLabel("Address: " + address));
		panel.add(new JLabel("Items: " + items));
		add(panel);
	}

}
