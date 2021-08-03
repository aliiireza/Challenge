import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

// import static org.junit.Assert.assertArrayEquals;

/**
 * 
 */

/**
 * @author Alireza
 *
 */
public class ChooseItemTest {

	@Test
	public void test1() {
		
		BestChoices bestChoices = new BestChoices();
		List<MenuItem> givenMenu = new ArrayList<>();
		
		bestChoices.populateMenu(givenMenu);
		
		List<MenuItem> actualAnswer = bestChoices.chooseItem(givenMenu, 30, 130, 10);
		List<MenuItem> expectedAnswer = new ArrayList<>(); 
		
		expectedAnswer.add(new MenuItem("Bottled Water", 0, 1));
		expectedAnswer.add(new MenuItem("Bottled Water", 0, 1));
		expectedAnswer.add(new MenuItem("House Salad", 100, 8));
		
		assertEquals(actualAnswer.size(), expectedAnswer.size());
		
		for (int i = 0; i < expectedAnswer.size(); i++) {
			assertEquals(actualAnswer.get(i).toString().equals(expectedAnswer.get(i).toString()), true);
		}
	}
	
	@Test
	public void test2() {
		
		BestChoices bestChoices = new BestChoices();
		List<MenuItem> givenMenu = null;

		try {

			bestChoices.chooseItem(givenMenu, 30, 130, 10);
			
		} catch (Exception exception) {
			assertEquals(exception.getMessage(), "The given menu is null.");
		}	
	}
	
	@Test
	public void test3() {
		
		BestChoices bestChoices = new BestChoices();
		List<MenuItem> givenMenu = new ArrayList<>();

		try {
			bestChoices.chooseItem(givenMenu, 10, 20, -10);
			
		} catch (Exception exception) {
			assertEquals(exception.getMessage(), "The budget cannot be negative.");
		}	
	}
	
	@Test
	public void test4() {
		
		BestChoices bestChoices = new BestChoices();
		List<MenuItem> givenMenu = new ArrayList<>();

		try {
			bestChoices.chooseItem(givenMenu, 130, 20, 10);
			
		} catch (Exception exception) {
			assertEquals(exception.getMessage(), "The minCalorie should not be bigger than maxCalorie.");
		}	
	}
	
	@Test
	public void test5() {
		
		BestChoices bestChoices = new BestChoices();
		List<MenuItem> givenMenu = new ArrayList<>();
		
		List<MenuItem> actualAnswer = bestChoices.chooseItem(givenMenu, 30, 130, 10);
		
		assertEquals(actualAnswer.size(), 0);
		
	}

}
