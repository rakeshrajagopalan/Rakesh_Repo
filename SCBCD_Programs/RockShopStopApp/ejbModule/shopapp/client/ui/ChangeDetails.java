package shopapp.client.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import shopapp.beans.*;
import shopapp.entities.*;
import shopapp.utils.*;

public class ChangeDetails extends JFrame {

	private static final long serialVersionUID = 5144084170385434189L;

	private JPanel centerPanel;

	private JButton homeButton;

	private JButton loginButton;

	private JButton userDetailsButton;

	private JButton changePwdButton;

	private JButton checkOutButton;

	private JButton exitButton;

	private JPanel westPanel;

	private UtilityRemote remote;

	private UserManagerRemote userManagerRemote;

	private ShopManagerRemote shopManagerRemote;

	private ArrayList<Object> stubList;

	private JLabel firstNameLabel;

	private JTextField firstNameField;

	private JLabel lastNameLabel;

	private JTextField lastNameField;

	private JLabel addressLabel;

	private JTextArea addressField;

	private JLabel emailIDLabel;

	private JTextField emailIDField;

	private UserDetails currentUser;

	private JButton updateButton;

	private JButton refreshButton;

	private JPanel eastPanel;

	private JButton viewCartButton;

	private JButton clearCartButton;

	private List<CartTrakker> cartItems;

	public ChangeDetails(ArrayList<Object> stubList) throws Exception {
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

	private void constructGUI() throws Exception {
		userDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Home(stubList).setVisible(true);
					ChangeDetails.this.dispose();
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
						ChangeDetails.this.dispose();
					} else {
						userManagerRemote.removeCurrentUser();
						stubList.remove(userManagerRemote);
						stubList.remove(shopManagerRemote);
						new Login(stubList).setVisible(true);
						ChangeDetails.this.dispose();
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
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateDetails();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		changePwdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ChangePassword(stubList).setVisible(true);
					ChangeDetails.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userManagerRemote.setCurrentUser(currentUser.getUserName());
					refreshDetails();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		viewCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new CartDetails(stubList).setVisible(true);
					ChangeDetails.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		addWindowListener(UtilityClass.exitAction(remote, userManagerRemote));
	}

	private void refreshDetails() throws Exception {
		currentUser = userManagerRemote.getCurrentUser();
		firstNameField.setText(currentUser.getFirstName());
		lastNameField.setText(currentUser.getLastName());
		addressField.setText(currentUser.getUserAddress());
		emailIDField.setText(currentUser.getEmailID());
		repaint();
		firstNameField.grabFocus();
	}

	private void updateDetails() throws Exception {
		Map<String, String> userDetails = new HashMap<String, String>();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String address = addressField.getText();
		String emailID = emailIDField.getText();
		if (firstName == null || firstName.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "First Name cannot be empty");
			firstNameField.grabFocus();
		} else if (lastName == null || lastName.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Last Name cannot be empty");
			lastNameField.grabFocus();
		} else if (address == null || address.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Address cannot be empty");
			addressField.grabFocus();
		} else if (emailID == null || emailID.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Email ID cannot be empty");
			emailIDField.grabFocus();
		} else {
			userDetails.put("firstname", firstName);
			userDetails.put("lastname", lastName);
			userDetails.put("address", address);
			userDetails.put("emailid", emailID);
			userManagerRemote.updateUserDetails(userDetails);
			JOptionPane.showMessageDialog(this, "Details updated successfully");
			new Home(stubList).setVisible(true);
			ChangeDetails.this.dispose();
		}
	}

	private void commonGUISettings() throws Exception {
		setTitle(ApplicationConstants.CHANGE_DETAILS_TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height = dim.height / 4;
		dim.width = dim.width / 3;
		setBounds(dim.width, dim.height, ApplicationConstants.SCREEN_WIDTH,
				ApplicationConstants.SCREEN_HEIGHT);
	}

	private void createOtherPanels() throws Exception {
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tempPanel.add(new JLabel("Update your details"));
		tempPanel.add(new JLabel(""));
		add(tempPanel, BorderLayout.NORTH);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		firstNameLabel = new JLabel("First Name");
		firstNameField = new JTextField(10);
		tempPanel.add(firstNameLabel);
		tempPanel.add(firstNameField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lastNameLabel = new JLabel("Last Name");
		lastNameField = new JTextField(10);
		tempPanel.add(lastNameLabel);
		tempPanel.add(lastNameField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addressLabel = new JLabel("Address");
		addressField = new JTextArea(5, 10);
		addressField.setWrapStyleWord(true);
		addressField.setLineWrap(true);
		addressField.setBorder(BorderFactory.createEtchedBorder());
		tempPanel.add(addressLabel);
		tempPanel.add(addressField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailIDLabel = new JLabel("Email ID");
		emailIDField = new JTextField(10);
		tempPanel.add(emailIDLabel);
		tempPanel.add(emailIDField);
		centerPanel.add(tempPanel);
		firstNameField.setText(currentUser.getFirstName());
		lastNameField.setText(currentUser.getLastName());
		addressField.setText(currentUser.getUserAddress());
		emailIDField.setText(currentUser.getEmailID());
		add(new JScrollPane(centerPanel), BorderLayout.CENTER);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		updateButton = new JButton("Update Details");
		refreshButton = new JButton("Refresh");
		tempPanel.add(updateButton);
		tempPanel.add(refreshButton);
		add(tempPanel, BorderLayout.SOUTH);
		firstNameField.grabFocus();
		getRootPane().setDefaultButton(updateButton);
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

}
