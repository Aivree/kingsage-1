package pl.piomin.kingsage.machine.model;

import java.util.Date;

public class Mission {

	private Integer id;

	private MissionType type;

	private Date time;

	private Village destination;

	private Army army;

	public Mission(MissionType type, Date time, Village destination, Army army) {
		this.type = type;
		this.time = time;
		this.destination = destination;
		this.army = army;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MissionType getType() {
		return type;
	}

	public void setType(MissionType type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Village getDestination() {
		return destination;
	}

	public void setDestination(Village destination) {
		this.destination = destination;
	}

	public Army getArmy() {
		return army;
	}

	public void setArmy(Army army) {
		this.army = army;
	}

	public double getDistance() {
		Village source = army.getVillage();
		int distanceX = Math.abs(destination.getX() - source.getX());
		int distanceY = Math.abs(destination.getY() - source.getY());
		double temp = Math.pow(distanceX, 2) + Math.pow(distanceY, 2);
		return Math.sqrt(temp);
	}
	
	@Override
	public String toString() {
		return "Mission [id=" + id + ", type=" + type + ", time=" + time + ", destination=" + destination + ", army=" + army + "]";
	}

}
