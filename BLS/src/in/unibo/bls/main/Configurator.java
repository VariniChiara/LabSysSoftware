package in.unibo.bls.main;

import in.unibo.controller.Controller;
import it.unibo.bls.interfaces.IButton;
import it.unibo.bls.interfaces.ILed;
import it.unibo.mock.ButtonMock;
import it.unibo.mock.LedMock;

public class Configurator {
	
	private IButton button;
	private ILed led;
	private Controller controller;
	private static Configurator istance;
	
	private Configurator() {};
	
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
	
	public static Configurator getInstance() {
		if(istance == null) {
			istance = new Configurator();
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
		Configurator.getInstance().start();
	}
}
