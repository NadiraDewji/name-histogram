import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.*;

/**
 * This is the myBST class the fundamental class in this project that is
 * repsonsibile for the data structure that stores the information
 * 
 * @author NadiraDewji
 *
 * @param <E>
 */
public class MyBST<E extends Comparable<E>> implements Iterable<E> {
	protected Node<E> root;
	protected int size;

	public MyBST() {
	}

	/**
	 * This add method throws a nullPointerException if you try and add null It
	 * will throw a casexception if you try and compare two objects that cannot
	 * be compared. Other wise it uses a contains method and uses another
	 * recursive add method to transverse through the tree until the nodes and
	 * then add the data field.
	 * 
	 * @param a
	 *            the data that you wish to add
	 * @return returns a boolean indicating whether it was successfully added.
	 */
	public boolean add(E a) {
		/**
		 * MyBST needs to be equivalent to that o tree set class
		 */
		if (a == null) {
			throw new NullPointerException();
		} else {
			if (root == null) {
				Node<E> newNode = new Node<E>(a);
				root = newNode;
				size += 1;
				return true;
			} else {
				Node<E> myNode = root;
				if (recAdd(myNode, a) == false) {
					return false;
				} else {
					size += 1;
					return true;

				}

			}

		}

	}

	/**
	 * This is a contains method that contains a wrapper method that calls on a
	 * recursive method to check whether the data is contained in the tree. It
	 * throws a NullPointerException if you try and see if a null object is
	 * present in the tree.
	 * 
	 * @param o
	 *            the object you are trying to see is in the tree
	 * @return boolean, returns true if it is present, otherwise returns false.
	 */

	public boolean contains(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		Node<E> currentNode = root;
		return recContains(currentNode, (E) o);

	}

	/**
	 * This returns the root node. The bottom node in the tree, and goes left,
	 * to get the smallest node.
	 * 
	 * @return the element at the most left node.
	 */

	public E first() {
		if (root == null) {
			throw new NoSuchElementException();
		}
		Node<E> currentNode = root;
		while (currentNode.getLeftNode() != null) {
			currentNode = currentNode.getLeftNode();
		}
		return currentNode.getData();

	}

	/**
	 * Determines whether the tree is empty if root == null then it returns
	 * true, else it returns false.
	 * 
	 * @return boolean value
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * To get the first element. You have to first check whether or not root is
	 * null, and then
	 * 
	 * @return the data that is in the right most node.
	 */

	public E last() {
		if (root == null) {
			throw new NoSuchElementException();
		}
		Node<E> currentNode = root;
		while (currentNode.getRightNode() != null) {
			currentNode = currentNode.getRightNode();
		}
		return currentNode.getData();

	}

	/**
	 * this is the remove method that throws a null pointer exception if the
	 * object added is null if the root is null it returns false.
	 * 
	 * @param o
	 *            the object that needs to be removed
	 * @return the return type is boolean true if this set contained the
	 *         specified element
	 */

	public boolean remove(Object o) {
		if (o == null) {
			throw new NullPointerException();
		} else if (root == null) {
			return false;
		} else if (recRemove(root, (E) o) == null) {
			return false;
		} else {
			size = size - 1;
			return true;
		}

	}

	/**
	 * This is the iterator method and it returns type iterator so that you can
	 * transvers through the three
	 */
	public Iterator<E> iterator() {
		BSTIterator myBSTIterator = new BSTIterator(root);
		return myBSTIterator;

	}

	/**
	 * This is the recursive add method that takes in a node and data field and
	 * recursively iterates through the tree until it finds the leaves so that
	 * it can add if the current data is less than the present node it will move
	 * to the left child or if largest move to the right else it is the same and
	 * no duplicates are allowed
	 * 
	 * @param node
	 *            the container
	 * @param newData
	 *            the new data needed to be added
	 * @return boolean stating whether it is true or not
	 */

