package shopapp.client.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.*;
import javax.swing.tree.*;
import shopapp.beans.*;
import shopapp.entities.*;
import shopapp.utils.*;

@SuppressWarnings("unchecked")
public class Home extends JFrame {

	private static final long serialVersionUID = -1994551397999731062L;

	private UtilityRemote remote;

	private UserManagerRemote userManagerRemote;

	private ShopManagerRemote shopManagerRemote;

	private ArrayList<Object> stubList;

	private List totalList;

	private JPanel eastPanel;

	private JLabel welcomeLabel;

	private JLabel creditDetailsLabel;

	private JPanel westPanel;

	private JPanel northPanel;

	private JPanel southPanel;

	private JPanel centerPanel;

	private JTree tree;

	private JButton homeButton;

	private JButton loginButton;

	private JButton userDetailsButton;

	private JButton changePwdButton;

	private JButton checkOutButton;

	private JButton exitButton;

	private JButton viewCartButton;

	private JButton clearCartButton;

	private JButton viewDetailsButton;

	private List<ItemFetcher> itemsOnBoard;

	private ItemFetcher currentlySelectedItem;

	private List<String> friendList;

	private JButton refreshButton;

	private List<CartTrakker> cartItems;

	private UserDetails currentUser;

	private static final String NIMBUS_CONSTANT = "Nimbus";

	public Home() throws Exception {
		for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
			if (laf.getName().equals(NIMBUS_CONSTANT)) {
				UIManager.setLookAndFeel(laf.getClassName());
			}
		}
		doBeanLookUps();
		stubList = new ArrayList<Object>();
		stubList.add(remote);
		itemsOnBoard = remote.getItems();
		constructGUI();
	}

	public Home(ArrayList<Object> stubList) throws Exception {
		if (stubList != null && stubList.size() > 1) {
			this.stubList = stubList;
			this.remote = (UtilityRemote) stubList.get(0);
			this.userManagerRemote = (UserManagerRemote) stubList.get(1);
			this.shopManagerRemote = (ShopManagerRemote) stubList.get(2);
			itemsOnBoard = remote.getItems();
		} else {
			doBeanLookUps();
			this.stubList = new ArrayList<Object>();
			this.stubList.add(remote);
			this.stubList.add(userManagerRemote);
			this.stubList.add(shopManagerRemote);
			itemsOnBoard = remote.getItems();
		}
		if (userManagerRemote != null) {
			currentUser = userManagerRemote.getCurrentUser();
		}
		constructGUI();
	}

	private void doBeanLookUps() throws Exception {
		remote = (UtilityRemote) UtilityClass
				.lookUpBeans(ApplicationConstants.SESSION_FACADE_BEAN_NAME);
	}

	private void constructGUI() throws Exception {
		commonGUISettings();
		createNorthPanel();
		createCenterPanel();
		createWestPanel();
		createEastPanel();
		createSouthPanel();
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (userManagerRemote == null || currentUser == null) {
						new Login(stubList).setVisible(true);
						Home.this.dispose();
					} else {
						userManagerRemote.removeCurrentUser();
						stubList.remove(userManagerRemote);
						stubList.remove(shopManagerRemote);
						new Login(stubList).setVisible(true);
						Home.this.dispose();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
					Home.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		exitButton.addActionListener(UtilityClass.exitButtonAction(remote,
				userManagerRemote));
		userDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ChangeDetails(stubList).setVisible(true);
					Home.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		changePwdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ChangePassword(stubList).setVisible(true);
					Home.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		viewDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showDescription();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		viewCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new CartDetails(stubList).setVisible(true);
					Home.this.dispose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		getRootPane().setDefaultButton(viewDetailsButton);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				currentlySelectedItem = null;
				if (e.getPath().getPathCount() == ApplicationConstants.LEAF_NODE_COUNT) {
					String selItem = e.getPath().getLastPathComponent()
							.toString();
					int reqIndex = friendList.indexOf(selItem);
					currentlySelectedItem = (ItemFetcher) itemsOnBoard
							.get(reqIndex);
				}
			}
		});
		addWindowListener(UtilityClass.exitAction(remote, userManagerRemote));
	}

	private void showDescription() throws Exception {
		if (currentlySelectedItem != null) {
			ItemTechDetailsFetcher reqdDetails = remote
					.getTechDetailsOfItem(currentlySelectedItem.getItemID());
			List<Object> requiredList = new ArrayList<Object>();
			requiredList.add(currentlySelectedItem);
			requiredList.add(reqdDetails);
			new ItemsDescription(stubList, requiredList).setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"Please select an item to \n view its description");
		}
	}

	private void commonGUISettings() throws Exception {
		setTitle(ApplicationConstants.HOME_TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.height = dim.height / 4;
		dim.width = dim.width / 3;
		setBounds(dim.width, dim.height, ApplicationConstants.SCREEN_WIDTH,
				ApplicationConstants.SCREEN_HEIGHT);
	}

	private void createNorthPanel() throws Exception {
		northPanel = new JPanel();
		JPanel tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		if (userManagerRemote != null && currentUser != null) {
			welcomeLabel = new JLabel("Welcome, " + currentUser.getFirstName());
			creditDetailsLabel = new JLabel("Your credit balance is: "
					+ currentUser.getCreditPoints());
		} else {
			welcomeLabel = new JLabel("Welcome, Guest");
			creditDetailsLabel = new JLabel("Enjoy your stay");
		}
		tempPanel.add(welcomeLabel);
		northPanel.add(tempPanel);
		tempPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tempPanel.add(creditDetailsLabel);
		northPanel.add(tempPanel);
		add(northPanel, BorderLayout.NORTH);
	}

	private void createCenterPanel() throws Exception {
		centerPanel = new JPanel();
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
				"Items for Sale");
		totalList = remote.fetchCategories();
		retrieveData(rootNode);
		tree = new JTree(rootNode);
		centerPanel.add(tree);
		add(new JScrollPane(centerPanel), BorderLayout.CENTER);
	}

	private void createEastPanel() throws Exception {
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		viewCartButton = new JButton("View Cart");
		clearCartButton = new JButton("Clear Cart");
		JLabel cartLabel = null;
		if (userManagerRemote == null || currentUser == null) {
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

	private void createSouthPanel() throws Exception {
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		viewDetailsButton = new JButton("View Details");
		refreshButton = new JButton("Refresh");
		southPanel.add(viewDetailsButton);
		southPanel.add(refreshButton);
		if (userManagerRemote != null && currentUser != null) {
			JButton viewTransactionHistButton = new JButton("View History");
			southPanel.add(viewTransactionHistButton);
			viewTransactionHistButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
		}
		add(southPanel, BorderLayout.SOUTH);
	}

	private void retrieveData(DefaultMutableTreeNode rootNode) throws Exception {
		for (Object object : totalList) {
			List innerList = (List) object;
			friendList = new ArrayList<String>();
			while (innerList.size() > 0) {
				DefaultMutableTreeNode firstLevelNode = new DefaultMutableTreeNode(
						(String) innerList.get(0));
				rootNode.add(firstLevelNode);
				List itemNameList = (List) innerList.get(1);
				innerList.remove(0);
				innerList.remove(0);
				for (Object obj : itemNameList) {
					ItemFetcher itemFetcher = (ItemFetcher) obj;
					friendList.add(itemFetcher.getItemName());
					DefaultMutableTreeNode subLevel = new DefaultMutableTreeNode(
							itemFetcher.getItemName());
					firstLevelNode.add(subLevel);
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			new Home().setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
