package Programas;

import ActorTypes.*;
import Dades.*;
import Dades.ActorProxy;
import Messages.*;

public class HelloWorldProgram {

	public static void main(String[] args) throws InterruptedException {
		
		ActorContext aContext = ActorContext.getInstance();
		System.out.println("");
		
		ActorProxy a = aContext.spawnActor("Actor1", new HelloWorldActor());
		a.send(new Message(null, "Hola"));
		System.out.println("Fin");
		
		
	}

}
