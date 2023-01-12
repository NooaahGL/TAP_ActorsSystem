package Messages;

import Dades.*;

public class Message {

	private Actor from;
	private String mensaje;
	
	public Message(Actor from, String mensaje) {
		
		this.mensaje = mensaje;
		this.from = from;
	}
	public Message(String mensaje) {
		
		this.mensaje = mensaje;
		this.from = null;
	}
	public Message() {

	}

	public Message(Actor from) {
		this.from = from;
	}
	
	public Actor getFrom() {
		return from;
	}

	public void setFrom(Actor from) {
		this.from = from;
	}

	public String getMessage() {
		return mensaje;
	}
	
	public void setMessage(String mensaje) {
		this.mensaje = mensaje;
	}
	public boolean containsActor() {
		
		return (from != null);
	}

	
	
}
