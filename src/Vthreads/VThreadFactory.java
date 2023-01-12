package Vthreads;

import Dades.Actor;

public class VThreadFactory implements ThreadAbstractFactory{

	@Override
	public AbstractThread createThread(Actor a) {
		return new VirtualThred(a);
	}

}
