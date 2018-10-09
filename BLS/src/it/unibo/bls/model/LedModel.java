package it.unibo.bls.model;

import it.unibo.bls.interfaces.ILed;

public class LedModel extends Observable implements ILed{

	private boolean state = false;

	@Override
	public void turnOn() {
		this.state = true;
		notify(state+"");
	}

	@Override
	public void turnOff() {
		this.state = false;	
		notify(state+"");
	}

	@Override
	public void ledSwitch() {
		this.state = !this.state;
		notify(state+"");
	}

	@Override
	public boolean isOn() {
		return this.state;
	}
	
	private void notify(String state) {
		observers.forEach(o->o.update(state));
	}
	
}
