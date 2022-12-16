package Decorator;

import ActorTypes.*;
import Dades.Actor;
import Dades.ActorContext;
import Dades.ActorProxy;
import Messages.*;

public class FireWallDecorator extends ActorGeneric {
	
	private Actor actor;
	private ActorContext contx;

	public FireWallDecorator(Actor actor, ActorContext contx) {
		this.actor = actor;
		this.contx = contx;
	}
	public FireWallDecorator(Actor actor) {
		this.actor = actor;

	}

	@Override
	public void send(Message m) throws InterruptedException {
		if (m.getFrom() instanceof ActorProxy || m.getFrom() instanceof ActorProxyResponder ) {
			if(contx.existeProxy((ActorProxyResponder) m.getFrom())) {
				actor.send(m);
			}else{
				System.out.println("El actor no existe en el context. Mensaje retenido por el firewall");
			}
		}
		else if (contx.existe(m.getFrom())) {
			actor.send(m);
		}else {
			System.out.println("El actor " + m.getFrom()+ " no existe en el context. Mensaje retenido por el firewall");
		}
	}

}
