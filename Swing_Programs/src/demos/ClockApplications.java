package demos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class ClockApplications extends SwingWorker<Object, Void> {

	private JFrame frame;

	private JLabel timeLabel;

	private JPanel timePanel;

	private JLabel dateLabel;

	private JPanel datePanel;

	private JButton exitButton;

	private JPanel buttonPanel;

	private Calendar calendar;

	public ClockApplications() {

	}

	public void constructAndShowGUI() {
		try {
			frame = new JFrame();
			frame.setTitle("Clock");
			frame.setSize(400, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			dateLabel = new JLabel();
			calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			Date date = calendar.getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateLabel.setText(dateFormat.format(date));
			datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			datePanel.add(dateLabel);
			frame.add(datePanel, BorderLayout.NORTH);
			timeLabel = new JLabel();
			GraphicsEnvironment graphics = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			graphics.getAllFonts();
			Font font = new Font("Verdana", Font.BOLD, 72);
			timeLabel.setFont(font);
			updateTime();
			timePanel = new JPanel();
			timePanel.setLayout(new GridBagLayout());
			GridBagConstraints mainPanelConstraints = new GridBagConstraints();
			mainPanelConstraints.anchor = GridBagConstraints.WEST;
			mainPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
			mainPanelConstraints.gridheight = 3;
			mainPanelConstraints.gridwidth = 4;
			mainPanelConstraints.gridx = 0;
			mainPanelConstraints.gridy = 0;
			timePanel.add(timeLabel, mainPanelConstraints);
			frame.add(timePanel, BorderLayout.CENTER);
			buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			exitButton = new JButton("Exit");
			exitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					exitButtonAction();
				}
			});
			buttonPanel.add(exitButton);
			frame.add(buttonPanel, BorderLayout.SOUTH);
			frame.getRootPane().setDefaultButton(exitButton);
			frame.setVisible(true);
			execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void exitButtonAction() {
		System.exit(0);
	}

	private void updateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		calendar.setTimeInMillis(System.currentTimeMillis());
		Date date = calendar.getTime();
		timeLabel.setText(dateFormat.format(date));
	}

	private void updateGUI() {
		updateTime();
		frame.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ClockApplications clock = new ClockApplications();
				clock.constructAndShowGUI();
			}
		});
	}

	@Override
	protected Object doInBackground() throws Exception {
		try {
			while (true) {
				Thread.sleep(1000);
				updateGUI();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
