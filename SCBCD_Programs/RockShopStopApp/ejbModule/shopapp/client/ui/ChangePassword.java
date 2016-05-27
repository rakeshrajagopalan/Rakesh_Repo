package shopapp.client.ui;

import javax.swing.*;
import shopapp.beans.*;
import shopapp.entities.*;
import shopapp.utils.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ChangePassword extends JFrame {

	private static final long serialVersionUID = 959777352565928158L;

	private JPanel westPanel;

	private ArrayList<Object> stubList;

	private UtilityRemote remote;

	private UserManagerRemote userManagerRemote;

	private ShopManagerRemote shopManagerRemote;

	private JButton loginButton;

	private JButton homeButton;

	private JButton userDetailsButton;

	private JButton changePwdButton;

	private JButton checkOutButton;

	private JButton exitButton;

	private UserDetails currentUser;

	private JLabel currentPasswordLabel;

	private JPasswordField currentPasswordField;

	private JLabel newPasswordLabel;

	private JPasswordField newPasswordField;

	private JLabel confirmNewPasswordLabel;

	private JPasswordField confirmNewPasswordField;

	private JPanel centerPanel;

	private JButton changePasswordButton;

	private JPanel eastPanel;

	private JButton viewCartButton;

	private JButton clearCartButton;

	private List<CartTrakker> cartItems;

	public ChangePassword(ArrayList<Object> stubList) throws Exception {
		this.stubList = stubList;
		this.remote = (UtilityRemote) stubList.get(0);
		this.userManagerRemote = (UserManagerRemote) stubList.get(1);
		this.shopManagerRemote = (ShopManagerRemote) stubList.get(2);
		currentUser = userManagerRemote.getCurrentUser();
		commonGUISettings();
		createWestPanel();
		createEastPanel();
		createOtherPanels();
		constructGUI();
	}

	private void createEastPanel() throws Exception {
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		viewCartButton = new JButton("View Cart");
		clearCartButton = new JButton("Clear Cart");
		JLabel cartLabel = null;
		if (userManagerRemote == null
				|| userManagerRemote.getCurrentUser() == null) {
			cartItems = remote
					.fetchUserCartDetails(ApplicationConstants.INITIAL_USER_NAME);
			if (cartItems != null && cartItems.size() > 0) {
				cartLabel = new JLabel("You have " + cartItems.size());
				viewCartButton.setEnabled(true);
				clearCartButton.setEnabled(true);
				checkOutButton.setEnabled(true);
			} else {
				cartLabel = new JLabel("You have 0");
				viewCartButton.setEnabled(false);
				clearCartButton.setEnabled(false);
				checkOutButton.setEnabled(false);
			}
			eastPanel.add(cartLabel);
			cartLabel = new JLabel(" items on cart");
			eastPanel.add(cartLabel);
		} else {
			cartItems = userManagerRemote.getCurrentCart();
			if (cartItems != null && cartItems.size() > 0) {
				cartLabel = new JLabel("You have " + cartItems.size());
				viewCartButton.setEnabled(true);
				clearCartButton.setEnabled(true);
				checkOutButton.setEnabled(true);
			} else {
				cartLabel = new JLabel("You have 0");
				viewCartButton.setEnabled(false);
				clearCartButton.setEnabled(false);
				checkOutButton.setEnabled(false);
			}
			eastPanel.add(cartLabel);
			cartLabel = new JLabel(" items on cart");
			eastPanel.add(cartLabel);
		}
		eastPanel.add(viewCartButton);
		eastPanel.add(clearCartButton);
		add(new JScrollPane(eastPanel), BorderLayout.EAST);
	}

	private void commonGUISettings() throws Exception {
		setTitle(ApplicationConstants.CHANGE_PASSWORD_TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height = dim.height / 4;
		dim.width = dim.width / 3;
		setBounds(dim.width, dim.height, ApplicationConstants.SCREEN_WIDTH,
				ApplicationConstants.SCREEN_HEIGHT);
	}

	private void createWestPanel() throws Exception {
		westPanel = new UtilityClass().createWestPanel(this, userManagerRemote);
		for (int i = 0; i < westPanel.getComponentCount(); i++) {
			JButton button = (JButton) westPanel.getComponent(i);
			if (button.getText().startsWith("Log")) {
				loginButton = button;
			} else if (button.getText().equalsIgnoreCase("Home")) {
				homeButton = button;
			} else if (button.getText().equalsIgnoreCase("My Details")) {
				userDetailsButton = button;
			} else if (button.getText().equalsIgnoreCase("Change Pwd")) {
				changePwdButton = button;
			} else if (button.getText().equalsIgnoreCase("Checkout")) {
				checkOutButton = button;
			} else if (button.getText().equalsIgnoreCase("Exit")) {
				exitButton = button;
			}
		}
		add(westPanel, BorderLayout.WEST);
	}

	private void createOtherPanels() throws Exception {
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tempPanel.add(new JLabel("Change Password"));
		tempPanel.add(new JLabel(""));
		add(tempPanel, BorderLayout.NORTH);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		currentPasswordLabel = new JLabel("Current Password");
		currentPasswordField = new JPasswordField(10);
		currentPasswordField
				.setEchoChar(ApplicationConstants.PASSWORD_ECHO_CHAR);
		tempPanel.add(currentPasswordLabel);
		tempPanel.add(currentPasswordField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		newPasswordLabel = new JLabel("New Password");
		newPasswordField = new JPasswordField(10);
		newPasswordField.setEchoChar(ApplicationConstants.PASSWORD_ECHO_CHAR);
		tempPanel.add(newPasswordLabel);
		tempPanel.add(newPasswordField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		confirmNewPasswordLabel = new JLabel("Confirm Password");
		confirmNewPasswordField = new JPasswordField(10);
		confirmNewPasswordField
				.setEchoChar(ApplicationConstants.PASSWORD_ECHO_CHAR);
		tempPanel.add(confirmNewPasswordLabel);
		tempPanel.add(confirmNewPasswordField);
		centerPanel.add(tempPanel);
		add(new JScrollPane(centerPanel), BorderLayout.CENTER);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		changePasswordButton = new JButton("Change Password");
		tempPanel.add(changePasswordButton);
		add(tempPanel, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(changePasswordButton);
		currentPasswordField.grabFocus();
	}

	private void constructGUI() throws Exception {
		changePasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatePassword();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Home(stubList).setVisible(true);
					ChangePassword.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (userManagerRemote.getCurrentUser() == null) {
						new Login(stubList).setVisible(true);
						ChangePassword.this.dispose();
					} else {
						userManagerRemote.removeCurrentUser();
						stubList.remove(userManagerRemote);
						stubList.remove(shopManagerRemote);
						new Login(stubList).setVisible(true);
						ChangePassword.this.dispose();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		exitButton.addActionListener(UtilityClass.exitButtonAction(remote,
				userManagerRemote));
		changePwdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		userDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ChangeDetails(stubList).setVisible(true);
					ChangePassword.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		viewCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new CartDetails(stubList).setVisible(true);
					ChangePassword.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		addWindowListener(UtilityClass.exitAction(remote, userManagerRemote));
	}

	private void updatePassword() throws Exception {
		String originalPassword = new String(currentPasswordField.getPassword());
		String newPassword = new String(newPasswordField.getPassword());
		String confirmPassword = new String(confirmNewPasswordField
				.getPassword());
		if (originalPassword == null || originalPassword.trim().equals("")
				|| newPassword == null || newPassword.trim().equals("")
				|| confirmPassword == null || confirmPassword.trim().equals("")) {
			JOptionPane.showMessageDialog(ChangePassword.this,
					"Password cannot be left empty");
			currentPasswordField.grabFocus();
		} else if (!(originalPassword.equals(currentUser.getUserPassword()))) {
			JOptionPane.showMessageDialog(ChangePassword.this,
					"Old password is not correct");
			currentPasswordField.grabFocus();
		} else if (!(newPassword.equals(confirmPassword))) {
			JOptionPane.showMessageDialog(ChangePassword.this,
					"Passwords doesnt not match");
			newPasswordField.setText("");
			confirmNewPasswordField.setText("");
			newPasswordField.grabFocus();
		} else {
			userManagerRemote.changePassword(newPassword);
			JOptionPane.showMessageDialog(ChangePassword.this,
					"Password changed successfully");
			new Home(stubList).setVisible(true);
			ChangePassword.this.dispose();
		}
	}

}
