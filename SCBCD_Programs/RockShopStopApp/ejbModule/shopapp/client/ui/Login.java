package shopapp.client.ui;

import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import javax.swing.*;
import shopapp.beans.*;
import shopapp.entities.*;
import shopapp.utils.*;

public class Login extends JFrame {

	private static final long serialVersionUID = 40616242353799967L;

	private JPanel bigPanel;

	private JPanel loginPanel;

	private JLabel loginLabel;

	private JTextField loginField;

	private JLabel passwordLabel;

	private JPasswordField passwordField;

	private JButton loginButton;

	private JButton homeButton;

	private JButton registerButton;

	private String password;

	private UtilityRemote remote;

	private UserManagerRemote userManagerRemote;

	private ShopManagerRemote shopManagerRemote;

	private ArrayList<Object> stubList;

	public Login(ArrayList<Object> stubList) throws Exception {
		if (stubList != null && stubList.size() > 1) {
			this.stubList = stubList;
			this.remote = (UtilityRemote) stubList.get(0);
			this.userManagerRemote = (UserManagerRemote) stubList.get(1);
			this.shopManagerRemote = (ShopManagerRemote) stubList.get(2);
		}
		this.stubList = stubList;
		this.remote = (UtilityRemote) stubList.get(0);
		commonGUISettings();
		constructGUI();
	}

	private void commonGUISettings() throws Exception {
		setTitle(ApplicationConstants.LOGIN_TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height = dim.height / 4;
		dim.width = dim.width / 3;
		setBounds(dim.width, dim.height, ApplicationConstants.SCREEN_WIDTH,
				ApplicationConstants.SCREEN_HEIGHT);
	}

	private void constructGUI() throws Exception {
		add(new JLabel("Welcome to The Rock Shop Stop App"), BorderLayout.NORTH);
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
		homeButton = new JButton("Home");
		registerButton = new JButton("Register");
		loginPanel.add(loginButton);
		loginPanel.add(registerButton);
		loginPanel.add(homeButton);
		add(loginPanel, BorderLayout.SOUTH);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					doLogin();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Register(stubList).setVisible(true);
					Login.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Home(stubList).setVisible(true);
					Login.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		getRootPane().setDefaultButton(loginButton);
		addWindowListener(UtilityClass.exitAction(remote, userManagerRemote));
	}

	private void doLogin() throws Exception {
		password = new String(passwordField.getPassword());
		byte[] plainText = password.getBytes();
		byte[] digest = new byte[50];
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainText, 0, plainText.length);
		String encText = new String(md.digest(digest));
		UserDetails userDetails = remote.validateLogin(loginField.getText(),
				encText);
		if (userDetails == null) {
			JOptionPane.showMessageDialog(this, "Incorrect username/password");
			passwordField.setText("");
			passwordField.grabFocus();
		} else if (userManagerRemote == null) {
			userManagerRemote = (UserManagerRemote) UtilityClass
					.lookUpBeans(ApplicationConstants.USER_MANAGER_BEAN_NAME);
			userManagerRemote.setCurrentUser(userDetails.getUserName());
			shopManagerRemote = (ShopManagerRemote) UtilityClass
					.lookUpBeans(ApplicationConstants.SHOP_MANAGER_BEAN_NAME);
			userManagerRemote.doCartMigration();
			stubList = new ArrayList<Object>();
			stubList.add(remote);
			stubList.add(userManagerRemote);
			stubList.add(shopManagerRemote);
			new Home(stubList).setVisible(true);
			dispose();
		}
	}

}
