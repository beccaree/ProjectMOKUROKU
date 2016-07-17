package mokuroku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mokuroku.tabs.InventoryTab;
import mokuroku.tabs.interfaceParts.MokuStockItem;

public class DBConnection {
	
	private Connection c = null;
	private Statement stmt = null;

	public DBConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:MokuSessions.db");
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database Succesfully");
	}
	
	public void initDB() {
		// http://www.tutorialspoint.com/sqlite/sqlite_java.htm
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Inventory " +
						"(id INT PRIMARY KEY," +
						" name TEXT NOT NULL);";
			stmt.executeUpdate(sql);
			    	
			sql = "CREATE TABLE IF NOT EXISTS Items" +
					"(id INT," +
					" iid INT PRIMARY KEY," +
					" iname TEXT NOT NULL," +
					" description TEXT," +
					" price DECIMAL(3, 2)," +
					" stock INT NOT NULL," +
					" FOREIGN KEY(id) REFERENCES Inventory(id));";
			stmt.executeUpdate(sql);
			    	
	    	sql = "CREATE TABLE IF NOT EXISTS Sales" +
	    			"(iid INT," +
			    	" iname TEXT NOT NULL," +
			    	" datetime INT);";
	    	stmt.executeUpdate(sql);
	    	
//	    	sql = "DELETE FROM Inventory WHERE id = 4";
//	    	stmt.executeUpdate(sql);

	    	sql = "SELECT * FROM Inventory";
	    	ResultSet rs = stmt.executeQuery(sql);
	    	while( rs.next() ) {
				System.out.println(rs.getInt("id") + " " + rs.getString("name"));
			}
			rs.close();
			    	
	    	stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	public int getNewID(String tableName) {
		// get an ID for the new inventory
		try {
			int id = -1;
			stmt = c.createStatement();
			String sql = "SELECT COUNT(*) AS number FROM " + tableName + ";";
			
			ResultSet rs = stmt.executeQuery(sql);
			while( rs.next() ) {
				id = rs.getInt("number");
			}
			rs.close();
	    	stmt.close();
	    	return id + 1;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return -1;
		}

	}
	
	public void addNewInventory(int id, String name) {
		// create a new inventory
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO Inventory (id, name) VALUES (" + id + ", '" + name + "');";
			stmt.executeUpdate(sql);
			
	    	stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public ArrayList<String> getInventories() {
		// return a list of inventory names in the form ([id]). [name] eg (1) Market
		ArrayList<String> result = new ArrayList<String>();
		try {
			String tuple;
			stmt = c.createStatement();
			String sql = "SELECT * FROM Inventory;";
			ResultSet rs = stmt.executeQuery(sql);
			while( rs.next() ) {
				tuple = "(" + rs.getInt("id") + ") " + rs.getString("name"); 
				result.add(tuple);
			}
			
			rs.close();
	    	stmt.close();
	    	return result;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
	}

	public int addItem(int inventoryID, MokuStockItem newItem) {
		//returns the new item id
		try {
			int newItemID = getNewID("Items");
			stmt = c.createStatement();
			String sql = "INSERT INTO Items (id, iid, iname, description, price, stock) VALUES (" +
						 inventoryID + ", " +
						 newItemID + ", " +
						 "'" + newItem.getName() + "', " +
						 "'" + newItem.getDescription() + "', " +
						 newItem.getPrice() + ", " +
						 newItem.getStock() + ");";
			stmt.executeUpdate(sql);
			
	    	stmt.close();
	    	return newItemID;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return -1;
		}
	}

	public ArrayList<MokuStockItem> getInventoryItems(int iID, InventoryTab parent) {
		// returns a list of items that have inventoryID = iID
		ArrayList<MokuStockItem> result = new ArrayList<MokuStockItem>();
		try {
			stmt = c.createStatement();
			String sql = "SELECT * FROM Items WHERE id = " + iID + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while( rs.next() ) {
				int itemID = rs.getInt("iid");
				String name = rs.getString("iname"); 
				String descr = rs.getString("description");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				
				MokuStockItem item = new MokuStockItem(parent, itemID, name, descr, price, stock);
				result.add(item);
			}
			
			rs.close();
	    	stmt.close();
	    	return result;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
	}
	
	public void update(int itemID, MokuStockItem item) {
		
	}

	public void delete(int itemID) {
		// delete the item that has item id 'itemID'
		try {
			stmt = c.createStatement();
			String sql = "DELETE from Items where iid = " + itemID + ";";
			stmt.executeUpdate(sql);
			
	    	stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

}
