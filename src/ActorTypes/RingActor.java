package ActorTypes;

import Dades.*;
import Messages.*;

public class RingActor extends ActorGeneric {
	
	static int MAXIM = 100;
	static int spins = 0;
	static ActorProxy a;
	
	private Actor nextActor;
	private String name;
	private Boolean principal;
	
	
	public RingActor(Actor nextActor, String name) {
		this.nextActor = nextActor;
		this.name = name;
		this.principal=false;
	}
	public RingActor(Actor nextActor, String name, Boolean principal) {
		this.nextActor = nextActor;
		this.name = name;
		this.principal = principal;
	}


	@Override
	public void processMessage(Message m) throws InterruptedException {
		
		if(nextActor != null && spins < MAXIM) {
			m.setFrom(this);
			if (principal) spins++;
			System.out.println("RingActor: Mensaje '"+m.getMessage()+"' proccesed by " + name + " and send to the next actor");
			nextActor.send(m);
			
		} else {
			if (nextActor == null) System.out.println("RingActor: there is no next actor" );
			else System.out.println("RingActor: Has take "+spins+ " spins" );
			
			//Para responder al main, utilizamos el actorProxyresponder
			if (a != null) a.sendProxy(new Message(new ActorProxyResponder(a), "End"));

		}
		
	}

	public void setNextActor(Actor nextActor) {
		this.nextActor = nextActor;
	}

	public void setA(ActorProxy a) {
		RingActor.a = a;
	}

	
	
}
