package com.nm.toyRobot.utilities;

/*
 * Circular Linked List is used for direction of robot 
 * because of the rotateable of robot
 */
public class CircularlyLinkedList<E> {
	private Node<E> tail = null;		// last node of the list
	private int size = 0;				// number of nodes in the list
	public CircularlyLinkedList() {}	// constructs an initially empty list
	
	/*
	 * Constructor Singly Linked List with array param
	 */
	public CircularlyLinkedList (E[] array) {
		for (int i = 0; i < array.length; i++) {
			this.addLast(array[i]);
		}
	}
	
	// access methods
	public int size() { return this.size; }
	public boolean isEmpty() { return this.size == 0; }
	public E first() {
		if (isEmpty()) return null;
		return tail.getNext().getElement();
	}
	public E last() {
		if (isEmpty()) return null;
		return tail.getElement();
	}
	
	// update methods
	/*
	 * add element to the front of the list
	 */
	public void addFirst(E element) {
		if (isEmpty()) {
			tail = new Node<>(element, null);
			tail.setNext(tail);
		}
		else {
			Node<E> newNode = new Node<>(element, tail.getNext());
			tail.setNext(newNode);
		}
		size ++;
	}
	/*
	 * add element to the end of the list
	 */
	public void addLast(E element) {
		addFirst(element);
		tail = tail.getNext();
	}
	/*
	 * remove and return the first element
	 * @return null if the list is empty
	 */
	public E removeFirst() {
		if (isEmpty()) return null;
		
		Node<E> head = tail.getNext();
		if (head == tail)
			tail = null;
		else
			tail.setNext(head.getNext());
		size --;
		return head.getElement();
	}
	
	/*
	 * convert Singly Linked List to array
	 */
	@SuppressWarnings("unchecked")
	public E[] toArray() {
		E[] array = (E[])new Object[this.size];
		if (isEmpty())
			return array;
		
		Node<E> node = tail.getNext();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = node.getElement();
			node = node.getNext();
		}
		
		return array;
	}
	
	/*
	 * positive modulo
	 */
	private static int positiveModulos(int a, int b) {
		if (b == 0)
			throw new ArithmeticException();
		
		if (b < 0) {
			a = -a;
			b = -b;
		}
		return (((a % b) + b) % b);
	}
	
	public CircularlyLinkedList<E> rotate(int numOfStep) {
		if (isEmpty())
			return this;
		
		numOfStep = positiveModulos(numOfStep, this.size());
		if (this.tail != null) {
			for (int i = 0; i < numOfStep; i++)
				this.tail = this.tail.getNext();
		}
		return this;
	}
}

