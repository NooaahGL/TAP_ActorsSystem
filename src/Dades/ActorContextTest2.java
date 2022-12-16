package Dades;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ActorTypes.HelloWorldActor;

class ActorContextTest2 {
	ActorContext acContext = ActorContext.getInstance();
	
	@Test
	void testExiste() {
		
		ActorProxy a = acContext.spawnActor("Actor1", new HelloWorldActor());
		Boolean respuesta = acContext.existe(a);
		assertTrue(respuesta==true);
	}
	
	@Test
	void testExiste2() {
		
		ActorProxy b = new ActorProxy(new HelloWorldActor());
		Boolean respuesta = acContext.existe(b);
		assertTrue(respuesta==false);
	}
	
	@Test
	void testSpawn() {
		
		ActorProxy a = acContext.spawnActor("Actor1", new HelloWorldActor());
		Boolean respuesta = acContext.existe(a);
		assertTrue(respuesta==true);
	}

}
