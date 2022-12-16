package Programas;

import ActorTypes.*;
import Dades.*;
import Messages.*;


public class ActorProxyProgram {

	public static void main(String[] args) throws InterruptedException {
		
		ActorContext acContext = ActorContext.getInstance();
		System.out.println("");		
		System.out.println("");
		
		System.out.println("------Prueba con el InsultActor y sus distintos métodos------");
		ActorProxy b = acContext.spawnActor("actorA", new InsultActor());
		
		b.send(new AddInsultMessage(null, "Stupid"));
		b.send(new AddInsultMessage(null, "Berk"));
		b.send(new AddInsultMessage(null, "Airy-fairy"));
		
		int numMess=3;
		b.send(new AllInsultMessages());
		
		Message resultB;
		for (int i = 0; i<numMess;i++) {
			resultB= b.receive();
			System.out.println(resultB.getMensaje());
		}

		
		b.send(new GetInsultMessage());
		
		System.out.println("------------");
		
	}

}
