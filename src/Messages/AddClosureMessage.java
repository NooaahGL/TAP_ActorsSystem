package Messages;

import java.util.function.Predicate;

import Dades.Actor;

public class AddClosureMessage extends Message {
	private Predicate<Message> pred;

	public AddClosureMessage(Actor actorReference, String mensaje, Predicate<Message> pred) {
		super(actorReference, mensaje);
		this.pred = pred;
		
	}
	public AddClosureMessage(Predicate<Message> pred) {
		this.pred = pred;
		
	}

	public Predicate<Message> getPred() {
		return pred;
	}

	public void setPred(Predicate<Message> pred) {
		this.pred = pred;
	}
	
}
