package ActorTypes;

import Messages.*;

public class HelloWorldActor extends ActorGeneric{
	
		
	public HelloWorldActor() {
		
	}

	public void processMessage(Message m) throws InterruptedException {
		System.out.println(m.getMessage());
	}


}
