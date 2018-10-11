package it.unibo.bls.model;

import it.unibo.bls.interfaces.IButton;
import it.unibo.bls.interfaces.IObserver;
import it.unibo.bls.model.ButtonModel;

public class ButtonModel extends Observable implements IButton{
	
	//Factory method
	public static ButtonModel createButton( IObserver obs ){
		 ButtonModel button = new ButtonModel();
		 button.addObserver(obs);
		 return button;
	}

	@Override
	public void update(String state) {
		notifyObservers(state);
	}
}
