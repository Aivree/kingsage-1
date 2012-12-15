package pl.piomin.kingsage.machine.main;

import java.io.IOException;

import pl.piomin.kingsage.machine.http.Sender;
import pl.piomin.kingsage.machine.logic.Service;
import pl.piomin.kingsage.machine.util.ResourceContainer;
import pl.piomin.kingsage.machine.util.ResourceGenerator;

public class KingsAgeMachine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceGenerator generator = new ResourceGenerator();
		generator.init();
		
//		Service service = new Service();
//		service.process();
		
		Sender sender = new Sender();
		try {
			sender.send(ResourceContainer.getInstance().getMission(0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
