package it.unibo.bls.main;

import java.awt.Frame;

import it.unibo.bls.controller.Controller;
import it.unibo.bls.gui.ButtonGui;
import it.unibo.bls.gui.LedGui;
import it.unibo.bls.interfaces.IButton;
import it.unibo.bls.interfaces.ILed;
import it.unibo.bls.interfaces.IObservable;
import it.unibo.bls.interfaces.IObserver;
import it.unibo.bls.model.ButtonModel;
import it.unibo.bls.model.LedModel;
import it.unibo.bls.utils.UtilsBls;

public class ConfiguratorGui {
	
	private IButton button;
	private IObservable buttonGui;
	private ILed led;
	private IObserver ledGui;
	private Controller controller;
	private static ConfiguratorGui istance;
	private Frame frame;
	
	private ConfiguratorGui() {};
	
	public void createComponents() {
		button = new ButtonModel();
		led = new LedModel();
		controller = new Controller(led);
		frame = UtilsBls.initFrame();
		buttonGui = ButtonGui.createButton(frame, "press");
		ledGui = LedGui.createLed(frame);
	}
	
	public void integrateComponents() {
		buttonGui.addObserver(button);
		button.addObserver(controller);
		led.addObserver(ledGui);
	}
	
	public void start() {
		createComponents();
		integrateComponents();
		frame.setVisible(true);
	}
	
	public static ConfiguratorGui getInstance() {
		if(istance == null) {
			istance = new ConfiguratorGui();
		}
		return istance;
	}
	
	/*
	 * Just for a fast test
	 */
	public static void main(String[] args) {
		ConfiguratorGui.getInstance().start();
	}
}

