package ActorTypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Messages.Message;
import Messages.ObjectMessage;
import Messages.QuitMessage;

public class ReflectiveActor extends ActorGeneric {

	public Object instancia;
	
	public ReflectiveActor(Object clase) {
		this.instancia=clase;
		
	}


	public void processMessage(Message m) {
		ObjectMessage m2 = (ObjectMessage)m;
		//Class aClass = instancia.getClass();
		Method nameMethod;
		try {
			Class aClass = instancia.getClass();
			
			String name = m2.getMethod();
			System.out.println(name);
			
			Class[] parameterType = m2.getArgsClass();
			System.out.println(parameterType);
			
			aClass.getMethods();
			//nameMethod = aClass.getMethod(name, parameterType);
			nameMethod = aClass.getDeclaredMethod(name, parameterType);
			
			nameMethod.invoke(instancia);
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        
	}

}
