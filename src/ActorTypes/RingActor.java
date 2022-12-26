package ActorTypes;

import Dades.*;
import Messages.*;

public class RingActor extends ActorGeneric {
	
	private Actor nextActor;
	static int vueltas = 0;
	private String nom;
	
	private Boolean principal;
	static int MAXIM = 100;
	static ActorProxy a;
	
	public RingActor(Actor nextActor, String nom) {
		this.nextActor = nextActor;
		this.nom = nom;
		this.principal=false;
	}
	public RingActor(Actor nextActor, String nom, Boolean principal) {
		this.nextActor = nextActor;
		this.nom = nom;
		this.principal = principal;
	}


	@Override
	public void processMessage(Message m) throws InterruptedException {
		
		if(nextActor != null && vueltas < MAXIM) {
			m.setFrom(this);
			if (principal) vueltas++;
			System.out.println("RingActor: Mensaje '"+m.getMensaje()+"' procesado por " + nom + " y enviado al siguiente actor");
			nextActor.send(m);
			
		} else {
			if (nextActor == null) System.out.println("RingActor: no hay next actor" );
			else System.out.println("RingActor: He dado "+vueltas+ " vueltas" );
			
			//Para responder al main, utilizamos el actorProxyresponder
			if (a != null) a.sendProxy(new Message(new ActorProxyResponder(a), "Fin"));

		}
		
	}

	public void setNextActor(Actor nextActor) {
		this.nextActor = nextActor;
	}

	public void setA(ActorProxy a) {
		RingActor.a = a;
	}

	
	
}
