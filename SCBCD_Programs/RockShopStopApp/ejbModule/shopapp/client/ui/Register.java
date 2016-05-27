package shopapp.client.ui;

import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import shopapp.beans.*;
import shopapp.entities.*;
import shopapp.utils.*;

public class Register extends JFrame {

	private static final long serialVersionUID = 5182117472498005061L;

	private JPanel panel;

	private JLabel firstNameLabel;

	private JTextField firstNameField;

	private JLabel lastNameLabel;

	private JTextField lastNameField;

	private JLabel userNameLabel;

	private JTextField userNameField;

	private JLabel passwordLabel;

	private JPasswordField passwordField;

	private JLabel confirmPasswordLabel;

	private JPasswordField confirmPasswordField;

	private JLabel addressLabel;

	private JTextField addressField;

	private JLabel countryLabel;

	private JTextField countryField;

	private JLabel stateLabel;

	private JTextField stateField;

	private JLabel cityLabel;

	private JTextField cityField;

	private JLabel pinLabel;

	private JTextField pinField;

	private JLabel emailIDLabel;

	private JTextField emailIDField;

	private JButton registerButton;

	private JButton homeButton;

	private UtilityRemote remote;

	private ArrayList<Object> stubList;

	public Register(ArrayList<Object> stubList) throws Exception {
		this.stubList = stubList;
		this.remote = (UtilityRemote) stubList.get(0);
		commonGUISettings();
		constructGUI();
	}

