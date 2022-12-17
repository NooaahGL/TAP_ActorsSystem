package Dynamic;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import ActorTypes.*;
import Dades.ActorProxy;
import Messages.*;

public class DynamicProxy implements InvocationHandler{
	private Object target = null;
	private ActorProxy actor;
	
    public static Object intercept(Object target, ActorProxy a){
        Class targetClass = target.getClass();
        Class interfaces[] = targetClass.getInterfaces();
        
        return Proxy. newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target, a));
    }
    private DynamicProxy(Object target, ActorProxy a) {
        this.target = target;
        this.actor=a;
    }

    @Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocationResult = null;
        String name = method.getName();
        
        if("addInsult".equals(name)) {								
           actor.getActor().send(new AddInsultMessage(actor, (String) args[0]));
           
        } else if("getInsult".equals(name)) {
        	actor.getActor().send(new GetInsultMessage(new ActorProxyResponder(actor)));
        	invocationResult = actor.receive().getMensaje();
            
        } else if("getAllInsults".equals(name)) {
        	actor.getActor().send(new AllInsultMessages(new ActorProxyResponder(actor)));
        	for (int i = 0; i<((InsultActor) actor.getActor()).getNumInsults();i++) {
    			System.out.println(actor.receive().getMensaje());
    		}
        }
            return invocationResult;
	}
	
	
	public Object invoke2(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocationResult = null;
        
        actor.getActor().send(new ObjectMessage(actor, method.getName(), args));
        if (method.getReturnType() != void.class) {
        	invocationResult = actor.receive();
        }
        return invocationResult;
      
	}


}
