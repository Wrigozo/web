package hu.neuron.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A singleton class.
 * 
 * @author szaboa
 *
 */
@NoArgsConstructor
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
