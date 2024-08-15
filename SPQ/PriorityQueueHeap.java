package SPQ;

/**
 * The {@code Heap} class represents a priority queue data structure, where
 * elements are organized based on their priority. It supports operations for
 * inserting, removing, and inspecting elements. This implementation allows for
 * both min-heap and max-heap configurations, determined by the
 * {@code HeapType}.
 * 
 * <p>
 * This heap is backed by an {@code ExpandingArray}, a custom array-like
 * structure that dynamically resizes to accommodate more elements as needed.
 * This design choice ensures that the heap can grow without predefined limits,
 * aside from memory constraints.
 * 
 * <p>
 * The heap's type (min or max) is set at creation and can influence the
 * behavior of heap operations. In a min-heap, the element with the lowest value
 * has the highest priority, whereas, in a max-heap, the element with the
 * highest value is given precedence.
 * 
 * <p>
 * This class leverages generics, allowing for the storage of any type of
 * objects, provided that a comparison mechanism exists when establishing the
 * heap order.
 * 
 * @param <K,V> the type of elements stored in this heap
 */
@SuppressWarnings({ "unused", ("unchecked") })

public class PriorityQueueHeap<K extends Comparable<K>, V> {

	private ExpandingArray<K, V> expandingArray;
	private static final int DefaultCapacity = 8;
	private int last_added = 0;
	private int size = 0;

	// Set the initial value to Max
	private HeapType currentHeapType = HeapType.Max;

	// This enum represents the type of heap, whether it's a Max heap or a Min heap.
	public static enum HeapType {
		Max, Min
	}

	/**
	 * Constructs a Heap with the specified type.
	 * 
	 * @param type The class type of the elements stored in the heap.
	 */
	public PriorityQueueHeap() {
		this(HeapType.Max, DefaultCapacity); // Method Overloading by calling the main constructor of 3 arguments
	}

	public PriorityQueueHeap(HeapType heapType) { // Method Overloading by calling the main constructor
													// of 3 arguments
		this(heapType, DefaultCapacity);
	}

	public PriorityQueueHeap(int startingSize) { // Method Overloading by calling the main constructor of
													// 3 Arguments
		this(HeapType.Max, startingSize);
	}

	public PriorityQueueHeap(HeapType heapType, int startingSize) { // this is the main class construct
		this.expandingArray = new ExpandingArray<>(startingSize);
		this.currentHeapType = heapType;
	}

	/**
	 * Returns the number of items in the heap.
	 * 
	 * @return The size of the heap.
	 */
	public int size() {
		return expandingArray.size();
	}

	public int length() {
		return expandingArray.size();
	}

	public int capacity() {
		return expandingArray.Capacity();
	}

	/**
	 * Checks whether the heap is empty.
	 * 
	 * @return True if the heap is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return expandingArray.isEmpty();
	}

	/**
	 * Clears all items from the heap.
	 */
	public void clear() {
		expandingArray.clear();
		last_added = 0;
		size = 0;
	}

	/**
	 * Ensures that the internal array has enough capacity to accommodate new
	 * elements.
	 */
	private void ensureCapacity() {
		expandingArray.ensureCapacity();
	}

	/**
	 * Returns the index of the parent node of a node in the heap.
	 * 
	 * @param index The index of the node.
	 * @return The index of the parent node, or -1 if the node has no parent or the
	 *         index is out of bounds.
	 * 
	 *         Time Complexity: O(1) Space Complexity: O(1)
	 */
	private int getParent(int index) {

		if (index < 0 || index > expandingArray.length() - 1) {
			return -1;
		}
		int new_index = (index - 1) / 2;

		if (new_index < 0 || new_index > expandingArray.length() - 1 || expandingArray.get(new_index) == null) {
			return -1;
		} else {
			return new_index;
		}
	}

	/**
	 * Returns the index of the left child of a node in the heap.
	 * 
	 * @param index The index of the node.
	 * @return The index of the left child of the node, or -1 if the node has no
	 *         left child or the index is out of bounds.
	 * 
	 *         Time Complexity: O(1) Space Complexity: O(1)
	 */
	private int getLeftChild(int index) {
		if (index < 0 || index > expandingArray.length() - 1) {
			return -1;
		}
		int new_index = 2 * index + 1;

		if (new_index < 0 || new_index > expandingArray.length() - 1 || expandingArray.get(new_index) == null) {
			return -1;
		} else {
			return new_index;
		}
	}

