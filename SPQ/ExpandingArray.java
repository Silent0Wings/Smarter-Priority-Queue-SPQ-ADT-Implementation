package SPQ;

/**
 * The {@code ExpandingArray} class represents a dynamic array that
 * automatically expands to accommodate additional elements. It is specifically
 * designed to support generic types, allowing it to store any type of objects.
 * This class provides basic functionality such as adding, removing, and
 * accessing elements by index, as well as utility methods for managing the
 * array's capacity.
 *
 * <p>
 * Internally, it uses an array of {@code Node} objects to store the elements.
 * The {@code Node} class must be defined elsewhere in the package and is
 * assumed to have a constructor and possibly other relevant methods.
 * </p>
 *
 * @param <K, V> the type of elements stored in this expanding array
 */
@SuppressWarnings({ "unused", "unchecked" })
public class ExpandingArray<K extends Comparable<K>, V> {

	private Entry<K, V>[] Array; // Internal array to hold Queue elements
	private int size; // Number of elements in the Queue
	private static final int DefaultCapacity = 9; // Default starting size of the array in case an empty array is
													// needed

	/**
	 * Constructs an Array with the specified type.
	 * 
	 * @param type The class type of the elements stored in the array.
	 */
	public ExpandingArray() {
		this(DefaultCapacity);
	}

	public ExpandingArray(int starting_size) {
		this.Array = (Entry<K, V>[]) new Entry[starting_size];
		this.size = 0;
		for (int i = 0; i < size; i++) {
			Array[i] = null;
		}
	}

	/**
	 * Returns the number of items in the Queue.
	 * 
	 * Time Complexity: O(1), just returning the size. Space Complexity: O(1), no
	 * extra space used.
	 * 
	 * @return The size of the Queue.
	 */
	public int size() {
		return size;
	}

	public int length() {
		return size;
	}

	/**
	 * Returns the capacity of the array.
	 * 
	 * @return the capacity of the array
	 */
	public int Capacity() {
		return Array.length;
	}

	/**
	 * Decrements the size of the array.
	 */
	private void decrementSize() {
		size--;
	}

	/**
	 * Increments the size of the array.
	 */
	private void incrementSize() {
		size++;
	}  

	/**
	 * Checks whether the Queue is empty.
	 * 
	 * Time Complexity: O(1), checks only the size attribute. Space Complexity:
	 * O(1), no extra space used.
	 * 
	 * @return True if the Queue is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size <= 0;
	}

	/**
	 * Clears all items from the Queue.
	 * 
	 * Time Complexity: O(n), where n is the number of elements in the Queue. Space
	 * Complexity: O(1), as it doesn't allocate any new structures.
	 */
	public void clear() {
		for (int i = 0; i < size; i++)
			Array[i] = null;
		size = 0;
	}

	/**
	 * Ensures that the internal array has enough capacity to accommodate new
	 * elements.
	 * 
	 * Time Complexity: O(n), where n is the current size of the array. - The method
	 * checks if the current capacity of the array is sufficient to accommodate new
	 * elements.
	 * 
	 * If not, it creates a new array with double the capacity and copies the
	 * existing elements to it, which requires traversing through all the elements
	 * in the array once, resulting in a time complexity of O(n), where n is the
	 * current size of the array.
	 * 
	 * Overall, the ensureCapacity() method has a space complexity of O(n), a time
	 * complexity of O(n), and an amortized space complexity of O(1) per insertion
	 * operation.
	 */
	public void ensureCapacity() {
		if (size == Array.length-1) { // this is to ensure that 
			Entry<K, V>[] newQueueArray = (Entry<K, V>[]) new Entry[2 * (2 * size + 2)];
			System.arraycopy(Array, 0, newQueueArray, 0, size);
			Array = (Entry<K, V>[]) newQueueArray;
		}
	}

	/**
	 * Swaps the elements at the specified indices in the heap array.
	 * 
	 * @param index1 The index of the first element.
	 * @param index2 The index of the second element.
	 */
	public void swapIndex(int index1, int index2) {
		if (index1 < 0 || index1 >= Array.length || index2 < 0 || index2 >= Array.length) {
			throw new IndexOutOfBoundsException(
					"Index is out of range : ( index 1 :" + index1 + " | index 2 : " + index2 + " ).");
		}

		Entry<K, V> temp = Array[index1];
		Array[index1] = Array[index2];
		Array[index2] = temp;
		
		Array[index2].setIndex(index2);
		Array[index1].setIndex(index1);

	}

	/**
	 * Gets the element at the specified index.
	 * 
	 * @param index The index of the element to retrieve.
	 * @return The element at the specified index, or null if the index is out of
	 *         bounds.
	 */
	public Entry<K, V> get(int index) {
		if (index < 0 || index >= Array.length)
			return null;
		return Array[index];
	}  

	/**
	 * Sets the element at the specified index.
	 * 
	 * @param index The index at which to set the element.
	 * @param data  The data to set at the specified index.
	 */
	public void set(int index, Entry<K, V> D) {
		if (D == null) {
			throw new IllegalArgumentException("Dont use this for remove behavior");
		}

		if (index < 0 || index >= Array.length || D == null)
			return;
		if (Array[index] == null) {
			size++;
		}  

		Array[index] = D;
		Array[index].setIndex(index);
	}

	/**
	 * Sets the element at the specified index.
	 * 
	 * @param index The index at which to set the element.
	 * @param data  The data to set at the specified index.
	 */
	public void setNew(int index, K newK, V data) {
		if (index < 0 || index >= Array.length)
			return;
		Array[index] = new Entry<K, V>(newK, data);
		size++;
		Array[index].setIndex(index);

	}

	/**
	 * Removes the element at the specified index from the array and returns it.
	 * 
	 * @param index the index of the element to be removed
	 * @return the removed element, or null if the index is out of bounds
	 */
	public Entry<K, V> remove(int index) {
		// Check if the index is within the valid range of the array
		if (index < 0 || index >= Array.length)
			return null; // Return null if index is out of bounds

		// Create a temporary Node to store the element being removed
		Entry<K, V> temp = new Entry<K, V>(Array[index].getKey(), Array[index].getValue());

		// Set the element at the specified index to null to remove it from the array
		Array[index] = null;

		// Decrement the size of the array
		size--;

		// Return the removed element
		return temp;
	}

	/**
	 * Prints the elements of the array.
	 */
	public void printArray() {
		// Check if the array is empty
		if (isEmpty()) {
			System.out.println("[ ]"); // Print an empty array
			return;
		}

		// Iterate through the array
		for (int i = 0; i < Array.length; i++) {
			// Print the first element with an opening bracket
			if (i == 0) {
				System.out.print("[ ");
			}

			// Check if the current element is not null
			if (Array[i] != null) {
				// Print the first element with opening bracket
				if (i == 0) {
					System.out.print(Array[i].print());
				}
				// Print the last element with closing bracket
				else if (i == (Array.length - 1)) {
					System.out.print(" | " + Array[i].print() + " ]");
				}
				// Print intermediate elements with a separator
				else {
					System.out.print(" | " + Array[i].print());
				}
			}else    
			{
				if (i == 0) {
					System.out.print("( , )");
				}
				// Print the last element with closing bracket
				else if (i == (Array.length - 1)) {
					System.out.print(" | " + "( , )" + " ]");
				}
				// Print intermediate elements with a separator
				else {
					System.out.print(" | " + "( , )");
				}
			}
		}
		System.out.print(" ]");
		System.out.println();
	}

}
