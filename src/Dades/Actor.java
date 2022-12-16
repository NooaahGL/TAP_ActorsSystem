package Dades;


import Messages.*;

public interface Actor extends Runnable { //Tiene su estado privado
	
	void send(Message m) throws InterruptedException;
	
	default void run() {
		
	}

	default void processMessage(Message m) throws InterruptedException {
		
	}
}
