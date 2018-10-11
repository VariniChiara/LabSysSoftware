package it.unibo.bls.main;

import it.unibo.bls.applLogic.BlsApplicationLogic;
import it.unibo.bls.gui.ButtonGui;
import it.unibo.bls.interfaces.ILed;
import it.unibo.bls.interfaces.IObservable;
import it.unibo.bls.interfaces.IObserver;
import it.unibo.bls.model.ButtonModel;
import it.unibo.bls.model.LedModel;
import it.unibo.bls.utils.UtilsBls;
import it.unibo.raspberry.CommonNames;
import it.unibo.raspberry.LedProxyForRaspberry;

public class MainBlsModelWithLedOnRasp  {

private ILed ledmodel;
private IObserver buttonmodel;
private BlsApplicationLogic applLogic;

private IObservable buttongui;
private IObserver ledproxy;

private String protocol;
private String hostName;
private int portNum;


//Factory method   	
  	public static MainBlsModelWithLedOnRasp createTheSystem(){
 		return new MainBlsModelWithLedOnRasp();
 	} 	
 	protected MainBlsModelWithLedOnRasp( ) {
 		configure();
 	}		
 	
 	protected void configure(){
 		protocol = CommonNames.protocol;
 		hostName = CommonNames.hostName;
 		portNum  = CommonNames.portNum;
 		
  		
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
    	applLogic   = new BlsApplicationLogic(ledmodel);
    	//Create the button model that refers the Application logic
    	buttonmodel = ButtonModel.createButton(applLogic);
 	} 	
 	protected void createConcreteComponents(){
   		//Create the ButtonAsGui
		buttongui   = ButtonGui.createButton( UtilsBls.initFrame(200,200), "press");
		//Create the led proxy
		ledproxy = LedProxyForRaspberry.create(protocol,hostName,portNum);
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
   MainBlsModelWithLedOnRasp.createTheSystem();
 }
}