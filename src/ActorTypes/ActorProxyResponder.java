package ActorTypes;

import Dades.*;
import Messages.*;

public class ActorProxyResponder implements Actor {
	
	private ActorProxy actor;
	
	public ActorProxyResponder(ActorProxy actor) {
		
		this.actor = actor;
	}

	public void send(Message m) throws InterruptedException{
		actor.sendProxy(m);
		System.out.println("Mensaje enviado al proxy");
	}
	
	public void processMessage(Message m) throws InterruptedException{

	}

	public Actor getActor() {
		return actor.getActor();
	}


	

}
