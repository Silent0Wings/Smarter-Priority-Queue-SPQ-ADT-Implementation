package SPQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import SPQ.PriorityQueueHeap.HeapType;

/**
 * 
 * Demonstrates various operations on a custom priority queue implementation.
 * This class provides a series of tests to showcase the functionality of the
 * smarter priority queue (SPQ), which includes insertion, removal, toggling
 * between min and max heap, and node operation tests.
 *
 * <p>
 *
 * The tests cover: - Insertion of elements into the SPQ. - Removal of the top
 * element from the SPQ. - Toggling the SPQ between min and max heap
 * configurations. - Replacement of key and value in the SPQ. - Testing node
 * equality operations. - Demonstrating the dynamic array extension capability
 * of the SPQ.
 *
 * <p>
 *
 * The SPQ is designed to be flexible, allowing for dynamic changes between min
 * and max heap states and supporting operations like key/value replacement and
 * arbitrary element removal. This class uses a static array of integer arrays
 * to provide test data for the SPQ.
 */
@SuppressWarnings("unused")
public class SPQ {

	// Static array containing arrays of integers for testing purposes. 2d array
	// containing 21 arrays
	static Integer[][] arrayOfArrays = { { 50, 30, 20, 15, 10, 8, 16 }, { 1, 1, 2, 3, 2, 1, 1 },
			{ 1, 2, 3, 4, 3, 2, 1 }, { 1, 2, 3, 12, 9, 1 }, { 4, 5, 45, 21, 76, 12, 99 }, { 6, 7, 8, 9, 100, 3, 2, 1 },
			{ 10 }, { 11, 12, 13, 14, 15, 01, 9, 2, 1, 4, 6, 7, 8 }, { 16, 17 }, { 18, 19, 20, 21, 22 }, { 23, 24, 25 },
			{ 26, 27, 0, 29, 30, 31, 32 }, { -33, 34 }, { 35, 36, 37, 38 }, { 39, 40, 41, 42, 43, 44, 45 },
			{ 46, 47, 48 }, { 49 }, { 50, 51, 52, 53, 54 }, { 55, 0, -57, 58, 59, 60, 61 }, { 62, 63 }, { -64, 65, 66 },
			{ -95, 96, 97, 98, 99, 100 } };

	/**
	 * Main method to execute the test cases. Iterates through the arrayOfArrays,
	 * performing various SPQ operations and demonstrating their effects. Exceptions
	 * are caught and handled to provide error messages specific to the encountered
	 * issues.
	 * 
	 * @param args Command line arguments (unused).
	 */
	public static void main(String[] args) {
		// Test methods

		// testInsertionAndRandomRemoval(1);
		//
		// fullTest();
		singlefullTest(1);
		// testInsertionAndRandomRemoval(1);
		return;

	}

