
/**
 * 
 * @author Alireza
 * 
 */
public class MenuItem {

	private String name;
	private int cost;
	private int calorie;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		if (cost < 0) throw new IllegalArgumentException("The cost should not be negative.");
		this.cost = cost;
	}

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		if (calorie < 0) throw new IllegalArgumentException("The calorie should not be negative.");
		this.calorie = calorie;
	}
	
	/**
	 * @param name
	 * @param calorie
	 * @param cost
	 */
	public MenuItem(String name, int calorie, int cost) {
		if (cost < 0) throw new IllegalArgumentException("The cost should not be negative.");
		if (calorie < 0) throw new IllegalArgumentException("The calorie should not be negative.");
		this.name = name;
		this.calorie = calorie;
		this.cost = cost;
	}
	
	public String toString() {
		return this.name + " # " + this.calorie + " # " + this.cost;
	}

}

