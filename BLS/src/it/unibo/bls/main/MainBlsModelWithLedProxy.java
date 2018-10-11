package it.unibo.bls.main;

import it.unibo.bls.controller.Controller;
import it.unibo.bls.distributed.proxy.LedProxy;
import it.unibo.bls.distributed.proxy.LedThingReceiver;
import it.unibo.bls.gui.ButtonGui;
import it.unibo.bls.gui.LedGui;
import it.unibo.bls.interfaces.IButton;
import it.unibo.bls.interfaces.ILed;
import it.unibo.bls.interfaces.IObservable;
import it.unibo.bls.interfaces.IObserver;
import it.unibo.bls.model.ButtonModel;
import it.unibo.bls.model.LedModel;
import it.unibo.bls.utils.UtilsBls;

public class MainBlsModelWithLedProxy  {

private ILed ledmodel;
private IButton buttonmodel;
private Controller applLogic;

private IObservable buttongui;
private IObserver ledproxy;

private String protocol;
private String hostName;
private int portNum;


//Factory method   	
  	public static MainBlsModelWithLedProxy createTheSystem(){
 		return new MainBlsModelWithLedProxy();
 	} 	
 	protected MainBlsModelWithLedProxy( ) {
 		configure();
 	}		
 	
 	protected void configure(){
 		protocol = "TCP";
 		hostName = "localhost";
 		portNum  = 8012;
 		
 		//createARemoteLedThing() ; //just for testing with a single appl
 		
 		createLogicalComponents();
 		createConcreteComponents();
 		configureSystemArchitecture();
  		ledmodel.turnOff();
		blink();
 	}
 	protected void createLogicalComponents(){
		//Create the led model 
 		ledmodel    = LedModel.createLed();
		//Create the Application logic that refers the led model (as ILed)
    	applLogic   = new Controller(ledmodel);
    	//Create the button model that refers the Application logic
    	buttonmodel = ButtonModel.createButton(applLogic);
 	} 	
 	protected void createConcreteComponents(){
   		//Create the ButtonAsGui
		buttongui   = ButtonGui.createButton( UtilsBls.initFrame(200,200), "press");
		//Create the led proxy
		ledproxy = LedProxy.createLed(protocol,hostName,portNum);
  	} 	
 	
	//CREATE A REMOTE LED (just for testing with a single appl)
 	protected void createARemoteLedThing() {
		//Create the led thing
		IObserver ledgui = LedGui.createLed(UtilsBls.initFrame(200,200));
		System.out.println("LED GUI CREATED");
		IObservable ledReceiver = LedThingReceiver.createLed(protocol, portNum);
		System.out.println("LED RECEIVER CREATED");
		ledReceiver.addObserver(ledgui); 		
 	}
 	
 	protected void configureSystemArchitecture(){
 		ledmodel.addObserver(ledproxy);
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
	MainBlsModelWithLedProxy.createTheSystem();
 }
}