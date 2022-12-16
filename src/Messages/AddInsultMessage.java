package Messages;

import Dades.Actor;

public class AddInsultMessage extends Message{

	public AddInsultMessage(Actor actorReference, String mensaje) {
		super(actorReference, mensaje);
		
	}

}
