package hu.neuron.config;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;

/**
 * A singleton class.
 * 
 * @author szaboa
 *
 */
public class DatabaseProperties {

	private String url;
	
	private String userName;
	
	private String password;
	
	private String driverClass;

	private static final DatabaseProperties INSTANCE = new DatabaseProperties();

	private DatabaseProperties() {
	}

	public static DatabaseProperties getInstance() {
		return INSTANCE;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

}
