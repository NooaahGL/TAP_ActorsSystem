package Dades;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import ActorTypes.*;
import Messages.*;

public class ActorProxy implements Actor, Runnable{

	private Actor actor;
	private BlockingQueue<Message> colaMessage = new LinkedBlockingDeque<Message>();
	
	public ActorProxy(Actor actor) {
		
		this.actor = actor;
	}

	public Message receive() throws InterruptedException {
		Message m = colaMessage.take();
		if (m instanceof EncryptMessage) {
			actor.processMessage(m);
		}
		return m;
	}

	public void send(Message m){
		
		try {
			if (m.getFrom() == null) {
				ActorProxyResponder a = new ActorProxyResponder(this);

				m.setFrom(a);
				actor.send(m);
			}else {
				actor.send(m);
				//System.out.println("Mensaje enviado desde send del proxy");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sendProxy(Message m) throws InterruptedException{
		colaMessage.put(m);
		System.out.println("Mensaje en la cola del proxy");
		
	}

	public Actor getActor() {
		return actor;
	}
	
}
