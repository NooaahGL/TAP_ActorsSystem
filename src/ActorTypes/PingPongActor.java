package ActorTypes;

import Dades.Actor;
import Messages.Message;

public class PingPongActor extends ActorGeneric{
	private Actor nextActor;
	private String nom;
	
	public PingPongActor(Actor nextActor, String nom) {
		this.nextActor = nextActor;
		this.nom = nom;
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
			System.out.println(nom+": Mensaje '"+m.getMensaje()+"' recibido");
			System.out.println("");
			nextActor.send(m);
			
		} else {
			if (nextActor != null)
			System.out.println("PingPong: Ha habido un error " );
		}
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	

}
