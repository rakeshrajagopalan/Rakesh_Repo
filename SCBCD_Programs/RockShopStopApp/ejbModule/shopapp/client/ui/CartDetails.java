package shopapp.client.ui;

import javax.swing.*;
import javax.swing.table.*;
import shopapp.beans.*;
import shopapp.entities.*;
import shopapp.utils.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CartDetails extends JFrame {

	private static final long serialVersionUID = -77392352951229488L;

	private JPanel eastPanel;

	private JButton viewCartButton;

	private JButton clearCartButton;

	private UserManagerRemote userManagerRemote;

	private ShopManagerRemote shopManagerRemote;

	private List<CartTrakker> cartItems;

	private UtilityRemote remote;

	private JButton checkOutButton;

	private ArrayList<Object> stubList;

	private JPanel westPanel;

	private JButton loginButton;

	private JButton homeButton;

	private JButton userDetailsButton;

	private JButton changePwdButton;

	private JButton exitButton;

	private JPanel centerPanel;

	private JTable cartDisplayer;

	private Vector<Vector<Object>> rowData;

	private Vector<String> columnNames;

	private JButton removeItemButton;

	private JButton buyButton;

	private JButton refreshButton;

	private JPanel southPanel;

	private UserDetails currentUser;

	private JLabel cartLabel;

	public CartDetails(ArrayList<Object> stubList) throws Exception {
		this.stubList = stubList;
		remote = (UtilityRemote) stubList.get(0);
		userManagerRemote = (UserManagerRemote) stubList.get(1);
		shopManagerRemote = (ShopManagerRemote) stubList.get(2);
		if (userManagerRemote != null) {
			currentUser = userManagerRemote.getCurrentUser();
		}
		commonGUISettings();
		createWestPanel();
		createEastPanel();
		createCenterPanel();
		createSouthPanel();
		constructGUI();
	}

	private void createSouthPanel() throws Exception {
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buyButton = new JButton("Buy");
		refreshButton = new JButton("Refresh");
		southPanel.add(removeItemButton);
		southPanel.add(buyButton);
		southPanel.add(refreshButton);
		add(southPanel, BorderLayout.SOUTH);
	}

	private void createCenterPanel() throws Exception {
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		columnNames = new Vector<String>();
		columnNames.add("Name");
		columnNames.add("Quant");
		columnNames.add("Price");
		columnNames.add("Total");
		rowData = new Vector<Vector<Object>>();
		for (CartTrakker cartTrakker : cartItems) {
			Vector<Object> newEntry = new Vector<Object>();
			int quantity = cartTrakker.getQuantityReqd();
			int price = cartTrakker.getPrice();
			newEntry.add(cartTrakker.getItemName());
			newEntry.add(quantity);
			newEntry.add(price / quantity);
			newEntry.add(price);
			rowData.add(newEntry);
		}
		cartDisplayer = new JTable(rowData, columnNames);
		cartDisplayer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cartDisplayer.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = cartDisplayer.getTableHeader();
		centerPanel.add(header);
		centerPanel.add(new JScrollPane(cartDisplayer,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		removeItemButton = new JButton("Remove Item");
		add(centerPanel, BorderLayout.CENTER);
		cartDisplayer.updateUI();
	}

	private void constructGUI() throws Exception {
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Home(stubList).setVisible(true);
					CartDetails.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (userManagerRemote == null
							|| userManagerRemote.getCurrentUser() == null) {
						new Login(stubList).setVisible(true);
						CartDetails.this.dispose();
					} else {
						userManagerRemote.removeCurrentUser();
						stubList.remove(userManagerRemote);
						stubList.remove(shopManagerRemote);
						new Login(stubList).setVisible(true);
						CartDetails.this.dispose();
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
					CartDetails.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		removeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removeItemFromCart();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int totalPrice = 0;
					if (userManagerRemote == null || currentUser == null) {
						
					} else {
						if (cartItems != null && cartItems.size() > 0) {
							for (CartTrakker trakker : cartItems) {
								totalPrice += trakker.getPrice();
							}
						}
						int balance = currentUser.getCreditPoints();
						if (balance >= totalPrice) {
							int userOption = JOptionPane
									.showConfirmDialog(CartDetails.this,
											"Buy the items?", "Buy the items?",
											JOptionPane.YES_NO_OPTION);
							if (userOption == JOptionPane.YES_OPTION) {
								userManagerRemote.completeTransaction(
										cartItems, totalPrice);
								JOptionPane.showMessageDialog(CartDetails.this,
										"Transaction Successful :)");
								userManagerRemote.clearCart();
								new Home(stubList).setVisible(true);
								CartDetails.this.dispose();
							} else {
								CartDetails.this.setVisible(true);
							}
						} else {
							JOptionPane.showMessageDialog(CartDetails.this,
									"You do not have enough balance to "
											+ "purchase \nitems on the cart");
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		clearCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (userManagerRemote != null && currentUser != null) {
						remote.clearCart(currentUser.getUserName());
						userManagerRemote.clearCart();
					} else {
						remote
								.clearCart(ApplicationConstants.INITIAL_USER_NAME);
					}
					new Home(stubList).setVisible(true);
					CartDetails.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		getRootPane().setDefaultButton(buyButton);
		addWindowListener(UtilityClass.exitAction(remote, userManagerRemote));
	}

	private void removeItemFromCart() throws Exception {
		int selectedRow = cartDisplayer.getSelectedRow();
		if (selectedRow != -1) {
			CartTrakker cartTrakker = cartItems.get(selectedRow);
			if (userManagerRemote != null && currentUser != null) {
				userManagerRemote.removeFromCart(cartTrakker);
				cartItems.remove(selectedRow);
				rowData.remove(selectedRow);
				if (rowData.size() > 0) {
					cartDisplayer.updateUI();
					repaint();
				} else {
					new Home(stubList).setVisible(true);
					dispose();
				}
			} else {
				remote.removeFromCart(cartTrakker);
				cartItems.remove(selectedRow);
				rowData.remove(selectedRow);
				if (rowData.size() > 0) {
					cartDisplayer.updateUI();
					cartLabel.setText("You have " + cartItems.size());
					repaint();
				} else {
					new Home(stubList).setVisible(true);
					dispose();
				}
			}
		} else {
			JOptionPane.showMessageDialog(this,
					"Please select an item to remove");
		}
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

	private void createEastPanel() throws Exception {
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		viewCartButton = new JButton("View Cart");
		clearCartButton = new JButton("Clear Cart");
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
			JLabel tempLabel = new JLabel(" items on cart");
			eastPanel.add(tempLabel);
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
		viewCartButton.setEnabled(false);
		add(new JScrollPane(eastPanel), BorderLayout.EAST);
	}

	private void commonGUISettings() throws Exception {
		setTitle(ApplicationConstants.CART_DETAILS_TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height = dim.height / 4;
		dim.width = dim.width / 3;
		setBounds(dim.width, dim.height, ApplicationConstants.SCREEN_WIDTH,
				ApplicationConstants.SCREEN_HEIGHT);
	}

}
