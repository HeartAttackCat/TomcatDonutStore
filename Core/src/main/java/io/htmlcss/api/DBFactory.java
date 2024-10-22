package io.htmlcss.api;

public class DBFactory {
	
	private static DatabaseFetcher db = null;
	
	public static DatabaseFetcher getDatabaseFetcher() {
		if (db != null)
			return db;
		else {
			db = new DatabaseFetcher();
			return db;
		}
	}
}