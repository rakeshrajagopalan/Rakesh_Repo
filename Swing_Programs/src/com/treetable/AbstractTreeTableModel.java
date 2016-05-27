package com.treetable;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

/**
 * An abstract implementation of the TreeTableModel interface, handling the list
 * of listeners.
 * 
 * @version %I% %G%
 * 
 * @author Philip Milne
 */

public abstract class AbstractTreeTableModel implements TreeTableModel {
	protected Object root;
	protected EventListenerList listenerList = new EventListenerList();

	public AbstractTreeTableModel(Object root) {
		this.root = root;
	}

	//
	// Default implmentations for methods in the TreeModel interface.
	//

	public Object getRoot() {
		return root;
	}

	public boolean isLeaf(Object node) {
		return getChildCount(node) == 0;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	// This is not called in the JTree's default mode: use a naive
	// implementation.
	public int getIndexOfChild(Object parent, Object child) {
		for (int i = 0; i < getChildCount(parent); i++) {
			if (getChild(parent, i).equals(child)) {
				return i;
			}
		}
		return -1;
	}

	public void addTreeModelListener(TreeModelListener l) {
		listenerList.add(TreeModelListener.class, l);
	}

	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(TreeModelListener.class, l);
	}

	//
	// Default impelmentations for methods in the TreeTableModel interface.
	//

	public Class getColumnClass(int column) {
		return Object.class;
	}

	/**
	 * By default, make the column with the Tree in it the only editable one.
	 * Making this column editable causes the JTable to forward mouse and
	 * keyboard events in the Tree column to the underlying JTree.
	 */
	public boolean isCellEditable(Object node, int column) {
		return getColumnClass(column) == TreeTableModel.class;
	}

	public void setValueAt(Object aValue, Object node, int column) {
	}

	// Left to be implemented in the subclass:

	/*
	 * public Object getChild(Object parent, int index) public int
	 * getChildCount(Object parent) public int getColumnCount() public String
	 * getColumnName(Object node, int column) public Object getValueAt(Object
	 * node, int column)
	 */

}
