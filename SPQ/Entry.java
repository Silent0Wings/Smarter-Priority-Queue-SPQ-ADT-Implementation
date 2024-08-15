package SPQ;

/**
 * Represents a Entry in a priority queue, where each Entry has a key and a
 * value. The key is used for comparing Entrys to maintain the queue's order.
 *
 * @param <K> The type of the key, which must be Comparable to allow Entry
 *            comparison.
 * @param <V> The type of the value stored in the Entry.
 * 
 *            this : Entry<K extends Comparable<K>, V> implements
 *            Comparable<Entry<K, V>> ensures that Entry can be compared to
 *            another and that K and V can be compared to other values
 */

public class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {
	private K key; // The key of the Entry, used for comparisons.
	private V value; // The value associated with the key.
	private int current_index;
	
	public int getIndex() {
		return current_index;
	}

	public void setIndex(int current_index) {
		this.current_index = current_index;
	}

	
	/**
	 * Constructs a new Entry with the specified key and value.
	 *
	 * @param newK The key for the new Entry.
	 * @param newV The value for the new Entry.
	 */
	public Entry(K newK, V newV) {
		this.value = newV;
		this.key = newK;
	}

	/**
	 * Returns the key of this Entry.
	 *
	 * @return The key of this Entry.
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Returns the value of this Entry.
	 *
	 * @return The value of this Entry.
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Updates the key of this Entry.
	 *
	 * @param newK The new key to be set.
	 */
	public void setKey(K newK) {
		key = newK;
	}

	/**
	 * Updates the value of this Entry.
	 *
	 * @param newV The new value to be set.
	 */
	public void setValue(V newV) {
		value = newV;
	}

	/**
	 * Compares this Entry with another Entry for equality based on their keys and
	 * values. Two Entrys are considered equal if they have the same key and value.
	 *
	 * @param obj The object to be compared for equality with this Entry.
	 * @return true if the specified object is equal to this Entry; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		try {
			Entry<?, ?> other = (Entry<?, ?>) obj;
			return key.equals(other.key) && (value == null ? other.value == null : value.equals(other.value));
		} catch (ClassCastException e) {
			return false; // The object cannot be cast to Entry, indicating it's not equal.
		}
	}

	/**
	 * Generates and returns a string representation of this Entry. The string is
	 * formed by the Entry's key and value.
	 *
	 * @return A string representation of the Entry in the format "(key,value)".
	 */
	public String print() {
		return "(" + key.toString() + ',' + value.toString() + ")";
	}

	/**
	 * Compares this Entry with another Entry to determine the ordering. The
	 * comparison is based solely on the key.
	 *
	 * @param other The other Entry to be compared against.
	 * @return A negative integer, zero, or a positive integer as this Entry's key
	 *         is less than, equal to, or greater than the specified Entry's key.
	 */
	@Override
	public int compareTo(Entry<K, V> other) {
		return this.key.compareTo(other.getKey());
	}

}