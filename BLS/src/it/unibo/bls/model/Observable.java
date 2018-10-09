package it.unibo.bls.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.bls.interfaces.IObservable;
import it.unibo.bls.interfaces.IObserver;

public class Observable implements IObservable{
	
	protected List<IObserver> observers = new ArrayList<>();

	@Override
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}

}
