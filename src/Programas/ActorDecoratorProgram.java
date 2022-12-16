package Programas;

import java.util.function.Predicate;

import ActorTypes.*;
import Dades.*;
import Decorator.*;
import Messages.*;


public class ActorDecoratorProgram {
	public static void main(String[] args) throws InterruptedException {	
		
		ActorContext acContext = ActorContext.getInstance();
		System.out.println("");
		//--------------------------------------------------------------------------------
		System.out.println("------------Prueba decotator fireWall------------");
		//--------------------------------------------------------------------------------
		Message m = new Message("Hola decorator");
		ActorProxy fireWall = acContext.spawnActor("ActorFireWall", new FireWallDecorator(new InsultActor(), acContext));
		fireWall.send(m);
		
		Message m2 = new Message(new InsultActor(), "Hola desconocido");
		fireWall.send(m2);
		
		System.out.println("");
		System.out.println("");
		
		
		//--------------------------------------------------------------------------------
		System.out.println("------------Prueba decotator encrypt------------");
		//--------------------------------------------------------------------------------
		ActorProxy encrypt = acContext.spawnActor("ActorEncrypt", new EncryptionDecorator(new HelloWorldActor()));
		
		encrypt.send(new Message("hola"));
		encrypt.send(new Message("hola2"));
		encrypt.send(new Message("hola3"));
		for (int i = 0; i<3;i++) {
			m = encrypt.receive();
		}
		
		
		System.out.println("");
		System.out.println("");
		
		
		//--------------------------------------------------------------------------------
		System.out.println("------------Prueba decorator lambda------------");
		//--------------------------------------------------------------------------------
		
		Predicate<Message> pred = n -> n.containsActor();
		Message n = new Message(new HelloWorldActor(), "Hola decorator lambda");
		
		ActorProxy lambda = acContext.spawnActor("ActorLambda", new LambdaFirewallDecorator(new InsultActor(), pred));
		lambda.send(m);

		
		Message closure = new AddClosureMessage(null, "Hola decorator closure", z -> z.containsActor());
		lambda.send(closure);
		

		System.out.println("");
		System.out.println("");
		
		
		
		//--------------------------------------------------------------------------------
		System.out.println("------------Prueba decotator lambda-encrypt------------");
		//--------------------------------------------------------------------------------
		
		n = new Message(new HelloWorldActor(), "Hola decotator lambda-encrypt");
		
		m = new Message("Hola decorator lambda-encrypt");
		ActorProxy lambdaEncrypt = acContext.spawnActor("ActorLambdaEncrypt", new EncryptionDecorator (new LambdaFirewallDecorator(new InsultActor(), pred)));
		lambdaEncrypt.send(m);
		
		m = lambdaEncrypt.receive();
		System.out.println(n.getMensaje());

	}

}
