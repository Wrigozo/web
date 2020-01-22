package hu.neuron.login.modell;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("serial")
public class Result implements Serializable {

	private boolean result = false;

	public Result(boolean result) {
		this.result = result;
	}

}
