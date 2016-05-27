package scribblepadpack;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
import javax.print.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;

public class ScribblePad extends JFrame implements ClipboardOwner {

	private static final long serialVersionUID = -1123581324L;

	private JTextArea textPadArea;

	private static final int SCREEN_DIMENSION = 300;

	private boolean dirtyFlag;

	private boolean wrapStyleChosen;

	private JScrollPane scrollPane;

	private Clipboard clipboard;

	private String findText;

	private String replaceText;

	private int counter;

	private String subToken;

	private boolean subLoop;

	private int subTokenLength;

	private JFrame frame;

	private UndoManager undoManager = new UndoManager();

	private boolean smallSize;

	public ScribblePad() {
		constructGUI();
		new Thread(new Runnable() {
			public void run() {
				monitorSizeChange();
			}
		}).start();
	}

	private void constructGUI() {
		clipboard = getToolkit().getSystemClipboard();
		setTitle("Untitled - ScribblePad");
		setLayout(new BorderLayout());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		// setBounds(SCREEN_DIMENSION, SCREEN_DIMENSION, dimension.width / 3,
		// dimension.height / 2);
		setBounds(SCREEN_DIMENSION, SCREEN_DIMENSION, 400, dimension.height / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		createMenuBar();
		createToolBar();
		textPadArea = new JTextArea();
		scrollPane = new JScrollPane(textPadArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Document document = textPadArea.getDocument();
		document.addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				undoManager.addEdit(e.getEdit());
			}
		});
		add(scrollPane, BorderLayout.CENTER);

