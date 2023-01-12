package Vthreads;

import Dades.Actor;
public class VirtualThred implements AbstractThread{
	private Actor a;
	
	public VirtualThred(Actor a) {
		this.a = a;
	}
	@Override
	public void startThread() {
		Thread.startVirtualThread(a);
	}

}
