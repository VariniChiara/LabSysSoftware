package it.unibo.bls.interfaces;

public interface ILed extends IObservable {
	
	void turnOn();
	void turnOff();
	void ledSwitch();
	boolean isOn();

}
