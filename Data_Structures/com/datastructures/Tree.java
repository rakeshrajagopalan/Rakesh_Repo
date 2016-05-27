package com.datastructures;

import java.io.Serializable;

public class Tree implements Serializable {

	private static final long serialVersionUID = 3793526500801833732L;

	private Node rootNode;

	private int height;

	private class Node {

		private int key;

		private int value;

		private Node leftChild;

		private Node rightChild;

		private Node(int key, int value) {
			this.key = key;
			this.value = value;
		}

	}

	private class WrongValueException extends RuntimeException {

		private static final long serialVersionUID = -8243200184799985713L;

		public WrongValueException() {
			System.out.println("Key cannot be negative");
		}

	}

	public int find(int key) {
		if (key != -1) {
			Node current = rootNode;
			while (current.key != key) {
				if (key < current.key) {
					current = current.leftChild;
				} else {
					current = current.rightChild;
				}
				if (current == null) {
					return -1;
				}
			}
			return current.value;
		} else {
			throw new WrongValueException();
		}
	}

	public void insert(int key, int value) {
		if (key != -1) {
			Node newNode = new Node(key, value);
			if (rootNode == null) {
				rootNode = newNode;
			} else {
				Node current = rootNode;
				Node parent;
				while (true) {
					parent = current;
					if (key < current.key) {
						current = current.leftChild;
						if (current == null) {
							parent.leftChild = newNode;
							break;
						}
					} else {
						current = current.rightChild;
						if (current == null) {
							parent.rightChild = newNode;
							break;
						}
					}
				}
			}
		} else {
			throw new WrongValueException();
		}
	}

	public boolean delete(int key) {
		if (key != -1) {
			Node current = rootNode;
			Node parent = rootNode;
			boolean isLeftChild = true;
			while (current.key != key) {
				parent = current;
				if (key < current.key) {
					isLeftChild = true;
					current = current.leftChild;
				} else {
					isLeftChild = false;
					current = current.rightChild;
				}
				if (current == null) {
					return false;
				}
			}
			if (current.leftChild == null && current.rightChild == null) {
				if (current == rootNode) {
					rootNode = null;
				} else if (isLeftChild) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
			} else if (current.rightChild == null) {
				if (current == rootNode) {
					rootNode = current.leftChild;
				} else if (isLeftChild) {
					parent.leftChild = current.leftChild;
				} else {
					parent.rightChild = current.leftChild;
				}
			} else if (current.leftChild == null) {
				if (current == rootNode) {
					rootNode = current.rightChild;
				} else if (isLeftChild) {
					parent.leftChild = current.rightChild;
				} else {
					parent.rightChild = current.rightChild;
				}
			} else {
				Node successor = getSuccessor(current);
				if (current == rootNode) {
					rootNode = successor;
				} else if (isLeftChild) {
					parent.leftChild = successor;
				} else {
					parent.rightChild = successor;
					successor.leftChild = current.leftChild;
				}
			}
			return true;
		} else {
			throw new WrongValueException();
		}
	}

	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	// Left - Root - Right
	public void inorderTraversal(Node localRootNode) {
		if (localRootNode != null) {
			inorderTraversal(localRootNode.leftChild);
			System.out.print(localRootNode.value + " ");
			inorderTraversal(localRootNode.rightChild);
		}
	}

	public int treeHeight(Node rootNode) {
		if ((rootNode != null)
				&& (rootNode.leftChild != null || rootNode.rightChild != null)) {
			treeHeight(rootNode.leftChild);
			treeHeight(rootNode.rightChild);
			height++;
		}
		return height;
	}

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(32, 4);
		tree.insert(24, 3);
		tree.insert(45, 5);
		tree.insert(23, 2);
		tree.insert(46, 3);
		tree.insert(25, 4);
		tree.insert(43, 6);
		tree.insert(17, 6);
		tree.insert(60, 6);
		tree.insert(53, 6);
		tree.insert(22, 6);
		tree.insert(31, 6);
		tree.insert(33, 6);
		System.out.println(tree.treeHeight(tree.rootNode.leftChild));
		System.out.println(tree.treeHeight(tree.rootNode.rightChild));
	}

}
