package Vthreads;

import Dades.Actor;

public class NThreadFactory implements ThreadAbstractFactory {

	@Override
	public AbstractThread createThread(Actor a) {
		return new NormalThread(a);
	}

}
