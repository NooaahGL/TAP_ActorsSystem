package ActorTypes;

import MonitorService.*;

public class ActorListener implements ActorObserver{

	@Override
	public void update(States state) {
		System.out.println("New state:" + state);
		
	}

}
