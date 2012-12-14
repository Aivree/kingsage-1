package pl.piomin.kingsage.machine.model;

public enum UnitType {

	FARMER("farmer", 22), SWORD("sword", 22), SPEAR("spear", 18), AXE("axe", 18), BOW("bow", 22), SPY("spy", 8), LIGHT("light", 10), HEAVY("heavy", 16), RAM("ram", 30), KATA("kata", 30),
	SNOB("snob", 35);

	private UnitType(String name, int time) {
		this.name = name;
		this.time = time;
	}

	private String name;

	private int time;
	
	public String getName() {
		return name;
	}

	public int getTime() {
		return time;
	}

}
