package Programas;

import ActorTypes.*;
import Dades.*;
import Messages.*;

public class RingActorMain {

	public static void main(String[] args) throws InterruptedException {
		ActorContext acContext = ActorContext.getInstance();
		System.out.println("");
		/*
		ActorProxy d = acContext.spawnActor("Actor4", new RingActor(null, "Actor4", true));
		ActorProxy c = acContext.spawnActor("Actor3", new RingActor(d, "Actor3"));
		ActorProxy b = acContext.spawnActor("Actor2", new RingActor(c, "Actor2"));
		ActorProxy a = acContext.spawnActor("Actor1", new RingActor(b, "Actor1"));
		((RingActor) d.getActor()).setNextActor(a);

		a.send(new Message(d, "hola"));
		 */
		
		
		System.out.println("--------------Ciclo de 100 vueltas en 100 nodos--------------");
		
		ActorProxy num2 = acContext.spawnActor("Actor0", new RingActor(null, "Actor0", true));
		ActorProxy aux = null;
		for(int i = 1; i<100; i++) {
			if (i==1) aux = num2;
			ActorProxy num = acContext.spawnActor("Actor"+i, new RingActor(num2, "Actor"+i));
			num2=num;
			
		}
		//inicializamos el primer actor del ciclo con las últims referencias
		((RingActor) aux.getActor()).setNextActor(num2);
		((RingActor) aux.getActor()).setA(num2);
		
		long startTime = System.nanoTime();
		num2.send(new Message(null, "Hola"));	
		num2.receive();//bloqueamos el main hasta acabar el cicloS
		
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

}
