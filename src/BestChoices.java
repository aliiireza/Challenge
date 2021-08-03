
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BestChoices {
	
	/**
	 * This method choose items to get highest price less than budget
	 * that have calorie between minCalorie and maxCalorie.
	 * @param menu
	 * @param minCalorie
	 * @param maxCalorie
	 * @param budget
	 * @return returns the chosen items.
	 * 
	 */
	public List<MenuItem> chooseItem(List<MenuItem> menu, int minCalorie, int maxCalorie, int budget) {
		
		List<MenuItem> chosenItems = new ArrayList<>();
		
		if (menu == null) {
			throw new NullPointerException("The given menu is null.");
		}
		
		if (budget < 0) {
			throw new IllegalArgumentException("The budget cannot be negative.");
		}
		
		if (minCalorie > maxCalorie) {
			throw new IllegalArgumentException("The minCalorie should not be bigger than maxCalorie.");
		}
		
		if (menu.size() == 0) {
			return chosenItems;
		}
		
		int sizeOfMenu = menu.size();
		int[][] DPTable = new int[budget + 1][maxCalorie + 1];
		
		for (int i = 0; i <= budget; i++) {
			Arrays.fill(DPTable[i], -1);
		}

		DPTable[0][0] = sizeOfMenu;
		
		buildBottomUpTable(menu, maxCalorie, budget, DPTable);
		
		findBestSolution(menu, minCalorie, maxCalorie, budget, chosenItems, DPTable);
		
		return chosenItems;
	}

	/**
	 * Build the bottom up table for our dynamic programming solution.
	 * Each cell shows the last items that was chosen to achieve this calorie and cost.
	 * @param menu
	 * @param maxCalorie
	 * @param budget
	 * @param DPTable
	 */
	private void buildBottomUpTable(List<MenuItem> menu, int maxCalorie, int budget, int[][] DPTable) {
		
		for (int menuItemIndex = 0; menuItemIndex < menu.size(); menuItemIndex++) {
			
			MenuItem menuItem = menu.get(menuItemIndex);
			for (int cost = 0; cost <= budget; cost++) {
				
				for (int calorie = 0; calorie <= maxCalorie; calorie++) {
					
					if (DPTable[cost][calorie] == -1 && cost >= menuItem.getCost() && calorie >= menuItem.getCalorie() &&
							DPTable[cost - menuItem.getCost()][calorie - menuItem.getCalorie()] >= 0) {
						
								DPTable[cost][calorie] = menuItemIndex;
					}
				}
			}
		}
	}

	/**
	 * This method find the best cost that we can get and call extractSolution to get a list of chosen items for the best cost.
	 * @param menu
	 * @param minCalorie
	 * @param maxCalorie
	 * @param budget
	 * @param chosenItems
	 * @param DPTable
	 */
	private void findBestSolution(List<MenuItem> menu, int minCalorie, int maxCalorie, int budget,
			List<MenuItem> chosenItems, int[][] DPTable) {
		
		for (int cost = budget; cost >= 0; cost--) {
			
			for (int calorie = minCalorie; calorie <= maxCalorie; calorie++) {
				
				if (DPTable[cost][calorie] != -1) {
					
					 System.out.println("Total cost of chosen items: " + cost);
					 System.out.println("Total calorie of chosen items: : " + calorie);
					 
					 extractSolution(DPTable, cost, calorie, menu, chosenItems);
					 return;
				}
				
			}
		}
	}
	
	/**
	 * This method extract the solution from the table.
	 * @param dp
	 * @param cost
	 * @param calorie
	 * @param menu
	 * @param chosenItems
	 */
	private void extractSolution(int[][] DPTable, int cost, int calorie, List<MenuItem> menu, List<MenuItem> chosenItems) {
		
		int sizeOfMenu = menu.size();
		
		while (DPTable[cost][calorie] != sizeOfMenu) {
			int itemToBuy = DPTable[cost][calorie];
			
			chosenItems.add(menu.get(itemToBuy));
			cost -= menu.get(itemToBuy).getCost();
			calorie -= menu.get(itemToBuy).getCalorie();
		} 
	}
	
	public static void main(String[] args) {
		
		BestChoices bestChoices = new BestChoices();
		List<MenuItem> givenMenu = new ArrayList<>();
		
		bestChoices.populateMenu(givenMenu);
		
		List<MenuItem> chosenItems = bestChoices.chooseItem(givenMenu, 30, 130, 10);
		
		for (MenuItem menuItem: chosenItems) {
			System.out.println(menuItem.toString());
		}
	}

	/**
	 * This method can be use for making example.
	 * @param givenMenu
	 */
	public void populateMenu(List<MenuItem> givenMenu) {
		
		givenMenu.add(new MenuItem("Cheese Pizza Slice", 700, 4));
		givenMenu.add(new MenuItem("House Salad", 100, 8));
		givenMenu.add(new MenuItem("Grilled Shrimp ", 400, 15));
		givenMenu.add(new MenuItem("Beef Brisket", 400, 12));
		givenMenu.add(new MenuItem("Bottled Water", 0, 1));
		givenMenu.add(new MenuItem("Soda", 100, 1));
	}

}
