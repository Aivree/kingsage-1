package pl.piomin.kingsage.machine.util;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pl.piomin.kingsage.machine.model.Army;
import pl.piomin.kingsage.machine.model.ConfigurationType;
import pl.piomin.kingsage.machine.model.Mission;
import pl.piomin.kingsage.machine.model.MissionType;
import pl.piomin.kingsage.machine.model.UnitItem;
import pl.piomin.kingsage.machine.model.UnitType;
import pl.piomin.kingsage.machine.model.Village;

public class ResourceGenerator {

	private static final Logger logger = Logger.getLogger(ResourceGenerator.class);
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("pl.piomin.kingsage.machine.app");
	
	private static final String SPLIT_PATTERN = "\\.";

	private static final String DESTINATION_PATTERN = "^name:(.*),x:(\\d{3}),y:(\\d{3}).*";
	
	private static final String SOURCE_PATTERN = "^name:(.*),x:(\\d{3}),y:(\\d{3}),kingsageId:(\\w*).*";
	
	private static final String ARMY_PATTERN = "(\\w*):(\\d{1,5})";
	
	private static final String MISSION_PATTERN = "^village:(\\d{1,3}),army:(\\d{1,3}),time:(\\w*),type:(\\w*),reply:(\\d{1})";

	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("yyyyMMddHHmmss");
	
	private ResourceContainer container = ResourceContainer.getInstance();
	
	private Pattern sourcePattern;
	
	private Pattern destinationPattern;
	
	private Pattern armyPattern;
	
	private Pattern missionPattern;
	
	public void init() {
		this.sourcePattern = Pattern.compile(SOURCE_PATTERN);
		this.destinationPattern = Pattern.compile(DESTINATION_PATTERN);
		this.armyPattern = Pattern.compile(ARMY_PATTERN);
		this.missionPattern = Pattern.compile(MISSION_PATTERN);
		
		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			final String key = keys.nextElement();
			ConfigurationType type = ConfigurationType.valueOf(key.split(SPLIT_PATTERN)[0].toUpperCase());
			switch (type) {
			case SOURCE:
				Village source = createSource(bundle.getString(key));
				logger.info(MessageFormat.format("Source {0}", source));
				container.putSource(source);
				break;

			case DESTINATION:	
				Village destination = createDestination(bundle.getString(key));
				logger.info(MessageFormat.format("Destination {0}", destination));
				container.putDestination(destination);
				break;
				
			case ARMY:
				Army army = createArmy(bundle.getString(key));
				logger.info(MessageFormat.format("Army {0}", army));
				container.putArmy(army);
				break;
				
			case MISSION:
				Mission mission = createMission(bundle.getString(key));
				logger.info(MessageFormat.format("Mission {0}", mission));
				container.addMission(mission);
				break;
				
			default:
				break;
			}
		}
	}
	
	public Village createSource(String value) {
		Matcher matcher = sourcePattern.matcher(value);
		if (matcher.matches()) {
			return new Village(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), matcher.group(1), matcher.group(4));
		} else {
			return null;
		}
	}
	
	public Village createDestination(String value) {
		Matcher matcher = destinationPattern.matcher(value);
		if (matcher.matches()) {
			return new Village(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), matcher.group(1));
		} else {
			return null;
		}
	}
	
	public Army createArmy(String value) {
		Matcher matcher = armyPattern.matcher(value);
		Army army = new Army();
		while (matcher.find()) {
			String key = matcher.group(1);	
			String property = matcher.group(2);
			if (key.equals("village")) {
				Integer id = Integer.parseInt(property);
				army.setVillage(container.getSource(id));
			} else {
				UnitType unitType = UnitType.valueOf(key.toUpperCase());
				UnitItem unitItem = new UnitItem(unitType, Integer.parseInt(property));
				army.addUnit(unitItem);
			}
		}
		return army;
	}
	
	public Mission createMission(String value) {
		Matcher matcher = missionPattern.matcher(value);
		if (matcher.matches()) {
			MissionType type = MissionType.valueOf(matcher.group(4).toUpperCase());
			Village village = container.getDestination(Integer.parseInt(matcher.group(1)));
			Army army = container.getArmy(Integer.parseInt(matcher.group(2)));
			return new Mission(type, TIME_FORMATTER.parseDateTime(matcher.group(3)).toDate(), village, army, Integer.parseInt(matcher.group(5)));
		} else {
			return null;
		}
	}
	
}
