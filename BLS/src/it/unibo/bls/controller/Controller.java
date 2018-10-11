package it.unibo.bls.controller;

import it.unibo.bls.interfaces.ILed;
import it.unibo.bls.interfaces.IObserver;

public class Controller implements IObserver{
	
	private ILed led;
	
	public Controller(ILed led) {
		this.led = led;
	}

	@Override
	public void update(String state) {
		this.led.ledSwitch();
	}
}
