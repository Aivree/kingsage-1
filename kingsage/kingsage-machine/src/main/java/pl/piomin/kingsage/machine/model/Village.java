package pl.piomin.kingsage.machine.model;

public class Village {

	private Integer id;
	
	private String kingsageId;

	private int x;

	private int y;

	private String name;

	public Village(int x, int y, String name, String kingsageId) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.kingsageId = kingsageId;
	}

	public Village(int x, int y, String name) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKingsageId() {
		return kingsageId;
	}

	public void setKingsageId(String kingsageId) {
		this.kingsageId = kingsageId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Village [id=" + id + ", x=" + x + ", y=" + y + ", name=" + name + "]";
	}

}
