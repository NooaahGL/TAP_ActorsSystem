package Messages;

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

	public Class[] getArgsClass() {
		if (args!= null) {
			Class[] types = {int.class};
			int i=0;
			for(Object a:args) {
				types[i] = a.getClass();
				i++;
			}
			return types;
		}else return null;

	}
	
	
}
