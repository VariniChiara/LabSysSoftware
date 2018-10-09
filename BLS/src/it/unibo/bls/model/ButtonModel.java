package it.unibo.bls.model;

import it.unibo.bls.interfaces.IButton;

public class ButtonModel extends Observable implements IButton{

	@Override
	public void update(String state) {
		observers.forEach(o->o.update(state));
	}
}
