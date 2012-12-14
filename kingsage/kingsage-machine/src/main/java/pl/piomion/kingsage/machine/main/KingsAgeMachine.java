package pl.piomion.kingsage.machine.main;

import pl.piomin.kingsage.machine.logic.Service;
import pl.piomin.kingsage.machine.util.ResourceGenerator;

public class KingsAgeMachine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceGenerator generator = new ResourceGenerator();
		generator.init();
		
		Service service = new Service();
		service.process();
	}

}
