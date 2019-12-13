package hu.neuron.login.modell;

import java.io.Serializable;

public class Result implements Serializable {

	private boolean result = false;

	private Result() {
	}

	public Result(boolean r) {
		this.result = r;
	}

	public boolean getResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result=result;
	}
}
