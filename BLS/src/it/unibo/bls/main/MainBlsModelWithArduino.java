package it.unibo.bls.main;


import java.awt.Frame;
import it.unibo.bls.applLogic.BlsApplicationLogic;
import it.unibo.bls.arduino.LedOnArduino;
import it.unibo.bls.controller.Controller;
import it.unibo.bls.gui.ButtonGui;
import it.unibo.bls.interfaces.IButton;
import it.unibo.bls.interfaces.ILed;
import it.unibo.bls.interfaces.IObservable;
import it.unibo.bls.interfaces.IObserver;
import it.unibo.bls.model.LedModel;
import it.unibo.bls.model.ButtonModel;
import it.unibo.bls.utils.UtilsBls;


public class MainBlsModelWithArduino  {

	private ILed ledmodel;
	private IObserver buttonmodel;
	private Controller applLogic;

	private IObservable buttongui;
	private IObserver ledOnArduino;

	//Factory method   	
	public static MainBlsModelWithArduino createTheSystem(){
		return new MainBlsModelWithArduino();
	} 	
	protected MainBlsModelWithArduino( ) {
		configure();
	}		
	protected void configureMud(){
		//Create the frame
		Frame blsFrame = UtilsBls.initFrame(200,200);
		//Create the led on Arduino
		IObserver ledOnArduino = LedOnArduino.createLed("COM9");
		//Create the led model that refers the ledOnArduino as the observer
		ledmodel       = LedModel.createLed(ledOnArduino);		
		//Create the Application logic that refers the led model (as ILed)
		BlsApplicationLogic applLogic = new BlsApplicationLogic(ledmodel);
		//Create the button model that refers the Application logic
		IButton buttonmodel = ButtonModel.createButton(applLogic);
		//Create the button gui that refers the buttonmodel as observer
		ButtonGui.createButton( blsFrame, "press", buttonmodel);
		ledmodel.turnOff();
		blink();
	} 	

	protected void configure(){
		createLogicalComponents();
		createConcreteComponents();
		configureSystemArchitecture();
		ledmodel.turnOff();
		blink();
	}
	protected void createLogicalComponents(){
		//Create the led model t 
		ledmodel    = LedModel.createLed();
		//Create the Application logic that refers the led model (as ILed)
		applLogic   = new Controller(ledmodel);
		//Create the button model that refers the Application logic
		buttonmodel = ButtonModel.createButton(applLogic);
	} 	
	protected void createConcreteComponents(){
		//Create the frame
		Frame blsFrame = UtilsBls.initFrame(200,200);
		//Create the ButtonAsGui
		buttongui   = ButtonGui.createButton( blsFrame, "press");
		//Create the led on Arduino
		ledOnArduino = LedOnArduino.createLed("COM9");
	} 	
	protected void configureSystemArchitecture(){
		ledmodel.addObserver(ledOnArduino);
		buttongui.addObserver(buttonmodel);
	}

	protected void blink() {
		UtilsBls.delay(1000);
		ledmodel.turnOn(); 		
		UtilsBls.delay(1000);
		ledmodel.turnOff();
		UtilsBls.delay(1000);
		ledmodel.turnOn(); 		
		UtilsBls.delay(1000);
		ledmodel.turnOff();
	}
	
	public static void main(String[] args) {
		MainBlsModelWithArduino sys = createTheSystem();
	}
}