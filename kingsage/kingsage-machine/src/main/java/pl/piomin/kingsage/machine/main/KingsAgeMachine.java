package pl.piomin.kingsage.machine.main;

import org.apache.log4j.Logger;

import pl.piomin.kingsage.machine.logic.Service;
import pl.piomin.kingsage.machine.util.ResourceGenerator;

public class KingsAgeMachine {

	private static Logger logger = Logger.getLogger(KingsAgeMachine.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceGenerator generator = new ResourceGenerator();
		generator.init();
		
		Service service = new Service();
		service.process();
		
		try {
			Thread.sleep(60000);
			logger.info("running...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
