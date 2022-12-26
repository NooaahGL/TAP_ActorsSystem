package ActorTypes;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import Dades.*;
import Messages.Message;
import Messages.QuitMessage;
import MonitorService.*;

public class ActorGeneric implements Runnable, Actor  {

	BlockingQueue<Message> cola = new LinkedBlockingDeque<Message>();
	SubjectActor monitorService = SubjectActor.getInstance();
	
	public ActorGeneric() {
		
	}

	public void run() {
		Message m;
		try {
			monitorService.setState(States.creationMessage, this);
			m = cola.take();
			while (!(m instanceof QuitMessage)) {
				System.out.println("Actor recibio mensaje");
				processMessage(m);
				monitorService.setState(States.receivedMessage, this,m);
				m = cola.take();
			}
			monitorService.setState(States.FinalizationMessage, this);
		} catch (InterruptedException e) {
			monitorService.setState(States.incorrectFinalization, this);
			e.printStackTrace();
		}
	}
	
	public void send(Message m) throws InterruptedException {
		monitorService.setState(States.sendMessage, this, m);
		cola.add(m);
	}

	public void processMessage(Message m) throws InterruptedException {
		
	}

}
