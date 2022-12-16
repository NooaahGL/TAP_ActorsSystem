package Decorator;

import ActorTypes.*;
import Dades.*;
import Messages.*;

public class FireWallDecorator extends ActorGeneric {
	
	private Actor actor;

	public FireWallDecorator(Actor actor) {
		this.actor = actor;

	}

	@Override
	public void send(Message m) throws InterruptedException {
		ActorContext contx = ActorContext.getInstance();
		if (contx.existe(m.getFrom())) {
			actor.send(m);
		}else {
			System.out.println("El actor " + m.getFrom()+ " no existe en el context. Mensaje retenido por el firewall");
		}
	}

}
