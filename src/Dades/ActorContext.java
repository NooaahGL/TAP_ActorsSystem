package Dades;

import java.util.HashMap;
import java.util.Set;

import ActorTypes.ActorProxyResponder;
import Vthreads.*;

public class ActorContext{

	private static ActorContext actorContext;
	
	private HashMap<String, Actor> actorTable = new HashMap<String, Actor>();
	
	private ActorContext() {
		
	}
	
	public static ActorContext getInstance() {
		if (actorContext == null) {
			actorContext = new ActorContext();
			System.out.println("First created instance");
		}
		System.out.println("Returned instance");
		return actorContext;

	}
	
	public ActorProxy spawnActor (String name, Actor a) {
		
		//crea un thread que carga el proxy del actor y le deja trabajando
		ActorProxy proxy;
		proxy = new ActorProxy(a);
		
		//Pattern Factory para elegir entre threads y virtualThreads
		ThreadAbstractFactory factory = new NThreadFactory();
		AbstractThread th = factory.createThread(a);
		
		th.startThread();
		
		actorTable.put(name, a);

		System.out.println("Actor " + name + " added to context");
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
