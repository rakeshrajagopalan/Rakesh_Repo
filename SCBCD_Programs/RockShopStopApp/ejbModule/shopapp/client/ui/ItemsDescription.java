package shopapp.client.ui;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import shopapp.beans.*;
import shopapp.entities.*;
import shopapp.utils.*;

public class ItemsDescription extends JFrame {

	private static final long serialVersionUID = 2125691928842921444L;

	private JPanel westPanel;

	private JPanel centerPanel;

	private ArrayList<Object> stubList;

	private UtilityRemote remote;

	private ShopManagerRemote shopManagerRemote;

	private UserManagerRemote userManagerRemote;

	private ItemFetcher currentItem;

	private ItemTechDetailsFetcher reqdDetails;

	private JButton buyButton;

	private JButton loginButton;

	private JButton homeButton;

	private JButton userDetailsButton;

	private JButton changePwdButton;

	private JButton checkOutButton;

	private JButton exitButton;

	private JLabel itemMakeLabel;

	private JTextField itemMakeField;

	private JLabel itemCapacityLabel;

	private JTextField itemCapacityField;

	private JLabel itemDescLabel;

	private JTextArea itemDescArea;

	private JLabel availColorsLabel;

	private JTextField availColorsField;

	private JLabel itemPriceLabel;

	private JTextField itemPriceField;

	private JLabel numRemainLabel;

	private JTextField numRemainField;

	private JButton refreshButton;

	private JLabel quantityReqdLabel;

	private JTextField quantityReqdField;

	private JPanel eastPanel;

	private JButton viewCartButton;

	private JButton clearCartButton;

	private List<CartTrakker> cartItems;

	private UserDetails currentUser;

	public ItemsDescription(ArrayList<Object> stubList,
			List<Object> requiredList) throws Exception {
		this.stubList = stubList;
		remote = (UtilityRemote) stubList.get(0);
		if (stubList != null && stubList.size() > 1) {
			userManagerRemote = (UserManagerRemote) stubList.get(1);
			shopManagerRemote = (ShopManagerRemote) stubList.get(2);
		}
		this.currentItem = (ItemFetcher) requiredList.get(0);
		this.reqdDetails = (ItemTechDetailsFetcher) requiredList.get(1);
		if (userManagerRemote != null) {
			currentUser = userManagerRemote.getCurrentUser();
		}
		commonGUISettings();
		createWestPanel();
		createOtherPanels();
		createEastPanel();
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
		viewCartButton.setEnabled(false);
		add(new JScrollPane(eastPanel), BorderLayout.EAST);
	}

