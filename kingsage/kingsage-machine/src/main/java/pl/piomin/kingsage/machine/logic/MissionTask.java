package pl.piomin.kingsage.machine.logic;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import pl.piomin.kingsage.machine.model.Mission;

public class MissionTask extends TimerTask {

	private static final Logger logger = Logger.getLogger(MissionTask.class);
	
	private Mission mission;
	
	public MissionTask(Mission mission) {
		this.mission = mission;
	}

	@Override
	public void run() {
		logger.info("Running mission: " + mission);
	}

}
