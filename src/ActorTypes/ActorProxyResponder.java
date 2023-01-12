package ActorTypes;

import Dades.*;
import Messages.*;

public class ActorProxyResponder implements Actor {
	
	private ActorProxy actor;
	
	public ActorProxyResponder(Actor actor2) {
		
		this.actor = (ActorProxy) actor2;
	}

	public void send(Message m) throws InterruptedException{
		actor.sendProxy(m);
		System.out.println("Message sent to proxy");
	}
	
	public void processMessage(Message m) throws InterruptedException{

	}

	public Actor getActor() {
		return actor.getActor();
	}


	

}
