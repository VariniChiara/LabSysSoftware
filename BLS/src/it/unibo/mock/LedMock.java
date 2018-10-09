package it.unibo.mock;

import it.unibo.bls.interfaces.ILed;

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

}
