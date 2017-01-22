/**
 * This is the node class.
 * 
 * @author NadiraDewji
 *
 * @param <E>
 *            generic type that implements comparable
 */
public class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
	/**
	 * This is the right data field and it references the right child
	 */
	private Node<E> right;
	/**
	 * This is the left data field and it references the left child
	 */
	private Node<E> left;
	/**
	 * This is the node's own data.
	 */
	private E data;

	/**
	 * This is the constructor for the node.
	 */
	public Node() {

	}

	/**
	 * This constructor takes in data as a parameter and sets the node's data to
	 * data.
	 * 
	 * @param data
	 */
	public Node(E data) {
		this.data = data;

	}

	/**
	 * This is the node's third constructor that takes in three parameters node
	 * right and node left
	 * 
	 * @param data
	 * @param right
	 *            child
	 * @param left
	 *            child
	 */
	public Node(E data, Node<E> right, Node<E> left) {
		this.data = data;
		this.right = right;
		this.left = left;
	}

	/**
	 * This is the compareTo method that compares the data against each other
	 * using their own compareTo methods
	 */

	public int compareTo(Node<E> myNode) {
		return this.data.compareTo((E) myNode.data);
	}

	/**
	 * This is the getData to access the private data.
	 * 
	 * @return the data
	 */

	public E getData() {
		return this.data;
	}

	/**
	 * This is the setRight method that sets the rightNode to a specific data
	 * value
	 * 
	 * @param myRight,
	 *            the node you are setting
	 */
	public void setRight(Node<E> myRight) {
		this.right = myRight;
	}

	/**
	 * This is the setLeft method that sets the leftNode to a specific data
	 * value
	 * 
	 * @param myLeft,
	 *            the node you are setting
	 */
	public void setLeft(Node<E> myLeft) {
		this.left = myLeft;
	}

	/**
	 * This method sets the current node's data
	 * 
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * This gets the rightNode
	 * 
	 * @return the node, the right child
	 */
	public Node<E> getRightNode() {
		return this.right;
	}

	/**
	 * This gets the leftNode
	 * 
	 * @return the node, the left child
	 */
	public Node<E> getLeftNode() {
		return this.left;
	}

	/**
	 * This returns the data's tostring method
	 */

	public String toString() {
		return data.toString();

	}

}
