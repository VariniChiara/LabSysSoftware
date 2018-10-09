package it.unibo.bls;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import in.unibo.bls.main.Configurator;
import it.unibo.bls.interfaces.IButton;
import it.unibo.bls.interfaces.ILed;
import it.unibo.mock.*;

class IntegrationTest {
	
	Configurator configurator = Configurator.getInstance();

	@Test
	void testSystem() {
		configurator.createComponents();
		configurator.integrateComponents();
		
		IButton button = configurator.getButton();
		ILed led = configurator.getLed();
		
		assertFalse(led.isOn());
		
		((ButtonMock)button).press();
		assertTrue(led.isOn());
		
		((ButtonMock)button).press();
		assertFalse(led.isOn());
		
		((ButtonMock)button).press();
		assertTrue(led.isOn());
	}

}
