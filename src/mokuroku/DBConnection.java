package mokuroku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
						" name TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			    	
			sql = "CREATE TABLE IF NOT EXISTS Items" +
					"(id INT," +
					" iid INT PRIMARY KEY," +
					" iname TEXT NOT NULL," +
					" description TEXT," +
					" price DECIMAL(3, 2)," +
					" stock INT NOT NULL," +
					" FOREIGN KEY(id) REFERENCES Inventory(id))";
			stmt.executeUpdate(sql);
			    	
	    	sql = "CREATE TABLE IF NOT EXISTS Sales" +
	    			"(iid INT," +
			    	" iname TEXT NOT NULL," +
			    	" datetime INT)";
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
	
	public int getNewID() {
		// get an ID for the new inventory
		try {
			int id = -1;
			stmt = c.createStatement();
			String sql = "SELECT COUNT(*) AS number FROM Inventory";
			
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
			String sql = "INSERT INTO Inventory (id, name) VALUES (" + id + ", '" + name + "')";
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
			String sql = "SELECT * FROM Inventory";
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
}
