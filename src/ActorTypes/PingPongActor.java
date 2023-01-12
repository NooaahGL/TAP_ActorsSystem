package ActorTypes;

import Dades.Actor;
import Messages.Message;

public class PingPongActor extends ActorGeneric{
	
	private Actor nextActor;
	private String name;
	
	public PingPongActor(Actor nextActor, String name) {
		this.nextActor = nextActor;
		this.name = name;
	}

	public Actor getNextActor() {
		return nextActor;
	}

	public void setNextActor(Actor nextActor) {
		this.nextActor = nextActor;
	}

	@Override
	public void processMessage(Message m) throws InterruptedException {
		
		if(nextActor != null) {
			m.setFrom(this);
			System.out.println(name+": Message '"+m.getMessage()+"' received");
			System.out.println("");
			nextActor.send(m);
			
		} else {
			if (nextActor != null)
			System.out.println("PingPong: Ha habido un error " );
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
