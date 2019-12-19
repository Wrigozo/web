package hu.neuron.warehouse.client.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitVO {

	private String unit;
	
	public UnitVO() {
		
	}
	
	public UnitVO(String unit) {
		this.unit=unit;
	}
	
	public String getUnit() {
		return unit;
	}
}
