package Programas;

import ActorTypes.InsultActor;
import Dades.*;
import Dynamic.*;
import Messages.Message;

public class DynamicProxyProgram {
	public static void main(String[] args) throws InterruptedException {	
		
		ActorContext acContext = ActorContext.getInstance();
		System.out.println("");
		
		ActorProxy actor = acContext.spawnActor("Actor",new InsultActor());
		
		InsultServiceInterface insulter = (InsultServiceInterface)DynamicProxy.intercept(new InsultService(), actor);
		insulter.addInsult("stupid");
		System.out.println(insulter.getInsult());
		
		String[] m = insulter.getAllInsults();
		for(String insult: m) {
			System.out.println(insult);
			
		}

	}
	
}
