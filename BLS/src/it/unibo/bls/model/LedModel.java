package it.unibo.bls.model;

import it.unibo.bls.interfaces.ILed;
import it.unibo.bls.interfaces.IObserver;
import it.unibo.bls.model.LedModel;

public class LedModel extends Observable implements ILed{

	private boolean state = false;
	
	//Factory method
	public static ILed createLed(){
		return new LedModel();
	}
	public static ILed createLed(IObserver observer){
		ILed led = new LedModel();
		led.addObserver(observer);
		return led;
	}

	@Override
	public void turnOn() {
		this.state = true;
		notifyObservers(state+"");
	}

	@Override
	public void turnOff() {
		this.state = false;	
		notifyObservers(state+"");
	}

	@Override
	public void ledSwitch() {
		this.state = !this.state;
		notifyObservers(state+"");
	}

	@Override
	public boolean isOn() {
		return this.state;
	}

}
