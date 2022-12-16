package ActorTypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Dades.Actor;
import Dynamic.InsultService;
import Messages.Message;
import Messages.ObjectMessage;
import Messages.QuitMessage;

public class ReflectiveActor extends ActorGeneric {

	public Object instancia;
	
	public ReflectiveActor(Object clase) {
		this.instancia=clase;
		
	}

	@Override
	public void run() {
		Message m;
		try {
			m = cola.take();
			while (m.getClass() != QuitMessage.class) {
				System.out.println("ReflectiveActor recibio mensaje");
				processMessage(m);
				m = cola.take();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void processMessage(Message m)  throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Class aClass = Class.forName((String) instancia);
		Class aClass = instancia.getClass();
		Method nameMethod=aClass.getDeclaredMethod(m.getMethod());
		
		Object invocationResult = null;
		nameMethod.invoke(m.getArgs());
        
	}

}
