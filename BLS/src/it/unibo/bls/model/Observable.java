package it.unibo.bls.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.bls.interfaces.IObservable;
import it.unibo.bls.interfaces.IObserver;

public class Observable implements IObservable{
	
	private List<IObserver> observers = new ArrayList<>();

	@Override
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}
	
	@Override
	public void notifyObservers(String state) {
		this.observers.forEach(o -> o.update(state));
	}

}
