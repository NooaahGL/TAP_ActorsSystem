package Decorator;

import java.util.LinkedList;
import java.util.function.Predicate;

import ActorTypes.ActorGeneric;
import Dades.Actor;
import Messages.*;

public class LambdaFirewallDecorator extends ActorGeneric{
	
	private Actor actor;
	private LinkedList<Predicate<Message>> pred = new LinkedList<Predicate<Message>>(); 
	

	public LambdaFirewallDecorator(Actor actor, Predicate<Message> pred) {
		this.actor = actor;
		this.pred.add(pred);
	}
	
	@Override
	public void send(Message m) throws InterruptedException {
		if (m instanceof AddClosureMessage) {
			pred.add(((AddClosureMessage) m).getPred());
			System.out.println("Recibido mensaje: AddClosureMessage. Predicate añadido ");
		}else {
			Boolean cumple = true;
			
			for(Predicate<Message> p:pred) {
				if(!p.test(m)) {
					cumple = false;
				}
			}
			if (cumple) {
				System.out.println("Mensaje cumple con la lambda"); 
				m.getFrom().send(m);
			}
		}


	}

	public Actor getActor() {
		return actor;
	}
}




