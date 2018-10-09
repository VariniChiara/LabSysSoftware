package it.unibo.bls.mock;


import java.util.*;
import it.unibo.bls.interfaces.IButton;
import it.unibo.bls.interfaces.IObserver;

public class ButtonMock implements IButton{
	
	private List<IObserver> observers = new ArrayList<>(); 
	

	@Override
	public void addObserver(IObserver observer) {
		this.observers.add(observer);	
	}
	
	/*
	 * Only for mock version
	 */
	public void press() {
		this.observers.forEach(o -> o.update("press"));
	}

	@Override
	public void update(String state) {
		// update version bls2
		
	}

}
