package main.java;

public class Node<E> {
	private E element;	// reference to the element stored at this node
	private Node<E> next;		// reference to the subsequent node in the list
	
	public Node(E element, Node<E> next) {
		this.element = element;
		this.next = next;
	}
	
	// access and update methods
	public E getElement() { return element; }
	public Node<E> getNext() { return next; }
	public void setNext(Node<E> next) { this.next = next; }
}
