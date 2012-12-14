package pl.piomin.kingsage.machine.model;

import java.util.ArrayList;
import java.util.List;

public class Army {

	private Integer id;

	private List<UnitItem> units = new ArrayList<UnitItem>();

	private Village village;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public void addUnit(UnitItem unit) {
		units.add(unit);
	}
	
	public UnitItem getSlowestUnit() {
		UnitItem lastUnit = units.get(0); 
		for (UnitItem unitItem : units) {
			if (unitItem.getUnit().getTime() > lastUnit.getUnit().getTime()) {
				lastUnit = unitItem;
			}
		}
		return lastUnit;
	}

	@Override
	public String toString() {
		return "Army [id=" + id + ", unitItem=" + units + ", village=" + village + "]";
	}

}
