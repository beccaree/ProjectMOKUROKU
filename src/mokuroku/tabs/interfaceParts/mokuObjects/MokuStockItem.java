package mokuroku.tabs.interfaceParts.mokuObjects;

/**
 * @author Rebecca Lee
 *
 */
public class MokuStockItem {
	
	protected String name;
	protected String description;
	protected double price;
	protected int inStock;
	
	public MokuStockItem(String name, String description, double price, int inStock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
	}
	
	public void edit(String name, String description, double price, int inStock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
	}
	
	public String getName() {
		return name;
	}
	
}
