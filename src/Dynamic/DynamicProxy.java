package Dynamic;

import java.lang.reflect.*;
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
        ActorProxyResponder a = new ActorProxyResponder(actor);
        actor.getActor().send(new ObjectMessage(a, method.getName(), args));
        if (method.getReturnType() != void.class) {
        	invocationResult = actor.receive().getMessage();
        	
        }
        return invocationResult;
	}


}
