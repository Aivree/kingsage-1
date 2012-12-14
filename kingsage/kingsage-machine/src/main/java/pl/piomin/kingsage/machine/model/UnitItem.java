package pl.piomin.kingsage.machine.model;

public class UnitItem {

	private UnitType unit;

	private int count;

	public UnitItem(UnitType unit, int count) {
		this.unit = unit;
		this.count = count;
	}

	public UnitType getUnit() {
		return unit;
	}

	public void setUnit(UnitType unit) {
		this.unit = unit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "UnitItem [unit=" + unit + ", count=" + count + "]";
	}

}
