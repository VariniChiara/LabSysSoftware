package it.unibo.bls.mock;

import it.unibo.bls.interfaces.ILed;
import it.unibo.bls.interfaces.IObserver;

public class LedMock implements ILed{
	
	private boolean state = false;

	@Override
	public void turnOn() {
		this.state = true;	
	}

	@Override
	public void turnOff() {
		this.state = false;	
	}

	@Override
	public void ledSwitch() {
		this.state = !this.state;
	}

	@Override
	public boolean isOn() {
		return this.state;
	}

	@Override
	public void addObserver(IObserver observer) {
		// updated version bls2
		
	}

}