	private void commonGUISettings() throws Exception {
		setTitle(ApplicationConstants.ITEM_DESCRIPTION_TITLE);
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
		tempPanel
				.add(new JLabel("Description for " + currentItem.getItemName()));
		tempPanel.add(new JLabel(""));
		add(tempPanel, BorderLayout.NORTH);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		itemPriceLabel = new JLabel("Item Price");
		itemPriceField = new JTextField(10);
		itemPriceField.setText(currentItem.getItemPrice() + " points");
		itemPriceField.setEditable(false);
		tempPanel.add(itemPriceLabel);
		tempPanel.add(itemPriceField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		itemMakeLabel = new JLabel("Item Make");
		itemMakeField = new JTextField(10);
		itemMakeField.setText(reqdDetails.getItemMake());
		itemMakeField.setEditable(false);
		tempPanel.add(itemMakeLabel);
		tempPanel.add(itemMakeField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		itemCapacityLabel = new JLabel("Item Capacity");
		itemCapacityField = new JTextField(10);
		itemCapacityField.setText(reqdDetails.getItemCapacity());
		itemCapacityField.setEditable(false);
		tempPanel.add(itemCapacityLabel);
		tempPanel.add(itemCapacityField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		itemDescLabel = new JLabel("Item Desc");
		itemDescArea = new JTextArea(5, 10);
		itemDescArea.setText(reqdDetails.getItemDesc());
		itemDescArea.setBackground(getBackground());
		itemDescArea.setEditable(false);
		itemDescArea.setWrapStyleWord(true);
		itemDescArea.setLineWrap(true);
		itemDescArea.setBorder(BorderFactory.createEtchedBorder());
		tempPanel.add(itemDescLabel);
		tempPanel.add(new JScrollPane(itemDescArea));
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		availColorsLabel = new JLabel("Available Colors");
		availColorsField = new JTextField(10);
		availColorsField.setText(reqdDetails.getItemColors());
		availColorsField.setEditable(false);
		tempPanel.add(availColorsLabel);
		tempPanel.add(availColorsField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		numRemainLabel = new JLabel("Nos Remaining");
		numRemainField = new JTextField(10);
		numRemainField.setText(currentItem.getRemainingNum().toString());
		numRemainField.setEditable(false);
		tempPanel.add(numRemainLabel);
		tempPanel.add(numRemainField);
		centerPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		quantityReqdLabel = new JLabel("Quanitity Reqd");
		quantityReqdField = new JTextField(10);
		tempPanel.add(quantityReqdLabel);
		tempPanel.add(quantityReqdField);
		centerPanel.add(tempPanel);
		add(new JScrollPane(centerPanel), BorderLayout.CENTER);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buyButton = new JButton("Add to Cart");
		refreshButton = new JButton("Refresh");
		tempPanel.add(buyButton);
		tempPanel.add(refreshButton);
		add(tempPanel, BorderLayout.SOUTH);
		quantityReqdField.grabFocus();
	}

	private void constructGUI() throws Exception {
		exitButton.addActionListener(UtilityClass.exitButtonAction(remote,
				userManagerRemote));
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		changePwdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ChangePassword(stubList).setVisible(true);
					ItemsDescription.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		userDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ChangeDetails(stubList).setVisible(true);
					ItemsDescription.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Home(stubList).setVisible(true);
					ItemsDescription.this.dispose();
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
						ItemsDescription.this.dispose();
					} else {
						userManagerRemote.removeCurrentUser();
						stubList.remove(userManagerRemote);
						stubList.remove(shopManagerRemote);
						new Login(stubList).setVisible(true);
						ItemsDescription.this.dispose();
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
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addToCart();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		viewCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						new CartDetails(stubList).setVisible(true);
						ItemsDescription.this.dispose();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		clearCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						if (userManagerRemote != null && currentUser != null) {
							remote.clearCart(currentUser.getUserName());
							userManagerRemote.clearCart();
						} else {
							remote
									.clearCart(ApplicationConstants.INITIAL_USER_NAME);
						}
						new Home(stubList).setVisible(true);
						ItemsDescription.this.dispose();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		getRootPane().setDefaultButton(buyButton);
		addWindowListener(UtilityClass.exitAction(remote, userManagerRemote));
	}

	private void addToCart() throws Exception {
		int quantityReqd = 0;
		try {
			quantityReqd = Integer.parseInt(quantityReqdField.getText());
		} catch (NumberFormatException ex) {
			quantityReqd = 1;
		}
		if (quantityReqd <= currentItem.getRemainingNum()) {
			int[] transactionID = remote.configureTransactionID();
			CartPrimaryKey primaryKey = new CartPrimaryKey();
			primaryKey.setTransactionID(transactionID[0]);
			primaryKey.setCartSerNum(transactionID[1]);
			CartTrakker cartTrakker = new CartTrakker();
			cartTrakker.setCartPK(primaryKey);
			if (userManagerRemote != null
					&& userManagerRemote.getCurrentUser() != null) {
				UserDetails userDetails = userManagerRemote.getCurrentUser();
				cartTrakker.setUserName(userDetails.getUserName());
				cartTrakker.setItemName(currentItem.getItemName());
				cartTrakker.setItemID(currentItem.getItemID());
				cartTrakker.setQuantityReqd(quantityReqd);
				cartTrakker.setPrice(quantityReqd * currentItem.getItemPrice());
				userManagerRemote.addToCart(cartTrakker);
			} else {
				cartTrakker.setUserName(ApplicationConstants.INITIAL_USER_NAME);
				cartTrakker.setItemName(currentItem.getItemName());
				cartTrakker.setItemID(currentItem.getItemID());
				cartTrakker.setQuantityReqd(quantityReqd);
				cartTrakker.setPrice(quantityReqd * currentItem.getItemPrice());
				remote.addToCart(cartTrakker);
			}
			JOptionPane.showMessageDialog(this, "Item added to the cart");
			new Home(stubList).setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"The quantity that you require is not \n "
							+ "currently available. Stocks are expected soon.");
		}
	}
}