	public static void singlefullTest(int i) {
		try {

			// Code that may throw IndexOutOfBoundsException
			System.out.println();
			System.out.println("________________________________________________________________________");
			System.out.println("==========================|     i: " + i + "       |====================");

			testInsertion(i);

			testInsertionAndTopRemoval(i);

			testInsertionAndToggling(i);

			testInsertionAndRandomRemoval(i);

			testInsertionAndReplacementValue(i);

			testInsertionAndReplacementKey(i);

			testArrayIncrease(i);
			
			testNodeOperations(i);

			System.out.println();
			System.out.println("________________________________________________________________________");
			System.out.println();

		} catch (IndexOutOfBoundsException e) {
			// Code to handle the exception
			System.err.println("Index is out of range: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("Validation Error: " + e.getMessage());
			// Handle the exception gracefully
		} catch (Exception e) {
			System.err.println("An unexpected error occurred: " + e.getMessage());
		}
	}

	public static void fullTest() {
		try {
			int range = arrayOfArrays.length; // the range of inputs to run from the global variable

			for (int i = 0; i < range; i++) {
				// Code that may throw IndexOutOfBoundsException
				System.out.println();
				System.out.println("________________________________________________________________________");
				System.out.println("==========================|     i: " + i + "       |====================");

				testInsertion(i);

				testInsertionAndTopRemoval(i);

				testInsertionAndToggling(i);

				testInsertionAndRandomRemoval(i);

				testInsertionAndReplacementValue(i);

				testInsertionAndReplacementKey(i);

				testArrayIncrease(i);
				
				testNodeOperations(i);

				System.out.println();
				System.out.println("________________________________________________________________________");
				System.out.println();
			}
		} catch (IndexOutOfBoundsException e) {
			// Code to handle the exception
			System.err.println("Index is out of range: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("Validation Error: " + e.getMessage());
			// Handle the exception gracefully
		} catch (Exception e) {
			System.err.println("An unexpected error occurred: " + e.getMessage());
		}
	}

	/**
	 * Tests the insertion Behavior
	 */
	public static void testInsertion(int datum) {
		System.out.println("------testInsertion");

		// Creating a priority queue instance
		HeapType currentType = HeapType.Max;
		PriorityQueueHeap<Integer, Integer> PriorityQueueHeap = new PriorityQueueHeap<>(currentType);
		System.out.println("--Current Heap is : " + PriorityQueueHeap.state());

		// Test data
		Integer[] testData = arrayOfArrays[datum];
		@SuppressWarnings("unchecked")
		Entry<Integer, Integer>[] insertStored = (Entry<Integer, Integer>[]) new Entry<?, ?>[testData.length];

		System.out.println("--Insertions :");
		// Inserting elements into the priority queue
		for (int i = 0; i < testData.length; i++) {
			System.out.println("--Inserting : " + testData[i] + "," + (Integer) i);

			insertStored[i] = PriorityQueueHeap.insert(testData[i], (Integer) i);

			PriorityQueueHeap.printQueue();

		}

		System.out.println();
		System.out.println("--The values inserted into the heap after they were returned :");

		System.out.print("[ ");

		// Printing the value of the node returned from insert method and stored
		for (int i = 0; i < insertStored.length; i++) {
			if (i == insertStored.length - 1) {
				System.out.print(insertStored[i].print());
			} else {
				System.out.print(insertStored[i].print() + " | ");
			}

		}

		System.out.print("] ");
		System.out.println();
		System.out.println("--Final Heap :");

		// Printing the state of the priority queue
		PriorityQueueHeap.printQueue();

	}

	/**
	 * Tests the insertion, heapification, and removal behaviors of the priority
	 * queue Behaviors.
	 */
	public static void testInsertionAndTopRemoval(int datum) {
		System.out.println("================================================================");

		System.out.println("------testInsertionAndTopRemoval");

		// Creating a priority queue instance
		HeapType currentType = HeapType.Max;
		PriorityQueueHeap<Integer, Integer> PriorityQueueHeap = new PriorityQueueHeap<>(currentType);
		System.out.println("--Current Heap is : " + PriorityQueueHeap.state());

		// Test data
		Integer[] testData = arrayOfArrays[datum];
		Integer[] removedPriorities = new Integer[testData.length];
  
		System.out.println("--Insertions :");
		// Inserting elements into the priority queue
		for (int i = 0; i < testData.length; i++) {
			PriorityQueueHeap.insert(testData[i], (Integer) i);
		}
		System.out.println("--Current Heap :");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// Removing elements from the priority queue
		for (int i = 0; i < testData.length; i++) {
			System.out.println("--Current Heap :");
			System.out.println("-----Element Being Removed : " + PriorityQueueHeap.top().print());
			Entry<Integer, Integer> removedNode = PriorityQueueHeap.removeTop();
			System.out.print("           ");
			PriorityQueueHeap.printQueue();   
			if (removedNode != null) {
				// Printing the removed node

				removedPriorities[i] = removedNode.getKey();
				System.out.print("           -");  

				PriorityQueueHeap.printQueue();
			}
			System.out.println();
		}

		// Printing the state of the priority queue
		PriorityQueueHeap.printQueue();
		System.out.println();

		System.out.println(" ----Element that were Removed and the order they have been removed : ");
		// Printing the priorities of the removed elements
		for (int i = 0; i < testData.length; i++) {
			System.out.print(removedPriorities[i] + " | ");
		}
		System.out.println();

	}

	/**
	 * Tests the insertion and toggling behavior of the priority queue Behaviors.
	 */
	public static void testInsertionAndToggling(int datum) {
		System.out.println("================================================================");
		System.out.println("------testInsertionAndToggling");

		// Creating a priority queue instance
		PriorityQueueHeap<Integer, Integer> PriorityQueueHeap = new PriorityQueueHeap<>(HeapType.Max);
		System.out.println("--Current Heap is : " + PriorityQueueHeap.state());

		// Test data
		Integer[] testData = arrayOfArrays[datum];

		System.out.println("--Insertions :");
		// Inserting elements into the priority queue
		for (int i = 0; i < testData.length; i++) {
			PriorityQueueHeap.insert(testData[i], (Integer) i);
		}

		// Toggling the heap type

		System.out.println("--Switching from : " + PriorityQueueHeap.state() + " to :"
				+ ((PriorityQueueHeap.state() == HeapType.Max.toString()) ? HeapType.Min.toString()
						: HeapType.Max.toString()));
		System.out.println("                ");
		System.out.print("        " + PriorityQueueHeap.state());
		PriorityQueueHeap.printQueue();
		PriorityQueueHeap.toggle();
		System.out.print("        " + PriorityQueueHeap.state());
		PriorityQueueHeap.printQueue();
		System.out.println("                ");

		System.out.println("--Switching from : " + PriorityQueueHeap.state() + " to :"
				+ ((PriorityQueueHeap.state() == HeapType.Max.toString()) ? HeapType.Min.toString()
						: HeapType.Max.toString()));
		System.out.println("                ");
		System.out.print("        " + PriorityQueueHeap.state());
		PriorityQueueHeap.printQueue();
		PriorityQueueHeap.toggle();
		System.out.print("        " + PriorityQueueHeap.state());
		PriorityQueueHeap.printQueue();
		System.out.println("                ");

		System.out.println("--Switching from : " + PriorityQueueHeap.state() + " to :"
				+ ((PriorityQueueHeap.state() == HeapType.Max.toString()) ? HeapType.Min.toString()
						: HeapType.Max.toString()));
		System.out.println("                ");
		System.out.print("        " + PriorityQueueHeap.state());
		PriorityQueueHeap.printQueue();
		PriorityQueueHeap.toggle();
		System.out.print("        " + PriorityQueueHeap.state());
		PriorityQueueHeap.printQueue();
		System.out.println("                ");

		// Printing the size of the priority queue
		System.out.println("Size : " + PriorityQueueHeap.size());
	}

	/*
	 * Tests the insertion and removal behavior of the priority queue Behaviors.
	 */
	public static void testInsertionAndRandomRemoval(int datum) {
		System.out.println("================================================================");
		System.out.println("------testInsertionAndRandomRemoval");

		// Creating a priority queue instance
		PriorityQueueHeap<Integer, Integer> PriorityQueueHeap = new PriorityQueueHeap<>(HeapType.Min);
		System.out.println("--Current Heap is : " + PriorityQueueHeap.state());

		PriorityQueueHeap.printQueue();

		// Test data
		Integer[] testData = arrayOfArrays[datum];
		List<Entry<Integer, Integer>> Removelist = new ArrayList<>();

		System.out.println("--Insertions :");
		// Inserting elements into the priority queue
		for (int i = 0; i < testData.length; i++) {
			Removelist.add(PriorityQueueHeap.insert(testData[i], (Integer) i));
			PriorityQueueHeap.printQueue();

		}

		System.out.println("Removelist :" + Removelist.toString());

		Random random = new Random();
		int numberOfIterations = Removelist.size(); // Define the number of iterations
		for (int i = 0; i < numberOfIterations; i++) {
			if (Removelist == null || Removelist.size() == 0)
				break;
			int randomIndex = random.nextInt(Removelist.size());
			Entry<Integer, Integer> randomValue = Removelist.get(randomIndex);
			Removelist.remove(randomIndex);

			System.out.println("Removing :" + randomValue.getKey());
			PriorityQueueHeap.printQueue();
			PriorityQueueHeap.remove(randomValue);
			System.out.println("After : ");
			PriorityQueueHeap.printQueue();
			System.out.println();
		}

		// Removal
		PriorityQueueHeap.printQueue();
		// Printing the size of the priority queue
		System.out.println("Size : " + PriorityQueueHeap.size());
	}

	/*
	 * Tests the insertion and replacement behavior of the values in queue
	 * Behaviors.
	 */
	public static void testInsertionAndReplacementValue(int datum) {
		System.out.println("================================================================");
		System.out.println("------testInsertionAndReplacementValue");

		// Creating a priority queue instance
		PriorityQueueHeap<Integer, Integer> PriorityQueueHeap = new PriorityQueueHeap<>(HeapType.Max);
		System.out.println("--Current Heap is : " + PriorityQueueHeap.state());

		PriorityQueueHeap.printQueue();

		// Test data
		Integer[] testData = arrayOfArrays[datum];

		System.out.println("--Insertions :");
		// Inserting elements into the priority queue
		for (int i = 0; i < testData.length; i++) {
			PriorityQueueHeap.insert(testData[i], (Integer) i);
		}
		// Removal

		PriorityQueueHeap.printQueue();

		// replacing priority top value with 60
		System.out.println("replacing top value with 200 ");
		PriorityQueueHeap.replaceValue(PriorityQueueHeap.top(), 200);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		PriorityQueueHeap.printQueue();

		// replace the value of 50 with 999
		System.out.println("replacing value of :top with 999 ");
		PriorityQueueHeap.replaceValue(PriorityQueueHeap.top(), (Integer) 999);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// replace the value of 30 with 123
		System.out.println("replacing value of :top with 123 ");
		PriorityQueueHeap.replaceValue(PriorityQueueHeap.top(), (Integer) 123);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// replace the value of 20 with 34
		System.out.println("replacing value of :top with 34 ");
		PriorityQueueHeap.replaceValue(PriorityQueueHeap.top(), (Integer) 34);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// replace the value of 15 with 12
		System.out.println("replacing value of :top with 12 ");
		PriorityQueueHeap.replaceValue(PriorityQueueHeap.top(), (Integer) 12);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// Printing the size of the priority queue
		System.out.println("Size : " + PriorityQueueHeap.size());
	}

	/*
	 * Tests the insertion and replacement Key behavior of the priority queue
	 * Behaviors.
	 */
	public static void testInsertionAndReplacementKey(int datum) {
		System.out.println("================================================================");
		System.out.println("------testInsertionAndReplacementKey");

		// Creating a priority queue instance
		PriorityQueueHeap<Integer, Integer> PriorityQueueHeap = new PriorityQueueHeap<>(HeapType.Max);
		System.out.println("--Current Heap is : " + PriorityQueueHeap.state());

		PriorityQueueHeap.printQueue();

		// Test data
		Integer[] testData = { 50, 30, 20, 15, 10, 8, 16 };

		System.out.println("--Insertions :");
		// Inserting elements into the priority queue
		for (int i = 0; i < testData.length; i++) {
			PriorityQueueHeap.insert(testData[i], (Integer) i);
		}

		PriorityQueueHeap.printQueue();

		// replacing priority top key with 60
		System.out.println("replacing top key with 60 ");
		PriorityQueueHeap.replaceKey(PriorityQueueHeap.top(), 60);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// replacing priority top with 40
		System.out.println("replacing top key with 40 ");
		PriorityQueueHeap.replaceKey(PriorityQueueHeap.top(), 40);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// print
		PriorityQueueHeap.printQueue();

		// replacing priority top with -13
		System.out.println("replacing top key with -13 ");
		PriorityQueueHeap.replaceKey(PriorityQueueHeap.top(), -13);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// replacing priority top with 25
		System.out.println("replacing top key with 25 ");
		PriorityQueueHeap.replaceKey(PriorityQueueHeap.top(), 25);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// replacing priority top with 0
		System.out.println("replacing top key with 0 ");
		PriorityQueueHeap.replaceKey(PriorityQueueHeap.top(), 0);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();
		System.out.println();

		// replacing priority top with -1
		System.out.println("replacing top key with -1 ");
		PriorityQueueHeap.replaceKey(PriorityQueueHeap.top(), -1);
		System.out.println("After : ");
		PriorityQueueHeap.printQueue();

		System.out.println();

		// Printing the size of the priority queue
		System.out.println("Size : " + PriorityQueueHeap.size());
	}

	/*
	 * Tests the insertion and Arry Expanssion Behaviors.
	 */
	public static void testArrayIncrease(int datum) {
		System.out.println("================================================================");
		System.out.println("------testArrayIncrease");

		// Creating a priority queue instance
		PriorityQueueHeap<Integer, Integer> PriorityQueueHeap = new PriorityQueueHeap<>(HeapType.Max);
		System.out.println("--Current Heap is : " + PriorityQueueHeap.state());

		// Test data
		Integer[] testData = { 50, 30, 20, 15, 10, 8, 16, 45, 78, 554, -885, 1, 0, 54, -47, 12, -1 };

		System.out.println("--Insertions :");
		System.out.println("--Size : " + PriorityQueueHeap.size());
		System.out.println("--Capacity : " + PriorityQueueHeap.capacity());
		// Inserting elements into the priority queue
		for (int i = 0; i < testData.length; i++) {
			System.out.println();
			PriorityQueueHeap.insert(testData[i], (Integer) i);
			System.out.println("--Size : " + PriorityQueueHeap.size());
			System.out.println("--Capacity : " + PriorityQueueHeap.capacity());
			PriorityQueueHeap.printQueue();
			System.out.println();
		}
	}

	/**
	 * Tests the node operations.
	 */
	public static void testNodeOperations(int datum) {
		System.out.println("================================================================");
		System.out.println("------testNodeOperations");

		// Creating a priority queue instance
		PriorityQueueHeap<Integer, Integer> PriorityQueueHeap = new PriorityQueueHeap<>();

		// Creating nodes for comparison
		Entry<Integer, Integer> node1 = new Entry<Integer, Integer>(2, 3);
		Entry<Integer, Integer> node2 = new Entry<Integer, Integer>(1, 2);

		// Printing values
		System.out.println("-Values  : ");
		System.out.println("node1  : " + (node1).print());
		System.out.println("node2  : " + (node2).print());

		System.out.println();

		// Comparing nodes
		System.out.println("-Comparing  : ");  
		System.out.println("(node1 == node1) : " + (node1.equals(node1)));
		System.out.println("(node1 == node2) : " + (node1.equals(node2)));
		System.out.println();
		
		System.out.println("-Compare to  : ");
		System.out.println("(node1 == node1) : " + ((node1.compareTo(node1) == 0) ? true : false));
		System.out.println("(node1 == node2) : " + ((node1.compareTo(node2) == 0) ? true : false));
	
		System.out.println();

		System.out.println("-Comparing values: ");  
		System.out.println("(node1.key = node1.key) : " +("("+node1.getKey()+"="+node1.getKey()+ ")") +(node1.getKey() == node1.getKey()));
		System.out.println("(node1.key = node2.key) : " +("("+node1.getKey()+"="+node2.getKey()+ ")") +(node1.getKey() == node2.getKey()));

		System.out.println();

		System.out.println("(node1.key > node1.key) : " +("("+node1.getKey()+">"+node1.getKey()+ ")") +(node1.getKey() > node1.getKey()));
		System.out.println("(node1.key > node2.key) : " +("("+node1.getKey()+">"+node2.getKey()+ ")") +(node1.getKey() > node2.getKey()));

		System.out.println();

		System.out.println("(node1.key < node1.key) : " +("("+node1.getKey()+"<"+node1.getKey()+ ")") +(node1.getKey() < node1.getKey()));
		System.out.println("(node2.key < node1.key) : " +("("+node2.getKey()+"<"+node1.getKey()+ ")") +(node2.getKey() < node1.getKey()));
		
		System.out.println();
	}

}