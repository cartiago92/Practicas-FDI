package tp.pr4.testprofesor.items;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.items.Item;
import tp.pr4.testprofesor.MockItem;

public class ItemTest {
	
	Item iTest;
	String testId;
	String testDescription;
	@Before
	public void setUp() {
		testId="MyId";
		testDescription = "Test description";
		iTest = new MockItem(testId, testDescription);		
	}

	@Test
	public void testGetId() {
		assertEquals("ERROR: Item id has been modified during Item construction", testId, iTest.getId());		
	}
	
	@Test
	public void testToString() {
		String itemString = iTest.toString();
		assertTrue("ERROR: String returned by toString method does not contain the item id", itemString.contains(testId));
		assertTrue("ERROR: String returned by toString method does not contain the item description", itemString.contains(testDescription));
	}

}