		textPadArea.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F) {
					findGUI();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					saveFile();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_P) {
					print();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O) {
					openFile();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
					undo();
				} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
					redo();
				}
			}

			public void keyReleased(KeyEvent e) {
				if (!dirtyFlag && !textPadArea.getText().equals("")) {
					dirtyFlag = true;
					setTitle(getTitle() + "*");
				}
			}

			public void keyTyped(KeyEvent e) {

			}
		});

		textPadArea.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					JPopupMenu popupMenu = new JPopupMenu();
					JMenuItem item = new JMenuItem("Undo");
					popupMenu.add(item);
					item.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							undo();
						}
					});
					item = new JMenuItem("Redo");
					popupMenu.add(item);
					item.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							redo();
						}
					});
					popupMenu.addSeparator();
					item = new JMenuItem("Cut");
					popupMenu.add(item);
					item.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cutOperation();
						}
					});
					item = new JMenuItem("Copy");
					popupMenu.add(item);
					item.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							copyOperation();
						}
					});
					item = new JMenuItem("Paste");
					popupMenu.add(item);
					item.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pasteOperation();
						}
					});
					popupMenu.addSeparator();
					item = new JMenuItem("Select All");
					popupMenu.add(item);
					item.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							textPadArea.selectAll();
						}
					});
					textPadArea.setComponentPopupMenu(popupMenu);
				}
			}

			public void mouseEntered(MouseEvent e) {
				if (!dirtyFlag && !textPadArea.getText().equals("")) {
					dirtyFlag = true;
					setTitle(getTitle() + "*");
				}
			}

			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}
		});

		addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent e) {
				textPadArea.requestFocus();
			}

			public void windowClosed(WindowEvent e) {

			}

			public void windowClosing(WindowEvent e) {
				if (dirtyFlag) {
					int option = JOptionPane.showConfirmDialog(
							ScribblePad.this, "Save changes before exit?",
							"Save Changes?", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						saveFile();
						System.exit(0);
					} else {
						System.exit(0);
					}
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
		});
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.setMnemonic('F');
		JMenuItem newItem = new JMenuItem("New");
		newItem.setMnemonic('N');
		menu.add(newItem);
		JMenuItem openItem = new JMenuItem("Open");
		openItem.setMnemonic('O');
		menu.add(openItem);
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setMnemonic('S');
		menu.add(saveItem);
		menu.addSeparator();
		JMenuItem printItem = new JMenuItem("Print");
		printItem.setMnemonic('P');
		menu.add(printItem);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		menu.add(exitItem);
		menuBar.add(menu);
		menu = new JMenu("Edit");
		menu.setMnemonic('E');
		JMenuItem cutItem = new JMenuItem("Cut");
		cutItem.setMnemonic('T');
		menu.add(cutItem);
		JMenuItem copyItem = new JMenuItem("Copy");
		copyItem.setMnemonic('C');
		menu.add(copyItem);
		JMenuItem pasteItem = new JMenuItem("Paste");
		pasteItem.setMnemonic('A');
		menu.add(pasteItem);
		menu.addSeparator();
		JMenuItem undoItem = new JMenuItem("Undo");
		undoItem.setMnemonic('U');
		menu.add(undoItem);
		JMenuItem redoItem = new JMenuItem("Redo");
		redoItem.setMnemonic('R');
		menu.add(redoItem);
		menu.addSeparator();
		JMenuItem findItem = new JMenuItem("Find");
		findItem.setMnemonic('F');
		menu.add(findItem);
		menuBar.add(menu);
		menu = new JMenu("Format");
		menu.setMnemonic('O');
		final JMenuItem wrapItem = new JMenuItem("Wrap Word");
		wrapItem.setMnemonic('W');
		menu.add(wrapItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		undoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});

		redoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redo();
			}
		});

		wrapItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!wrapStyleChosen) {
					wrapItem.setText("Unwrap Word");
					textPadArea.setWrapStyleWord(true);
					textPadArea.setLineWrap(true);
					wrapStyleChosen = !wrapStyleChosen;
				} else {
					wrapItem.setText("Wrap Word");
					textPadArea.setWrapStyleWord(false);
					textPadArea.setLineWrap(false);
					wrapStyleChosen = !wrapStyleChosen;
				}
			}
		});

		cutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutOperation();
			}
		});

		copyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyOperation();
			}
		});

		pasteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasteOperation();
			}
		});

		printItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});

		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});

		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dirtyFlag) {
					int openOption = JOptionPane.showConfirmDialog(
							ScribblePad.this,
							"Do you want to save this before opening?",
							"Save before open?",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (openOption == JOptionPane.YES_OPTION) {
						saveFile();
						openFile();
					} else if (openOption == JOptionPane.NO_OPTION) {
						openFile();
					} else {
						ScribblePad.this.setVisible(true);
					}
				} else {
					openFile();
				}
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dirtyFlag) {
					System.exit(0);
				} else {
					int option = JOptionPane.showConfirmDialog(
							ScribblePad.this, "Save changes before exit?",
							"Save Changes?", JOptionPane.YES_NO_CANCEL_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						saveFile();
						System.exit(0);
					} else if (option == JOptionPane.NO_OPTION) {
						System.exit(0);
					} else {
						ScribblePad.this.setVisible(true);
					}
				}
			}
		});

		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dirtyFlag) {
					int option = JOptionPane.showConfirmDialog(
							ScribblePad.this, "Do you want to save this file?",
							"Save file?", JOptionPane.YES_NO_CANCEL_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						saveFile();
						textPadArea.setText("");
					} else if (option == JOptionPane.NO_OPTION) {
						textPadArea.setText("");
					} else {
						ScribblePad.this.setVisible(true);
					}
				}
			}
		});

		findItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				findGUI();
			}
		});

	}

	private void undo() {
		if (undoManager.canUndo()) {
			undoManager.undo();
		}
	}

	private void redo() {
		if (undoManager.canRedo()) {
			undoManager.redo();
		}
	}

	private void createToolBar() {
		JToolBar toolBar = new JToolBar();
		JButton newButton = new JButton();
		newButton.setIcon(new ImageIcon("Icons//new.gif"));
		toolBar.add(newButton);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dirtyFlag) {
					int option = JOptionPane.showConfirmDialog(
							ScribblePad.this, "Do you want to save this file?",
							"Save file?", JOptionPane.YES_NO_CANCEL_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						saveFile();
						textPadArea.setText("");
					} else if (option == JOptionPane.NO_OPTION) {
						textPadArea.setText("");
					} else {
						ScribblePad.this.setVisible(true);
					}
				}
			}
		});
		newButton = new JButton();
		newButton.setIcon(new ImageIcon("Icons//open.gif"));
		toolBar.add(newButton);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		newButton = new JButton();
		newButton.setIcon(new ImageIcon("Icons//save.gif"));
		toolBar.add(newButton);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		toolBar.addSeparator();
		newButton = new JButton();
		newButton.setIcon(new ImageIcon("Icons//cut.gif"));
		toolBar.add(newButton);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutOperation();
			}
		});
		newButton = new JButton();
		newButton.setIcon(new ImageIcon("Icons//copy.gif"));
		toolBar.add(newButton);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyOperation();
			}
		});
		newButton = new JButton();
		newButton.setIcon(new ImageIcon("Icons//paste.gif"));
		toolBar.add(newButton);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasteOperation();
			}
		});
		toolBar.addSeparator();
		newButton = new JButton();
		newButton.setIcon(new ImageIcon("Icons//find.gif"));
		toolBar.add(newButton);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findGUI();
			}
		});
		newButton = new JButton();
		newButton.setIcon(new ImageIcon("Icons//help.gif"));
		toolBar.add(newButton);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(ScribblePad.this,
						"ScribblePad v1.0. Created by Rakesh");
			}
		});
		add(toolBar, BorderLayout.NORTH);
	}

	private void openFile() {
		if (dirtyFlag) {
			int openOption = JOptionPane.showConfirmDialog(ScribblePad.this,
					"Do you want to save this before opening?",
					"Save before open?", JOptionPane.YES_NO_CANCEL_OPTION);
			if (openOption == JOptionPane.YES_OPTION) {
				saveFile();
				openOperation();
			} else if (openOption == JOptionPane.NO_OPTION) {
				dirtyFlag = !dirtyFlag;
				setTitle(getTitle().substring(0, (getTitle().length() - 1)));
				repaint();
				openOperation();
			} else {
				ScribblePad.this.setVisible(true);
			}
		} else {
			openOperation();
		}
	}

	private void saveFile() {
		JFrame interFrame = new JFrame();
		try {
			interFrame.setBounds(
					(SCREEN_DIMENSION + getX() / 4),
					(SCREEN_DIMENSION + getY() / 2), 200, 100);
			interFrame.setUndecorated(true);
			interFrame.setLayout(new BorderLayout());
			interFrame.add(new JLabel("Saving.. Please wait"),
					BorderLayout.CENTER);
			JProgressBar progress = new JProgressBar();
			progress.setIndeterminate(true);
			JFileChooser fileChooser = new JFileChooser();
			int value = fileChooser.showSaveDialog(ScribblePad.this);
			if (value == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (!file.getName().contains(".")
						|| file.getName().endsWith(".")) {
					String absolutePath = file.getAbsolutePath();
					file = new File(absolutePath + ".txt");
				}
				if (!file.exists()) {
					interFrame.add(progress, BorderLayout.SOUTH);
					interFrame.setVisible(true);
					String text = textPadArea.getText();
					FileOutputStream outputStream = new FileOutputStream(file);
					outputStream.write(text.getBytes());
					outputStream.close();
					dirtyFlag = !dirtyFlag;
					ScribblePad.this
							.setTitle(file.getName() + " - ScribblePad");
				} else {
					int option = JOptionPane.showConfirmDialog(
							ScribblePad.this,
							"Do you want to replace the file?",
							"Replace File?", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						interFrame.add(progress, BorderLayout.SOUTH);
						interFrame.setVisible(true);
						String text = textPadArea.getText();
						FileOutputStream outputStream = new FileOutputStream(
								file);
						outputStream.write(text.getBytes());
						outputStream.close();
						dirtyFlag = !dirtyFlag;
						ScribblePad.this.setTitle(file.getName()
								+ " - ScribblePad");
					} else {
						ScribblePad.this.setVisible(true);
					}
				}
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(ScribblePad.this,
					"Does not have permission to write");
		} catch (Error er) {
			JOptionPane.showMessageDialog(ScribblePad.this,
					"File too huge for ScribblePad");
		} finally {
			interFrame.dispose();
			repaint();
		}
	}

	private void cutOperation() {
		String selectedText = textPadArea.getSelectedText();
		int endPosition = 0;
		int startPosition = 0;
		String text = textPadArea.getText();
		if (selectedText != null) {
			startPosition = textPadArea.getSelectionStart();
			endPosition = textPadArea.getSelectionEnd();
			int length = text.length();
			if (length > endPosition) {
				String text1 = text.substring(0, startPosition);
				String text2 = text.substring((endPosition + 1), length);
				textPadArea.setText(text1 + text2);
				textPadArea.setCaretPosition(startPosition);
			} else {
				text = text.substring(0, startPosition);
				textPadArea.setText(text);
			}
			StringSelection contents = new StringSelection(selectedText);
			clipboard.setContents(contents, ScribblePad.this);
		}
	}

	private void copyOperation() {
		String selectedText = textPadArea.getSelectedText();
		if (selectedText != null) {
			StringSelection contents = new StringSelection(selectedText);
			clipboard.setContents(contents, ScribblePad.this);
		}
	}

	private void pasteOperation() {
		Transferable content = clipboard.getContents(ScribblePad.this);
		String text = textPadArea.getText();
		if (content != null) {
			try {
				String clipBoardData = (String) content
						.getTransferData(DataFlavor.stringFlavor);
				int start = textPadArea.getSelectionStart();
				int end = textPadArea.getSelectionEnd();
				int textLength = text.length();
				if (textLength >= end) {
					if (start == 0 && end == 0) {
						textPadArea.append(clipBoardData);
					} else {
						String text1 = text.substring(0, start);
						String text2 = text.substring(end);
						String newText = text1 + clipBoardData + text2;
						textPadArea.setText(newText);
						textPadArea.setCaretPosition(text1.length()
								+ clipBoardData.length());
					}
				} else if (textPadArea.getSelectedText() != null) {
					textPadArea.setText(clipBoardData);
				} else {
					textPadArea.append(clipBoardData);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(ScribblePad.this,
						"Couldnt access Clipboard");
			}
		}
	}

	private void print() {
		try {
			InputStream inputStream = new ByteArrayInputStream(textPadArea
					.getText().getBytes());
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			PrintService service = PrintServiceLookup
					.lookupDefaultPrintService();
			DocPrintJob printJob = service.createPrintJob();
			Doc doc = new SimpleDoc(inputStream, flavor, null);
			printJob.print(doc, null);
			inputStream.close();
			JOptionPane.showMessageDialog(ScribblePad.this,
					"Document sent to the printer");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(ScribblePad.this,
					"Printer not available");
		}
	}

	private void findGUI() {
		frame = new JFrame();
		JButton findButton = new JButton("Find");
		JButton replaceButton = new JButton("Replace");
		JButton replaceAllButton = new JButton("ReplaceAll");
		final JTextField findTextBox = new JTextField();
		final JTextField replaceTextBox = new JTextField();
		frame.setTitle("Find/Replace");
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		frame.setBounds((SCREEN_DIMENSION + getX() / 8),
				(SCREEN_DIMENSION + getY() / 2), 275, 150);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.getRootPane().setDefaultButton(findButton);
		panel.add(new JLabel("Find:"));
		panel.add(findTextBox);
		panel.add(new JLabel("Replace:"));
		panel.add(replaceTextBox);
		frame.add(panel, BorderLayout.CENTER);
		panel = new JPanel();
		panel.add(findButton);
		panel.add(replaceButton);
		panel.add(replaceAllButton);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setVisible(true);
		findTextBox.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {
				if (findTextBox.getText().trim().equals("")) {
					counter = 0;
				}
			}

			public void keyTyped(KeyEvent e) {

			}
		});
		frame.addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent e) {

			}

			public void windowClosed(WindowEvent e) {
				counter = 0;
			}

			public void windowClosing(WindowEvent e) {

			}

			public void windowDeactivated(WindowEvent e) {

			}

			public void windowDeiconified(WindowEvent e) {

			}

			public void windowIconified(WindowEvent e) {

			}

			public void windowOpened(WindowEvent e) {

			}
		});
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (findTextBox.getText() != null
						&& !findTextBox.getText().equals("")) {
					findText = findTextBox.getText();
					find();
				}
			}
		});
		replaceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (findTextBox.getText() != null
						&& !findTextBox.getText().equals("")
						&& replaceTextBox.getText() != null) {
					findText = findTextBox.getText();
					replaceText = replaceTextBox.getText();
					replace();
				}
			}
		});
		replaceAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (findTextBox.getText() != null
						&& !findTextBox.getText().equals("")
						&& replaceTextBox.getText() != null) {
					findText = findTextBox.getText();
					replaceText = replaceTextBox.getText();
					replaceAll();
				}
			}
		});
	}

	private void openOperation() {
		new Thread(new Runnable() {
			public void run() {
				JFrame interFrame = new JFrame();
				try {
					interFrame.setBounds((SCREEN_DIMENSION + ScribblePad.this
							.getX() / 4), (SCREEN_DIMENSION + ScribblePad.this
							.getY() / 2), 200, 100);
					interFrame.setUndecorated(true);
					interFrame.setLayout(new BorderLayout());
					interFrame.add(new JLabel("Opening.. Please wait"),
							BorderLayout.CENTER);
					JProgressBar progress = new JProgressBar();
					progress.setIndeterminate(true);
					JFileChooser fileChooser = new JFileChooser();
					int option = fileChooser.showOpenDialog(ScribblePad.this);
					File file = fileChooser.getSelectedFile();
					if (option == JFileChooser.APPROVE_OPTION) {
						if (file.isFile() && file.canRead()) {
							interFrame.add(progress, BorderLayout.SOUTH);
							interFrame.setVisible(true);
							FileReader reader = new FileReader(file);
							char[] data = new char[4096];
							int i;
							StringBuffer buffer = new StringBuffer();
							while ((i = reader.read(data, 0, data.length)) != -1) {
								buffer.append(new String(data, 0, i));
							}
							textPadArea.setText(buffer.toString());
							setTitle(file.getName() + " - ScribblePad");
						} else {
							JOptionPane.showMessageDialog(ScribblePad.this,
									"Could not open the file");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(ScribblePad.this,
							"Error in opening file");
				} catch (Error er) {
					JOptionPane.showMessageDialog(ScribblePad.this,
							"File too huge to open");
				} finally {
					interFrame.dispose();
					ScribblePad.this.repaint();
				}
			}
		}).start();
	}

	private void find() {
		if (subLoop) {
			find(subToken);
		} else {
			String newText = textPadArea.getText().substring(counter);
			StringTokenizer tokenizer = new StringTokenizer(newText, " ", true);
			LOOP: while (tokenizer.hasMoreTokens()) {
				String nextToken = tokenizer.nextToken();
				int decrementor = nextToken.length();
				if (nextToken.length() > 1 && contains(nextToken)) {
					if (find(shaveString(nextToken))) {
						break LOOP;
					}
				} else {
					counter += (nextToken.length());
					if (nextToken.trim().equalsIgnoreCase(findText)) {
						if (counter - decrementor == 0) {
							textPadArea.setSelectionStart(0);
							textPadArea.setSelectionEnd(counter);
						} else {
							textPadArea
									.setSelectionStart((counter - decrementor));
							textPadArea.setSelectionEnd(counter);
						}
						break LOOP;
					}
				}
			}
			if (counter >= textPadArea.getText().length()) {
				JOptionPane.showMessageDialog(frame, "End of document");
				counter = 0;
			}
		}
	}

	private boolean find(String inputString) {
		boolean returnValue = false;
		subLoop = true;
		inputString = removeTabSpaces(inputString);
		subToken = inputString;
		subTokenLength = inputString.length();
		StringTokenizer tokenizer = new StringTokenizer(inputString, " ", true);
		while (tokenizer.hasMoreTokens()) {
			String nextToken = tokenizer.nextToken();
			counter += nextToken.length();
			int decrementor = nextToken.length();
			if (nextToken.trim().equalsIgnoreCase(findText)) {
				textPadArea.setSelectionStart((counter) - (decrementor));
				textPadArea.setSelectionEnd(counter);
				subToken = subToken.substring(decrementor);
				returnValue = true;
				break;
			} else {
				subToken = subToken.substring(decrementor);
			}
		}
		if (subTokenLength <= 0) {
			subLoop = false;
		}
		return returnValue;
	}

	private String removeTabSpaces(String input) {
		StringBuffer returnString = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) != '\t') {
				returnString.append(input.charAt(i));
			} else {
				returnString.append(" ");
			}
		}
		return returnString.toString();
	}

	private boolean contains(String inputString) {
		boolean returnValue = false;
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) == '\n' || inputString.charAt(i) == '\t') {
				returnValue = true;
				break;
			}
		}
		return returnValue;
	}

	private String shaveString(String inputString) {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < inputString.length(); i++) {
			char character = inputString.charAt(i);
			if (character != '\n' || character == '\t') {
				output.append(character);
			} else {
				output.append(" ");
			}
		}
		return output.toString();
	}

	private void replace() {
		Pattern pattern = Pattern.compile(findText);
		Matcher matcher = pattern.matcher(textPadArea.getText());
		StringBuffer buffer = new StringBuffer();
		boolean result = matcher.find();
		int occurance = 0;
		if (result) {
			matcher.appendReplacement(buffer, replaceText);
			occurance += 1;
		}
		matcher.appendTail(buffer);
		textPadArea.setText(buffer.toString());
		if (occurance != 0) {
			dirtyFlag = true;
			JOptionPane.showMessageDialog(this, "One occurance replaced");
			if (!getTitle().contains("*")) {
				setTitle(getTitle() + "*");
			} else {
				setTitle(getTitle());
			}
		}
	}

	private void replaceAll() {
		Pattern pattern = Pattern.compile(findText);
		Matcher matcher = pattern.matcher(textPadArea.getText());
		StringBuffer buffer = new StringBuffer();
		int matchCount = 0;
		boolean result = matcher.find();
		while (result) {
			matcher.appendReplacement(buffer, replaceText);
			result = matcher.find();
			matchCount += 1;
		}
		matcher.appendTail(buffer);
		textPadArea.setText(buffer.toString());
		JOptionPane.showMessageDialog(frame, String.valueOf(matchCount)
				+ " occurance(s) replaced");
		if (matchCount != 0) {
			dirtyFlag = true;
			if (!getTitle().contains("*")) {
				setTitle(getTitle() + "*");
			} else {
				setTitle(getTitle());
			}
		}
	}

	private void monitorSizeChange() {
		while (true) {
			try {
				Thread.sleep(500);
				if (getWidth() < 400 && !smallSize) {
					for (int i = 0; i < getContentPane().getComponentCount(); i++) {
						Component component = getContentPane().getComponent(i);
						if (component instanceof JToolBar) {
							JToolBar newToolBar = (JToolBar) component;
							newToolBar.setLayout(new GridLayout(3, 3));
							add(newToolBar, BorderLayout.NORTH);
							setVisible(false);
							repaint();
							setVisible(true);
							smallSize = true;
							break;
						}
					}
				} else if (getWidth() > 400 && smallSize) {
					for (int j = 0; j < getContentPane().getComponentCount(); j++) {
						Component component = getContentPane().getComponent(j);
						if (component instanceof JToolBar) {
							JToolBar newToolBar = (JToolBar) component;
							newToolBar
									.setLayout(new FlowLayout(FlowLayout.LEFT));
							add(newToolBar, BorderLayout.NORTH);
							setVisible(false);
							repaint();
							setVisible(true);
							smallSize = false;
							break;
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents) {

	}

	public static void main(String[] args) {
		new ScribblePad().setVisible(true);
	}

}
