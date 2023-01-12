package Programas;

import java.util.ArrayList;
import java.util.HashMap;

import ActorTypes.*;
import Dades.*;
import Messages.*;
import MonitorService.*;

public class MonitoreServiceProgram {

	public static void main(String[] args) throws InterruptedException {
		ActorContext acContext = ActorContext.getInstance();
		System.out.println("");
		
		MonitorService monitorService = MonitorService.getInstance();
		monitorService.addActor(new ActorListener());
		//
		
		System.out.println("--Ciclo de 100 vueltas en 10 nodos para probar el monitor--");
		
		ActorProxy num2 = acContext.spawnActor("Actor0", new RingActor(null, "Actor0", true));
		ActorProxy aux = null;
		for(int i = 1; i<10; i++) {
			if (i==1) aux = num2;
			ActorProxy num = acContext.spawnActor("Actor"+i, new RingActor(num2, "Actor"+i));
			num2=num;
			
		}
		//inicializamos el primer actor del ciclo con las últims referencias
		((RingActor) aux.getActor()).setNextActor(num2);
		((RingActor) aux.getActor()).setA(num2);
		
		num2.send(new Message(null, "Hola"));	
		num2.receive();//bloqueamos el main hasta acabar el cicloS

		
		
		
		System.out.println("");
		HashMap<String, ArrayList<Actor>> messagesTrafic = new HashMap<String, ArrayList<Actor>>();
		messagesTrafic = monitorService.getTrafic();
		System.out.println(messagesTrafic);
		
		System.out.println("");
		HashMap<Actor, ArrayList<Message>> sentMessages = new HashMap<Actor, ArrayList<Message>>();
		sentMessages = monitorService.getSentMessages(num2.getActor());
		System.out.println(sentMessages);
		
		System.out.println("");
		HashMap<Actor, ArrayList<Message>> receivedMessages = new HashMap<Actor, ArrayList<Message>>();
		receivedMessages = monitorService.getReceivedMessages(num2.getActor());
		System.out.println(receivedMessages);
		
		System.out.println("");
		HashMap<States, ArrayList<Actor>> events = new HashMap<States, ArrayList<Actor>>();
		events = monitorService.getEvents();
		System.out.println(events);
		
		System.out.println("");
		int nMess = monitorService.getNumberofMessages(num2.getActor());
		System.out.println("El numero de mensajes recibidos por el actor 1 es "+ nMess);
		
	}


	
}
