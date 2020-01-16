package hu.neuron.config;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hsqldb.server.Server;

@WebListener
public class StartingListener implements ServletContextListener {

	private Server server = new Server();

	private ServletContext context;

	private DatabaseProperties databaseProperties = DatabaseProperties.getInstance();

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		server.shutdownCatalogs(1);

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		context = event.getServletContext();
		String databaseName = context.getInitParameter("database");

		// Host and Port are not available in mem database

		server.setDatabaseName(0, databaseName);
		// HsqlProperties props = new HsqlProperties();
		// props.setProperty(key, value);
		// server.setProperties(props);
		// settings and data of myDb will be stored in files
		// myDb.properties and myDb.script
		server.setDatabasePath(0, "mem:warehouse");

		server.start();

		// connecting server

		databaseProperties.setUrl(context.getInitParameter("JDBC-url"));

		databaseProperties.setUserName(context.getInitParameter("username"));

		databaseProperties.setPassword(context.getInitParameter("password"));

		databaseProperties.setDriverClass(context.getInitParameter("JDBCDriverClass"));

		try {
			DatabaseUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.print("nincs connection");
		}

	}

}
