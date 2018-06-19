package edu.csu2017fa314.T16.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Model {

	private String user;
	private String pass;
	
	public Model(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	//This is the first query that is performed using the search word (not phrase yet)
	//It constructs an ArrayList of Location objects, which each have a String code and a String name
	public ArrayList<Location> firstQuery(String search) { // command line string contains query to search
		ArrayList<Location> queryLocations = new ArrayList<>();
		try { // connect to the database 
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://faure.cs.colostate.edu/cs314", user, pass);
			try { // create a statement
				PreparedStatement prep = createStatement(conn, search);
				try { // submit a query
	    			ResultSet rs = prep.executeQuery();
	    			try { // iterate through the query results and add selected columns to an arraylist
	    				while(rs.next()){
							queryLocations.add(new Location(rs.getString("code"), rs.getString("name")));
	    				}
    				} finally {rs.close();}
    			} finally {prep.close();}
    		} finally {conn.close();}
		} catch(Exception e) { // catches all exceptions in the nested try's
    		System.err.printf("Exception: ");
    		System.err.println(e.getMessage());
		}
		return queryLocations;
	}

	public ArrayList<String[]> secondQuery(ArrayList<String> selected) { 
		ArrayList<String[]> selectedLocations = new ArrayList<>();
		try { // connect to the database 
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://faure.cs.colostate.edu/cs314", user, pass);
			try { // create a statement		
				PreparedStatement prep = conn.prepareStatement(getCodes(selected));
				try { // submit a query
	    			ResultSet rs = prep.executeQuery();
	    			try { // iterate through the query results and add selected columns to an arraylist
						int column = 1;
	    				while(rs.next()){
							ResultSetMetaData rsmd = rs.getMetaData();
							String[] results = new String[rsmd.getColumnCount()];
							for (int i = 1; i < rsmd.getColumnCount(); i++) { 
							  String tableName = rsmd.getTableName(i);
								String columnName = rsmd.getColumnName(i);
								columnName = Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
								switch(tableName) {
									case "airports":
										switch(columnName) {
											case "Name": columnName = "Airport Name"; break;
											case "Wikipedia_link": columnName = "Airport Wikipedia Link"; break;
											case "Keywords": columnName = "Airport Keywords"; break;
											default: columnName = "Airport_" + columnName;
										} break;
									case "regions":
										switch(columnName) {
											case "Name": columnName = "Region Name"; break;
											case "Wikipedia_link": columnName = "Region Wikipedia Link"; break;
											case "Keywords": columnName = "Region Keywords"; break;
										} break;
									case "countries":
										switch(columnName) {
											case "Name": columnName = "Country Name"; break;
											case "Wikipedia_link": columnName = "Country Wikipedia Link"; break;
											case "Keywords": columnName = "Country Keywords"; break;
										} break;
									case "continents":
										switch(columnName) {
											case "Name": columnName = "Continent Name"; break;
											case "Wikipedia_link": columnName = "Continent Wikipedia Link"; break;
										} break;
								}
								if(columnName.contains("_")) { 
									columnName = columnName.replaceAll("_", " ");
									for(int j = 0; j < columnName.length(); j++) {
										if(columnName.charAt(j) == ' ') {
											columnName = columnName.replace(columnName.charAt(j+1), Character.toUpperCase(columnName.charAt(j+1)));
										}
									}
								}
								results[i-1] = columnName + ":" + rs.getString(i); 
							}
							selectedLocations.add(results);
	    				}
    				} finally {rs.close();}
    			} finally {prep.close();}
    		} finally {conn.close();}
		} catch(Exception e) { // catches all exceptions in the nested try's
    		System.err.printf("Exception: ");
    		System.err.println(e.getMessage());
		}
		return selectedLocations;
	}

	public PreparedStatement createStatement(Connection conn, String search) {
		try{
			String query = "select airports.code as 'code', airports.name as 'name' from continents inner join countries on countries.continent = continents.code inner join regions on regions.iso_country = countries.code inner join airports on airports.iso_region = regions.code where ";
			for(int i = 0; i < search.split(", ").length; i++) {
				if(i != search.split(", ").length - 1) {
					query += "airports.name like ? or municipality like ? or regions.name like ? or countries.name like ? or continents.name like ? or ";				
				}
				else { query += "airports.name like ? or municipality like ? or regions.name like ? or countries.name like ? or continents.name like ? ";				 }
			}
 			query += "limit 100";	
			PreparedStatement prep = conn.prepareStatement(query);
			int count = 1;
			for(int i = 0; i < search.split(", ").length; i++) {
				prep.setString(count++, "%" + search.split(", ")[i] + "%");
				prep.setString(count++, "%" + search.split(", ")[i] + "%");
				prep.setString(count++, "%" + search.split(", ")[i] + "%");
				prep.setString(count++, "%" + search.split(", ")[i] + "%");
				prep.setString(count++, "%" + search.split(", ")[i] + "%");
			}
			return prep;
		} catch(Exception e) { // catches all exceptions in the nested try's
    		System.err.printf("Exception: ");
    		System.err.println(e.getMessage());
		}
		return null;
	}

	public String getCodes(ArrayList<String> selected) {
		String query = "select airports.code, airports.name, airports.type, latitude, longitude, municipality, elevation, scheduled_service, home_link, airports.wikipedia_link, airports.keywords, regions.name, regions.wikipedia_link, regions.keywords, countries.name, countries.wikipedia_link, countries.keywords, continents.name, continents.wikipedia_link from continents inner join countries on countries.continent = continents.code inner join regions on regions.iso_country = countries.code inner join airports on airports.iso_region = regions.code where ";
		for (int i = 0; i < selected.size(); i++) {
    		if (i == selected.size() - 1) { query += "airports.code = '" + selected.get(i) + "';"; }
    		else { query += "airports.code = '" + selected.get(i) + "' OR "; }
		}
		return query;
	}
}