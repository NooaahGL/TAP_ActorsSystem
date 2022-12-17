package Dynamic;

import java.util.*;

import Dades.*;

public class InsultService implements InsultServiceInterface{
	private Actor a;
	private List<String> insults ;
	

	public InsultService(Actor a) {
		this.a = a;
		this.insults = new ArrayList<String>();

	}
	public InsultService() {
		insults = new ArrayList<String>();
	}

	public void addInsult(String s) {
		insults.add(s);
	}
	public void addInsult() {
		System.out.println("Agregado insulto mediante InsultService");
		System.out.println("");
		insults.add("stupid");
	}

	public String getInsult() {
		if (insults.size()>0) {
			System.out.println("Get insult mediante InsultService");
			int n = (int) ((Math.random() * (insults.size() + 1) - 1)) + 1;
			System.out.println("Random insult:");
			System.out.println(insults.get(n-1));
			return insults.get(n-1);
		}else{
			System.out.println("No hay insultos en la lista");
			return null;
		}
	}
	
	public List<String> getAllInsults() {
		System.out.println("GetAllinsult mediante InsultService");
		return insults;

	}

	public Actor getA() {
		return a;
	}

	public void setA(Actor a) {
		this.a = a;
	}


}
