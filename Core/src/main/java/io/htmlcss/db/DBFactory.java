package io.htmlcss.db;
/**
 * @author Hunter Lane
 * 
 * To prevent multiple connections and multiple of allocating the same object to the database
 * this class was created. 
 * 
 * Usage:
 * 
 * Whenever establishing a DatabaseFetcher use the DatabaseFactory.getDatabaseFetcher();
 */
public class DBFactory {
	
	private static DatabaseFetcher db = null;
	
	
	/**
	 * Function is designed to get the database fetcher and keep one
	 * concurrent database object.
	 * 
	 * @return The database fetcher/handler back to you.
	 */
	public static DatabaseFetcher getDatabaseFetcher() {
		if (db != null)
			return db;
		else {
			db = new DatabaseFetcher();
			return db;
		}
	}
}