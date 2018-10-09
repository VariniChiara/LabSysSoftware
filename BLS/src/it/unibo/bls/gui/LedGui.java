package it.unibo.bls.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;

import it.unibo.bls.interfaces.IObserver;


/*
 * A Led that USES a GUI Panel into a given a Frame
 */
public class LedGui implements IObserver {

	private Panel p = new Panel(); 
	private final Dimension sizeOn  = new Dimension(100,100);
	private final Dimension sizeOff = new Dimension(30,30);
	
	//Factory method
	public static IObserver createLed( Frame frame){
		LedGui led = new LedGui(frame);
		led.turnOff();
		return led;
	}
	public LedGui( Frame frame ) {
		configure(frame); 
	}	
	protected void configure(Frame frame){
		frame.add(BorderLayout.CENTER,p);
		p.setBackground(Color.red); 
		p.setSize( new Dimension(10,10) );
		frame.validate(); 		 
		p.validate();
	}		    
	protected void turnOn(){
		p.setSize( sizeOn );
		p.validate();
	}
	protected void turnOff() {
		p.setSize( sizeOff );
		p.validate();
	}

	@Override
	public void update(String state) {
		if(state.equals("true")) {
			turnOn();
		} else {
			turnOff();
		}

	}
}
