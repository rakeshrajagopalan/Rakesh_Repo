package synthtest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

public class SynthSample extends JFrame {

	private static final long serialVersionUID = 4234100311808593764L;

	public SynthSample() {
		try {
			SynthLookAndFeel synthLNF = new SynthLookAndFeel();
			Class<SynthSample> synthClass = SynthSample.class;
			InputStream inputStream = synthClass
					.getResourceAsStream("config.xml");
			if (inputStream == null) {
				System.err.println("Unable to find xml file");
				System.exit(-1);
			}
			synthLNF.load(inputStream, synthClass);
			UIManager.setLookAndFeel(synthLNF);
			setTitle("Synth Test");
			setSize(500, 300);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			JButton button = new JButton("Jai Sri Ram");
			add(button, BorderLayout.CENTER);
			final JLabel label = new JLabel("Sri Ram");
			add(label, BorderLayout.SOUTH);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					label.setBackground(Color.BLUE);
					label.setText("Sri Rama Jayam");
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SynthSample().setVisible(true);
			}
		});
	}
}
