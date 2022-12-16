package Programas;

import ActorTypes.*;
import Dades.*;
import Messages.Message;

public class PingPongMain {
	public static void main(String[] args) throws InterruptedException {
		ActorContext acContext = ActorContext.getInstance();
		System.out.println("");
		
		ActorProxy ping = acContext.spawnActor("PingActor", new PingPongActor(null, "Ping"));
		ActorProxy pong = acContext.spawnActor("PongActor", new PingPongActor(ping, "Pong"));

		((PingPongActor) ping.getActor()).setNextActor(pong);

		ping.send(new Message(ping, "PingPong message"));

	}
}
