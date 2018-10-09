package it.unibo.bls;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unibo.bls.interfaces.ILed;

class LedTest {

	ILed led;
	
	@Test
	void creationTest() {
		assertFalse(led.isOn());
	}
	
	@Test
	void turnOnTest() {
		led.turnOn();
		assertTrue(led.isOn());
	}
	
	@Test
	void turnOffTest() {
		led.turnOff();
		assertFalse(led.isOn());
	}
	
	@Test
	void isOntest() {
		assertFalse(led.isOn());
		led.turnOn();
		assertTrue(led.isOn());
		led.turnOff();
		assertFalse(led.isOn());
	}
	
	@Test
	void ledSwitchtest() {
		assertFalse(led.isOn());
		led.ledSwitch();
		assertTrue(led.isOn());
		led.ledSwitch();
		assertFalse(led.isOn());
	}

}
