package Test;
import static org.junit.Assert.*;

import org.junit.Test;

import Utilities.Node;

public class NodeTest {
	
	@Test
	public void shouldContructUsingValidParameters() {
		Node<Integer> myNode = new Node<>(12, null);
		assertEquals((Integer)12,  myNode.getElement());
		assertEquals(null, myNode.getNext());
		
		myNode = new Node<>(Integer.MAX_VALUE, null);
		assertEquals((Integer)Integer.MAX_VALUE, myNode.getElement());
		assertEquals(null, myNode.getNext());
		
		myNode = new Node<>(Integer.MIN_VALUE, null);
		assertEquals((Integer)Integer.MIN_VALUE, myNode.getElement());
		assertEquals(null, myNode.getNext());
		
		Node<Integer> newNode = new Node<>(0, myNode);
		assertEquals((Integer)0, newNode.getElement());
		assertEquals(myNode, newNode.getNext());
	}
	
	@Test
	public void shouldSetNextCorrectly() {
		Node<Integer> myNode = new Node<>(12, null);
		Node<Integer> newNode = new Node<>(10, null);
		
		myNode.setNext(newNode);
		assertEquals(newNode, myNode.getNext());
	}

}
