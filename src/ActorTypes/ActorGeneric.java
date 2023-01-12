package ActorTypes;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import Dades.*;
import Messages.Message;
import Messages.QuitMessage;
import MonitorService.*;

public class ActorGeneric implements Runnable, Actor  {

	BlockingQueue<Message> queque = new LinkedBlockingDeque<Message>();
	MonitorService monitorService = MonitorService.getInstance();
	
	public ActorGeneric() {
		
	}

	public void run() {
		Message m;
		try {
			monitorService.setState(States.creationMessage, this);
			m = queque.take();
			while (!(m instanceof QuitMessage)) {
				System.out.println("Actor receives a message");
				processMessage(m);
				monitorService.setState(States.receivedMessage, this,m);
				m = queque.take();
			}
			monitorService.setState(States.FinalizationMessage, this);
		} catch (InterruptedException e) {
			monitorService.setState(States.incorrectFinalization, this);
			e.printStackTrace();
		}
	}
	
	public void send(Message m) throws InterruptedException {
		monitorService.setState(States.sendMessage, this, m);
		queque.add(m);
	}

	public void processMessage(Message m) throws InterruptedException {
		
	}

}
