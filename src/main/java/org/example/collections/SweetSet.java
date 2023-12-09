package org.example.collections;

import org.example.Sweet;

import java.util.*;

/**
 * The SweetSet class represents a generic set data structure for storing objects of type T, which extends the Sweet class.
 *
 * @param <T> The type of elements in the set, extending the Sweet class.
 */
public class SweetSet<T extends Sweet> implements Set<T> {

    /**
     * Represents a node in the linked list structure of the set.
     *
     * @param <T> The type of data stored in the node, extending the Sweet class.
     */
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    /**
     * Constructs an empty SweetSet.
     */
    public SweetSet() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Constructs a SweetSet with a single element.
     *
     * @param sweet The initial element to be added to the set.
     */
    public SweetSet(T sweet) {
        add(sweet);
    }

    /**
     * Constructs a SweetSet with elements from the given collection.
     *
     * @param sweets The collection of elements to be added to the set.
     */
    public SweetSet(Collection<T> sweets) {
        addAll(sweets);
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return The number of elements in the set.
     */
    @Override
    public int size() {
        int size = 0;
        Node<T> current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    /**
     * Checks if the set is empty.
     *
     * @return true if the set is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * Checks if the set contains the specified element.
     *
     * @param o The element to be checked for containment in the set.
     * @return true if the set contains the specified element, false otherwise.
     * @throws NullPointerException if the specified element is null.
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot search for null in the set");
        }
        if (isEmpty()) {
            return false;
        }
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in the set.
     *
     * @return an iterator over the elements in the set.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            /**
             * Returns true if the iteration has more elements.
             *
             * @return true if the iteration has more elements, false otherwise.
             */
            @Override
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration.
             * @throws NoSuchElementException if there are no more elements in the set.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the set");
                }

                T value = current.data;
                current = current.next;
                return value;
            }

            /**
             * Removes the current element from the set (not supported).
             */
            @Override
            public void remove() {
                SweetSet.this.remove(next());
            }
        };
    }

    /**
     * Returns an array containing all of the elements in the set.
     *
     * @return an array containing all of the elements in the set.
     */
    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }
        Object[] array = new Object[size()];
        Node<T> current = head;
        int i = 0;
        while (current != null) {
            array[i] = current.data;
            current = current.next;
            i++;
        }
        return array;
    }

    /**
     * Returns an array containing all of the elements in the set; the runtime type of the returned array is that of the specified array.
     *
     * @param a The array into which the elements of the set are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @param <T1> The runtime type of the array to contain the collection.
     * @return an array containing all of the elements in the set.
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (isEmpty()) {
            return a;
        }
        if (a.length < size()) {
            a = Arrays.copyOf(a, size());
        }
        Node<T> current = head;
        int i = 0;
        while (current != null) {
            a[i] = (T1) current.data;
            current = current.next;
            i++;
        }
        return a;
    }

    /**
     * Adds the specified element to the set.
     *
     * @param t The element to be added to the set.
     * @return true if the set did not already contain the specified element, false otherwise.
     * @throws NullPointerException if the specified element is null.
     */
    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new NullPointerException("Cannot add null to the set");
        }
        if (contains(t)) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        return true;
    }

    /**
     * Removes the specified element from the set.
     *
     * @param o The element to be removed from the set.
     * @return true if the set contained the specified element, false otherwise.
     */
    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        if (!contains(o)) {
            return false;
        }
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                if (size() == 1) {
                    head = null;
                    tail = null;
                } else if (current.equals(tail)) {
                    tail.prev.next = null;
                    tail = tail.prev;
                } else if (current.equals(head)) {
                    head.next.prev = null;
                    head = head.next;
                } else {
                    Node<T> previous = current.prev;
                    Node<T> nextNode = current.next;
                    previous.next = nextNode;
                    nextNode.prev = previous;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if the set contains all of the elements in the specified collection.
     *
     * @param c The collection to be checked for containment in the set.
     * @return true if the set contains all of the elements in the specified collection, false otherwise.
     * @throws NullPointerException if the specified collection is null.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return false;
        }
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to the set.
     *
     * @param c The collection whose elements are to be added to the set.
     * @return true if the set changed as a result of the call, false otherwise.
     * @throws NullPointerException if the specified collection is null.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) {
            return true;
        }
        for (T t : c) {
            add(t);
        }
        return true;
    }

    /**
     * Retains only the elements in the set that are contained in the specified collection.
     *
     * @param c The collection containing elements to be retained in the set.
     * @return true if the set changed as a result of the call, false otherwise.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (isEmpty() || c.isEmpty()) {
            clear();
            return true;
        }
        SweetSet<T> retainedSet = new SweetSet<>();
        for (T element : this) {
            if (c.contains(element)) {
                retainedSet.add(element);
            }
        }
        if (retainedSet.size() <= size()) {
            clear();
            addAll(retainedSet);
            return true;
        }
        return false;
    }

    /**
     * Removes from the set all of its elements that are contained in the specified collection.
     *
     * @param c The collection containing elements to be removed from the set.
     * @return true if the set changed as a result of the call, false otherwise.
     * @throws NullPointerException if the specified collection is null.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return false;
        }
        boolean modified = false;
        for (Object o : c) {
            if (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all elements from the set.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * Compares the specified object with this set for equality.
     *
     * @param o The object to be compared for equality with this set.
     * @return true if the specified object is equal to this set, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof SweetSet)) {
            return false;
        }
        SweetSet<T> other = (SweetSet<T>) o;
        if (size() != other.size()) {
            return false;
        }
        Node<T> current = head;
        while (current != null) {
            if (!other.contains(current.data)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    /**
     * Returns the hash code value for this set.
     *
     * @return the hash code value for this set.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        Node<T> current = head;
        while (current != null) {
            hash += current.data.hashCode();
            current = current.next;
        }
        return hash;
    }
}