	/**
	 * Returns the index of the right child of a node in the heap.
	 * 
	 * @param index The index of the node.
	 * @return The index of the right child of the node, or -1 if the node has no
	 *         Time Complexity: O(1) Space Complexity: O(1)
	 */
	private int getRightChild(int index) {
		// Check if index is out of bounds
		if (index < 0 || index > expandingArray.length() - 1) {
			return -1;
		}

		// Calculate the index of the right child
		int new_index = 2 * index + 2;

		// Check if the calculated index is out of bounds or the right child is null
		if (new_index < 0 || new_index >= expandingArray.length() - 1 || expandingArray.get(new_index) == null) {
			return -1;
		} else {
			return new_index;
		}
	}

	/**
	 * Checks if a node in the heap has a empty parent node.
	 * 
	 * @param index The index of the node.
	 * @return True if the node has a parent, otherwise false. Time Complexity: O(1)
	 *         Space Complexity: O(1)
	 */
	private Boolean parentAvailable(int index) {
		if (index < 0 || index > expandingArray.length() - 1) {
			return false;
		}

		int parentIndex = (index - 1) / 2;

		return expandingArray.get(parentIndex) == null;
	}

	/**
	 * Checks if a node in the heap has a empty left child node.
	 * 
	 * @param index The index of the node.
	 * @return True if the node has a left child, otherwise false. Time Complexity:
	 *         O(1) Space Complexity: O(1)
	 */
	private Boolean leftChildAvailable(int index) {
		if (index < 0 || index > expandingArray.length() - 1) {
			return false;
		}

		int leftChildIndex = 2 * index + 1;

		return expandingArray.get(leftChildIndex) == null;
	}

	/**
	 * Checks if a node in the heap has a empty right child node.
	 * 
	 * @param index The index of the node.
	 * @return True if the node has a right child, otherwise false. Time Complexity:
	 *         O(1) Space Complexity: O(1)
	 */
	private Boolean rightChildAvailable(int index) {
		if (index < 0 || index > expandingArray.length() - 1) {
			return false;
		}

		int rightChildIndex = 2 * index + 2;

		return expandingArray.get(rightChildIndex) == null;
	}

	/**
	 * Checks if a node in the heap has a empty right child node.
	 * 
	 * @param index The index of the node.
	 * @return True if the node has a right child, otherwise false. Time Complexity:
	 *         O(1) Space Complexity: O(1)
	 */
	private Boolean parentIsAvailable(int index) {
		return (getRightChild(index) > 0);
	}

	/*
	 * Checks if a node in the heap has a parent.
	 * 
	 * @param index The index of the node.
	 * 
	 * @return True if the node has a parent, otherwise false.
	 * 
	 * Time Complexity: O(1) Space Complexity: O(1)
	 * 
	 */
	private Boolean hasParent(int index) {
		return (this.getParent(index) >= 0);
	}

	/**
	 * Checks if a node in the heap has at least one child node.
	 * 
	 * @param index The index of the node.
	 * @return True if the node has at least one child, otherwise false.
	 * 
	 *         Time Complexity: O(1) Space Complexity: O(1)
	 * 
	 */
	private Boolean hasChild(int index) {
		return (getLeftChild(index) > 0 || getRightChild(index) > 0);
	}

	/**
	 * Checks if a node in the heap has a left child node.
	 * 
	 * @param index The index of the node.
	 * @return True if the node has a left child, otherwise false. Time Complexity:
	 *         O(1) Space Complexity: O(1)
	 */
	private Boolean hasLeftChild(int index) {
		return (getLeftChild(index) > 0);
	}

	/**
	 * Checks if a node in the heap has a right child node.
	 * 
	 * @param index The index of the node.
	 * @return True if the node has a right child, otherwise false. Time Complexity:
	 *         O(1) Space Complexity: O(1)
	 */
	private Boolean hasRightChild(int index) {
		return (getRightChild(index) > 0);
	}

