package pl.piomin.kingsage.machine.logic;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pl.piomin.kingsage.machine.model.Mission;
import pl.piomin.kingsage.machine.util.ResourceContainer;
import pl.piomin.kingsage.machine.util.ResourceGenerator;

public class Service {

	private static final Logger logger = Logger.getLogger(ResourceGenerator.class);

	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
	
	private ResourceContainer container = ResourceContainer.getInstance();

	public void process() {
		List<Mission> missions = container.getMissions();
		for (Mission mission : missions) {
			long distanceMilis = (long) (mission.getDistance() * mission.getArmy().getSlowestUnit().getUnit().getTime() * 60 * 1000);
			long newTimestamp = mission.getTime().getTime() - distanceMilis;
			createTimer(mission, new Date(newTimestamp));
		}
	}

	private void createTimer(Mission mission, Date date) {
		Timer timer = new Timer();
		logger.info(MessageFormat.format("Schedule {0} on {1}", mission.getId(), TIME_FORMATTER.print(new DateTime(date))));
		timer.schedule(new MissionTask(mission), date);
	}

}
