package Dades;

import java.util.HashMap;
import java.util.Set;

import ActorTypes.ActorProxyResponder;
import Vthreads.*;

public class ActorContext{

	private static ActorContext actorContext;
	
	// es un singleton con una única instancia que es una lista de actores
	private HashMap<String, Actor> actorTable = new HashMap<String, Actor>();
	
	private ActorContext() {
		// es un singleton no tiene constructor
	}
	
	
	public static ActorContext getInstance() {
		if (actorContext == null) {
			actorContext = new ActorContext();
			System.out.println("Instancia creada por primera vez");
		}
		System.out.println("Instancia retornada");
		return actorContext;

	}
	
	public ActorProxy spawnActor (String name, Actor a) {
		
		//crea un thread que carga el proxy del actor y le deja trabajando
		ActorProxy proxy;
		proxy = new ActorProxy(a);
		
		//Pattern Factory para elegir entre threads y virtualThreads
		ThreadAbstractFactory factory = new VThreadFactory();
		AbstractThread th = factory.createThread(a);
		
		th.startThread();
		//t. start();
		
		actorTable.put(name, a);

		System.out.println("Actor " + name + " añadido al context");
		System.out.println("");
		return proxy;
		
	}
	/*
	 * Método que recibe el nombre de un actor 
	 * Retorna un proxi a ese actor
	 */
	public Actor lookup (String name) {
		
		 return actorTable.get(name);
	}
	
	public Set<String> getNames() {
		
		return actorTable.keySet();
	}
	
	public boolean existProxy(ActorProxyResponder a) {
		return actorTable.containsValue(a.getActor());
	}
	
	public boolean exist(Actor a) {
		return actorTable.containsValue(a);
	}
	public int numActors() {
		return actorTable.size();
	}
	public Actor[] actorsList() {
		return (Actor[]) actorTable.values().toArray();
	}


}
