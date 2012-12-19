package pl.piomin.kingsage.machine.logic;

import java.io.IOException;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import pl.piomin.kingsage.machine.http.Sender;
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
		if (!Sender.getInstance().isLoggedIn()) {
			try {
				Sender.getInstance().login();
			} catch (IOException e) {
				logger.error("Error sending http login request", e);
			}
		}
		try {
			Sender.getInstance().send(mission);
		} catch (IOException e) {
			logger.error("Error sending http request", e);
		}
		if (Sender.getInstance().isLoggedIn()) {
			try {
				Sender.getInstance().logout();
			} catch (IOException e) {
				logger.error("Error sending http logout request", e);
			}
		}
	}

}
