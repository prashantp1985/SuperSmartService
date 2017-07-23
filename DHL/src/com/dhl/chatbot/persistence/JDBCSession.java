package com.dhl.chatbot.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class JDBCSession {

	private static JDBCSession repository;
	
	private static Connection connection;
	
	private static PreparedStatement preparedStatement;
	
	private static ResultSet resultSet;
	
	public static JDBCSession getInstance() {
		if(repository == null) {
			repository = new JDBCSession();
		}
		return repository;
	}
	
	private JDBCSession() {
		
	}
	
	public Connection getConnection() throws Exception {
		if(connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost/rammandir?user=root&password=password");
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			} catch (ClassNotFoundException e) {
				throw new Exception(e.getMessage());
			} finally {
//				try {
////					connection.close();
//				} catch (SQLException e) {
//					throw new Exception(e.getMessage());
//				}
			}
		}
		return connection;
		
	}
	
	public void executeInsertUpdateDeleteQuery(String query) throws Exception {
		getConnection();
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();
		connection.close();
	}
	
	public SortedMap<Integer, String[]> fetchResults(String query, int noOfColumns) throws Exception {
		getConnection();
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		SortedMap<Integer, String[]> allRows = new TreeMap<Integer, String[]>();
		int key = 1;
		while(resultSet.next()) {
			String[] row = new String[resultSet.getMetaData().getColumnCount()]; 
			for(int i = 1 ; i <= resultSet.getMetaData().getColumnCount(); i++) {
				row[i] = resultSet.getString(i);
				resultSet.getMetaData().getColumnName(i);
			}
			allRows.put(key, row);
			key++;
		}
		connection.close();
		return allRows;
	}
	
}