	private boolean recAdd(Node<E> node, E newData) {
		// if(node ==null){
		// return false;
		// }
		if (newData.compareTo(node.getData()) < 0) {
			if (node.getLeftNode() != null) {
				return recAdd(node.getLeftNode(), newData);
			} else {
				Node<E> myNewNode = new Node<E>(newData);
				node.setLeft(myNewNode);
				return true;
			}
		} else if (newData.compareTo(node.getData()) > 0) {
			if (node.getRightNode() != null) {
				return recAdd(node.getRightNode(), newData);
			} else {
				Node<E> myNewNode = new Node<E>(newData);
				node.setRight(myNewNode);
				return true;
			}
		}
		return false;

	}

	/**
	 * 
	 * @param node
	 *            the one you will use to use recursion
	 * @param the
	 *            data you are looking to remove.
	 * @return the node you removed.
	 */

	private Node<E> recRemove(Node<E> node, E data) {
		/**
		 * You must look at the different cases. You may remove a leaf Node. You
		 * may remove a node with one child you mat remove a node with two
		 * children.
		 */
		if (node == null) {
			return null;
		} else if (node.getData().compareTo(data) > 0) {
			node.setLeft(recRemove(node.getLeftNode(), data));
		} else if (node.getData().compareTo(data) < 0) {
			node.setRight(recRemove(node.getRightNode(), data));
		} else {
			node = remove(node);
		}
		return node;

	}

	/**
	 * this is the remove method that is used in the recursive remove method.
	 * 
	 * @param node
	 * @return the node that is removed.
	 */

	private Node<E> remove(Node<E> node) {
		if (node.getLeftNode() == null) {
			return node.getRightNode();
		}
		if (node.getRightNode() == null) {
			return node.getLeftNode();
		} else {
			E data = getPredecessor(node);
			node.setData(data);
			node.setLeft(recRemove(node.getLeftNode(), data));
			return node;
		}
	}

	/**
	 * this gets the node you must replace the node you removed with and it gets
	 * the predecessor
	 * 
	 * @param myNode
	 * @return the data in the predecessor
	 */

	private E getPredecessor(Node<E> myNode) {

		Node<E> myN = myNode.getLeftNode();
		while (myN.getRightNode() != null) {
			myN = myN.getRightNode();
		}
		return myN.getData();
	}

	/**
	 * this is the recursive contains method that transverses through the trees
	 * to see whether it contains the data if the data is less than it goes left
	 * and if it is bigger it goes right.
	 * 
	 * @param currentNode
	 * @param data
	 * @return
	 */

	private boolean recContains(Node<E> currentNode, E data) {
		if (currentNode == null) {
			return false;
		} else if (currentNode.getData().compareTo(data) < 0) {
			return recContains(currentNode.getRightNode(), data);

		} else if (currentNode.getData().compareTo(data) > 0) {
			return recContains(currentNode.getLeftNode(), data);
		} else {
			return true;
		}
	}

	public class BSTIterator implements Iterator<E> {
		ArrayList<E> myArrayList = new ArrayList<E>();
		private int index = 0;

		public BSTIterator(Node<E> root) {
			inOrder(root);

			/**
			 * Recursion in order call a helper method adding things to the
			 * arraylist
			 */
		}

		public void inOrder(Node<E> n) {
			if (n != null) {
				inOrder(n.getLeftNode());
				myArrayList.add(n.getData());
				inOrder(n.getRightNode());

			}
		}

		/**
		 * This method is the hasNext method and checks to see whether there are
		 * more elements in the arraylist.
		 */

		@Override
		public boolean hasNext() {
			return (index < myArrayList.size());
		}

		@Override
		/**
		 * This is the next method and it returns the data type in the arraylist
		 * using the index.
		 */
		public E next() {
			// index++;
			return myArrayList.get(index++);
		}

		/**
		 * This method throws an exception because it is not implemented.
		 * 
		 * @param action
		 */
		public void foreachRemaining(Consumer<? super E> action) {
			throw new UnsupportedOperationException("Method is not implemented");
		}

		/**
		 * This method throws an exception because it is not implemented.
		 * 
		 * @param action
		 */
		public void remove() {
			throw new UnsupportedOperationException("Method is not implemented");
		}

	}

}
