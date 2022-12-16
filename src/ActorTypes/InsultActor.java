package ActorTypes;

import java.util.*;
import Messages.*;

public class InsultActor extends ActorGeneric {
	
	LinkedList<String> insults; 

	public InsultActor() {
		insults = new LinkedList<String>();
	}

	
	public void processMessage(Message m) throws InterruptedException {
		/*
		switch (m){
        case AddInsultMessage m1 -> addInsultMessage(m);
        case QuitMessage m1 -> System.out.println("BWYE BYE ");
        case GetInsultMessage m1 -> GetInsultMessage(m1);
        case AllInsultMessages m1 -> GetAllInsultsMessage(m1);
        default -> System.out.println("ERROR");
	       
	    }
	   */
		if (m instanceof AddInsultMessage) {
			addInsultMessage(m);
			
		}else if(m instanceof GetInsultMessage){
			GetInsultMessage(m);
			
		}else if(m instanceof AllInsultMessages){
			GetAllInsultsMessage(m);
		}

	}
	
	public void GetInsultMessage(Message m) throws InterruptedException {
		int n = (int) ((Math.random() * (insults.size() + 1) - 1)) + 1;
		System.out.println("Random insult:");
		System.out.println(insults.get(n-1));
		
		m.getFrom().send(new Message(this, insults.get(n-1)));
		
		System.out.println("");
	}
	
	public void GetAllInsultsMessage(Message m) throws InterruptedException {
		System.out.println("Get all insults:");
		
		for(String n:insults) {
			m.getFrom().send(new Message(this, n));
		}
		System.out.println("");
	}
	

	private void addInsultMessage(Message m) {
		System.out.println("Agregado el insulto " + m.getMensaje());
		insults.add(m.getMensaje());
		System.out.println("");

	}
	
	private int getNumInsults() {
		return insults.size();
	}
	@Override
	public void run() {
		Message m;
		try {
			m = cola.take();
			while (m.getClass() != QuitMessage.class) {
				System.out.println("InsultActor recibio mensaje");
				processMessage(m);
				m = cola.take();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
