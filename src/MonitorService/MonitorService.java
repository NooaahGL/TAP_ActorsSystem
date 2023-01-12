package MonitorService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import Dades.Actor;
import Dades.ActorContext;
import Messages.Message;


//MonitorService

public class MonitorService {
	
	private static MonitorService monitorService;
	
	private List<ActorObserver> suscribers = new ArrayList<ActorObserver>();
	private HashMap<States, ArrayList<Actor>> actorStates = new HashMap<States, ArrayList<Actor>>();
	private HashMap<Actor, ArrayList<Message>> mSendedList = new HashMap<Actor, ArrayList<Message>>();
	private HashMap<Actor, ArrayList<Message>> mReceivedList = new HashMap<Actor, ArrayList<Message>>();
	
	public MonitorService() {

	}
	
	public static MonitorService getInstance() {
		if (monitorService == null) {
			monitorService = new MonitorService();
			//System.out.println("MonitoreService creado por primera vez");
		}
		//System.out.println("MonitorService retornado");
		return monitorService;
	}
	
	public void notifyObservers(States state) {
		for (ActorObserver a: suscribers) {
			a.update(state);
		}
	}
	
	/* Método que añade uno o todos los ActorObservers
	 * a la lista de suscriptores
	 */
	public void addActor(ActorObserver a) {
		suscribers.add(a);
	}
	
	public void monitorAllActors() {
		ActorContext context = ActorContext.getInstance();
		Actor[] array = context.actorsList();
		for(Actor a:array) {
			if (a instanceof ActorObserver)
				addActor((ActorObserver) a);
		}
	}
	
	/*
	 * Método que añade a la lista de cambios de estado una nueva situación.
	 */
	public void setState(States state, Actor actor) {
		
		ArrayList<Actor> list = actorStates.get(state);
		if(list==null) list = new ArrayList<Actor>();
			list.add(actor);
			actorStates.put(state, list);

		notifyObservers(state); 
		
	}
	
	/* Método invocado por el run ActorGenérico.
	 * Añade en cada caso a una lista, el evento que esté ocurriendo
	 */
	public void setState(States state, Actor actor, Message m) {
		if (state == States.sendMessage){

			ArrayList<Message> list = mSendedList.get(actor);
			if(list==null) list = new ArrayList<Message>();
			list.add(m);
			mSendedList.put(actor, list);
		
		}else if (state == States.receivedMessage){
			
			ArrayList<Message> list = mReceivedList.get(actor);
			if(list==null) list = new ArrayList<Message>();
			list.add(m);
			mReceivedList.put(actor, list);

		}
		notifyObservers(state);
	}
	
	/* Método que retorna un HashMap con las claves la frecuencia de mensajes
	 * que han enviado una serie de actores
	 * (LOW<5 messages, MEDIUM>5 <15, HIGH >15)
	 */
	public HashMap<String, ArrayList<Actor>> getTrafic() {
		
		Set<Actor> keys = mSendedList.keySet();
		int size=-1;
		HashMap<String, ArrayList<Actor>> messagesTrafic = new HashMap<String, ArrayList<Actor>>();
		messagesTrafic.put("LOW", null);
		messagesTrafic.put("MEDIUM", null);
		messagesTrafic.put("HIGH", null);
		
		String name = null;
		for(Actor key:keys) {
			//Como está guardado en un arrayList, con el size nos devuelve
			//la cantidad de mensajes que hay.
			size = mSendedList.get(key).size();
			
			if (size < 5) name = "LOW";
			else if(size > 15) name = "HIGH";
			else name = "MEDIUM";
			
				
			ArrayList<Actor> list = messagesTrafic.get(name);
			
			if(list==null) list = new ArrayList<Actor>();
			list.add(key);
			messagesTrafic.put(name, list);
							
		}
		return messagesTrafic;
	}
	
	/* Método que retorna una lista de los mensajes que ha enviado
	 * Un actor en concreto
	 * Key: actorName	Content: ArrayList de mensajes
	 */
	public HashMap<Actor, ArrayList<Message>> getSentMessages(Actor a){
		HashMap<Actor, ArrayList<Message>> messagesTrafic = new HashMap<Actor, ArrayList<Message>>();
		
		ArrayList<Message> list = mSendedList.get(a);
		messagesTrafic.put(a, list);

		return messagesTrafic;
	}
	
	/* Método que retorna el número de mensajes que ha enviado
	 * un actor que se pasa por parámetro
	 */
	public int getNumberofMessages(Actor a){
		return mSendedList.get(a).size();
	}
	
	/* Método que retorna una lista de los mensajes que ha recibido
	 * Un actor en concreto
	 * Key: actorName	Content: ArrayList de mensajes
	 */
	public HashMap<Actor, ArrayList<Message>> getReceivedMessages(Actor a) {
		HashMap<Actor, ArrayList<Message>> messagesTrafic = new HashMap<Actor, ArrayList<Message>>();
		
		ArrayList<Message> list = mReceivedList.get(a);
		messagesTrafic.put(a, list);

		return messagesTrafic;
	}
	
	/* Método que retorna todos los cambios de evento que ha hecho una actor
	 * en un hashmap cuya clave es el evento
	 */
	public HashMap<States, ArrayList<Actor>> getEvents() {

		return actorStates;
	}
}
