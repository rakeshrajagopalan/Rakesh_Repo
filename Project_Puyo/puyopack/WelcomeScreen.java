package puyopack;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class WelcomeScreen extends JFrame {

	static {
		new DBHandler();
	}

	private static final long serialVersionUID = 2767034869507547404L;

	JPanel panel = new JPanel();

	JLabel label = new JLabel("Welcome to Puyo-Puyo");

	JButton button1 = new JButton("Play");

	JButton button2 = new JButton("Help");

	JButton button3 = new JButton("High Scores");

	JButton button4 = new JButton("Exit");

	ImageIcon redPuyo = new ImageIcon("puyo_red.png");

	ButtonGroup bg = new ButtonGroup();

	JRadioButton radio1;

	JRadioButton radio2;

	JRadioButton radio3;

	/**
	 * Constructor
	 * 
	 */
	public WelcomeScreen() {
		try {
			for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(laf.getName())) {
					UIManager.setLookAndFeel(laf.getClassName());
				}
			}
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(250, 200);
			setUndecorated(true);
			getContentPane().setLayout(new BorderLayout());
			constructGUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method that arranges components on the frame
	 * 
	 */
	private void constructGUI() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		label.setFont(new Font("Verdana", Font.BOLD, 12));
		panel.add(label);
		label = new JLabel("Created by Rakesh");
		label.setFont(new Font("Verdana", Font.BOLD, 12));
		panel.add(label);
		this.getContentPane().add(panel, BorderLayout.NORTH);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		radio1 = new JRadioButton("Level 1", true);
		radio2 = new JRadioButton("Level 2");
		radio3 = new JRadioButton("Level 3");
		bg.add(radio1);
		bg.add(radio2);
		bg.add(radio3);
		panel3.add(radio1);
		panel3.add(radio2);
		panel3.add(radio3);
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		label = new JLabel(redPuyo);
		panel2.add(label);
		redPuyo = new ImageIcon("puyo_blue.png");
		label = new JLabel(redPuyo);
		panel2.add(label);
		// JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// redPuyo = new ImageIcon("puyo_green.png");
		// label = new JLabel(redPuyo);
		// panel1.add(label);
		// redPuyo = new ImageIcon("puyo_yellow.png");
		// label = new JLabel(redPuyo);
		// panel1.add(label);
		panel.add(panel3);
		// panel.add(panel1);
		panel.add(panel2);
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel = new JPanel(new GridLayout(2, 2));
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String level = null;
				if (radio1.isSelected()) {
					level = "1";
				} else if (radio2.isSelected()) {
					level = "2";
				} else {
					level = "3";
				}
				new PuyoGame(Long.parseLong(level)).setVisible(true);
				WelcomeScreen.this.dispose();
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] cmd = {
							"C:\\Program Files\\Windows NT\\Accessories\\Wordpad.exe",
							"Puyo_User_Guide.doc" };
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(WelcomeScreen.this,
							"User Guide doesnt exist");
				}
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ScoreViewer().setVisible(true);
				WelcomeScreen.this.dispose();
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.getRootPane().setDefaultButton(button1);
	}

	/**
	 * Main Method of the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new WelcomeScreen().setVisible(true);
	}

}
