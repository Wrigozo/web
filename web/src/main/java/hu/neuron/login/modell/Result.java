package hu.neuron.login.modell;

import java.io.Serializable;

public class Result implements Serializable {

	private boolean result = false;

	private Result() {
	}

	public Result(boolean r) {
		result = r;
	}

	public boolean getResult() {
		return result;
	}
}
