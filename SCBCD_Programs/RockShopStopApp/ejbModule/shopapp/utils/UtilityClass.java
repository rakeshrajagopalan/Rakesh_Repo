package shopapp.utils;

import java.util.*;
import javax.naming.*;
import javax.swing.*;
import java.awt.event.*;
import shopapp.beans.*;
import shopapp.client.ui.*;

public class UtilityClass {

	public static Object lookUpBeans(String jndiName) throws Exception {
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		prop.put(Context.URL_PKG_PREFIXES,
				" org.jboss.naming:org.jnp.interfaces");
		prop.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		Context jndiContext = new InitialContext(prop);
		Object ref = jndiContext.lookup(jndiName);
		return ref;
	}

	public JPanel createWestPanel(JFrame frame, UserManagerRemote remote)
			throws Exception {
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		JButton userDetailsButton = new JButton("My Details");
		JButton homeButton = new JButton("Home");
		JButton loginButton = null;
		if (remote != null && remote.getCurrentUser() != null) {
			loginButton = new JButton("Logout");
		} else {
			loginButton = new JButton("Login");
		}
		JButton changePwdButton = new JButton("Change Pwd");
		JButton checkOutButton = new JButton("Checkout");
		if (frame instanceof Home) {
			homeButton.setEnabled(false);
		} else {
			homeButton.setEnabled(true);
		}
		if (remote == null || remote.getCurrentUser() == null
				|| frame instanceof ChangeDetails) {
			userDetailsButton.setEnabled(false);
		} else {
			userDetailsButton.setEnabled(true);
		}
		if (remote == null || remote.getCurrentUser() == null
				|| frame instanceof ChangePassword) {
			changePwdButton.setEnabled(false);
		} else {
			changePwdButton.setEnabled(true);
		}
		JButton exitButton = new JButton("Exit");
		westPanel.add(homeButton);
		westPanel.add(loginButton);
		westPanel.add(userDetailsButton);
		westPanel.add(changePwdButton);
		westPanel.add(checkOutButton);
		westPanel.add(exitButton);
		return westPanel;
	}

	public static ActionListener exitButtonAction(final UtilityRemote remote,
			final UserManagerRemote userManagerRemote) throws Exception {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (remote != null && userManagerRemote != null
							&& userManagerRemote.getCurrentUser() != null) {
						try {
							remote.clearCart(userManagerRemote.getCurrentUser()
									.getUserName());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else if (remote != null) {
						if (userManagerRemote == null) {
							remote
									.clearCart(ApplicationConstants.INITIAL_USER_NAME);
						}
					}
					System.exit(0);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		return action;
	}

	public static WindowListener exitAction(final UtilityRemote remote,
			final UserManagerRemote userManagerRemote) throws Exception {
		WindowListener action = new WindowListener() {
			public void windowActivated(WindowEvent e) {

			}

			public void windowClosed(WindowEvent e) {

			}

			public void windowClosing(WindowEvent e) {
				try {
					if (remote != null && userManagerRemote != null
							&& userManagerRemote.getCurrentUser() != null) {
						try {
							remote.clearCart(userManagerRemote.getCurrentUser()
									.getUserName());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else if (remote != null) {
						if (userManagerRemote == null) {
							remote
									.clearCart(ApplicationConstants.INITIAL_USER_NAME);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			public void windowDeactivated(WindowEvent e) {

			}

			public void windowDeiconified(WindowEvent e) {

			}

			public void windowIconified(WindowEvent e) {

			}

			public void windowOpened(WindowEvent e) {

			}
		};
		return action;
	}

}
