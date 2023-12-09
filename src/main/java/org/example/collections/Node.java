package org.example.collections;

import org.example.Sweet;

/**
 * The Node class represents a node in a linked list used in various data structures.
 *
 * @param <T> The type of data stored in the node, extending the Sweet class.
 */
public class Node<T extends Sweet> {

    /**
     * The data stored in the node.
     */
    public T data;

    /**
     * Reference to the next node in the linked list.
     */
    public Node<T> next;

    /**
     * Reference to the previous node in the linked list.
     */
    public Node<T> prev;

    /**
     * Constructs a Node with the specified data.
     *
     * @param data The data to be stored in the node.
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}