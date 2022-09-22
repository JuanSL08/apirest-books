package com.tismart.book.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssertEqualsTeoria {

	@Test
	public void miTest() {
		assertEquals(1, 1);		//VERDE
		//assertEquals(1, 2);	//ROJO
	}
}
