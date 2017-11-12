package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Utilities.CircularlyLinkedList;

public class CircularlyLinkedListTest {

	@Test
	public void shouldConstuctUsingArray() {
		Integer[] array = new Integer[]{1, 2, 3};
		CircularlyLinkedList<Integer> myList = new CircularlyLinkedList<>(array);
		assertArrayEquals(array, myList.toArray());
		
		array = new Integer[3];
		myList = new CircularlyLinkedList<>(array);
		assertArrayEquals(array, myList.toArray());		
	}
	
	@Test
	public void shouldReturnSizeCorrectly() {
		CircularlyLinkedList<Integer> myList = new CircularlyLinkedList<>();
		assertEquals(0,  myList.size());
		assertTrue(myList.isEmpty());
		
		Integer[] array = new Integer[]{1, 2, 3};
		myList = new CircularlyLinkedList<>(array);
		assertEquals(array.length, myList.size());
	}
	
	@Test
	public void shouldAddFirstCorrectly() {
		CircularlyLinkedList<Integer> myList = new CircularlyLinkedList<>();
		myList.addFirst(12);
		assertEquals((Integer)12, myList.first());
		assertEquals((Integer)12, myList.last());
		
		myList.addFirst(10);
		assertEquals((Integer)10, myList.first());
		assertEquals((Integer)12, myList.last());
	}
	
	@Test
	public void shouldAddLastCorrectly() {
		CircularlyLinkedList<Integer> myList = new CircularlyLinkedList<>();
		myList.addLast(12);
		assertEquals((Integer)12, myList.first());
		assertEquals((Integer)12, myList.last());
		
		myList.addLast(10);
		assertEquals((Integer)12, myList.first());
		assertEquals((Integer)10, myList.last());
	}
	
	@Test
	public void shouldRemoveFirstCorrectly () {
		Integer[] array = new Integer[]{2, 3, 4};
		CircularlyLinkedList<Integer> sList = new CircularlyLinkedList<>(array);
		
		array = new Integer[]{3, 4};
		assertEquals((Integer)2, sList.removeFirst());
		assertArrayEquals(array, sList.toArray());
		
		array = new Integer[]{4};
		assertEquals((Integer)3, sList.removeFirst());
		assertArrayEquals(array, sList.toArray());
		
		array = new Integer[]{};
		assertEquals((Integer)4, sList.removeFirst());
		assertArrayEquals(array, sList.toArray());
	
		assertEquals(null, sList.removeFirst());
	}

	@Test
	public void shouldRotateCorrectly() {
		CircularlyLinkedList<Integer> sList = new CircularlyLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
		
		assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, sList.rotate(0).toArray());
		assertArrayEquals(new Integer[]{3, 4, 5, 1, 2}, sList.rotate(2).toArray());
		assertArrayEquals(new Integer[]{2, 3, 4, 5, 1}, sList.rotate(-1).toArray());
		assertArrayEquals(new Integer[]{5, 1, 2, 3, 4}, sList.rotate(13).toArray());
		
		sList = new CircularlyLinkedList<>(new Integer[]{});
		assertArrayEquals(new Integer[]{}, sList.rotate(0).toArray());
		assertArrayEquals(new Integer[]{}, sList.rotate(5).toArray());
		assertArrayEquals(new Integer[]{}, sList.rotate(-5).toArray());
	}
}

