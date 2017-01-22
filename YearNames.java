import java.util.*;;

/**
 * 
 * @author NadiraDewji This is a YearNames class.
 *
 */

public class YearNames extends MyBST<Name> {
	/**
	 * NO BST CLASS
	 */
	private int year;
	private int totalBabyCount;
	// private YearNames myYearNameTree;

	/**
	 * this constructor makes a YearNames object
	 * 
	 * @param year
	 */

	public YearNames(int year) {
		this.year = year;
	}

	/**
	 * This is the add method and it adds a name object to the YearNames object.
	 * If n exists it will thrown and IllegalArgumentException It also uses the
	 * contains method, but first it checks the exists method that only uses a
	 * compareTo method that checks gender and name otherwise it adds the name
	 * object using the myBST add method.
	 */
	@Override
	public boolean add(Name n) {
		if (this.exists(n)) {
			throw new IllegalArgumentException("You are trying to add a Name that already exists");
		}
		if (this.contains(n)) {
			throw new IllegalArgumentException("You are trying to add a Name that already exists");
		} else {
			totalBabyCount += n.getCount();
			return super.add(n);

		}

	}

	/**
	 * This is the getCountByName method and it takes in name and gender
	 * arguments
	 * 
	 * @param name
	 *            the string name
	 * @param gender
	 *            the gender
	 * @return returns an integer indicating the count if the parameters are
	 *         null it throws and illegal argument exception if the gender is
	 *         not m or f it throws and invalid gender exception.
	 */

	public int getCountByName(String name, String gender) {
		if (name == null || gender == null) {
			throw new IllegalArgumentException("Invalid");
		}
		if (!(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f"))) {
			throw new IllegalArgumentException("Invalid Gender");

		}

		// int count = 0;
		// Iterator<Name> myIterator = super.iterator();
		// while (myIterator.hasNext()) {
		// Name myName = myIterator.next();
		// if (myName.getName().equalsIgnoreCase(name) &&
		// myName.getGender().equalsIgnoreCase(gender)) {
		// count = myName.getCount();
		// }
		// }
		Name myName = new Name(name, gender, 0);
		Name myNewName = getName(this.root, myName);
		if (myNewName == null) {
			return 0;
		} else {
			return myNewName.getCount();
		}

	}

	/**
	 * this is getFractionByName method it takes in name and gender as parameter
	 * and if the arguments are null it throws an exception if it is not m or f
	 * it throws another error.
	 * 
	 * @param name
	 *            the name of the name object
	 * @param gender
	 *            the gender of the name object
	 * @return a double representing the percentage of the name. The method uses
	 *         the iterator and transverses through the tree and calculates the
	 *         count for eveyr Name object in the tree.
	 */
	public double getFractionByName(String name, String gender) {
		if (name == null || gender == null) {
			throw new IllegalArgumentException("Invalid");
		}
		if (!(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f"))) {
			throw new IllegalArgumentException("Invalid Gender");

		}
		// int totalCount = 0;
		// Iterator<Name> myIterator = super.iterator();
		// while (myIterator.hasNext()) {
		// Name myName = myIterator.next();
		// totalCount += myName.getCount();
		// }
		float myNum = (float) (getCountByName(name, gender)) / (totalBabyCount);
		return (myNum * 100);

	}

	@Override
	public String toString() {
		return "YearNames [year=" + year + ", total number of names=" + this.size + "]";
	}

	/**
	 * This is the exists method, a copy and tailored verison of the contains
	 * method however uses the compareToE method in the name class.
	 * 
	 * @param myName
	 * @return a boolean expressing whether or not the name object is present.
	 */
	public boolean exists(Name myName) {
		Node<Name> currentNode = root;
		return recExists(currentNode, myName);
	}

	/**
	 * a tailored copy of my recContians method using compareToE
	 * 
	 * @param currentNode
	 *            takes in a currentNose
	 * @param data
	 *            takes in the name object
	 * @return boolean expressing whether it exists
	 */
	public boolean recExists(Node<Name> currentNode, Name data) {
		if (currentNode == null) {
			return false;
		} else if (currentNode.getData().compareToE(data) < 0) {
			return recExists(currentNode.getRightNode(), data);

		} else if (currentNode.getData().compareToE(data) > 0) {
			return recExists(currentNode.getLeftNode(), data);
		} else {
			return true;
		}

	}

	/**
	 * this is a getName method that uses binary search algorithm looks for a
	 * name with a same gender and name, count does not matter so it is set to
	 * zero, because compareToE only uses gender and name anyway.
	 * 
	 * @param currentNode
	 *            is the node through which you transverse
	 * @param name
	 *            the name object
	 * @return returns a name with the same name and gender as the parameter.
	 */
	public Name getName(Node<Name> currentNode, Name name) {
		if (currentNode == null) {
			return null;
		} else if (name.compareToE(currentNode.getData()) < 0) {
			return getName(currentNode.getLeftNode(), name);
		} else if (name.compareTo(currentNode.getData()) > 0) {
			return getName(currentNode.getRightNode(), name);
		} else {
			return currentNode.getData();
		}
	}

}
