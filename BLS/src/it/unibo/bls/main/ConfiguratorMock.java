package it.unibo.bls.main;

import it.unibo.bls.controller.Controller;
import it.unibo.bls.interfaces.IButton;
import it.unibo.bls.interfaces.ILed;
import it.unibo.bls.mock.ButtonMock;
import it.unibo.bls.mock.LedMock;

public class ConfiguratorMock {
	
	private IButton button;
	private ILed led;
	private Controller controller;
	private static ConfiguratorMock istance;
	
	private ConfiguratorMock() {};
	
	public void createComponents() {
		button = new ButtonMock();
		led = new LedMock();
		controller = new Controller(led);
		
	}
	
	public void integrateComponents() {
		button.addObserver(controller);
	}
	
	public void start() {
		createComponents();
		integrateComponents();
		
		for(int i=0; i<10; i++) {
			((ButtonMock)button).press();
			System.out.println(led.isOn());
		}
	}
	
	public static ConfiguratorMock getInstance() {
		if(istance == null) {
			istance = new ConfiguratorMock();
		}
		return istance;
	}
	
	public IButton getButton() {
		return this.button;
	}
	
	public ILed getLed() {
		return this.led;
	}
	
	/*
	 * Just for a fast test
	 */
	public static void main(String[] args) {
		ConfiguratorMock.getInstance().start();
	}
}
