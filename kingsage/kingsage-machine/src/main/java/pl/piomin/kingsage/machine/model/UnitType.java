package pl.piomin.kingsage.machine.model;

public enum UnitType {

	FARMER("farmer"), SWORD("sword"), SPEAR("spear"), AXE("axe"), BOW("bow"), SPY("spy"), LIGHT("light"), HEAVY("heavy"), RAM("ram"), KATA("kata"),
	SNOB("snob");

	private UnitType(String name) {

	}

	private String name;

	public String getName() {
		return name;
	}

}
