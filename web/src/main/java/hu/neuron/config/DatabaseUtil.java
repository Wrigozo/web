package hu.neuron.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for connect and close database.
 * @author szaboa
 *
 */
public class DatabaseUtil {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		Connection connection = null;
		try {
			Class.forName(DatabaseProperties.getInstance().getDriverClass());

			connection = DriverManager.getConnection(DatabaseProperties.getInstance().getUrl(),
					DatabaseProperties.getInstance().getUserName(), DatabaseProperties.getInstance().getPassword());

		} catch (SQLException e) {

			e.getMessage();
			System.out.append("rossz az sql");

		} catch (ClassNotFoundException e) {

			e.getMessage();
			System.out.append("nem találja az osztályt");
		}

		return connection;
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
