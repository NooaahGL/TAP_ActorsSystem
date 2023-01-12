package ActorTypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Messages.*;

public class ReflectiveActor extends ActorGeneric {

	public Object instance;
	
	public ReflectiveActor(Object clase) {
		this.instance=clase;
		
	}

	public void processMessage(Message m) {
		ObjectMessage m2 = (ObjectMessage) m;
		Method nameMethod = null;
		
		try {
			Class aClass = instance.getClass();
			
			String name = m2.getMethod();
			System.out.println(name);
			
			Class[] parameterType = m2.getArgsClass();

			nameMethod = aClass.getDeclaredMethod(name, parameterType);
			System.out.println("method = " + nameMethod.toString());
			Object invocationResult = null;
			
			if (parameterType != null) {
				invocationResult = nameMethod.invoke(instance, m2.getArgs());
			}else {
				invocationResult = nameMethod.invoke(instance);
			}
			
			if (invocationResult != null) {
				m2.getFrom().send(new Message(this, invocationResult.toString()));
			}
			
		}  catch (SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
