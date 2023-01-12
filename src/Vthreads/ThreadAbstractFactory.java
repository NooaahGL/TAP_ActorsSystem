package Vthreads;

import Dades.Actor;

public interface ThreadAbstractFactory {
	
	public abstract AbstractThread createThread(Actor a);
}
