package pl.piomin.kingsage.machine.util;

import java.util.Map;
import java.util.TreeMap;

import pl.piomin.kingsage.machine.model.Army;
import pl.piomin.kingsage.machine.model.Mission;
import pl.piomin.kingsage.machine.model.Village;

public class ResourceContainer {

	private static ResourceContainer instance = null;

	public static ResourceContainer getInstance() {
		if (instance == null) {
			instance = new ResourceContainer();
		}
		return instance;
	}

	private Map<Integer, Village> sources = new TreeMap<Integer, Village>();

	private static int sourceId = 0;
	
	private Map<Integer, Village> destinations = new TreeMap<Integer, Village>();

	private static int destinationId = 0;
	
	private Map<Integer, Army> armies = new TreeMap<Integer, Army>();

	private static int armyId = 0;

	private Map<Integer, Mission> missions = new TreeMap<Integer, Mission>();

	private static int missionId = 0;
	
	private ResourceContainer() {

	}

	public void putSource(Village village) {
		village.setId(++sourceId);
		this.sources.put(sourceId, village);
	}
	
	public void putDestination(Village village) {
		village.setId(++destinationId);
		this.destinations.put(destinationId, village);
	}

	public void putArmy(Army army) {
		army.setId(++armyId);
		this.armies.put(armyId, army);
	}
	
	public void putMission(Mission mission) {
		mission.setId(++missionId);
		this.missions.put(missionId, mission);
	}
	
	public Village getSource(Integer id) {
		return sources.get(id);
	}

	public Village getDestination(Integer id) {
		return destinations.get(id);
	}

	public Army getArmy(Integer id) {
		return armies.get(id);
	}
	
	public Mission getMission(Integer id) {
		return missions.get(id);
	}
	
}
