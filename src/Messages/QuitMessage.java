package Messages;

import Dades.Actor;

public class QuitMessage extends Message{

	public QuitMessage(Actor actorReference, String message) {
		super(actorReference, message);
	}

}
