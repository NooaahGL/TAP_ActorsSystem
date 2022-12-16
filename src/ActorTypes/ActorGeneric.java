package ActorTypes;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import Dades.*;
import Messages.Message;
import Messages.QuitMessage;

public class ActorGeneric implements Runnable, Actor {

	BlockingQueue<Message> cola = new LinkedBlockingDeque<Message>();
	
	public ActorGeneric() {

	}

	public void run() {
		Message m;
		try {
			m = cola.take();
			while (!(m instanceof QuitMessage)) {
				System.out.println("Actor recibio mensaje");
				processMessage(m);
				m = cola.take();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Message m) throws InterruptedException {
		cola.add(m);
	}

	public void processMessage(Message m) throws InterruptedException {
		
	}


}