	/**
	 * Performs linear search to find the index of the node with the specified
	 * priority.
	 * 
	 * @param target The priority to search for.
	 * @return The index of the node with the specified priority, or -1 if not
	 *         found.
	 * 
	 *         The method iterates through the expanding array and compares the
	 *         priority of each node with the target priority. If a node with the
	 *         target priority is found, its index is returned. If no node with the
	 *         target priority is found, -1 is returned.
	 * 
	 *         Time Complexity: O(n), where n is the number of nodes in the
	 *         expanding array. - In the worst case, the method iterates through all
	 *         elements in the array to find the target. Space Complexity: O(1)
	 */
	private int linearSearch(K target) {

		for (int i = 0; i < expandingArray.Capacity(); i++) {

			if (expandingArray.get(i) != null && expandingArray.get(i).getKey() == target) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Performs linear search to find the index of the node with the specified
	 * Node<K,V>.
	 * 
	 * @param target The priority to search for.
	 * @return The index of the node with the specified priority, or -1 if not
	 *         found.
	 * 
	 *         The method iterates through the expanding array and compares the
	 *         priority of each node with the target priority. If a node with the
	 *         target priority is found, its index is returned. If no node with the
	 *         target priority is found, -1 is returned.
	 * 
	 *         Time Complexity: O(n), where n is the number of nodes in the
	 *         expanding array. - In the worst case, the method iterates through all
	 *         elements in the array to find the target. Space Complexity: O(1)
	 */
	private int linearSearch(Entry<K, V> target) {

		if (target == null)
			return -1;
		for (int i = 0; i < expandingArray.Capacity(); i++) {

			if (expandingArray.get(i) != null)
				if (expandingArray.get(i).equals(target)) {
					return i;
				}
		}
		return -1;
	}

	/**
	 * Returns the current state (Min or Max) of the priority queue.
	 * 
	 * @return The current state of the priority queue as a string representation
	 *         (Min or Max).
	 * 
	 *         Time Complexity: O(1) Space Complexity: O(1)
	 */
	public String state() {
		return currentHeapType.toString();
	}

	/**
	 * Transforms the priority queue from a min-heap to a max-heap, or vice versa.
	 * 
	 * Time Complexity: O(n log n), where n is the number of nodes in the heap. -
	 * The method performs n removeTop operations, each with a time complexity of
	 * O(log n). Space Complexity: O(n), where n is the number of nodes in the heap.
	 * - Additional space is required for the temporary array used to store the
	 * removed elements.
	 */
	public void toggle() {

		if (currentHeapType == HeapType.Max) {
			currentHeapType = HeapType.Min;
		} else {
			currentHeapType = HeapType.Max;
		}
		heapSortAndInsert();
	}

	/**
	 * Performs heap sort on the priority queue.
	 * 
	 * This method first removes all elements from the heap using removeTop method,
	 * which has a time complexity of O(log n) per removal operation, where n is the
	 * number of nodes in the heap. Then, it re-inserts each element into the heap
	 * using insert method, which has a time complexity of O(log n) per insertion
	 * operation. The overall time complexity of the heapSort method is O(n log n),
	 * where n is the number of nodes in the heap. Space complexity: O(n).
	 */
	private void heapSortAndInsert() {

		Entry<K, V>[] ff = (Entry<K, V>[]) new Entry[expandingArray.length()];
		for (int i = 0; i < size; i++) {
			ff[i] = this.removeTop();// Remove each element from the heap
		}
		for (int i = 0; i < ff.length; i++) {
			if (ff[i] != null)
				insert(ff[i].getKey(), ff[i].getValue());// Re-insert each element into the heap
		}

	}

	/**
	 * Removes and returns the entry object with the smallest or biggest key,
	 * depending on the current state of the priority queue.
	 * 
	 * @return The node representing the removed entry, or null if the priority
	 *         queue is empty.
	 * 
	 *         Time Complexity: O(log n), where n is the number of nodes in the
	 *         heap. - The method may perform top-down heapification, which has a
	 *         time complexity of O(log n). Space Complexity: O(1)
	 */
	public Entry<K, V> removeTop() {
		// expandingArray.printArray();
		if (!isEmpty() && last_added >= 0) {
			size--;
			if (size == 0) { // if it is the last element we set it to null and return it
				Entry<K, V> temp = new Entry<K, V>(expandingArray.get(size).getKey(),
						expandingArray.get(size).getValue());
				expandingArray.remove(size);

				last_added = 0;
				return temp;
			} else {
				// this code will go to the parent if parent has no children
				Entry<K, V> temp = null;
				temp = new Entry<K, V>(expandingArray.get(last_added).getKey(),
						expandingArray.get(last_added).getValue());
				Entry<K, V> returned = new Entry<K, V>(expandingArray.get(0).getKey(),
						expandingArray.get(0).getValue());

				expandingArray.swapIndex(0, last_added);
				expandingArray.remove(last_added);
				--last_added;

				downHeap(0);// since the removal happens at the top of the tree we heapify
							// starting from the top

				return returned;

			}
		} else {
			return null;
		}
	}

	/**
	 * Removes the entry object with the specified priority from the priority queue
	 * and returns it.
	 * 
	 * @param e The priority of the entry to remove.
	 * @return The removed entry object, or null if the entry with the specified
	 *         priority is not found.
	 * 
	 *         The method removes the entry with the specified priority from the
	 *         priority queue and returns it. It first performs a linear search to
	 *         find the index of the entry with the given priority, which has a time
	 *         complexity of O(n), where n is the number of nodes in the expanding
	 *         array. Then, it performs heap operations including bottom-up
	 *         heapification and top-down heapification, each with a time complexity
	 *         of O(log n), where n is the number of nodes in the heap. Therefore,
	 *         the overall time complexity of the remove method is O(n + log n),
	 *         which simplifies to O(n) since log n dominates for large values of n.
	 */
	public Entry<K, V> remove(K e) {

		if (e == expandingArray.get(0).getKey()) {
			return removeTop();
		}
		int indexfound = linearSearch(e);
		if (indexfound >= 0 && expandingArray.get(indexfound) != null) {

			if (indexfound == last_added) {
				Entry<K, V> test = expandingArray.get(indexfound);
				expandingArray.remove(last_added);

				if (expandingArray.get(last_added - 1) != null) {
					--last_added;
				} else {
					if (this.hasParent(last_added)) {
						last_added = this.getParent(last_added);
					}
				}

				return test;

			}

			if (expandingArray.get(last_added) != null) {

				Entry<K, V> test = expandingArray.get(indexfound);

				expandingArray.swapIndex(indexfound, last_added);
				expandingArray.remove(last_added);

				if (expandingArray.get(last_added - 1) != null) {
					--last_added;
				} else {
					if (this.hasParent(last_added)) {
						last_added = this.getParent(last_added);
					}
				}

				// bottomUpheapify(indexfound);

				downHeap(upHeap(downHeap(indexfound)));

				return test;
			}

		}
		return null;

	}

	/**
	 * Removes the specified entry object from the priority queue and returns it.
	 * 
	 * @param e The entry object to remove.
	 * @return The removed entry object, or null if the specified entry is not
	 *         found.
	 * 
	 *         This method removes the specified entry object from the priority
	 *         queue and returns it. It first performs a linear search to find the
	 *         index of the entry in the expanding array, which has a time
	 *         complexity of O(n), where n is the number of nodes in the expanding
	 *         array. Then, it performs heap operations including bottom-up
	 *         heapification and top-down heapification, each with a time complexity
	 *         of O(log n), where n is the number of nodes in the heap. Therefore,
	 *         the overall time complexity of the remove method is O(n + log n),
	 *         which simplifies to O(n) since log n dominates for large values of n.
	 */
	public Entry<K, V> remove(Entry<K, V> e) {

		if (e == null) { // if the target is null
			return null;
		} else { // if it isnt
			if (e.compareTo(expandingArray.get(0)) == 0) { // if the element being removed is the top element
				return removeTop();
			} else // if this is not the top element then :
			{
				int indexfound = linearSearch(e);
				if (indexfound >= 0 && expandingArray.get(indexfound) != null) { // if an element is found

					if (indexfound == last_added) { // in case the element being removed is the last element in this
													// heap
						Entry<K, V> test = expandingArray.get(indexfound);
						expandingArray.remove(last_added);

						if (expandingArray.get(last_added - 1) != null) {
							--last_added;
						} else {
							if (this.hasParent(last_added)) {
								last_added = this.getParent(last_added);
							}
						}
						size--;
						return test;
					} else { // if this is not the last element being removed

						if (expandingArray.get(last_added) != null) {
							Entry<K, V> test = expandingArray.get(indexfound);

							expandingArray.swapIndex(indexfound, last_added);
							expandingArray.remove(last_added);

							if (expandingArray.get(last_added - 1) != null) {
								--last_added;
							} else {
								if (this.hasParent(last_added)) {
									last_added = this.getParent(last_added);
								}
							}
							// and for the other item that just became the last element added
							downHeap(indexfound); // O(log(n))

							size--;
							return test;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Inserts a key-value pair into the priority queue, creating a new entry with
	 * the specified priority and value. The method places the entry in the
	 * appropriate position within the heap based on its priority, ensuring the
	 * heap's structural and ordering properties are maintained. After insertion, a
	 * bottom-up heapification process may occur to adjust the heap, preserving the
	 * min-heap or max-heap configuration.
	 * 
	 * @param priority The priority of the entry, determining its position within
	 *                 the heap.
	 * @param value    The value associated with the priority, to be stored in the
	 *                 heap.
	 * @return A Node object representing the inserted entry, allowing direct access
	 *         to the key and value.
	 * 
	 *         Time Complexity: O(log n), where n is the number of nodes in the
	 *         heap. This reflects the potential need for bottom-up heapification
	 *         after insertion. Space Complexity: O(1), as insertion adjusts the
	 *         existing structure without allocating additional significant space.
	 */

	public Entry<K, V> insert(K priority, V value) {
		// since this is a array based representation of the priority queue the size
		// variable will hold the last parent known and last_added will hold the last
		// know child node added and also in the array based implementation each new
		// entry is added at the end of the array making the knowledge of where to
		// insert very simple since insert is going to happen at size -1 aka last empty
		// index of the array
		ensureCapacity(); // Ensure capacity of the internal array

		if (expandingArray.get(size) == null) { // If the array is empty
			expandingArray.set(size, new Entry<K, V>(priority, value)); // Create a new node at the last index
			last_added = size; // Update the index of the last added node
		} else { // If the array is not empty
			int current = -1; // Initialize variable to track current index

			// Determine the index of the next available position
			if (leftChildAvailable(size)) {
				current = getLeftChild(size);
			} else if (rightChildAvailable(size)) {
				current = getRightChild(size);
			}

			// If a valid index is found, insert the node
			if (current >= 0) {
				expandingArray.set(current, new Entry<K, V>(priority, value)); // Create a new node at the last
																				// index

				last_added = current; // Update the index of the last added node
			}
		}

		size++; // Increment the size of the heap

		// Perform bottom-up heapification if necessary
		if (size > 1)
			upHeap(last_added);

		return expandingArray.get(size - 1); // Return the inserted node
	}

	/**
	 * Returns the top entry (with the minimum or maximum key) without removing it.
	 * 
	 * @return The top entry (with the minimum or maximum key), or null if the heap
	 *         is empty.
	 * 
	 *         Time Complexity: O(1) Space Complexity: O(1)
	 */
	public Entry<K, V> top() {
		return expandingArray.get(0);
	}

	/**
	 * Updates the key for a specific entry within the priority queue, identified by
	 * its current node, with a new key value. This method returns the old key of
	 * the entry, allowing for a change in the entry's priority within the queue.
	 * 
	 * @param e      The node representing the entry whose key is to be updated.
	 * @param newKey The new key to replace the existing key of the entry.
	 * @return The old key of the specified entry if found; null if the entry does
	 *         not exist in the queue.
	 * 
	 *         The method performs a linear search to locate the entry by its node,
	 *         which has a computational complexity of O(n), where n is the total
	 *         number of entries in the queue. Upon finding the entry, the key is
	 *         updated, and the heap is restructured to maintain the heap invariant
	 *         through necessary heapification processes. This reheapification has a
	 *         time complexity of O(log n), making the total time complexity O(n +
	 *         log n), which is simplified to O(n) for large n.
	 */
	public K replaceKey(Entry<K, V> e, K newKey) {

		int indexfound = linearSearch(e); // O(n)
		if (indexfound >= 0) {
			if (expandingArray.get(indexfound) != null) {
				K old = expandingArray.get(indexfound).getKey();
				expandingArray.get(indexfound).setKey(newKey);

				downHeap(upHeap(downHeap(indexfound)));
				upHeap(last_added);
				return old;
			}
		}
		// O(n+log(n))=O(n)
		return null;
	}

	/**
	 * Updates the value of a specific entry within the priority queue and returns
	 * the entry's previous value. This operation identifies the entry by its
	 * associated node and replaces the entry's current value with a new one
	 * provided.
	 * 
	 * @param e     The node of the entry whose value is to be updated. The node
	 *              contains the key used for identifying the entry.
	 * @param value The new value to assign to the entry.
	 * @return The previous value associated with the entry, or null if no entry
	 *         matching the specified node is found.
	 * 
	 *         The process involves searching the heap for the entry corresponding
	 *         to the given node, which incurs a linear traversal, making the time
	 *         complexity O(n). If the entry is found, its value is updated, and the
	 *         old value is returned to the caller.
	 */
	public V replaceValue(Entry<K, V> e, V value) {
		int indexfound = linearSearch(e); // O(n)
		if (indexfound >= 0) {
			if (expandingArray.get(indexfound) != null) {
				V old = expandingArray.get(indexfound).getValue();
				expandingArray.get(indexfound).setValue(value);
				return old;
			}
		}
		return null;
	}

	/**
	 * Performs bottom-up heapification to ensure that values bubble up and switch
	 * places according to the type of heap (min or max).
	 * 
	 * The method starts from the last added element and moves up the heap, swapping
	 * elements with their parent until the heap property is satisfied. If the heap
	 * is a max heap, it swaps with the parent if the parent has a lower priority.
	 * If it's a min heap, it swaps with the parent if the parent has a higher
	 * priority. The process continues until the node satisfies the heap property or
	 * reaches the root node.
	 * 
	 * Time Complexity: O(log n), where n is the number of nodes in the heap. - Each
	 * iteration of the while loop traverses up the height of the heap, which has a
	 * maximum depth of log n. Space Complexity: O(1)
	 */
	private int upHeap(int startIndex) {
		// unlike top down there is no checking children we simply check out parent and
		// if the min or heap comparison enforced is satisfied we simply switch and
		// repeat the process by starting at the newly switched to position where we
		// keep repeating until we reach the top node aka root
		int temp = startIndex; // Start from the index of the last added node

		while (temp > 0) { // Continue until reaching the root node

			if (temp == startIndex)
				System.out.println("------Up Heap : ");

			int parent = getParent(temp); // Get the index of the parent node
			if (parent >= 0) { // Check if the parent index is valid

				if (expandingArray.get(parent) != null && expandingArray.get(temp) != null) { // Ensure parent and
					System.out.print("        ");
					printQueue(); // current nodes are not
					// null
					if (currentHeapType == HeapType.Max) { // If the heap is a max-heap
						if (expandingArray.get(parent).getKey().compareTo(expandingArray.get(temp).getKey()) < 0) {
							// If parent's priority is less than current node's priority
							expandingArray.swapIndex(parent, temp); // Swap parent and current nodes
							temp = parent; // Move to the parent node
							System.out.print("        ");
							printQueue();
							continue; // Continue heapifying
						}
					} else if (currentHeapType == HeapType.Min) { // If the heap is a min-heap
						if (expandingArray.get(parent).getKey().compareTo(expandingArray.get(temp).getKey()) > 0) {// If
							// If parent's priority is greater than current node's priority
							expandingArray.swapIndex(parent, temp); // Swap parent and current nodes
							temp = parent; // Move to the parent node
							System.out.print("        ");
							printQueue();
							continue; // Continue heapifying
						}
					}
				}
			} else { // If parent index is invalid (reached the root node)
				break; // Exit the loop
			}
			temp = parent; // Move to the parent node
		}
		return temp;
	}

	/**
	 * Ensures the heap maintains its structure from a specified index downwards,
	 * adjusting nodes to adhere to the heap's configuration (max-heap or min-heap).
	 * This method compares the node at the given index with its children, swapping
	 * them if necessary to maintain the correct order. The process is iterative,
	 * moving down the heap to ensure each parent is properly ordered relative to
	 * its children according to the heap type.
	 * 
	 * @param index The starting point for the heapification process.
	 * @return The final position of the node that was heapified, ensuring the heap
	 *         integrity is preserved.
	 * 
	 *         In a max-heap, a parent node is swapped with its greatest child until
	 *         no child is greater, or it has no children. Conversely, in a
	 *         min-heap, swapping occurs until no child is lesser, maintaining the
	 *         heap property throughout the structure. The method efficiently
	 *         re-establishes the heap order after insertions or removals,
	 *         maintaining optimal heap condition.
	 */
	private int downHeap(int endIndex) {
		// in this heapify methode we check either the smallest or bigest based on the
		// type of the heap min or max whoever satisfy the condition is compared with
		// the parent then switch we then start from that position and repeat the
		// process until we reach the last known node aka the bottom of the tree
		int currentEntry = endIndex; // Start from the root node
		while (currentEntry >= endIndex && currentEntry <= last_added) { // Continue until reaching the last added node

			if (currentEntry == endIndex)
				System.out.println("------Down Heap : ");

			int child = -1; // Initialize child index

			// Determine which child has higher/lower priority based on the type of heap
			// by checking first if the node has 2 children in that case wec compare their
			// priority
			// if they have only one we only process that one remember in this phase the
			// priority check is performed between the 2 node children and not the parent
			// since in maz heap we swithc with the bigest of the children in min heap with
			// the smallest of them
			// if they dont have any we break
			if (hasLeftChild(currentEntry) && hasRightChild(currentEntry)) {
				if (currentHeapType == HeapType.Max) {
					child = (expandingArray.get(getLeftChild(currentEntry)).getKey()
							.compareTo(expandingArray.get(getRightChild(currentEntry)).getKey()) > 0)
									? getLeftChild(currentEntry)
									: getRightChild(currentEntry);
				} else if (currentHeapType == HeapType.Min) {
					child = (expandingArray.get(getLeftChild(currentEntry)).getKey()
							.compareTo(expandingArray.get(getRightChild(currentEntry)).getKey()) < 0)
									? getLeftChild(currentEntry)
									: getRightChild(currentEntry);
				}

			} else if (!hasLeftChild(currentEntry) && hasRightChild(currentEntry)) {
				child = getRightChild(currentEntry);
			} else if (hasLeftChild(currentEntry) && !hasRightChild(currentEntry)) {
				child = getLeftChild(currentEntry);
			} else if (!hasLeftChild(currentEntry) && !hasRightChild(currentEntry)) {
				break; // If no children exist, break the loop
			}
			// it is in this phase where we actualy compared the chosen child in the
			// previous phase whith the parent and porform the switch
			if (child >= 0) { // Check if the child index is valid
				if (expandingArray.get(child) != null && expandingArray.get(currentEntry) != null) {

					System.out.print("        ");
					printQueue();
					// Ensure child and current nodes are not null Swap nodes if necessary based on
					// the type of heap
					if (currentHeapType == HeapType.Max) {
						if (expandingArray.get(child).getKey()
								.compareTo(expandingArray.get(currentEntry).getKey()) > 0) {
							expandingArray.swapIndex(child, currentEntry);
							currentEntry = child;

							System.out.print("        ");
							printQueue();
							continue; // Continue heapifying
						} else {
							break; // Break the loop if the heap property is satisfied
						}
					} else if (currentHeapType == HeapType.Min) {
						if (expandingArray.get(child).getKey()
								.compareTo(expandingArray.get(currentEntry).getKey()) < 0) {
							expandingArray.swapIndex(child, currentEntry);
							currentEntry = child;

							System.out.print("        ");
							printQueue();
							continue; // Continue heapifying
						} else {
							break; // Break the loop if the heap property is satisfied
						}
					}
				}
			}
			currentEntry = child; // Move to the parent node
		}
		return currentEntry;
	}

	/**
	 * Prints the Queue to the console (for demonstration purposes). Time
	 * Complexity: O(n), iterates through each element of the Queue. Space
	 * Complexity: O(1), uses constant extra space regardless of Queue
	 */
	public void printQueue() {
		expandingArray.printArray();
	}
}