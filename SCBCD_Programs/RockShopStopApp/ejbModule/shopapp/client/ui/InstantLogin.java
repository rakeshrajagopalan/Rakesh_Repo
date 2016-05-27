package shopapp.client.ui;

import java.util.*;
import javax.swing.*;

import shopapp.beans.*;
import shopapp.utils.ApplicationConstants;

import java.awt.*;
import java.awt.event.*;

public class InstantLogin extends JDialog {

	private static final long serialVersionUID = -3852726519855122131L;

	private ArrayList<Object> stubList;

	private UtilityRemote utilityRemote;
	
	private UserManagerRemote userManagerRemote;

	private ShopManagerRemote shopManagerRemote;
	
	private JPanel bigPanel;

	private JPanel loginPanel;

	private JLabel loginLabel;

	private JTextField loginField;

	private JLabel passwordLabel;

	private JPasswordField passwordField;

	private JButton loginButton;

	private JButton cancelButton;
	
	public InstantLogin(ArrayList<Object> stubList) throws Exception {
		this.stubList = stubList;
		utilityRemote = (UtilityRemote) stubList.get(0);
		commonGUISettings();
		constructGUI();
		
	}

	private void commonGUISettings() throws Exception {
		setTitle(ApplicationConstants.INSTANT_LOGIN_TITLE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height = (int) (dim.width / 3);
		dim.width = (int) (dim.width / 3);
		setBounds(dim.width, dim.height,
				ApplicationConstants.INSTANT_LOGIN_WIDTH,
				ApplicationConstants.INSTANT_LOGIN_HEIGHT);
		setModal(true);
	}

	private void constructGUI() throws Exception {
		bigPanel = new JPanel();
		bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
		loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		loginLabel = new JLabel("User Name");
		loginField = new JTextField(10);
		loginPanel.add(loginLabel);
		loginPanel.add(loginField);
		bigPanel.add(loginPanel);
		loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(10);
		passwordField.setEchoChar(ApplicationConstants.PASSWORD_ECHO_CHAR);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);
		bigPanel.add(loginPanel);
		add(bigPanel, BorderLayout.CENTER);
		loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
	}

}
