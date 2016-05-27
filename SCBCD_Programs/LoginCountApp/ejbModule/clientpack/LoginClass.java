package clientpack;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.swing.*;

import entitybeantest.*;

public class LoginClass extends JFrame implements Serializable {

	private static final long serialVersionUID = -3084109789269330210L;

	public int visit_count = 0;

	public LoginClass() {
		try {
			final File file = new File("Login_Count.ser");
			if (file.exists()) {
				ObjectInputStream objectInputStream = new ObjectInputStream(
						new FileInputStream(file));
				LoginClass test = (LoginClass) objectInputStream.readObject();
				if (test != null) {
					visit_count = test.visit_count;
				}
			}
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			prop.put(Context.URL_PKG_PREFIXES,
					" org.jboss.naming:org.jnp.interfaces");
			prop.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			Context jndiContext = new InitialContext(prop);
			Object ref = jndiContext.lookup("LoginValidator/remote");
			final LoginRemote beanRef = (LoginRemote) PortableRemoteObject
					.narrow(ref, LoginRemote.class);
			setTitle("Login Count App");
			setSize(300, 300);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel l1 = new JLabel("Username");
			final JTextField f1 = new JTextField(10);
			JLabel l2 = new JLabel("Password");
			final JPasswordField f2 = new JPasswordField(10);
			panel.add(l1);
			panel.add(f1);
			panel.add(l2);
			panel.add(f2);
			add(panel, BorderLayout.CENTER);
			panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JButton b1 = new JButton("Login");
			panel.add(b1);
			add(panel, BorderLayout.SOUTH);
			b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (beanRef.validate(f1.getText(), new String(f2
							.getPassword()))) {
						EntityBeanClass entityClass = new EntityBeanClass();
						entityClass.setVisitCount(++LoginClass.this.visit_count);
						entityClass.setName(f1.getText());
						beanRef.save(entityClass);
						try {
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(
									new FileOutputStream(file));
							objectOutputStream.writeObject(LoginClass.this);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
						new WelcomeScreen(entityClass);
						LoginClass.this.dispose();
					} else {
						new ExitScreen();
						LoginClass.this.dispose();
					}
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new LoginClass().setVisible(true);
	}
}