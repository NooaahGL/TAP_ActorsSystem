package ActorTypes;

import Messages.*;

public class HelloWorldActor extends ActorGeneric{
	
		
	public HelloWorldActor() {
		super();
		
	}

	@Override
	public void run() {
		Message m;
		try {
			m = cola.take();
			while (m.getClass() != QuitMessage.class) {
				System.out.println("HelloWorldActor recibio mensaje");
				processMessage(m);
				m = cola.take();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void processMessage(Message m) throws InterruptedException {
		System.out.println(m.getMensaje());
	}


}
