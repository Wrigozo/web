package hu.neuron.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for storing the required data members to connect to the database.
 * A singleton class.
 * 
 * @author szaboa
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class DatabaseProperties {

	private String url;

	private String userName;

	private String password;

	private String driverClass;

	private static final DatabaseProperties INSTANCE = new DatabaseProperties();

	public static DatabaseProperties getInstance() {
		return INSTANCE;
	}
}
