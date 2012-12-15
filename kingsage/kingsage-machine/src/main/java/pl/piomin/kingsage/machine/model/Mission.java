package pl.piomin.kingsage.machine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Mission {

	private Integer id;

	private MissionType type;

	private Date time;

	private Village destination;

	private Army army;
	
	private int replyCount;

	public Mission(MissionType type, Date time, Village destination, Army army, int replyCount) {
		this.type = type;
		this.time = time;
		this.destination = destination;
		this.army = army;
		this.replyCount = replyCount;
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

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public double getDistance() {
		Village source = army.getVillage();
		int distanceX = Math.abs(destination.getX() - source.getX());
		int distanceY = Math.abs(destination.getY() - source.getY());
		double temp = Math.pow(distanceX, 2) + Math.pow(distanceY, 2);
		return Math.sqrt(temp);
	}
	
	public List<NameValuePair> prepare(String command) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (UnitItem unitItem : army.getUnits()) {
			nameValuePairs.add(new BasicNameValuePair(unitItem.getUnit().getName(), String.valueOf(unitItem.getCount())));
		} 
		nameValuePairs.add(new BasicNameValuePair("send_x", String.valueOf(destination.getX())));
		nameValuePairs.add(new BasicNameValuePair("send_y", String.valueOf(destination.getY())));
		nameValuePairs.add(new BasicNameValuePair(type.name().toLowerCase(), command));
		nameValuePairs.add(new BasicNameValuePair("discharge", ""));
		return nameValuePairs;
	}
	
	@Override
	public String toString() {
		return "Mission [id=" + id + ", type=" + type + ", time=" + time + ", destination=" + destination + ", army=" + army + "]";
	}

}
