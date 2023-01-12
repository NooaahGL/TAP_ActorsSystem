package Programas;

import ActorTypes.*;
import Dades.ActorContext;
import Dades.ActorProxy;
import Dynamic.*;
import Messages.Message;


public class ActorReflectiveProgram {
	public static void main(String[] args) throws InterruptedException {	
		
		ActorContext acContext = ActorContext.getInstance();
		System.out.println("");
		
		ActorProxy insult = acContext.spawnActor("Actor",new ReflectiveActor(new InsultService()));
		
		InsultServiceInterface insulter = (InsultServiceInterface)DynamicProxy.intercept(new InsultService(), insult);
		insulter.addInsult("stupid");
		System.out.println(insulter.getInsult());

		
	}
}
