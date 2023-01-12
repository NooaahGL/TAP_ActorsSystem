package Vthreads;

import Dades.Actor;

public class NormalThread implements AbstractThread{
	private Thread th;
	
	public NormalThread(Actor a) {
		this.th = new Thread(a);
	}
	@Override
	public void startThread() {
		th.start();
	}

}
