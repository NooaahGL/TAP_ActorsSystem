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

	public void processMessage(Message m) {
		
		//Class aClass = instancia.getClass();
		Method nameMethod;
		try {
			Class aClass = Class.forName("Dynamic.InsultService");
			String name = ((ObjectMessage) m).getMethod();
			Class parameterType = ((ObjectMessage) m).getArgsClass();
			nameMethod = aClass.getDeclaredMethod(name);
			
			Object newObject = aClass.newInstance();
			nameMethod.invoke(newObject);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
        
	}

}
