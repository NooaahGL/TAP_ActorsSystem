package Messages;

import java.lang.reflect.Method;

import Dades.Actor;

public class ObjectMessage extends Message{
	
	private String method;
	private Object[] args;
	
	public ObjectMessage(Actor from, String method, Object[] args) {
		super(from);
		this.method = method;
		this.args = args;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Class getArgsClass() {
		if (args!= null) {
			return args[0].getClass();
		}else return null;
		
	}
	
	
}
