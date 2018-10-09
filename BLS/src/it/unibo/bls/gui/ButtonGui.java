package it.unibo.bls.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unibo.bls.interfaces.IObservable;
import it.unibo.bls.interfaces.IObserver;
import it.unibo.bls.model.Observable;

public class ButtonGui extends Observable implements ActionListener{

	//Factory method
	public static IObservable createButton( Frame frame, String cmd, IObserver obs ){
		ButtonGui button         = new ButtonGui();
		java.awt.Button buttonBase = new java.awt.Button(cmd);
		buttonBase.addActionListener(  button );
		frame.add(BorderLayout.WEST,buttonBase); 
		frame.validate();
		if( obs != null ) button.addObserver(obs);
		return button;
	}
	
	public static IObservable createButton( Frame frame, String cmd){
		return createButton(frame,cmd,null);
	}

	@Override //from ActionListener
	public void actionPerformed(ActionEvent e) {
		observers.forEach(o->o.update("press"));
	}


}
