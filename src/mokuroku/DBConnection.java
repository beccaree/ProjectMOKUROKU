package mokuroku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
	
	private Connection c = null;
	private Statement stmt = null;

	public DBConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:MokuSessions.db");
			stmt = c.createStatement();
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database Succesfully");
	}
	
	public void initDB() {
		// http://www.tutorialspoint.com/sqlite/sqlite_java.htm
		try {			    
			String sql = "CREATE TABLE IF NOT EXISTS Inventory " +
						"(id 	INT 	PRIMARY KEY," +
						" name 	TEXT 	NOT NULL)";
			stmt.executeUpdate(sql);
			    	
			sql = "CREATE TABLE IF NOT EXISTS Items" +
					"(id	INT," +
					" iid	INT		PRIMARY KEY," +
					" iname TEXT	NOT NULL," +
					" description TEXT," +
					" price DECIMAL(3, 2)," +
					" stock	INT		NOT NULL," +
					" FOREIGN KEY(id) REFERENCES Inventory(id))";
			stmt.executeUpdate(sql);
			    	
	    	sql = "CREATE TABLE IF NOT EXISTS Sales" +
	    			"(iid INT," +
			    	" iname TEXT NOT NULL," +
			    	" datetime INT)";
			    	
	    	stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	public int getNewID() {
		// get an ID for the new inventory
		
		return 0;
	}
}
