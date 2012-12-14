package pl.piomin.kingsage.machine.model;

import java.util.ArrayList;
import java.util.List;

public class Army {

	private Integer id;

	private List<UnitItem> unitItem = new ArrayList<UnitItem>();

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
		unitItem.add(unit);
	}

	@Override
	public String toString() {
		return "Army [id=" + id + ", unitItem=" + unitItem + ", village=" + village + "]";
	}

}
