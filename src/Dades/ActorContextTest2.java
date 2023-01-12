package Dades;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import ActorTypes.HelloWorldActor;

class ActorContextTest2 {
	ActorContext acContext = ActorContext.getInstance();
	
	@Test
	void testExiste() {
		
		ActorProxy a = acContext.spawnActor("Actor1", new HelloWorldActor());
		Boolean answer = acContext.exist(a);
		assertTrue(answer==true);
	}
	
	@Test
	void testExiste2() {
		
		ActorProxy b = new ActorProxy(new HelloWorldActor());
		Boolean answer = acContext.exist(b);
		assertTrue(answer==false);
	}
	
	@Test
	void testSpawn() {
		
		ActorProxy a = acContext.spawnActor("Actor1", new HelloWorldActor());
		Boolean answer = acContext.exist(a);
		assertTrue(answer==true);
	}

}
