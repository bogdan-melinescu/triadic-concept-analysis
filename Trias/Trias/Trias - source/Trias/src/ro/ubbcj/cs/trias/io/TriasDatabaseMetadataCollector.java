package ro.ubbcj.cs.trias.io;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class extracts metadata (table and column names) from a database.
 *
 */
public class TriasDatabaseMetadataCollector {

	private DatabaseMetaData metaData;

	public TriasDatabaseMetadataCollector(Connection connection) {
		try {
			this.metaData = connection.getMetaData();
		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex);
		}
	}

	public List<String> getTableNames() {
		try {
			List<String> tableNames = new ArrayList<String>();
			String types[] = { "TABLE" };
			ResultSet resultSet = metaData.getTables(null, null, "%", types);
			while (resultSet.next()) {
		        tableNames.add(resultSet.getString("TABLE_NAME"));
		    }
			return tableNames;

		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex);
		}
	}
	
	public List<String> getColumns(String table) {
		try {
			List<String> columnNames = new ArrayList<String>();
			ResultSet resultSet = metaData.getColumns(null, null, table, "%");
			while (resultSet.next()) {
		        columnNames.add(resultSet.getString("COLUMN_NAME"));
		    }
			return columnNames;

		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex);
		}
	}
}
