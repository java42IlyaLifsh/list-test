package telran.util;

//HW_12 IlyaL  new

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
private static final int N_NUMBERS_PERFORMANCE = 1_000_0000;
private List<Integer> numbers;
private List<String> strings;
Integer initialNumbers[] = {10, 20, 40};
String initialStrings[] = {"name1", "name2"};
	@BeforeEach
	void setUp() throws Exception {
		numbers = getInitialNumbers();
		strings = getInitialStrings();
	}

	private List<String> getInitialStrings() {
		List<String> res = new ArrayList<>();
		//List<String> res = new LinkedList<>();
		for (int i = 0; i < initialStrings.length; i++) {
			res.add(initialStrings[i]);
		}
		return res;
	}

	private List<Integer> getInitialNumbers() {
		
		//List<Integer> res = new ArrayList<>(1);
		List<Integer> res = new LinkedList<>();
		for (int num: initialNumbers) {
			res.add(num);
		}
		return res;
	}
	@Test
	void sortedSearchExist() {
		assertEquals(0, numbers.sortedSearch(10));
		assertEquals(1, numbers.sortedSearch(20));
		assertEquals(2, numbers.sortedSearch(40));
	}
	@Test
	void sortedSearchNotExist() {
		assertEquals(-1, numbers.sortedSearch(5));
		assertEquals(-2, numbers.sortedSearch(15));
		assertEquals(-3, numbers.sortedSearch(25));
		assertEquals(-4, numbers.sortedSearch(45));
	}

	@Test
	void testGet() {
		assertEquals(10, numbers.get(0));
		assertEquals("name1", strings.get(0));
		assertNull(numbers.get(-1));
		assertNull(numbers.get(3));
		


	}
	@Test
	void testAddAtIndex() {
		int inserted0 = 100;
		int inserted2 = -8;
		int inserted4 = 1000;
		Integer[] expected = {inserted0, 10, inserted2, 20, 40, inserted4};
		assertTrue(numbers.add(0, inserted0));
		assertTrue( numbers.add(2, inserted2));
		assertTrue( numbers.add(5, inserted4));
		assertArrayEquals(expected, getArrayFromList(numbers));
		assertFalse(numbers.add(7, 1000));
		assertFalse( numbers.add(-1, 1000));
	}
	@Test
	void testRemove() {
		Integer expected0[] = {20, 40};
		Integer expected1[] = {20};
		assertNull(numbers.remove(3));
		assertNull(numbers.remove(-1));
		assertEquals(10, numbers.remove(0));
		assertArrayEquals(expected0, getArrayFromList(numbers));
		assertEquals(40, numbers.remove(1));
		assertArrayEquals(expected1, getArrayFromList(numbers));
		
	}
	@Test 
	void testSize() {
		assertEquals(initialNumbers.length, numbers.size());
		numbers.add(100);
		assertEquals(initialNumbers.length + 1, numbers.size());
		numbers.remove(0);
		assertEquals(initialNumbers.length, numbers.size());
	}
	
	@Test
	void testContainsNumbers() {
		assertTrue(numbers.contains(initialNumbers[0]));
		assertFalse(numbers.contains(1000));
		numbers.add(1000);
		assertTrue(numbers.contains(1000));
		
		
	}
	@Test
	void testContainsStrings() {
		
		
		strings.add("Hello");
		String pattern = new String("Hello");
		assertTrue(strings.contains(pattern));
		assertTrue(strings.contains("Hello"));
	}
	@Test
	void testContainsPersons() {
		Person prs = new Person(123, "Moshe");
		Person prs2 = new Person(124, "Vasya");
//		List<Person> persons = new ArrayList<>();
		List<Person> persons = new LinkedList<>();
		persons.add(prs);
		persons.add(prs2);
		assertTrue(persons.contains(new Person(124, "Vasya")));
		assertTrue(persons.contains(prs));
		assertFalse(persons.contains(new Person(125, "Olya")));
	}
	@Test
	void containsPredicateNumbersTest() {
		int num100 = 100, num25=25;
		Predicate<Integer> predicate100 = n-> n>num100; /* rewrite code without any predicate class */
		 /* rewrite code without any predicate class */
		assertFalse(numbers.contains(predicate100)); //synt var1
		assertTrue(numbers.contains(n-> n>num25)); //synt var2
		
	}
	@Test
	void containsPredicateStringsTest() {
		String prefixName = "name", prefixMain = "main";
		
		Predicate<String> predicateMain = str1->str1.contains(prefixMain); /* rewrite code without any predicate class */
		 /* rewrite code without any predicate class */
		assertFalse(strings.contains(predicateMain));
		assertTrue(strings.contains(str2->str2.contains(prefixName)));
		
		
	}

	@SuppressWarnings("unchecked")
	private <T> T[] getArrayFromList(List<T> list) {
		int size = list.size();
		T[] res = (T[]) new Object[size];
		int resInd = 0;
		for(T obj: list) {
			res[resInd++] = obj;
		}
		return res;
	}
	
	@Test
	void indexOfTest() {
		assertEquals(0, numbers.indexOf(10));
		assertEquals(2, numbers.indexOf(40));
		assertEquals(-1, numbers.indexOf(100));
	}
	@Test
	void lastIndexOfTest() {
		assertEquals(0, numbers.lastIndexOf(10));
		assertEquals(2, numbers.lastIndexOf(40));
		assertEquals(-1, numbers.lastIndexOf(100));
		numbers.add(10);
		assertEquals(3, numbers.lastIndexOf(10));
		
	}
	@Test
	void indexOfPredicate() {
		int num25=25, num5 =5, num45 = 45;
		assertEquals(2, numbers.indexOf(n->n>num25));
		assertEquals(0, numbers.indexOf(n->n>num5));
		assertEquals(-1,numbers.indexOf(n->n>num45));
	}
	@Test
	void lastIndexOfPredicate() {
		int num25=25, num5 =5, num45 = 45;
		assertEquals(2, numbers.lastIndexOf(n->n>num25));
		assertEquals(2, numbers.lastIndexOf(n->n>num5));
		assertEquals(-1,numbers.lastIndexOf(n->n>num45));
	}
	@Test
	void removeIfTest() {
		Integer expected[] = {10, 20};
		Integer expectedEmpty[] = {};
		Predicate<Integer> greater25 = n->n>25; /* rewrite code without any predicate class */
		assertTrue(numbers.removeIf(greater25));
		assertFalse(numbers.removeIf(n->n>25));
		assertArrayEquals(expected, getArrayFromList(numbers));
		assertTrue(numbers.removeIf((n)->(n>0)));//var3 :)))
		assertArrayEquals(expectedEmpty, getArrayFromList(numbers));
		
		
		
	}
	@Test
	void removeAllTest() {
		numbers.add(20);
		List<Integer> otherNumbers = new ArrayList<>();
		otherNumbers.add(20);
		otherNumbers.add(40);
		assertTrue(numbers.removeAll(otherNumbers));
		Integer expected[] = {10};
		assertArrayEquals(expected, getArrayFromList(numbers));
		assertFalse(numbers.removeAll(otherNumbers));
	}
	@Test
	void removeAllSame() {
		assertTrue(numbers.removeAll(numbers));
		assertArrayEquals(new Integer[0], getArrayFromList(numbers));
	}
	@Test
	void retainAllTest() {
		numbers.add(20);
		List<Integer> otherNumbers = new ArrayList<>();
		otherNumbers.add(20);
		otherNumbers.add(40);
		assertTrue(numbers.retainAll(otherNumbers));
		Integer expected[] = {20,40,20};
		assertArrayEquals(expected, getArrayFromList(numbers));
		assertFalse(numbers.retainAll(otherNumbers));
	}
	@Test
	void retainAllSame() {
		assertFalse(numbers.retainAll(numbers));
		assertArrayEquals(initialNumbers, getArrayFromList(numbers));
	}
	
	@Test
	void removeObjectTest() {
		Integer expected0[] = {20, 40};
		Integer expected1[] = {20};
		assertNull(numbers.remove((Integer)25));
		assertEquals(10, numbers.remove((Integer)10));
		assertArrayEquals(expected0, getArrayFromList(numbers));
		assertEquals(40, numbers.remove((Integer)40));
		assertArrayEquals(expected1, getArrayFromList(numbers));
	}
	@Test
	void sortNaturalTest() {
		numbers.add(40);
		numbers.add(10);
		numbers.add(20);
		Integer expected[] = {10, 10, 20, 20, 40, 40};
		numbers.sort();
		assertArrayEquals(expected, getArrayFromList(numbers));
	}
	@Test
	void sortComparatorTest() {
		Integer expectedReverse[] = {40, 20, 10};
		Integer expectedProximity23[] = {20, 10, 40}; //sorted per proximity to 23
		Comparator<Integer> compNatural = Comparator.naturalOrder();
		numbers.sort(compNatural.reversed());
		assertArrayEquals(expectedReverse, getArrayFromList(numbers));
		int num=21;
		numbers.sort((a,b)->(Math.abs( num-a)-Math.abs( num-b)));
		assertArrayEquals(expectedProximity23, getArrayFromList(numbers));
	}
	@Test
	void removeIfPerformanceTest() {
		//List<Integer> list = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		fillListPerformance(list);
		//Predicate<Integer> divider4Predicate = null/* rewrite code without any predicate class */;
		list.removeIf(n->n%4==0);
		assertEquals(-1, list.indexOf(n->(n%4==0)));
		
	}
	@Test
	void removeByIteratorTest() {
		Iterator<Integer> it = numbers.iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
		assertArrayEquals(new Integer[0], getArrayFromList(numbers));
	}

	private void fillListPerformance(List<Integer> list) {
		for (int i = 0; i < N_NUMBERS_PERFORMANCE ; i++) {
			list.add((int)(Math.random() * Integer.MAX_VALUE));
		}
		
	}

}