	private void commonGUISettings() throws Exception {
		setTitle(ApplicationConstants.REGISTER_TITLE);
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
		add(new JLabel("Please register to continue"), BorderLayout.NORTH);
		JPanel bigPanel = new JPanel();
		bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		firstNameLabel = new JLabel("First Name");
		firstNameField = new JTextField(10);
		panel.add(firstNameLabel);
		panel.add(firstNameField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lastNameLabel = new JLabel("Last Name");
		lastNameField = new JTextField(10);
		panel.add(lastNameLabel);
		panel.add(lastNameField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		userNameLabel = new JLabel("User Name");
		userNameField = new JTextField(10);
		panel.add(userNameLabel);
		panel.add(userNameField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(10);
		passwordField.setEchoChar(ApplicationConstants.PASSWORD_ECHO_CHAR);
		panel.add(passwordLabel);
		panel.add(passwordField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordField = new JPasswordField(10);
		confirmPasswordField
				.setEchoChar(ApplicationConstants.PASSWORD_ECHO_CHAR);
		panel.add(confirmPasswordLabel);
		panel.add(confirmPasswordField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addressLabel = new JLabel("Address");
		addressField = new JTextField(10);
		panel.add(addressLabel);
		panel.add(addressField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		countryLabel = new JLabel("Country");
		countryField = new JTextField(10);
		panel.add(countryLabel);
		panel.add(countryField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		stateLabel = new JLabel("State");
		stateField = new JTextField(10);
		panel.add(stateLabel);
		panel.add(stateField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cityLabel = new JLabel("City");
		cityField = new JTextField(10);
		panel.add(cityLabel);
		panel.add(cityField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pinLabel = new JLabel("Pincode");
		pinField = new JTextField(10);
		panel.add(pinLabel);
		panel.add(pinField);
		bigPanel.add(panel);
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		emailIDLabel = new JLabel("E-mail ID");
		emailIDField = new JTextField(10);
		panel.add(emailIDLabel);
		panel.add(emailIDField);
		bigPanel.add(panel);
		JScrollPane scroller = new JScrollPane(bigPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroller, BorderLayout.CENTER);
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		registerButton = new JButton("Register");
		homeButton = new JButton("Home");
		panel.add(registerButton);
		panel.add(homeButton);
		add(panel, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(registerButton);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					register();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Home(stubList).setVisible(true);
					Register.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		addWindowListener(UtilityClass.exitAction(remote, null));
	}

	private boolean validateEMail(String input) throws Exception {
		boolean valid = false;
		String regex = "([\\w|\\.|-]+)@([\\w&&[^_]]+)\\.(com|co\\.in|org)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		valid = matcher.matches();
		return valid;
	}

	private void register() throws Exception {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String userName = userNameField.getText();
		String emailID = emailIDField.getText();
		String address = addressField.getText();
		String country = countryField.getText();
		String state = stateField.getText();
		String city = cityField.getText();
		String pincode = pinField.getText();
		String password = new String(passwordField.getPassword());
		String confirmPassword = new String(confirmPasswordField.getPassword());
		if (firstName == null || firstName.trim().equals("")) {
			JOptionPane.showMessageDialog(Register.this,
					"Please enter first name");
			firstNameField.grabFocus();
		} else if (lastName == null || lastName.trim().equals("")) {
			JOptionPane.showMessageDialog(Register.this,
					"Please enter last name");
			lastNameField.grabFocus();
		} else if (userName == null || userName.trim().equals("")) {
			JOptionPane.showMessageDialog(Register.this,
					"Please enter user name");
			userNameField.grabFocus();
		} else if (address == null || address.trim().equals("")) {
			JOptionPane
					.showMessageDialog(Register.this, "Please enter address");
			addressField.grabFocus();
		} else if (country == null || country.trim().equals("")) {
			JOptionPane
					.showMessageDialog(Register.this, "Please enter country");
			countryField.grabFocus();
		} else if (state == null || state.trim().equals("")) {
			JOptionPane.showMessageDialog(Register.this, "Please enter state");
			stateField.grabFocus();
		} else if (city == null || city.trim().equals("")) {
			JOptionPane.showMessageDialog(Register.this, "Please enter city");
			cityField.grabFocus();
		} else if (pincode == null || pincode.trim().equals("")) {
			JOptionPane
					.showMessageDialog(Register.this, "Please enter pincode");
			pinField.grabFocus();
		} else if (password == null || password.trim().equals("")) {
			JOptionPane.showMessageDialog(Register.this,
					"Please enter password");
			passwordField.grabFocus();
		} else if (confirmPassword == null || confirmPassword.trim().equals("")
				|| !confirmPassword.equals(password)) {
			JOptionPane
					.showMessageDialog(
							Register.this,
							"Confirm password empty or "
									+ "\nPassword and Confirm Passwords does not match");
			confirmPasswordField.grabFocus();
		} else if (emailID == null || emailID.trim().equals("")
				|| !validateEMail(emailID)) {
			JOptionPane.showMessageDialog(Register.this,
					"Please enter a valid e-mail id");
			emailIDField.grabFocus();
		} else {
			if (!remote.verifyUsernameExists(userName)) {
				UserDetails newUserDetails = new UserDetails();
				newUserDetails.setFirstName(firstName);
				newUserDetails.setLastName(lastName);
				newUserDetails.setUserName(userName);
				byte[] plainText = password.getBytes();
				byte[] digest = new byte[50];
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(plainText, 0, plainText.length);
				String encText = new String(md.digest(digest));
				newUserDetails.setUserPassword(encText);
				newUserDetails.setUserAddress(address);
				newUserDetails.setUserCountry(country);
				newUserDetails.setUserState(state);
				newUserDetails.setUserCity(city);
				newUserDetails.setUserPincode(pincode);
				newUserDetails.setEmailID(emailID);
				newUserDetails
						.setCreditPoints(ApplicationConstants.INITIAL_CREDIT_POINTS);
				remote.registerNewUser(newUserDetails);
				JOptionPane.showMessageDialog(Register.this,
						"Thanks for registering :)");
				new Login(stubList).setVisible(true);
				Register.this.dispose();
			} else {
				JOptionPane.showMessageDialog(Register.this,
						"The user name you've entered already exists."
								+ "\n Please select a different user name");
			}
		}
	}

}
